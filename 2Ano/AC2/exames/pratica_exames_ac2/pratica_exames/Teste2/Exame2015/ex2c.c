#include<detpic32.h>
void delay(unsigned int);
void putc(char);
static const unsigned char codes[] = {0x77, 0x41, 0x3B, 0x6B, 0x4D, 0x6E, 0x7E, 0x43, 0x7F, 0x6F, 0x5F, 0x7C, 0x36, 0x79, 0x3E, 0x1E};
unsigned char codes_hex[] = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
int troca = 0;

int resultado = 0;

int main(void){

    //UART1 para teste
    U1BRG = ((20000000 + 8 * 115200) / (16 * 115200)) - 1;
    U1MODEbits.BRGH = 0; // divisao
    U1MODEbits.PDSEL = 00; //8 bits, no parity
    U1MODEbits.STSEL = 0; // 1 stop bits
    U1STAbits.UTXEN = 1;
    U1STAbits.URXEN = 1;
    U1MODEbits.ON = 1;

    //UART Interrupts
    U1STAbits.URXISEL = 00;
    IEC0bits.U1RXIE = 1;
    IPC6bits.U1IP = 3;

    TRISB = (TRISB & 0xFC00);
    TRISE = TRISE | 0x00F0;

    EnableInterrupts();

    while(1);

}

void putc(char byte2send) {
    while (U1STAbits.UTXBF == 1);
    U1TXREG = byte2send;
}

void _int_(24) isr_uart1(void){
    if(IFS0bits.U1RXIF){
        char c = U1RXREG;
        if(c == 'p' && !troca){
            troca = 1;
            while(1) {
                LATBbits.LATB8 = 0;
                LATB = (LATB & 0xFF00) | codes[resultado];
                LATBbits.LATB9 = 1;
                delay(500); // 1/2Hz = 0.5 s = 500 ms
                LATBbits.LATB8 = 0;
                LATB = (LATB & 0xFF00);
                LATBbits.LATB9 = 1;
                delay(500);
                char c = inkey();
                if(c=='p') break;
            }
        } else if(c == 'p' && troca){
            troca = 0;
            putc(resultado);
            LATBbits.LATB8 = 0;
            LATB = (LATB & 0xFF00) | codes[resultado];
            LATBbits.LATB9 = 1;
        } else if(c >= '0' && c <= '9'){
            PORTE = (PORTE & 0x00F0);
            int x = PORTE >> 4;
            resultado = codes_hex[((int) c-'0' + x)%16];
            putc(resultado);
            putc('\n');
        }
    }

    IFS0bits.U1RXIF = 0;

}
