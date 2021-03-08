#include<detpic32.h>

int main(void){
    TRISE = TRISE | 0x00F0;
    TRISE = TRISE & 0xFFF0;

    while (1) {
        int x = PORTE & 0x00F0;
        x = x >> 4;

        LATEbits.LATE0 = (x >> 3) & 0x1;
        LATEbits.LATE1 = (x >> 2) & 0x1;
        LATEbits.LATE2 = (x >> 1) & 0x1;
        LATEbits.LATE3 = (x) & 0x1;
    }
}
