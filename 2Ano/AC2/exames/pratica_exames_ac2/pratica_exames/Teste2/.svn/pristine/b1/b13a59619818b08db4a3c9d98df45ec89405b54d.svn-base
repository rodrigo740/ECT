// 3) a) Implementar exercicio anterior usando Timer para frequencias, usando interrupçoes.

#include<detpic32.h>
void delay(unsigned int);

void putc(char byte2send){
  while(U2STAbits.UTXBF == 1);
  U2TXREG = byte2send;
}

int main(void){

  TRISB = TRISB | 0x00F0;

  //UART2

  U2BRG = ((20*10^6 + 8 * 115200) / (16 * 115200)) - 1;
  U2MODEbits.BRGH = 0;
  U2MODEbits.PDSEL = 00; // 8 data bits sem paridade
  U2MODEbits.STSEL = 0; //1 stopbit - 1
  U2STAbits.UTXEN = 1;
  U2STAbits.URXEN = 1;
  U2MODEbits.ON = 1;

  //Timer 3 - 100Hz
  PR3 = 49 999;
  T3CONbits.TCKPS = 2;
  TMR3 = 0;
  T3CONbits.TON = 1;

  // interrupts T3
  IFS0bits.T3IF = 0;
  IPC3bits.T3IP = 2;
  IEC0bits.T3IE = 1;

  //Timer 2 - 1Hz não dá, configurar para 2Hz
  PR2 = 39 061;
  T2CONbits.TCKPS = 2;
  TMR2 = 0;
  T2CONbits.TON = 1;

  // interrupts T2
  IFS0bits.T2IF = 0;
  IPC2bits.T2IP = 2;
  IEC0bits.T2IE = 1;


  while(1);

  return 0;
}
int troca = 0;
void _int_(8) isr_t2(void){
  if (troca == 0){
    putc(PORTB);
    troca = 1;
  }else{
    troca = 0;
  }
}

void _int_(12) isr_t3(void){
  //ler DS
  PORTB = (PORTB & 0x00F0);
  PORTB = PORTB >> 4;
}
