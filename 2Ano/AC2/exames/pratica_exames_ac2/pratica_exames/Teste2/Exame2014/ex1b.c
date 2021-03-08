#include<detpic32.h>
static const unsigned char codes[] = {0x77, 0x41, 0x3B, 0x6B, 0x4D, 0x6E, 0x7E, 0x43, 0x7F, 0x6F, 0x5F, 0x7C, 0x36, 0x79, 0x3E, 0x1E};
int main(void){

    TRISE = TRISE | 0x00F0; //  DS
    TRISB = TRISB & 0xFC00; //  displays

    while(1){
      int x = (PORTE & 0x00F0) >> 4;
      LATBbits.LATB8 = 0;
      LATB = (LATB & 0xFF00) | codes[x];
      LATBbits.LATB9 = 1; 
    }
}
