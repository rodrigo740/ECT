#include<detpic32.h>
void putc(char);
int main(void){

    TRISE = TRISE | 0x00F0;
    TRISE = TRISE & 0xFFF0;

    //UART 1
    U1BRG = ((20000000 + 8 * 57600) / (16 * 57600)) - 1;
    U1MODEbits.BRGH = 0;
    U1MODEbits.PDSEL = 2;
    U1MODEbits.STSEL = 0; // 1
    U1STAbits.UTXEN = 1;
    U1STAbits.URXEN = 1;
    U1MODEbits.ON = 1;

    //uart interrupts
    U1STAbits.URXISEL = 00;
    IEC0bits.U1RXIE = 1;
    IPC6bits.U1IP = 3;
    EnableInterrupts();

    while(1);

}

void _int_(24) isr_uart1(void) {
    if(IFS0bits.U1RXIF){
        char c = U1RXREG;
        if(c == 'l') {
            int x = PORTE & 0x00F0;
            x = x >> 4;

            LATE = (LATE & 0xFFF0) | x;

            if(x%2==0){
                puts("É par!");
            }else if(x%2){
                puts("É ímpar!");
            }
        }else if( c == 's'){
            puts("Daniela 76771");
        }else if(c == 'q'){
            exit(1);
        }
    }

    IFS0bits.U1RXIF = 0;
}

void putc(char byte2send) {
    while(U1STAbits.UTXBF == 1);    // wait while UTXBF == 1
    U1TXREG = byte2send;    // Copy byte2send to the UxTXREG register
}

void puts(char *str) {
    int i = 0;
    while (str[i]!='\0') {
        putc(str[i]);
        i++;
    }
}
