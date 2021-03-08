#include<detpic32.h>

void delay(unsigned int ms){
    for ( ; ms > 0; ms--)
    {
        resetCoreTimer();
        while (readCoreTimer()<20000);
    }
    
}

int main(void){

    int samples = 1;
    TRISB = TRISB & 0x000F;

    //Configuraçao do modulo A/D
    TRISBbits.TRISB4 = 1;
    AD1PCFGbits.PCFG4 = 0;
    AD1CON1bits.SSRC = 7;
    AD1CON1bits.CLRASAM = 1;
    AD1CON3bits.SAMC = 16;
    AD1CON2bits.SMPI = samples-1;
    AD1CHSbits.CH0SA = 4;
    AD1CON1bits.ON = 1;


    while(1){

        printf("RB3: %d, ",PORTBbits.RB3);
        printf("RB2: %d, ",PORTBbits.RB2);
        printf("RB1: %d, ",PORTBbits.RB1);
        printf("RB0: %d\n",PORTBbits.RB0);

        //Começar a conversao
        AD1CON1bits.ASAM = 1;
        while(IFS1bits.AD1IF == 0);

        int freq = 1+(ADC1BUF0/255);

        delay((1/freq)*1000);
        
        //Reset do AD1IF
        IFS1bits.AD1IF = 0;        
    }

    return 0;
}
