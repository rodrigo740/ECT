#include <detpic32.h>
void delay(unsigned int);
unsigned char toBCD(unsigned char);
void send2displays(unsigned char);

int main(void)
{
  TRISBbits.TRISB14 = 1; // RB4 digital output disconnected
  AD1PCFGbits.PCFG14 = 0; // RB4 configured as analog input (AN4)

  AD1CHSbits.CH0SA = 14; // Selects AN4 as input for the A/D converter

  AD1CON2bits.SMPI = 3; // 4 samples will be converted and stored

  AD1CON1bits.SSRC = 7;
  AD1CON1bits.CLRASAM = 1; // Stop conversions when the 1st A/D converter
  AD1CON3bits.SAMC = 16;
  AD1CON1bits.ON = 1;

  int amostras = 0;
  int sum, V;
  sum = 0;
  V = 0;
  int j = 0;
  int i = 0;
  while(1)
  {
    delay(10);
    if(i++ == 25) // 250 ms
    {
      AD1CON1bits.ASAM = 1; // Start conversion
      while( IFS1bits.AD1IF == 0 ); // Wait while conversion not done
      int *p = (int *)(&ADC1BUF0);
      for( j = 0; j < 16; j++ )
      {
        printInt( p[j*4], 10 );
        printStr("\n");
        amostras+=p[j*4];
        if(p[j*4]>0){
          sum++;
        }
      }

      IFS1bits.AD1IF = 0;
      printStr("Media das amostras: ");
      printInt(amostras/sum, 10);
      printStr("\nTensão: ");
      V=((amostras/sum)*33+511)/1023;
      printInt(V, 10);

      V = toBCD(V);
    i = 0;
    sum = 0;
    amostras = 0;
    }
  send2displays(V);
  }
  return 0;
}
