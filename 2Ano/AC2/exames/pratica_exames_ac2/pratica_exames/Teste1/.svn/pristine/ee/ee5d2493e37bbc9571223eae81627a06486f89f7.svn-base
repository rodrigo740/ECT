#include<detpic32.h>
void delay(unsigned int);
unsigned char toBcd(unsigned char);
void send2displays(unsigned char);
int main(void){
int sum = 0, V = 0, i=0;
  TRISBbits.TRISB14 = 1; // RB4 digital output disconnected
  AD1PCFGbits.PCFG14 = 0;
  AD1CHSbits.CH0SA = 14;
  AD1CON2bits.SMPI = 3;
  AD1CON1bits.SSRC = 7;
  AD1CON1bits.CLRASAM = 1;
  AD1CON3bits.SAMC = 16;
  AD1CON1bits.ON = 1;

  while (1) {

    delay(10);
    sum = 0;

    AD1CON1bits.ASAM = 1;
    while( IFS1bits.AD1IF == 0 );

    int *p = (int *)(&ADC1BUF0);
    for( i = 0; i < 4; i++ )
    {
      sum += p[i*4];
    }

    V=((sum/4)*33+511)/1023;
    send2displays(toBcd(V));

    IFS1bits.AD1IF = 0;
  }

  return 0;
}
