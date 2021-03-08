#include<detpic32.h>

void delay(unsigned int);
void send2displays(unsigned char);

int main(void) {
  int i;
  int counter = 1;
  int init = 25;
  int delay_time = 0;

  TRISB = TRISB & 0XFC00;
  TRISBbits.TRISB14 = 1;		//sets TRISB (7-segmentos + input ADC)

  AD1CON1bits.SSRC = 7;
  AD1CON1bits.CLRASAM = 7;

  AD1CON3bits.SAMC = 16;

  AD1PCFGbits.PCFG14 = 0; //analog input
  AD1CHSbits.CH0SA = 14; //sets pin 14 as input for A/D converter
  AD1CON2bits.SMPI = 0; // sets the number of samples max=7

  AD1CON1bits.ON = 1;		//turnson A/D converter

  while(1){

    AD1CON1bits.ASAM = 1;	//starts conversion
  	while(IFS1bits.AD1IF == 0);

    int separate = 1023/8;

    if((int)ADC1BUF0 > separate && (int)ADC1BUF0 <= 2*separate){
      delay_time = init;
    }else if((int)ADC1BUF0 > 2*separate && (int)ADC1BUF0 <= 3*separate){
      delay_time = 2*init;
    }else if((int)ADC1BUF0 > 3*separate && (int)ADC1BUF0 <= 4*separate){
      delay_time = 3*init;
    }else if((int)ADC1BUF0 > 4*separate && (int)ADC1BUF0 <= 5*separate){
      delay_time = 4*init;
    }else if((int)ADC1BUF0 > 5*separate && (int)ADC1BUF0 <= 6*separate){
      delay_time = 5*init;
    }else if((int)ADC1BUF0 > 6*separate && (int)ADC1BUF0 <= 7*separate){
      delay_time = 6*init;
    }else if((int)ADC1BUF0 > 7*separate && (int)ADC1BUF0 <= 8*separate){
      delay_time = 7*init;
    }else if((int)ADC1BUF0 > 8*separate){
      delay_time = 8*init;
    }


    i = 0;
    do{
      delay(delay_time);
      send2displays(counter);
    } while(++i < 10);
    counter = (counter + 1)%12;
  }

  return 0;
}
