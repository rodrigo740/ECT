#include <detpic32.h>

void putc(char);
void configUart(int, char, int);
unsigned char toBcd(unsigned char);
void send2displays(unsigned char);
void setPWM(unsigned int);
int sum = 0, V = 0, x = 0;
int main(void){

  // configurar a UART1
  U1BRG = ((20000000 + 8 * 38400) / (16 * 38400)) - 1;
  U1MODEbits.BRGH = 0; //divisão por 16
  U1MODEbits.PDSEL = 0b10;
  U1MODEbits.STSEL = 0; // 1 stop bit - 1
  U1STAbits.URXEN = 1;
  U1STAbits.UTXEN = 1;
  U1MODEbits.ON = 1;
  IFS0bits.U1RXIF = 1;

  // UART interrupts
  U1STAbits.URXISEL = 00;
  IEC0bits.U1RXIE = 1;
  IPC6bits.U1IP = 3;
  EnableInterrupts();

  // ADC

  TRISBbits.TRISB14 = 1; // RB4 digital output disconnected
  AD1PCFGbits.PCFG14 = 0; // RB4 configured as analog input (AN4)
  AD1CHSbits.CH0SA = 14; // Selects AN7 as input for the A/D converter
  AD1CON2bits.SMPI = 7; // 8 conversoes
  AD1CON1bits.SSRC = 7;
  AD1CON1bits.CLRASAM = 1;
  AD1CON3bits.SAMC = 16;
  AD1CON1bits.ON = 1;

  IPC6bits.AD1IP = 3;
  IEC1bits.AD1IE = 1;
  IFS1bits.AD1IF = 0;
  EnableInterrupts();

  // Timer 4
  PR4 = 34482;
  T4CONbits.TCKPS = 2;
  TMR4 = 0;

  //Timer 4 interrupts
  IFS0bits.T4IF = 0;
  IPC4bits.T4IP = 2;
  IEC0bits.T4IE = 1;

  //Timer 2
  PR2 = 49999;
  T2CONbits.TCKPS = 2;
  TMR2 = 0;
  T2CONbits.TON = 1;

  OC1CONbits.OCM = 6;
  OC1CONbits.OCTSEL = 0;
  OC1RS = 12500;
  OC1CONbits.ON = 1;

  while(1);
  return 0;
}

void _int_(24) isr_uart1(void){
  // esta função é chamada quer seja pela receção, quer pela transmissão
  if(IFS0bits.U1RXIF){
    char c = U1RXREG;
    if(c == 'A'){

      AD1CON1bits.ASAM = 1;

    }else if( c == 'D'){

      T4CONbits.TON = 1;
      x = V;

    }else if( c == 'E'){

      putc('0' + ((toBcd(V) & 0xF0)>>4));
      putc('0' + (toBcd(V) & 0x0F));

    }else if( c == 'P'){
      setPWM(V);
    }
  }
  IFS0bits.U1RXIF = 0;
}

void _int_(27) isr_adc(void){
  int i;
  sum = 0;
  int *p = (int *)(&ADC1BUF0);

  for(i = 0; i < 8; i++){
    sum +=p[i*4];
  }

  V=((sum/8)*99+511)/1023;
  printf("%d\n", V);

  IFS1bits.AD1IF = 0;

}

void putc(char byte2send) {
  while(U1STAbits.UTXBF==1);// wait while UTXBF == 1
  U1TXREG = byte2send;  // Copy byte2send to the UxTXREG register
}

void _int_(16) isr_T4(void){
  send2displays(toBcd(x));
  IFS0bits.T4IF = 0;
}

void setPWM(unsigned int dutyCycle) {
  // duty_cycle must be in the range [0, 100]
  OC1RS = ((PR2 + 1) * dutyCycle)/100; // Evaluate OC1RS as a function of "dutyCycle"
}
