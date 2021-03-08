#include<detpic32.h>
void send2displays(unsigned char);
void delay(unsigned int);
int V = 0;
int sum = 0;
int i = 0;

unsigned char toBcd(unsigned char value) {
return ((value / 10) << 4) + (value % 10); }


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
    PR1 = 49999;
    T1CONbits.TCKPS = 0;
    TMR1 = 0;
    T1CONbits.TON = 1;

    // timer interrupts
    IFS0bits.T1IF = 0;
    IPC1bits.T1IP = 2;
    IEC0bits.T1IE = 1;

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

void _int_(4) isr_T1(void){
    send2displays(toBcd(V));
    IFS0bits.T1IF = 0;
}
