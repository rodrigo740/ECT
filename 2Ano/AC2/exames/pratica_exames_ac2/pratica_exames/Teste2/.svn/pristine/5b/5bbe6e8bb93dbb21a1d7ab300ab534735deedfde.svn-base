#include<detpic32.h>

#define BUF_SIZE 64
#define INDEX_MASK (BUF_SIZE - 1)
int i = 0, V = 0, sum = 0, j = 0;

//SPI

typedef struct
{
    unsigned char data[BUF_SIZE];
    unsigned int head;
    unsigned int tail;
    unsigned int count;
} circularBuffer;

volatile circularBuffer buffer; // Transmission buffer
void comDrv_putc(char);
unsigned char toBcd(unsigned char);
void buffer_flush(void);
char comDrv_getc(char *);

int main(void){

    //Timer3
    PR3 = 49999;
    T3CONbits.TCKPS = 4;
    TMR3 = 0;

    // timer interrupts
    IFS0bits.T3IF = 0;
    IPC3bits.T3IP = 5;
    IEC0bits.T3IE = 1;


    //UART1
    U1BRG = ((20000000 + 8 * 57600) / (16 * 57600)) - 1;
    U1MODEbits.BRGH = 0;
    U1MODEbits.PDSEL = 2;
    U1MODEbits.STSEL =  0; // 1 stopbit
    U1STAbits.UTXEN = 1;
    U1STAbits.URXEN = 1;
    U1MODEbits.ON = 1;

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
    IPC6bits.AD1IP = 5;
    IEC1bits.AD1IE = 1;
    IFS1bits.AD1IF = 0;

    // interrupts
    U1STAbits.URXISEL = 00;
    IPC6bits.U1IP = 3;
    IEC0bits.U1RXIE = 1;
    EnableInterrupts();

    buffer_flush();

    while(1);
}

void putc(char byte2send) {
    while(U1STAbits.UTXBF == 1);    // wait while UTXBF == 1
    U1TXREG = byte2send;    // Copy byte2send to the UxTXREG register
}

void puts_(char *str) {
    int i = 0;
    while(str[i]!='\0'){
        putc(str[i]);
        i++;
    }
}

void _int_(12) isr_T3(void){
    AD1CON1bits.ASAM = 1;
    IFS0bits.T3IF = 0;
    if(++j == 25){
        comDrv_putc(V);
        puts_("Valor: ");
        putc('0' + ((toBcd(V) & 0x00F0) >> 4));
        putc('0' + (toBcd(V) & 0x000F));
        putc('\n');
        j=0;
    }
}

void _int_(27) isr_adc(void){

    int *p = (int *)(&ADC1BUF0);
    for( i = 0; i < 16; i++ ){
        sum += p[i*4];
    }

    V = ((sum/16)*70+511)/1023;
    sum = 0;

    IFS1bits.AD1IF = 0;
}

void comDrv_putc(char ch){
    while(buffer.count == BUF_SIZE);
    buffer.data[buffer.tail] = ch;
    buffer.tail = (buffer.tail+1) & INDEX_MASK;
    buffer.count++;  // Increment "count" variable
}

void _int_(24) isr_uart1(void) {
    if(IFS0bits.U1RXIF){
        char d = U1RXREG;
        if(d == 'r'){
            T3CONbits.TON = 1;
        }else if( d == 'l'){
            T3CONbits.TON = 0;
            char pchar;
            while(comDrv_getc(&pchar)){
                putc(((toBcd(pchar) & 0xF0) >> 4) + '0'); // 61
                putc((toBcd(pchar) & 0x0F) + '0'); // 61
                /*
                int i = toBcd(pchar); // 61

                while(i!=0){
                    int tmp = (i & 0xF0) >> 4;
                    printf("%c", tmp + '0');
                    i = i >> 4;
                }
                */
            }
        }

        IFS0bits.U1RXIF = 0;
    }
}

unsigned char toBcd(unsigned char value) {
return ((value / 10) << 4) + (value % 10); }

char comDrv_getc(char *pchar) {
    if(!buffer.count) return 0;
    *pchar= buffer.data[buffer.head];
    buffer.count--;// Decrement "count" variable
    buffer.head = (buffer.head + 1) & INDEX_MASK;
    return 1;
}


void buffer_flush(void) {
  // Initialize variables of the transmission buffer
  buffer.tail = 0;
  buffer.head = 0;
  buffer.count = 0;

  for (i = 0; i < BUF_SIZE; i++) {
    buffer.data[i] = 0;
  }
}
