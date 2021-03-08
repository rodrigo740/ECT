#include<detpic32.h>

void delay(unsigned int ms)
{
    for(; ms > 0; ms--)
    {
        resetCoreTimer();
        while(readCoreTimer() < 20000);
    }
}

void send2displays(unsigned char value){
    static const char display7Scodes[] = {0x3F, 0x06, 0x5B, 0x4F, 0x66, 0x6D, 0x7D, 0x07, 
    0x7F, 0x6F, 0x77, 0x7C, 0x39, 0x5E, 0x79, 0x71};
    // send digit_high (dh) to display_high: 
    unsigned char dh = (value & 0xF0) >> 4;
    // send digit_low (dl) to display_low: 
    unsigned dl = value & 0x0F;
    char hexCode;
    LATDbits.LATD5 = 1;
    LATDbits.LATD6 = 0;    
    hexCode = display7Scodes[dl];
    LATB = (LATB & 0x00FF) | ((int)hexCode << 8);
    delay(10); 
    LATDbits.LATD5 = 0;
    LATDbits.LATD6 = 1; 
    hexCode = display7Scodes[dh];
    LATB = (LATB & 0x00FF) | ((int)hexCode << 8);
    delay(10);
}

int main(void){

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
    AD1CON2bits.SMPI = 4;       // 4 sample will be converted and stored in buffer locations ADC1BUF0

    AD1CON1bits.ON = 1;         // Enable A/D converter
                                // This must the last command of the A/D
                                // configuration sequence 

    // Configure all (digital I/O, analog input, A/D module)
    int i = 0;
    int value = 0;
    int q = 0;
    int r = 0;
    while(1)
    {
        if(i++ % 25 == 0) // 0, 250ms, 500ms, 750ms, ...
        {
            // Convert analog input (4 samples)

            // Start conversion
            AD1CON1bits.ASAM = 1;
            // Wait while conversion not done (AD1IF == 0)
            while( IFS1bits.AD1IF == 0 );

            int *p = (int *)(&ADC1BUF0);
            int i;
            int soma=0;

            for(i = 0; i < 4; i++ )
            {
                soma +=  (p[i*4]);
            }
            double media = soma/4;          // Calculate buffer average

            printf("\n");
            printf("Media: %f\n",media);

            // Reset AD1IF
            IFS1bits.AD1IF = 0;
            
            // Calculate voltage amplitude
            value = (media*33)/1023;
            q = value/10;
            r = value%10;

            // Convert voltage amplitude to decimal
        }
        // Send voltage value to displays
        char out = (q << 4 | r);
        send2displays(out);
        printInt(out,2);
        printf("\n");
        // Wait 10 ms (using the core timer)
        delay(10);
    }

    return 0;
}
