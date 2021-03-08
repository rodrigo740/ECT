#include<detpic32.h>

int main(void){

    TRISDbits.TRISD5 = 0;
    T2CONbits.TCKPS = 5;
    PR3 = 312499; 
    TMR3 = 0; // Reset timer T3 count register
    T3CONbits.TON = 1; // Enable timer T3 (must be the last command of the timer configuration sequence)

    // Configure Timer T3 (2 Hz with interrupts disabled)
    while(1)
    {
        while (IFS0bits.T3IF == 0);
        
        // Wait until T3IF = 1
        // Reset T3IF
        putChar('.');
        IFS0bits.T3IF = 0;
    }


    return 0;
}