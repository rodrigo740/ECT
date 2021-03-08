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
    
    static const char display7Scodes[] = {0x3F, 0x06, 0x5B, 0X4F, 0x66, 0x6D, 0X7D, 0X07, 
    0X7F, 0X6F, 0X77, 0X7C, 0X39, 0X5E, 0X79, 0X71};

    TRISB = (TRISB | 0x000F);   // configure RB3-RB0 as inputs and RB8-RB14 as outputs
    TRISB = (TRISB & 0x80FF);
    TRISDbits.TRISD5 = 0;       // configure RD5-RD6 as outputs
    TRISDbits.TRISD6 = 0;

    LATB = (LATB & 0x00FF);
    
    LATDbits.LATD6 = 1;         // display high active
    LATDbits.LATD5 = 0;         // display low inactive

    unsigned char toPut;
    char hex;

    while(1)
    {/*
        LATBbits.LATB0 = RB0;
        LATBbits.LATB1 = RB1;
        LATBbits.LATB2 = RB2;
        LATBbits.LATB3 = RB3;*/

        toPut = PORTB & 0x000F;
        hex = display7Scodes[toPut];

        LATB = (LATB & 0x0000) | ((int) hex <<8);
    }

 return 0;
} 