#include<detpic32.h>

// ler valor do dipswitch e apresentar nos leds
void delay(unsigned int);

int main(void){

  // portos de entrada -> RE4 a RE7
  // portos de saída -> RE0 a RE3

  TRISE = (TRISE & 0xF0) | 0xF0;
  PORTE = PORTE & 0x00F0;
  // Ler do dipswitch o valor
  int i;

  while (1) {

      delay(1000);
      // apresentar nos leds

      LATE = (LATE & 0xFFF0) | (PORTE >> 4);

  }
  return 0;
}

void delay(unsigned int n_intervals)
{
volatile unsigned int i;
  for(; n_intervals != 0; n_intervals--)
    for(i = 6000; i != 0; i--);
  }
