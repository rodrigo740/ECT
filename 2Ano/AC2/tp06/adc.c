#include<detpic32.h>

volatile unsigned char voltage = 0;

void _int_(27) isr_adc(void){

    int i;
    double media = 0;

    int *p = (int *) (&ADC1BUF0);

    for ( i = 0; i < 8; i++)
    {
        media += p[i*4];
    }
    media /= 8;

    int v = media * 33 /1023;

    int q = v/10;
    int r = v%10;

    voltage = (q << 4 | r);
    IFS1bits.AD1IF = 0;
    
}




void _int_(27) isr_adc(void){


    int i;
    double media = 0;

    int p* = (int *) (&AD1BUF0);

    for ( i = 0; i < 8; i++)
    {
        media += p[i*4];
    }
    media /= 8;

    int v = media *33 /1023;

    int q = v/10;
    int r = v%10;

    voltage = (q << 4 | r);
    IFS1bits.AD1IF = 0;

    TRISB = TRISB & 0x80FF;
    
}











void _int_(27) isr_adc(void){


    int i;
    double media = 0;
    int p* = (int *)(&ADC1BUF0);

    for (i = 0; i < 8; i++)
    {
        media += p[i*4];
    }

    media /= 8;

    int v = media *33 / 1023;

    int q = v/10;
    int r = v%10;

    voltage = (q <<4 | r);
    IFS1bits.AD1IF = 0;
}









void send2displays(unsigned char value){
    static const char display7Scodes[] = {};
    unsigned char displayFlag = 0;
    unsigned char dh = display7Scodes[value >> 4];
    unsigned char dl = display7Scodes[value & 0x0F];

    if (displayFlag)
    {
        LATDbits.LATD5 = 1;
        LATDbits.LATD6 = 0;

        LATB = (dh << 8 ) | (LATB & 0x00FF);



        if (value % 2 != 0)
        {
            LATDbits.LATD15 = 1;
        }
        
    }else
    {
        LATDbits.LATD5 = 0;
        LATDbits.LATD6 = 1;

        LATB = (dh << 8 ) | (LATB & 0x00FF);



        if (value % 2 == 0)
        {
            LATDbits.LATD15 = 1;
        }
    }
    
    displayFlag != displayFlag;
}