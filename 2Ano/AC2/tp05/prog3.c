#include<detpic32.h>

void delay(unsigned int ms)
{
    for(; ms > 0; ms--)
    {
        resetCoreTimer();
        while(readCoreTimer() < 20000);
    }
}

int main(void){

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
    AD1CON2bits.SMPI = 4;       // 4 sample will be converted and stored in buffer locations ADC1BUF0

    AD1CON1bits.ON = 1;         // Enable A/D converter
                                // This must the last command of the A/D
                                // configuration sequence 

    while(1)
    {
        // Start conversion
        AD1CON1bits.ASAM = 1;
        // Wait while conversion not done (AD1IF == 0)
        while( IFS1bits.AD1IF == 0 );
        
        // Read conversion result (ADC1BUF0 value) and print it
        int *p = (int *)(&ADC1BUF0);
        int i;
        for(i = 0; i < 16; i++ )
        {
        printInt( p[i*4],16 | 3 << 16);
        printf(" ");
        } 
        
        printf("\n");
        delay(100);
        
        // Reset AD1IF
        IFS1bits.AD1IF = 0;

        
    }

    return 0;
}