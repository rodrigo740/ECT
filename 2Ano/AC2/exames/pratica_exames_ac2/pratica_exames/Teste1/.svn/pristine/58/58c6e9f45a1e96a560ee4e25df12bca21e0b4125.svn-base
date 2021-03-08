#include<detpic32.h>

void send2display(unsigned char value) {
static const unsigned char snake[] = {0x02, 0x01, 0x08, 0x10, 0x20, 0x40, 0x08};
unsigned char digit_low = value & 0x0F;

/* Select display high */

TRISB = TRISB & 0xFC00;

LATBbits.LATB9 = 0;

LATB = (LATB & 0xFF00) | snake[digit_low];

LATBbits.LATB8 = 1;


}
