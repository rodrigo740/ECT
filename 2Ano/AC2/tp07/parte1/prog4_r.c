#include<detpic32.h>

int main(void){

    // Configure Timer T3 with interrupts enabled

    TRISDbits.TRISD5 = 0;
    T3CONbits.TCKPS = 5;
    PR3 = 62499;
    TMR3 = 0; // Reset timer T3 count register
    T3CONbits.TON = 1; // Enable timer T3 (must be the last command of the timer configuration sequence)

    IPC3bits.T3IP = 2; // Interrupt priority (must be in range [1..6])
    IEC0bits.T3IE = 1; // Enable timer T3 interrupts
    IFS0bits.T3IF = 0; // Reset timer T3 interrupt flag 


    // Configure Timer T1 with interrupts enabled

    TRISDbits.TRISD5 = 0;
    T1CONbits.TCKPS = 3;
    PR1 = 39062;
    TMR1 = 0; // Reset timer T3 count register
    T1CONbits.TON = 1; // Enable timer T3 (must be the last command of the timer configuration sequence)

    IPC1bits.T1IP = 2; // Interrupt priority (must be in range [1..6])
    IEC0bits.T1IE = 1; // Enable timer T3 interrupts
    IFS0bits.T1IF = 0; // Reset timer T3 interrupt flag 

    EnableInterrupts(); // Global Interrupt Enable
    while(1);
    return 0;
}

void _int_(4) isr_T1(void){
    // print character '1'
    putChar('1');
    // Reset T1IF flag
    IFS0bits.T1IF = 0;
}
void _int_(12) isr_T3(void){
    // print character '3'
    putChar('3');
    // Reset T3IF flag
    IFS0bits.T3IF = 0;
} 
