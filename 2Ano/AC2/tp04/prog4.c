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
    unsigned char segment;
    
    TRISB = (TRISB & 0x80FF);   // configure RB8-RB14 as outputs
    TRISDbits.TRISD5 = 0;       // configure RD5-RD6 as outputs
    TRISDbits.TRISD6 = 0;

    LATDbits.LATD6 = 1;         // display high active
    LATDbits.LATD5 = 0;         // display low inactive

    while(1)
    {
        LATDbits.LATD6 = !LATDbits.LATD6; //
        LATDbits.LATD5 = !LATDbits.LATD5; // toggle display selection
        segment = 1;
        int i;
        
        for(i=0; i < 7; i++)
        {
            // send "segment" value to display

            LATB = (LATB & 0x00FF) | (segment <<8);
            
            segment = segment << 1;
            // wait 0.5 second
            delay(1000);
        }
    }

 return 0;
} 