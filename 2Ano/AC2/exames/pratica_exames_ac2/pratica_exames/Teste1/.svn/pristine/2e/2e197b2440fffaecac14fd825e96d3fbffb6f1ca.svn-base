/*
 Neste exercício deve-se ler o valor da ADC e apresentar o resultado sobre barra de leds de 4 leds.

*/

#include <detpic32.h>
void delay(unsigned int);
int main(void) {

  // leds
  TRISE = TRISE & 0xFFF0;
  LATE = (LATE & 0xF0);

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
	int i, media=0;
	int *p = (int *)(&ADC1BUF0);

	for(i=0; i<4; i++){
		media += p[i*4];
	}

	media /= 4;

	if(media>=0x3FE){
		LATE = (LATE & 0xF0) | 0x0F;
		delay(500);
		LATE = (LATE & 0xF0);
		delay(500);
	}else if(media >= 3*0xFF){
		LATE = (LATE & 0xFF) | 0x0E;
	}else if(media >= 2*0xFF){
		LATE = (LATE & 0xF0) | 0x0C;
	}else if(media >= 0xFF){
		LATE = (LATE & 0xF0) | 0x08;
	}else{
		LATE = (LATE & 0xF0);
	}

	IFS1bits.AD1IF = 0;
	AD1CON1bits.ASAM = 1;
}

void delay(unsigned int n_intervals)
{
volatile unsigned int i;
  for(; n_intervals != 0; n_intervals--)
    for(i = 6000; i != 0; i--);
}
