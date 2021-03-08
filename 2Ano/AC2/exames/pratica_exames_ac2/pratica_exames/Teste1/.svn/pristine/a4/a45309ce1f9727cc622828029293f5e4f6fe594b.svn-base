/* Fazer um contador de 0 a 20 nos displays em Hex */

#include <detpic32.h>
void delay(unsigned int);
void reset();
void send2displays(unsigned char);

static char counter = 0;

int main(void){
  // Saídas -> RB0 a RB9
int i;
  while(1){
    i = 0;

    do{
      delay(10);
      send2displays(counter);
    } while(++i < 4);

    if(counter==20) reset();
    counter++;

  }

  return 0;
}

void reset(){
  counter = 0;
}

void delay(unsigned int n_intervals)
{
volatile unsigned int i;
  for(; n_intervals != 0; n_intervals--)
    for(i = 6000; i != 0; i--);
}
