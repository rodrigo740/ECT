#include <detpic32.h>

void send2displays(unsigned char value) {
  static unsigned char displayFlag = 0;
  static const unsigned char codes[] = {0x77, 0x41, 0x3B, 0x6B, 0x4D, 0x6E, 0x7E, 0x43, 0x7F, 0x6F, 0x5F, 0x7C, 0x36, 0x79, 0x3E, 0x1E};
  int digit_low = value & 0x0F;
  int digit_high = value >> 4;
  TRISB = TRISB & (0xFC00);
  int pair = 0;
  if(value%2==0){
    pair = 1;
  }

  if(!displayFlag){
    LATBbits.LATB9=1;
    LATBbits.LATB8=0;

    LATB = (LATB & 0xFF00) | codes[digit_low];
    if(pair){
      LATBbits.LATB7 = 1;
    }

  }else{
    LATBbits.LATB9=0;
    LATBbits.LATB8=1;

    LATB = (LATB & 0xFF00) | codes[digit_high];
    if(!pair){
      LATBbits.LATB7 = 1;
    }
  }

  displayFlag = !displayFlag;
}
