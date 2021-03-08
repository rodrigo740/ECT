#include<detpic32.h>

void delay(unsigned int);
void send2display(unsigned char);

int main(void) {
  int i;
  int counter = 0;
  while(1){
    i = 0;
    do{
      delay(25);
      send2display(counter);
    } while(++i < 10);
  counter = (counter + 1)%7;
  }

  return 0;
}
