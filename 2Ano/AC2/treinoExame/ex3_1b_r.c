#include<detpic32.h>

int main(void){

    //make trisb as inputs
    TRISB = TRISB & 0x000F;

    //make trise as outputs
    TRISE = TRISE & 0xFFF0;

    while(1){

        LATEbits.LATE3 = PORTBbits.RB0;
        LATEbits.LATE2 = PORTBbits.RB1;
        LATEbits.LATE1 = PORTBbits.RB2;
        LATEbits.LATE0 = PORTBbits.RB3;

        // igualar LATE ao valor que quero
        LATE = LATE & 0xFFF0;   
    }

    return 0;
}