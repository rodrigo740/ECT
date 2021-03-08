#include<detpic32.h>
void putc(char);
unsigned char toBcd(unsigned char);
void puts(char *str) {
    int i = 0;
    while(str[i]!='0'){
        putc(str[i]);
        i++;
    }
}

int main(void){

    // TRIS
    TRISE = TRISE | 0x00F0;

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

    EnableInterrupts();

    while(1);

}

void _int_(24) isr_uart1(void){
    if(IFS0bits.U1RXIF){
        char c = U1RXREG;

        if(c == 'L'){
            int x = PORTE & 0x00E0;
            x = x >> 4;
            putc('0' + toBcd(x));

            putc('\n');
        }else if(c == 'S'){
            puts("Daniela 76771");
        }else if(c == 'Q'){
            exit(1);
        }
    }
    IFS0bits.U1RXIF = 0;
}

void putc(char byte2send) {
    while(U1STAbits.UTXBF);// wait while UTXBF == 1
    U1TXREG = byte2send;    // Copy byte2send to the UxTXREG register
}
