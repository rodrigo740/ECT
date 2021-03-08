#include<detpic32.h>
void delay(unsigned int);
int main(void){
    printf("%s\n", "cheguei");

    //spi2_init();
    //spi2_setClock(EEPROM_CLOCK);

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

    EnableInterrupts();

    while (1){
        delay(20);
        AD1CON1bits.ASAM = 1;
    }

}
