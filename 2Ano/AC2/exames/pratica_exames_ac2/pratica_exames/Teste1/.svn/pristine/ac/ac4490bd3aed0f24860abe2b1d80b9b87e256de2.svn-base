#include <detpic32.h>

void delay(unsigned int);

int main(void) {
  static const unsigned char codes[] = {0x02, 0x01, 0x40, 0x20, 0x10, 0x04, 0x08};
  // configure RB0-RB9 as outputs
  TRISB = TRISB & (0xFC00);
  int i;

  while(1)
  {
    // select display low
    LATBbits.LATB9 = 1;
    LATBbits.LATB9 = 0;

    for(i=0; i < 7; i++) {
      LATB = (LATB & 0xFF00) | codes[i];
      // write codes[i] in port B
      delay(1000);
      // wait 1 second
    }

    LATBbits.LATB9 = 0;
    LATBbits.LATB9 = 1;
    // select display high
  }
  return 0;
}
