#include<detpic32.h>


volatile unsigned char voltage = 0; // Global variable

void delay(unsigned int ms)
{
    for(; ms > 0; ms--)
    {
        resetCoreTimer();
        while(readCoreTimer() < 20000);
    }
}

void send2displays(unsigned char value){
    static const char display7Scodes[] = {0x3F, 0x06, 0x5B, 0x4F, 0x66, 0x6D, 0x7D, 0x07, 0x7F, 0x6F, 0x77, 0x7C, 0x39, 0x5E, 0x79, 0x71};
    static char displayFlag = 0;
    unsigned char dh = display7Scodes[value >> 4];
    unsigned char dl = display7Scodes[value & 0x0F];

    if(displayFlag){
        LATDbits.LATD5 = 1;
        LATDbits.LATD6 = 0;    
        LATB = (dh << 8) | (LATB & 0x00FF);
        if(value % 2 != 0){
            LATBbits.LATB15 = 1;
        }
    }

    else{
        LATDbits.LATD5 = 0;
        LATDbits.LATD6 = 1;
        LATB = (dl << 8) | (LATB & 0x00FF);
        if(value % 2 == 0){
            LATBbits.LATB15 = 1;
        }
    }

    displayFlag = !displayFlag;  
}


// Interrupt Handler
void _int_(27) isr_adc(void)    // Replace VECTOR by the A/D vector number - see "PIC32 family data sheet" (pages 74-76)
{
    int i;
    double media = 0;

    int *p = (int *)(&ADC1BUF0);

    for ( i = 0; i < 8; i++)
    {
        media += p[i*4];
    }
    media /= 8;

    int v = media * 33 / 1023;
    
    // Calculate voltage amplitude
    
    int q = v/10;
    int r = v%10;

    voltage = (q << 4 | r);
    IFS1bits.AD1IF = 0;
}

int main(void)
{
    TRISB = (TRISB | 0x000F);
    TRISB = (TRISB & 0x80FF);
    TRISDbits.TRISD5 = 0;       // configure RD5-RD6 as outputs
    TRISDbits.TRISD6 = 0;

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
    AD1CON2bits.SMPI = 7;       // 7 sample will be converted and stored in buffer locations ADC1BUF0

    AD1CON1bits.ON = 1;         // Enable A/D converter
                                // This must the last command of the A/D
                                // configuration sequence 

    // ISR configs
    IPC6bits.AD1IP = 2; // A/D interrupts priority 
    IEC1bits.AD1IE = 1; // A/D interrupts enabled
    IFS1bits.AD1IF = 0; // Reset AD1IF flag
    
    EnableInterrupts(); // Global Interrupt Enable

    int i = 0

    while(1)
    {
        // Wait 10 ms using the core timer
        delay(10);
        
        if(i++ == 25) // 250 ms (4 samples/second)
        {
            // Start A/D conversion
            AD1CON1bits.ASAM = 1;

            i = 0;
        }
        // Send "voltage" variable to displays
        send2displays(voltage);
    }

    return 0;
}
