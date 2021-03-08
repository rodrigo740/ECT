#include<detpic32.h>
void delay(unsigned int);
void putc(char);
unsigned char codes[] = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
int main(void){
  //UART1 para teste
  U1BRG = ((20000000 + 8 * 115200) / (16 * 115200)) - 1;
  U1MODEbits.BRGH = 0; // divisao
  U1MODEbits.PDSEL = 00; //8 bits, no parity
  U1MODEbits.STSEL = 0; // 1 stop bits
  U1STAbits.UTXEN = 1;
  U1STAbits.URXEN = 1;
  U1MODEbits.ON = 1;

  TRISE = TRISE | 0x00F0; //  DS

  while(1){
    PORTE = (PORTE & 0x00F0);
    int x = PORTE >> 4;
    putc('\n');
    putc(codes[x]);
    delay(500); // 1/2Hz = 0.5 s = 500 ms
  }
  return 0;
}

void putc(char byte2send) {
  while (U1STAbits.UTXBF == 1);
  U1TXREG = byte2send;
}
