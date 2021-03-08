#include<detpic32.h>
void delay(unsigned int);
void putc(char);
char getc();
static const unsigned char codes[] = {0x77, 0x41, 0x3B, 0x6B, 0x4D, 0x6E, 0x7E, 0x43, 0x7F, 0x6F, 0x5F, 0x7C, 0x36, 0x79, 0x3E, 0x1E};
unsigned char codes_hex[] = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};

int main(void){
  //UART1 para teste
  U1BRG = ((20000000 + 8 * 115200) / (16 * 115200)) - 1;
  U1MODEbits.BRGH = 0; // divisao
  U1MODEbits.PDSEL = 00; //8 bits, no parity
  U1MODEbits.STSEL = 0; // 1 stop bits
  U1STAbits.UTXEN = 1;
  U1STAbits.URXEN = 1;
  U1MODEbits.ON = 1;

  TRISB = (TRISB & 0xFC00);
  TRISE = TRISE | 0x00F0;

  while(1){
    PORTE = (PORTE & 0x00F0);
    int lido = PORTE >> 4;
    char inserido = getc();
    if(inserido >= '0' && inserido <= '9'){
        int inser = inserido - '0';
        int resultado = lido + inserido;
        //printf("%c\n", codes_hex[(resultado)%16]);
        putc(codes_hex[(resultado)%16]);
        putc('\n');
        LATBbits.LATB9 = 0;
        LATB = (LATB & 0xFF00) | codes[(resultado)%16];
        LATBbits.LATB8 = 1;
    }
    delay(500); // 1/2Hz = 0.5 s = 500 ms
  }
  return 0;
}

void putc(char byte2send) {
  while (U1STAbits.UTXBF == 1);
  U1TXREG = byte2send;
}

char getc(void) {
  while(U1STAbits.URXDA == 0);
  return U1RXREG;
}
