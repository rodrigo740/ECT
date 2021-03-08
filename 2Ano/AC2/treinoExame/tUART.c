#include<detpic32.h>

int main(void){

    U1BRG = ((PBCLK + 8 * 115200)/(16 * 115200)) - 1;

    U1MODEbits.BRGH = 0;
    U1MODEbits.PDSEL = 0;
    U1MODEbits.STSEL = 0;
    U1STAbits.UTXEN = 1;
    U1STAbits.URXEN = 1;
    U1MODEbits.ON = 1;

    U1STAbits.UTXISEL = 0;
    U1STAbits.URXISEL = 0;

    IEC0bits.U1RXIE = 1;
    IEC0bits.U1TXIE = 1;
    IPC6bits.U1IP = 3;

    IFS0bits.U1RXIF = 0;

    EnableInterrupts();

    TRISB = TRISB | 0x000F;

    while (1);
    

    return 0;
}

void putc(char c){
    while(U1STAbits.UTXBF == 1);
    U1RXREG = c;
}

void putS(char *s){
    int i = 0;
    while (s[i] != '\0')
    {
        putc(s[i]);
        i++;
    }
    
}


void _int_(24) isr_uart1(void){

    while (IFS0bits.U1RXIF == 0);

    char c = U1RXREG;

    if (c == 'l')
    {
        putS("\nPAR!\n");
    }else if(c == 's'){
        putS("\nRodrigo Martins\nnMec: 93264\n");
    }else if (c == 'q')
    {
        exit(1);
    }
    
    
    IFS0bits.U1RXIF = 0;

} 