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

    IPC6bits.AD1IP = 3;
    IEC1bits.AD1IE = 1;
    IFS1bits.AD1IF = 0;
    EnableInterrupts();
    AD1CON1bits.ASAM = 1;

    while(1){
        delay(7); // Wait 20 ms using the core timer
        if(i++ == 57) // deve fazer 1 a cada 40 ms
        {
            AD1CON1bits.ASAM = 1;
            i = 0;
        }
        send2displays(toBcd(V & 0x00FF));// Send voltage value to displays
    };
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
