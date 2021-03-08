#include<detpic32.h>
void send2displays(unsigned char);
void delay(unsigned int);
int V = 0;
int sum = 0;
int i = 0;

unsigned char toBcd(unsigned char value) {
return ((value / 10) << 4) + (value % 10); }

void setPWM(unsigned int dutyCycle) {
// duty_cycle must be in the range [0, 100]
OC1RS = ((PR3 + 1) * dutyCycle)/100;
}

int main(void){

    //ADC
    TRISBbits.TRISB14 = 1;
    AD1PCFGbits.PCFG14 = 0;
    AD1CHSbits.CH0SA = 14;
    AD1CON2bits.SMPI = 15;
    AD1CON1bits.SSRC = 7;
    AD1CON1bits.CLRASAM = 1;
    AD1CON3bits.SAMC = 16;
    AD1CON1bits.ON = 1;
    AD1CON1bits.ASAM = 1;

    //Timer1
    PR3 = 49999;
    T3CONbits.TCKPS = 0;
    TMR3 = 0;
    T3CONbits.TON = 1;

    // timer interrupts
    IFS0bits.T3IF = 0;
    IPC3bits.T3IP = 2;
    IEC0bits.T3IE = 1;

    //OC1
    OC1CONbits.OCM = 6;
    OC1CONbits.OCTSEL =1;
    OC1CONbits.ON = 1;

    EnableInterrupts();

    while(1){
        AD1CON1bits.ASAM = 1;
        while(IFS1bits.AD1IF == 0);
        delay(20);

        int *p = (int *)(&ADC1BUF0);
        for( i = 0; i < 16; i++ ){
            sum += p[i*4];
        }

        V = ((sum/16)*70+511)/1023;
        printf("%d\n", V);
        sum = 0;

        IFS1bits.AD1IF = 0;
    }
}

void _int_(12) isr_T1(void){
    send2displays(toBcd(V));
    setPWM(25);
    IFS0bits.T1IF = 0;
}
