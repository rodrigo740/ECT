#include<detpic32.h>
unsigned char toBcd(unsigned char);
void send2displays(unsigned char);
void delay(unsigned int);
int sum = 0, V = 0, i = 0;
int main(void){
    //ADC
    TRISBbits.TRISB14 = 1;
    AD1CON1bits.SSRC = 7;
    AD1CON1bits.CLRASAM = 1;
    AD1CON3bits.SAMC = 16;
    AD1PCFGbits.PCFG4 = 0;
    AD1CHSbits.CH0SA = 14;
    AD1CON2bits.SMPI = 3;
    AD1CON1bits.ON = 1;

    EnableInterrupts();

    // Configurar Timer1 para 40 ms => 25Hz
    PR1 = 49999;
    T1CONbits.TCKPS = 5;
    TMR1 = 0;
    T1CONbits.TON = 1;

    IFS0bits.T1IF = 0;
    IPC1bits.T1IP = 2;
    IEC0bits.T1IE = 1;

    while(1){
        delay(6); // Wait 20 ms using the core timer
        send2displays(toBcd(V & 0x00FF));   // Send voltage value to displays
    };
}

void _int_(4) isr_T1(void){
    AD1CON1bits.ASAM = 1;
    while(IFS1bits.AD1IF == 0 );// Wait while conversion not done (AD1IF == 0)

    int *p = (int *)(&ADC1BUF0);
    for( i = 0; i < 16; i++ ){
        sum += p[i*4];
    }

    V=((sum/4)*33+511)/1023;

    sum = 0;
    IFS1bits.AD1IF = 0;
}