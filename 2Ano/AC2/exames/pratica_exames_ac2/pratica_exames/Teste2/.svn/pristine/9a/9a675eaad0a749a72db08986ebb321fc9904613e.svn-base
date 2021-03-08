#include<detpic32.h>
unsigned char toBcd(unsigned char);
void delay(unsigned int);
void setPWM(unsigned int);

int i = 0, sum = 0, V = 0, j = 0;
static const unsigned char codes[] = {0x77, 0x41, 0x3B, 0x6B, 0x4D, 0x6E, 0x7E, 0x43, 0x7F, 0x6F, 0x5F, 0x7C, 0x36, 0x79, 0x3E, 0x1E};

int main(void){
    // Configurar ADC
    TRISBbits.TRISB14 = 1;
    AD1PCFGbits.PCFG14 = 0;
    AD1CHSbits.CH0SA = 14;
    AD1CON2bits.SMPI = 15;
    AD1CON1bits.SSRC = 7;
    AD1CON1bits.CLRASAM = 1;
    AD1CON3bits.SAMC = 16;
    AD1CON1bits.ON = 1;

    IFS1bits.AD1IF = 0;
    IPC6bits.AD1IP = 3;
    IEC1bits.AD1IE = 1;
    EnableInterrupts();

    //timer 3
    PR3 = 41666;
    T3CONbits.TCKPS = 2;
    TMR3 = 0;
    T3CONbits.TON = 1;

    //timer interrupts
    IFS0bits.T3IF = 0;
    IPC3bits.T3IP = 2;
    IEC0bits.T3IE = 1;

    //OC1
    OC1CONbits.OCM = 6;
    OC1CONbits.OCTSEL = 1;
    OC1CONbits.ON = 1;

    TRISB = TRISB & 0xFC00;

    while(1){
        delay(100);
        AD1CON1bits.ASAM = 1;
    };
}

void _int_(27) isr_adc(void){
    int *p = (int *)(&ADC1BUF0);
    for( i = 0; i < 16; i++ ){
        sum += p[i*4];
    }

    V=((sum/16)*15+511)/1023;
    printf("%d\n", V);
    sum = 0;
    IFS1bits.AD1IF = 0;
}

void _int_(12) isr_T3(void){
    LATBbits.LATB9 = 0;
    LATB = (LATB & 0xFF00) | codes[V];
    LATBbits.LATB8 = 1;

    setPWM(75);

    IFS0bits.T1IF = 0;
}

void setPWM(unsigned int dutyCycle) {
    OC1RS = ((PR2 + 1) * dutyCycle)/100;
}
