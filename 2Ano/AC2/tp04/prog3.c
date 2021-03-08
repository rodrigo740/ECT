#include <detpic32.h>

void delay(unsigned int ms)
{
    for(; ms > 0; ms--)
    {
        resetCoreTimer();
        while(readCoreTimer() < 20000);
    }
}

int main(void){

    TRISB = (TRISB & 0x00FF);
    LATB = (LATB & 0x00FF);

    PORTBbits.RB0 = 1;
    

    TRISDbits.TRISD5 = 0;
    TRISDbits.TRISD6 = 0;

    LATDbits.LATD5 = 1;
    LATDbits.LATD6 = 0;

    char c;

   while (1)
   {
       
       c = getChar();

       LATB = (LATB & 0x00FF);

       if (PORTBbits.RB0 == 0)
        {
            LATDbits.LATD5 = 1;
            LATDbits.LATD6 = 0;
       }else
        {
            LATDbits.LATD5 = 0;
            LATDbits.LATD6 = 1;
       }
       
       

    switch(c) {
      case 'a':
      case 'A':
        LATBbits.LATB8 = 1;
        break;

      case 'b':
      case 'B':
        LATBbits.LATB9 = 1;
        break;

      case 'c':
      case 'C':
        LATBbits.LATB10 = 1;
        break;

      case 'd':
      case 'D':
        LATBbits.LATB11 = 1;
        break;

      case 'e':
      case 'E':
        LATBbits.LATB12 = 1;
        break;

      case 'f':
      case 'F':
        LATBbits.LATB13 = 1;
        break;

      case 'g':
      case 'G':
        LATBbits.LATB14 = 1;
        break;

      case '.':
        LATBbits.LATB15 = 1;
        break;

      default:
        break;
    }

    
   }
   return 0;
}