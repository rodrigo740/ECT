//1) Ler o valor de 4 Dip Switches e imprimir na consola. Lidos a uma frequencia de 100 Hz, escritos a 1 hz.

#include<detpic32.h>
void delay(unsigned int);

int main(void){

  TRISB = TRISB | 0x00F0;

  while(1){

    for (int i = 0; i < 10; i++) {
      //ler DS
      PORTB = (PORTB & 0x00F0);
      PORTB = PORTB >> 4;
      delay(10);
    }

    delay(900);
    printInt10(PORTB);

  }

  return 0;
}
