#include<detpic32.h>

int count = 0;

int main(void)
{
    // Configure Timer T3 with interrupts enabled

    TRISDbits.TRISD5 = 0;
    T3CONbits.TCKPS = 5;
    PR3 = 312499;
    TMR3 = 0; // Reset timer T3 count register
    T3CONbits.TON = 1; // Enable timer T3 (must be the last command of the timer configuration sequence)

    IPC3bits.T3IP = 3; // Interrupt priority (must be in range [1..6])
    IEC0bits.T3IE = 1; // Enable timer T3 interrupts
    IFS0bits.T3IF = 0; // Reset timer T3 interrupt flag 
    EnableInterrupts();
    while(1);
    return 0;
}

void _int_(12) isr_T3(void) // Replace VECTOR by the timer T3 vector number
{
    count++;
    if (count == 2)
    {
        putChar('.');
        count = 0;
    }
    
    // Reset T3 interrupt flag
    IFS0bits.T3IF = 0;
}
