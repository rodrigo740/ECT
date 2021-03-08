#include<detpic32.h>


void delay(unsigned int ms)
{
    for(; ms > 0; ms--)
    {
        resetCoreTimer();
        while(readCoreTimer() < 20000);
    }
}

// Interrupt Handler
void _int_(27) isr_adc(void)    // Replace VECTOR by the A/D vector number - see "PIC32 family data sheet" (pages 74-76)
{
    LATBbits.LATB6 = 0;

    // Print ADC1BUF0 value // Hexadecimal (3 digits format)
    // Start A/D conversion

    printInt(ADC1BUF0, 16 | 3 << 16);
    printf("\n");

    AD1CON1bits.ASAM = 1;
    while( IFS1bits.AD1IF == 0 );
    IFS1bits.AD1IF = 0; // Reset AD1IF flag

    LATBbits.LATB6 = 1;
}


int main(void){
    // Configure all (digital I/O, analog input, A/D module)
    // Configure interrupt system

    // Configure the A/D module and port RB4 as analog input
    TRISBbits.TRISB4 = 1;       // RB4 digital output disconnected
    AD1PCFGbits.PCFG4 = 0;      // RB4 configured as analog input (AN4)

    AD1CON1bits.SSRC = 7;       // Conversion trigger selection bits: in this
                                // mode an internal counter ends sampling and
                                // starts conversion 
    AD1CON1bits.CLRASAM = 1;    // Stop conversions when the 1st A/D converter
                                // interrupt is generated. At the same time,
                                // hardware clears the ASAM bit

    AD1CON3bits.SAMC = 16;      // Sample time is 16 TAD (TAD = 100 ns) 

    AD1CHSbits.CH0SA = 4;       // Selects AN4 as input for the A/D converter 
    AD1CON2bits.SMPI = 0;       // 1 sample will be converted and stored in buffer locations ADC1BUF0

    AD1CON1bits.ON = 1;         // Enable A/D converter
                                // This must the last command of the A/D
                                // configuration sequence 

    // ISR configs
    IPC6bits.AD1IP = 2; // A/D interrupts priority 
    IEC1bits.AD1IE = 1; // A/D interrupts enabled
    IFS1bits.AD1IF = 0; // Reset AD1IF flag
    
    EnableInterrupts(); // Global Interrupt Enable

    // Start conversion
    AD1CON1bits.ASAM = 1;
    // Wait while conversion not done (AD1IF == 0)
    while( IFS1bits.AD1IF == 0 );
    while(1){} // all activity is done by the ISR
    return 0;
}
