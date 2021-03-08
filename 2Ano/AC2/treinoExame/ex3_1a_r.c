#include<detpic32.h>

int main(void){

    //make trisb as inputs
    TRISB = TRISB & 0x000F;

    //make trise as outputs
    TRISE = TRISE & 0xFFF0;

    while(1){
        // read trisb 4 last bits
        PORTB = PORTB & 0x000F;

        // igualar LATE ao valor que quero
        LATE = LATE & 0xFFF0;
        LATE = LATE + PORTB;    
    }
    return 0;
}
