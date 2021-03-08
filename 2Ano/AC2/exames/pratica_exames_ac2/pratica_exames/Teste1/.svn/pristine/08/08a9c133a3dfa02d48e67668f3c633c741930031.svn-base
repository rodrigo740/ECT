#include <detpic32.h>

int main(void){
    int i;
    TRISBbits.TRISB14 = 1; // RB4 digital output disconnected
    AD1PCFGbits.PCFG14 = 0; // RB4 configured as analog input (AN4)

    AD1CHSbits.CH0SA = 14; // Selects AN4 as input for the A/D converter

    AD1CON2bits.SMPI = 3; // 4 samples will be converted and stored

    AD1CON1bits.SSRC = 7;
    AD1CON1bits.CLRASAM = 1; // Stop conversions when the 1st A/D converter
    AD1CON3bits.SAMC = 16;
    AD1CON1bits.ON = 1;

    AD1CON1bits.ASAM = 1; // Start conversion
    while( IFS1bits.AD1IF == 0 ); // Wait while conversion not done
    int *p = (int *)(&ADC1BUF0);
    for( i = 0; i < 16; i++ )
    {
      printInt( p[i*4], 10 );
      printStr("\n");
    }

    IFS1bits.AD1IF = 0;

    return 0;

}
