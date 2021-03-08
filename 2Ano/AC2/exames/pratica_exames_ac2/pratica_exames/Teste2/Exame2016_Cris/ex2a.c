#include<detpic32.h>
int i = 0, sum = 0, V = 0;
unsigned char toBcd(unsigned char);
void delay(unsigned int);
void send2displays(unsigned char);
int main(void){
    //ADC
    TRISBbits.TRISB14 = 1;
    AD1PCFGbits.PCFG14 = 0;
    AD1CHSbits.CH0SA = 14;
    AD1CON2bits.SMPI = 3;
    AD1CON1bits.SSRC = 7;
    AD1CON1bits.CLRASAM = 1;
    AD1CON3bits.SAMC = 16;
    AD1CON1bits.ON = 1;

    //interrupts ADC
    IPC6bits.AD1IP = 3;
    IEC1bits.AD1IE = 1;
    EnableInterrupts();

    //Timer 3
    PR3 = 49999;
    T3CONbits.TCKPS = 0;
    TMR3 = 0;
    T3CONbits.TON = 1;

    // timer 3 interrupts
    IFS0bits.T3IF = 0;
    IPC3bits.T3IP = 2;
    IEC0bits.T3IE = 1;

    while(1){
        delay(25);
        AD1CON1bits.ASAM = 1;
    }
}

void _int_(27) isr_adc(void){

    int *p = (int *)(&ADC1BUF0);
    for( i = 0; i < 16; i++ ){
        sum += p[i*4];
    }

     V=((sum/4)*33+511)/1023;
     printf("%d\n", V);

     sum = 0;
     IFS1bits.AD1IF = 0;
}

void _int_(12) isr_T3(void){
    send2displays(toBcd(V));
    IFS0bits.T3IF = 0;
}
