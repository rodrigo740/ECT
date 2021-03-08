#include<detpic32.h>


unsigned int freq = 0;


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

void delay(unsigned int ms){
    for (; ms > 0; ms--)
    {
        resetCoreTimer();
        while (readCoreTimer() < 20000);   
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

    // ISR configs
    IPC6bits.AD1IP = 2; // A/D interrupts priority 
    IEC1bits.AD1IE = 1; // A/D interrupts enabled
    IFS1bits.AD1IF = 0; // Reset AD1IF flag

    TRISDbits.TRISD5 = 0;       // configure RD5-RD6 as outputs
    TRISDbits.TRISD6 = 0;

    T2CONbits.TCKPS = 2;
    PR2 = 62499;
    TMR2 = 0;
    T2CONbits.TON = 1;

    IPC2bits.T2IP = 2;  // Interrupt priority (must be in range [1..6])
    IEC0bits.T2IE = 1;  // Enable timer T3 interrupts
    IFS0bits.T2IF = 0;  // Reset timer T3 interrupt flag

    EnableInterrupts();


    while(1){
        printf("RB3: %d, ",PORTBbits.RB3);
        printf("RB2: %d, ",PORTBbits.RB2);
        printf("RB1: %d, ",PORTBbits.RB1);
        printf("RB0: %d\n",PORTBbits.RB0);

        AD1CON1bits.ASAM = 1;
        //while(IFS1bits.AD1IF == 0);
    }

    return 0;
}

void _int_(27) isr_adc(void){

    freq = 1+(ADC1BUF0/255);
    printInt(freq,10);
    putChar('\n');

    delay((1/freq)*1000);

    IFS1bits.AD1IF = 0;
}

void _int_(8) isr_T2(void){
    send2displays(freq);

    // Reset T2 interrupt flag
    IFS0bits.T2IF = 0;
}
