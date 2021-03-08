#include <detpic32.h>

int main(void){

  TRISB = (TRISB & 0xFC00);
  LATBbits.LATB9=1;
  LATBbits.LATB8=0;

  char c;

  while(1){
      c = getChar();

      LATB = LATB & 0xFF00;

      if(c=='A' || c=='a'){
          LATBbits.LATB1 = 1;
      }else if(c=='B' || c=='b'){
          LATBbits.LATB0 = 1;
      }else if(c=='C' || c=='c'){
          LATBbits.LATB6 = 1;
      }else if(c=='D' || c=='d'){
          LATBbits.LATB5 = 1;
      }else if(c=='E' || c=='e'){
          LATBbits.LATB4 = 1;
      }else if(c=='F' || c=='f'){
          LATBbits.LATB2 = 1;
      }else if(c=='G' || c=='g'){
          LATBbits.LATB3 = 1;
      }else if(c == '.'){
          LATBbits.LATB7 = 1;
      }
  }

}
