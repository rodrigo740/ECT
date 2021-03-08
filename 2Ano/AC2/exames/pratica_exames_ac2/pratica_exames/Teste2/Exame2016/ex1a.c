#include<detpic32.h>

int main(void){

    TRISE = TRISE & 0xFFF0; // leds
    TRISE = TRISE | 0x00F0; // DS

    while(1){
        int x = PORTE & 0x00F0;
        x = x >> 4;

        LATEbits.LATE3 = x & 0x1;
        LATEbits.LATE2 = (x & 0x2) >> 1;
        LATEbits.LATE1 = (x & 0x4) >> 2;
        LATEbits.LATE0 = (x & 0x8) >> 3;
    }

}
