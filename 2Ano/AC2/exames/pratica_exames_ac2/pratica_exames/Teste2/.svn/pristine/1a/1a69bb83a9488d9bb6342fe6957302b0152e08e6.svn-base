#include<detpic32.h>

int main(void){
    TRISE = TRISE | 0x00F0;
    TRISE = TRISE & 0xFFF0;

    while (1) {
        int x = PORTE & 0x00F0;
        x = x >> 4;

        LATEbits.LATE0 = (x & 8) >> 3;
        LATEbits.LATE1 = (x & 4) >> 2;
        LATEbits.LATE2 = (x & 2) >> 1;
        LATEbits.LATE3 = (x & 1);
    }
}
