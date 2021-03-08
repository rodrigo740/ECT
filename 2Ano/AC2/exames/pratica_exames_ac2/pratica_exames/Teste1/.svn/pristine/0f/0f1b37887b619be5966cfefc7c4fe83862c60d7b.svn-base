#include <detpic32.h>

int display7Scodes();
void send2displays(unsigned char);
void delay(unsigned int);
unsigned char toBcd(unsigned char);

int main(void) {
int counter = 0;
int i,j,k;
// declare variables
// initialize ports
  while(1){
    i = 0;
    j=0;
    k = 0;
    do{
      delay(10);  // wait 50 ms
      send2displays(toBcd(counter)); // call send2displays with counter value as argumen
    } while(++i < 4);
    counter = (counter+1)%60;
    if(counter==0){
      for (j = 0; j < 6; j++) {
        LATB = (LATB & 0xFC00);
        delay(500);
        for (k = 0; k < 500; k++) {
          send2displays(0x0);
          delay(1);
        }
      }
    }
  }

  return 0;
}
