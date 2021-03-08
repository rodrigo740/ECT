#include<detpic32.h>
void delay(unsigned int);
static const unsigned char codes[] = {0x04, 0x02, 0x01, 0x10, 0x20, 0x40, 0x01, 0x02, 0x04, 0x40, 0x20, 0x10};
int main(void) {
  int sum = 0, V = 0, i = 0;

  TRISBbits.TRISB14 = 1; // RB4 digital output disconnected
  AD1PCFGbits.PCFG14 = 0; // RB4 configured as analog input (AN4)
  AD1CHSbits.CH0SA = 14;
  AD1CON2bits.SMPI = 3;
  AD1CON1bits.SSRC = 7;
  AD1CON1bits.CLRASAM = 1;
  AD1CON3bits.SAMC = 16;
  AD1CON1bits.ON = 1;

  while (1) {

    // DISPLAYS
    TRISB = TRISB & 0xFC00;
    int index = 0;

    for(index = 0; index <= 11; index++){

      sum = 0;

      AD1CON1bits.ASAM = 1;
      while( IFS1bits.AD1IF == 0 );

      int *p = (int *)(&ADC1BUF0);
      for( i = 0; i < 4; i++ )
      {
        sum += p[i*4];
      }

      V=((sum/4)*8+511)/1023;
      printf("%d\n", V);

      switch (V) {
        case 0:
          delay(250);
          break;
        case 1:
          delay(500);
          break;
        case 2:
          delay(750);
          break;
        case 3:
          delay(1000);
          break;
        case 4:
          delay(1250);
          break;
        case 5:
          delay(1500);
          break;
        case 6:
          delay(1750);
          break;
        case 7:
          delay(2000);
          break;
      }

      switch (index) {
        case 0:
          LATBbits.LATB8 = 0;
          LATB = (LATB & 0xFF00) | codes[index];
          LATBbits.LATB9 = 1;
          break;
        case 1:
          LATBbits.LATB8 = 0;
          LATB = (LATB & 0xFF00) | codes[index];
          LATBbits.LATB9 = 1;
          break;
        case 2:
          LATBbits.LATB8 = 0;
          LATB = (LATB & 0xFF00) | codes[index];
          LATBbits.LATB9 = 1;
          break;
        case 3:
          LATBbits.LATB9 = 0;
          LATB = (LATB & 0xFF00) | codes[index];
          LATBbits.LATB8 = 1;
          break;
        case 4:
          LATBbits.LATB9 = 0;
          LATB = (LATB & 0xFF00) | codes[index];
          LATBbits.LATB8 = 1;
          break;
        case 5:
          LATBbits.LATB9 = 0;
          LATB = (LATB & 0xFF00) | codes[index];
          LATBbits.LATB8 = 1;
          break;
        case 6:
          LATBbits.LATB9 = 0;
          LATB = (LATB & 0xFF00) | codes[index];
          LATBbits.LATB8 = 1;
          break;
        case 7:
          LATBbits.LATB9 = 0;
          LATB = (LATB & 0xFF00) | codes[index];
          LATBbits.LATB8 = 1;
          break;
        case 8:
          LATBbits.LATB9 = 0;
          LATB = (LATB & 0xFF00) | codes[index];
          LATBbits.LATB8 = 1;
          break;
        case 9:
          LATBbits.LATB9 = 0;
          LATB = (LATB & 0xFF00) | codes[index];
          LATBbits.LATB8 = 1;
          break;
        case 10:
          LATBbits.LATB8 = 0;
          LATB = (LATB & 0xFF00) | codes[index];
          LATBbits.LATB9 = 1;
          break;
        case 11:
          LATBbits.LATB8 = 0;
          LATB = (LATB & 0xFF00) | codes[index];
          LATBbits.LATB9 = 1;
          break;
      }

    }

    IFS1bits.AD1IF = 0;
  }

  return 0;
}
