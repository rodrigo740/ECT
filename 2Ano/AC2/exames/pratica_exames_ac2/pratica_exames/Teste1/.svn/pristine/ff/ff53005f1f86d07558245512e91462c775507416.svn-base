#include<detpic32.h>

void send2displays(unsigned char value) {

  TRISB = TRISB & 0xFC00;
  LATB = LATB & 0xFF00;

    if(value==1){
      LATBbits.LATB9 = 0;
      LATBbits.LATB2 = 1;
      LATBbits.LATB8 = 1;

    }else if(value==2){
      LATBbits.LATB9 = 0;
      LATBbits.LATB1 = 1;
      LATBbits.LATB8 = 1;

    }else if(value==3){
      LATBbits.LATB9 = 0;
      LATBbits.LATB0 = 1;
      LATBbits.LATB8 = 1;

    }else if(value==4){
      LATBbits.LATB8 = 0;
      LATBbits.LATB4 = 1;
      LATBbits.LATB9 = 1;

    }else if(value==5){
      LATBbits.LATB8 = 0;
      LATBbits.LATB5 = 1;
      LATBbits.LATB9 = 1;

    }else if(value==6){
      LATBbits.LATB8 = 0;
      LATBbits.LATB6 = 1;
      LATBbits.LATB9 = 1;

    }else if(value==7){
      LATBbits.LATB8 = 0;
      LATBbits.LATB0 = 1;
      LATBbits.LATB9 = 1;

    }else if(value==8){
      LATBbits.LATB8 = 0;
      LATBbits.LATB1 = 1;
      LATBbits.LATB9 = 1;

    }else if(value==9){
      LATBbits.LATB8 = 0;
      LATBbits.LATB2 = 1;
      LATBbits.LATB9 = 1;

    }else if(value==10){
      LATBbits.LATB9 = 0;
      LATBbits.LATB6 = 1;
      LATBbits.LATB8 = 1;

    }else if(value==11){
      LATBbits.LATB9 = 0;
      LATBbits.LATB5 = 1;
      LATBbits.LATB8 = 1;

    }else if(value == 12){
      LATBbits.LATB9 = 0;
      LATBbits.LATB4 = 1;
      LATBbits.LATB8 = 1;
    }
}
