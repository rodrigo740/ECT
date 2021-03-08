#include<detpic32.h>
char getc(void);
void putc_(char);

int main(void){

    //Configurar UART1
    U1BRG = ((20000000 + 8 * 115200) / (16 * 115200)) - 1;
    U1MODEbits.BRGH = 0;
    U1MODEbits.PDSEL = 00;
    U1MODEbits.STSEL = 0; //1
    U1STAbits.URXEN = 1;
    U1STAbits.UTXEN = 1;
    U1MODEbits.ON = 1;

    //interrupts
    U1STAbits.URXISEL = 00;
    IEC0bits.U1RXIE = 1;
    IPC6bits.U1IP = 3;
    EnableInterrupts();

    TRISE = TRISE | 0x00F0;

    while (1);

}

void puts_(char *str) {
    int i = 0;
    while(str[i]!='\0'){
        putc_(str[i]);
        i++;
    }
}

void putc_(char byte2send) {
    while(U1STAbits.UTXBF == 1);    // wait while UTXBF == 1
    U1TXREG = byte2send;    // Copy byte2send to the UxTXREG register
}

char getc(void) {
    while(U1STAbits.URXDA == 0);// Wait while URXDA == 0
    return U1RXREG;
}

void _int_(24) isr_uart1(void) {
    if(IFS0bits.U1RXIF){
        char c = U1RXREG;
        if(c == 'l'){
            int x = PORTE & 0x00F0;
            x = x >> 4;
            if(x%2 == 0){
                puts_("Par!");
            }else{
                puts_("Ímpar!");
            }
        }else if(c=='s'){
            puts_("Daniela, 76771");
        }else if(c == 'q'){
            exit(1);
        }
    }

    IFS0bits.U1RXIF = 0;// Clear UART1 rx interrupt flag
}
