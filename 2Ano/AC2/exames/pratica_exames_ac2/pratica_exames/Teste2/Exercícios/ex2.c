//2) Imprimir o valor através da UART2 - 115200 bps, sem paridade, 8 data bits, 1 stop bit, divisão por 16

#include<detpic32.h>
void delay(unsigned int);

void putc(char byte2send){
  while(U2STAbits.UTXBF == 1);
  U2TXREG = byte2send;
}

int main(void){

  TRISB = TRISB | 0x00F0;

  //UART2

  U2BRG = ((20*10^6 + 8 * 115200) / (16 * 115200)) - 1;
  U2MODEbits.BRGH = 0;
  U2MODEbits.PDSEL = 00; // 8 data bits sem paridade
  U2MODEbits.STSEL = 0; //1 stopbit - 1
  U2STAbits.UTXEN = 1;
  U2STAbits.URXEN = 1;
  U2MODEbits.ON = 1;


  while(1){
    //ler DS
    PORTB = (PORTB & 0x00F0);
    PORTB = PORTB >> 4;

    for (int i = 0; i < 10; i++) {
        putc(PORTB);
    }


  }

  return 0;
}
