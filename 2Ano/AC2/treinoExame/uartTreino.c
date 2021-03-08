#include<detpic32.h>

void delay(unsigned int ms){
    for (; ms > 0; ms--)
    {
        resetCoreTimer();
        while (readCoreTimer() < 20000);
    }
}


void putc(char c){
    while (U1STAbits.UTXBF == 1);
    U1TXREG = c;
}

void putS(char *s){
    int i=0;
    while (s[i] != '\0')
    {
        putc(s[i]);
        i++;
    }
}

char getC(void){
    if (U1STAbits.OERR == 1)
    {
        U1STAbits.OERR = 0;
    }
    return U1RXREG;   
}




int main(void){

    printStr("\nRodrigo Lopes Martins\nnMec: 93264\n");

    int baudrate = 115200;
    
    U1BRG = ((PBCLK+8*baudrate)/(16*baudrate))-1;
    U1MODEbits.BRGH = 0;
    U1MODEbits.PDSEL = 0;
    U1MODEbits.STSEL = 0;
    U1STAbits.UTXEN = 1;
    U1STAbits.URXEN = 1;

    U1MODEbits.ON = 1;

    EnableInterrupts();

    IEC0bits.U1RXIE = 1;
    IEC0bits.U1TXIE = 1;

    IPC6bits.U1IP = 2;
    IPC6bits.U1IS = 1;
    IFS0bits.U1RXIF = 0;

    while (1);

    return 0;

}


void _int_(24) isr_UART1(){
    putc(U1RXREG);
    IFS0bits.U1RXIF = 0;
}
