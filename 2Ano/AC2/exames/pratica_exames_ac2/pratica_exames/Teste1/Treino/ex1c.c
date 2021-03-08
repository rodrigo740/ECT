/* Piscar os leds com uma frequência de 1Hz em ciclo inifinito. */

#include<detpic32.h>
void delay(unsigned int);

int main(void){

  /* Definir como saída os LEDS RE0 a RE3 */
  TRISE = TRISE & 0xFFF0;

  while(1){

    /* Acender os LEDS */
    LATE = (LATE & 0xFFF0) | 15;

    delay(500); /* Esperar meio segundo */

    /* Apagar os LEDS */
    LATE = (LATE & 0xFFF0) | 0;

    delay(500); /* Esperar meio segundo */

  }

  return 0;
}
