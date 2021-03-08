#include<detpic32.h>
void putc(char);
int main(void){
    //UART1
    U1BRG = ((20000000 + 8 * 57600) / (16 * 57600)) - 1;
    U1MODEbits.BRGH = 0;
    U1MODEbits.PDSEL = 2;
    U1MODEbits.STSEL =  0; // 1 stopbit
    U1STAbits.UTXEN = 1;
    U1STAbits.URXEN = 1;
    U1MODEbits.ON = 1;

    // interrupts
    U1STAbits.URXISEL = 00;
    IPC6bits.U1IP = 3;
    IEC0bits.U1RXIE = 1;
    EnableInterrupts();

    TRISE = TRISE | 0x0070;

    while (1);

}

void puts_(char *str) {
    int i = 0;
    while(str[i]!='\0'){
        putc(str[i]);
        i++;
    }
}

void _int_(24) isr_uart1(void) {
    if(IFS0bits.U1RXIF){
        char d = U1RXREG;
        if(d == 'l'){
            int x = PORTE & 0x0070;
            x = x >> 4;
            putc('0' + x);
        }else if( d == 's'){
            puts_("Daniela 76771");
        }else if(d == 'q'){
            exit(1);
        }
    }
    IFS0bits.U1RXIF = 0;    // Clear UART1 rx interrupt flag
}

void putc(char byte2send) {
    while(U1STAbits.UTXBF == 1);    // wait while UTXBF == 1
    U1TXREG = byte2send;    // Copy byte2send to the UxTXREG register
}
