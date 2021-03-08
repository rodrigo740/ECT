#include<detpic32.h>
void delay(unsigned int);
unsigned char toBcd(unsigned char);
void send2displays(unsigned char);
static const unsigned char codes[] = {0x77, 0x41, 0x3B, 0x6B, 0x4D, 0x6E, 0x7E, 0x43, 0x7F, 0x6F, 0x5F, 0x7C, 0x36, 0x79, 0x3E, 0x1E};
int sum = 0;
int V = 0, i = 0, j = 0;
int main(void){

  TRISBbits.TRISB14 = 1; // RB4 digital output disconnected
  AD1PCFGbits.PCFG14 = 0; // RB4 configured as analog input (AN4)
  AD1CHSbits.CH0SA = 14; // Selects AN4 as input for the A/D converter
  AD1CON2bits.SMPI = 3;
  AD1CON1bits.SSRC = 7;
  AD1CON1bits.CLRASAM = 1;
  AD1CON3bits.SAMC = 16;
  AD1CON1bits.ON = 1;

  IPC6bits.AD1IP = 3;
  IEC1bits.AD1IE = 1;
  IFS1bits.AD1IF = 0;
  EnableInterrupts();
  AD1CON1bits.ASAM = 1; // Start conversion

  TRISB = TRISB & 0xFC00;

  while(1);

  return 0;
}

void _int_(27) isr_adc(void){
    AD1CON1bits.ASAM = 1; // Start conversion

    delay(10);

    if(j++ == 20){
      sum = 0;
      V = 0;

      AD1CON1bits.ASAM = 1; // Start conversion
      while( IFS1bits.AD1IF == 0 ); // Wait while conversion not done

      int *p = (int *)(&ADC1BUF0);
      for( i = 0; i < 4; i++ )
      {
        sum += p[i*4];
        printf("%d\n", p[i*4]);
      }

      V=((sum/4)*50+511)/1023;


      j = 0;
    }

    send2displays(toBcd(V));

    IFS1bits.AD1IF = 0;

}
