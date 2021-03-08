/*
  Ler do dipswitch o valor e subtrair ao valor obtido na ADC depois representar no display de 7 segmentos.
  Quando a ADC atingir o valor máximo os valores no dipswitch devem piscar com uma frequência de 50Hz.
*/

#include <detpic32.h>
void delay(unsigned int);
void send2displays(unsigned char);

static int value;

int main(void) {

  // entradas dipswitch
  TRISE = (TRISE | 0xF0);

  PORTE = PORTE & 0x00F0;
  // Ler do dipswitch o valor
  value = (PORTE >> 4);

  TRISBbits.TRISB14 = 1;
  AD1PCFGbits.PCFG14 = 0;
  AD1CON1bits.SSRC = 7;
  AD1CHSbits.CH0SA = 14;
  AD1CON1bits.CLRASAM = 1;
  AD1CON2bits.SMPI = 3;
  AD1CON3bits.SAMC = 16;

  AD1CON1bits.ON = 1;

  IPC6bits.AD1IP = 4;
	IEC1bits.AD1IE = 1;
  EnableInterrupts();
  IFS1bits.AD1IF = 0;
	AD1CON1bits.ASAM = 1;

  while(1)
  {
  }

  return 0;
}

void _int_(27) isr_adc(void){

  unsigned int i = 0, media = 0;
  int *p = (int *)(&ADC1BUF0);

  for(; i<4; i++){
    media += p[i*4];
  }

  media /= 4;
  i = 0;

  if(media >= 0x3FE){
    TRISE = TRISE & 0xF0;
    LATE = (LATE & 0xF0) | 0x0F;
  }

  do{
    send2displays(media);
    delay(10);
    if(media >= 0x3FE && i>=50){
      LATE = (LATE & 0xF0);
    }
  }while(++i<100);

  IFS1bits.AD1IF = 0;
  AD1CON1bits.ASAM = 1;
}

void delay(unsigned int n_intervals)
{
volatile unsigned int i;
  for(; n_intervals != 0; n_intervals--)
    for(i = 6000; i != 0; i--);
}
