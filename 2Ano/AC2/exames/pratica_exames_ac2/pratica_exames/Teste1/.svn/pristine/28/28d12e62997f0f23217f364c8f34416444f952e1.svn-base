#include<detpic32.h>
void send2displays(unsigned char);
void delay(unsigned int);
int main(void) {

  int counter = 0, i = 0;

  while(1){
    i = 0;
     do
    {
      delay(10);
      send2displays(counter);
    } while(++i < 4);
    counter = (counter+1)%20;
  }

  return 0;
}
