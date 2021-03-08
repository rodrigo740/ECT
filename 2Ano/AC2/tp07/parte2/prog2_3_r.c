#include<detpic32.h>

void setPWM(unsigned int dutyCycle)
{
    // duty_cycle must be in the range [0, 100]
    OC1CONbits.OCM = 6; // PWM mode on OCx; fault pin disabled
    OC1CONbits.OCTSEL = 1;// Use timer T3 as the time base for PWM generation
    OC1RS =  (50000*dutyCycle)/100;
    OC1CONbits.ON = 1; // Enable OC1 module
}

void configureAll(){

    TRISEbits.TRISE1 = 0;

    //T3
    TRISDbits.TRISD5 = 0;
    T3CONbits.TCKPS = 2;
    PR3 = 49999;
    TMR3 = 0; // Reset timer T3 count register

    IPC3bits.T3IP = 3; // Interrupt priority (must be in range [1..6])
    IEC0bits.T3IE = 1; // Enable timer T3 interrupts
    IFS0bits.T3IF = 0; // Reset timer T3 interrupt flag 
    T3CONbits.TON = 1; // Enable timer T3 (must be the last command of the timer configuration sequence

    EnableInterrupts();
}

int main(void)
{
    int dutyCycle = 80;
    
    configureAll();
    setPWM(dutyCycle);
    
    while(1);
    return 0;
}

void _int_(12) isr_T3(void){
    
    LATEbits.LATE1 = 1;
    // Reset T3IF flag
    IFS0bits.T3IF = 0;
}
