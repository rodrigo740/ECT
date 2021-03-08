#include <detpic32.h>

int display7Scodes();

int main(void) {
  static const unsigned char codes[] = {0x77, 0x41, 0x3B, 0x6B, 0x4D, 0x6E, 0x7E, 0x43, 0x7F, 0x6F, 0x5F, 0x7C, 0x36, 0x79, 0x3E, 0x1E};
  // configure  RE4 to RE7 as inputs
  TRISE = TRISE | 0x00F0;
  TRISB = TRISB & (0xFC00);
  LATBbits.LATB9=1;
  LATBbits.LATB8=0;
  int c;

  while(1)
  {
    PORTE = (PORTE & 0x00F0); // read dip-switch
    c = display7Scodes(); // convert to 7 segments code
    LATB = (LATB & 0xFF00) | codes[c]; // send to display
  }

  return 0;
}
