#include<detpic32.h>
int main(void){

    TRISE = TRISE | 0x00F0; //DS
    TRISE = TRISE & 0xFFF0;

    while(1){
      PORTE = (PORTE & 0x00F0);
      PORTE = PORTE >> 4;
      LATE = (LATE & 0xFFF0) | (PORTE);
    }
}
