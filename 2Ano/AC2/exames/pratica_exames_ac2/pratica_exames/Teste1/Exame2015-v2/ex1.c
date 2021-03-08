#include<detpic32.h>
void delay(unsigned int);
static const unsigned char codes[] = {0x04, 0x02, 0x01, 0x08, 0x10, 0x20, 0x40, 0x08};
int main(void) {

  // DISPLAYS
  TRISB = TRISB & 0xFC00;
  int index = 0;

  while(1){
    delay(250);

    LATBbits.LATB8 = 0;
    LATB = (LATB & 0xFF00) | codes[index];
    LATBbits.LATB9 = 1;

    if(index>6){
      index = 0;
    }else{
      index++;
    }

  }

  return 0;
}
