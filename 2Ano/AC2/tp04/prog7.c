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
    
    unsigned char display7Scodes[] = {0x3F, 0x06, 0x5B, 0x4F, 0x66, 0x6D, 0x7D, 0x07, 0x7F, 0x6F};



    TRISB = (TRISB & 0x80FF);   // configure RB8-RB14 as outputs
    TRISDbits.TRISD5 = 0;       // configure RD5-RD6 as outputs
    TRISDbits.TRISD6 = 0;

    LATD = (LATD & 0xFF9F) | 0x0040; // display high active, low inactive

    while(1)
    {
        LATD = LATD ^ 0x0060; // toggle display selection
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