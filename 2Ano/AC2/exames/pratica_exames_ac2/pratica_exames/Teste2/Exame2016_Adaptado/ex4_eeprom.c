#include<detpic32.h>
#define RDSR 0x05
#define WRITE 0x02
#define EEPROM_CLOCK 300000
#define WREN 0x06
#define WRDI 0x04
#define READ 0x03

volatile int input = 0, V = 0, sum = 0, i = 0, k = 0;
void delay(unsigned int);
unsigned char toBcd(unsigned char);

void spi2_setClock(unsigned int clock_freq) {
  SPI2BRG = (20000000 + clock_freq)/(2*clock_freq)-1;
}

void spi2_init(void) {
volatile char trash;
   SPI2CONbits.ON = 0; // Disable SPI2 module
   SPI2CONbits.CKP=0; // Configure clock idle state as logic level 0
   SPI2CONbits.CKE = 1; // Configure the clock active transition: from active state to idle state
   SPI2CONbits.SMP = 0; // Configure SPI data input sample phase bit (middle of data output time)
   SPI2CONbits.MODE32 = 0; // Configure word length (8 bits)
   SPI2CONbits.MODE16 = 0;
   // AUDEN já definido a 0
   SPI2CONbits.ENHBUF = 1;// Enable Enhanced buffer mode (this allows the usage of FIFOs RX,TX)
   SPI2CONbits.MSTEN = 1; // Enable master mode
   SPI2CONbits.MSSEN = 1; // Enable slave select support (Master Mode Slave Select)
   // Clear RX FIFO:
   while(SPI2STATbits.SPIRBE == 0) // while RX FIFO not empty read
      trash = SPI2BUF; // FIFO and discard read character

   SPI2STATbits.SPIROV = 0;  // Clear overflow error flag
   SPI2CONbits.ON = 1;// Enable SPI2 module
}

char eeprom_readStatus(void) {
  volatile char trash;
  while(SPI2STATbits.SPIRBE == 0) // while RX FIFO not empty read
     trash = SPI2BUF; // FIFO and discard read character
  SPI2STATbits.SPIROV = 0;  // Clear overflow error flag bit
  SPI2BUF = RDSR; // Send RDSR command
  SPI2BUF = 0; // Send anything so that EEPROM clocks data into SO
  while(SPI2STATbits.SPIBUSY); // wait while SPI module is working
  trash = SPI2BUF; // First char received is garbage (received while sending command)
  return SPI2BUF; // Second received character is the STATUS value
}

void eeprom_writeStatusCommand(char command) {
  while( eeprom_readStatus() & 0x01 ); // Wait while WIP is true (write in progress)
  SPI2BUF = command;  // Copy "command" value to SPI2BUF (TX FIFO)
  while(SPI2STATbits.SPIBUSY == 1);  // Wait while SPI module is working (SPIBUSY set)
}

void eeprom_writeData(int address, char value) {
  address = address & 0x01FF;// Apply a mask to limit address to 9 bits
  while( eeprom_readStatus() & 0x01 );  // Read STATUS and wait while WIP is true (write in progress)
  eeprom_writeStatusCommand(WREN); // Enable write operations (activate WEL bit in STATUS register, using eeprom_writeStatusCommand() function )
  // Copy WRITE command and A8 address bit to the TX FIFO:
  SPI2BUF = WRITE | ((address & 0x100) >> 5);
  SPI2BUF = address & 0x00FF;  // Copy address (8 LSBits) to the TX FIFO
  SPI2BUF = value;  // Copy "value" to the TX FIFO
  while(SPI2STATbits.SPIBUSY == 1); // Wait while SPI module is working (SPIBUSY)
}

char eeprom_readData(int address) {
  volatile char trash;
  while(SPI2STATbits.SPIRBE == 0) // while RX FIFO not empty read
     trash = SPI2BUF; // FIFO and discard read character
  SPI2STATbits.SPIROV = 0; // Clear overflow error flag bit
  address = address & 0x01FF; // Apply a mask to limit address to 9 bits
  while( eeprom_readStatus() & 0x01 );  // Read STATUS and wait while WIP is true (write in progress)
  SPI2BUF = READ | ((address & 0x100) >> 5); // Copy READ command and A8 address bit to the TX FIFO
  SPI2BUF = address & 0x00FF;  // Copy address (8 LSBits) to the TX FIFO
  SPI2BUF = 0x00; // Copy any value (e.g. 0x00) to the TX FIFO
  while(SPI2STATbits.SPIBUSY == 1); // Wait while SPI module is working (SPIBUSY)
  // Read and discard 2 characters from RX FIFO (use "trash" variable)
  trash = SPI2BUF;
  trash = SPI2BUF;
  // Read RX FIFO and return the corresponding value
  return SPI2BUF;
}

int main(void){

    spi2_init();
    spi2_setClock(EEPROM_CLOCK);

    //UART1
    U1BRG = ((20000000 + 8 * 57600) / (16 * 57600)) - 1;
    U1MODEbits.BRGH = 0;
    U1MODEbits.STSEL = 0;
    U1MODEbits.PDSEL = 2;
    U1STAbits.URXEN = 1;
    U1STAbits.UTXEN = 1;
    U1MODEbits.ON = 1;

    //UART interrupts
    U1STAbits.URXISEL = 00;
    IEC0bits.U1RXIE = 1;
    IPC6bits.U1IP = 3;

    //ADC
    TRISBbits.TRISB14 = 1;
    AD1PCFGbits.PCFG14 = 0;
    AD1CHSbits.CH0SA = 14;
    AD1CON2bits.SMPI = 15;
    AD1CON1bits.SSRC = 7;
    AD1CON1bits.CLRASAM = 1;
    AD1CON3bits.SAMC = 16;
    AD1CON1bits.ON = 1;

    //ADC interrupts
    IPC6bits.AD1IP = 3;
    IEC1bits.AD1IE = 1;

    //Timer 2
    PR2 = 49999;
    T2CONbits.TCKPS = 3;
    TMR2 = 0;

    // Timer interrupts
    IFS0bits.T2IF = 0;
    IPC2bits.T2IP = 2;
    IEC0bits.T2IE = 1;

    EnableInterrupts();

    while (1);

}

void _int_(24) isr_uart1(void){

    if(IFS0bits.U1RXIF){
        char c = U1RXREG;

        if(c == 'L'){
            T2CONbits.TON = 1;
        }else if(c == 'S'){
            printf("%s\n", "LEITURA:    ");
            T2CONbits.TON = 0;
            int j;
            eeprom_readData(0);
            for (j = 0; j < input; j++) {
                printf("%d\n", eeprom_readData(j));
            }
            input = 0;
        }
    }
    IFS0bits.U1RXIF = 0;
}

void _int_(27) isr_adc(void){

    int *p = (int *)(&ADC1BUF0);
    for( i = 0; i < 16; i++ ){
        sum += p[i*4];
    }

    V=((sum/16)*9+511)/1023;

    sum = 0;

    IFS1bits.AD1IF = 0;
}

void _int_(8) isr_T2(void){
    AD1CON1bits.ASAM = 1;
    if(++k == 25){
        k = 0;
        printf("%d\n", toBcd(V));
        eeprom_writeData(input, toBcd(V));
        input++;
    }
    IFS0bits.T2IF = 0;
}
