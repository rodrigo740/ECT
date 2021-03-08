#include<detpic32.h>

void delay(unsigned int);
void send2displays(unsigned char);

int i;
int counter = 1;
int init = 25;
int delay_time = 0;

int main(void) {

  TRISB = TRISB & 0XFC00;
  TRISBbits.TRISB14 = 1; // RB4 digital output disconnected
  AD1PCFGbits.PCFG14 = 0; // RB4 configured as analog input (AN4)


  AD1CHSbits.CH0SA = 14; // Selects AN7 as input for the A/D converter
  AD1CON2bits.SMPI = 3; // 4 samples will be converted and stored
                        // in buffer locations ADC1BUF0 to ADC1BUF3
  AD1CON1bits.SSRC = 7;
  AD1CON1bits.CLRASAM = 1;
  AD1CON1bits.ON = 1;

  IPC6bits.AD1IP = 3; // valor aleatório entre 1 e 6, 0 é inativo e 7 prioridade máxima. Como só há uma interrupção pode ser qualquer valor entre 1 e 6
  IEC1bits.AD1IE = 1; // autorizar interrupções pelo módulo A/D

  IFS1bits.AD1IF = 0;
  EnableInterrupts();

    AD1CON1bits.ASAM = 1;	//starts conversion

  while(1);

  return 0;
}

void _int_(27) isr_adc(void){

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

  printf("%d\n", counter);

  IFS1bits.AD1IF = 0;
}
