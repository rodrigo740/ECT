/* Ler do dipswitch o valor e subtrair ao valor obtido na ADC depois representar no display de 7 segmentos.
Quando a ADC atingir o valor máximo os valores no displays devem piscar com uma frequência de 50Hz.*/

#include<detpic32.h>
void send2displays(unsigned char);
unsigned char toBcd(unsigned char);
void delay(unsigned int);

int main(void) {

  /* Definir entradas e saídas:
    Entradas: DS => RE4 a RE7
    Saídas: LEDS => RE0 a RE3 e Displays RB0 a RB9
  */

  TRISE = (TRISE & 0xFFF0) | 0x00F0;

  /* ADC, neste caso quero o porto RB14 porque é o que está ligado na placa  */

  TRISBbits.TRISB14 = 1; // RB4 digital output disconnected
  AD1PCFGbits.PCFG14 = 0; // RB4 configured as analog input (AN4)


  AD1CHSbits.CH0SA = 14; // Selects AN7 as input for the A/D converter
  AD1CON2bits.SMPI = 3; // 4 samples will be converted and stored
                        // in buffer locations ADC1BUF0 to ADC1BUF3
  AD1CON1bits.SSRC = 7;
  AD1CON1bits.CLRASAM = 1;
  AD1CON1bits.ON = 1;

  int V = 0;
  int i = 0;
  int j = 0;
  int sum = 0;
  int value = 0;

  while(1){
    delay(10);

    if(j++ == 25){

    /* Ler o valor do DS */

      PORTE = PORTE & 0x00F0;
      value = PORTE >> 4; // Ajustar à direita

      AD1CON1bits.ASAM = 1; // Start conversion

      while( IFS1bits.AD1IF == 0 ); // Wait while conversion not done

      int *p = (int *)(&ADC1BUF0);
      for( i = 0; i < 16; i++ )
      {
        sum += p[i*4];
      }

     V =((sum/4)*33+511)/1023;

     if(V==33){
       int k = 0;
       do {
         delay(10);
         TRISB = TRISB & 0xFF00;
         LATB = LATB & 0xFF00;
         delay(10);
         send2displays(0);
       }while(++k<100);
     }

       printf("%d\n",V);
     j = 0;
     sum = 0;
     i = 0;
     // RESET
     IFS1bits.AD1IF = 0;
   }

    /* Ler o resultado */

    send2displays(toBcd(V));
  }

  return 0;
}
