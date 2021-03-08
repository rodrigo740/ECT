#include<detpic32.h>

int count = 0,f=0,flag=0;
char c;

void delay(unsigned int ms)
{
    for(; ms > 0; ms--)
    {
        resetCoreTimer();
        while(readCoreTimer() < 20000);
    }
}

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


int main(void){

    TRISB = (TRISB & 0x80FF);
    TRISDbits.TRISD5 = 0;       // configure RD5-RD6 as outputs
    TRISDbits.TRISD6 = 0;

    T3CONbits.TCKPS = 2;
    PR3 = 62499;
    TMR3 = 0;
    T3CONbits.TON = 1;

    IPC3bits.T3IP = 3;
    IEC0bits.T3IE = 1;
    IFS0bits.T3IF = 0;
    EnableInterrupts();
    while(1);
    return 0;
}

void _int_(12) isr_T3(void){

    c = inkey();

    if (c == '\n')
    {
        flag = 1;
        f = count;
        send2displays((char)f);
    }

    if (flag==0)
    {
        printInt10(count);
        putChar('\r');
    }else
    {
        printf("%d >> %d\r",count, f);
        c == 'e';
    }
    
    count++;
    if (count>99)
    {
        count = 0;
    }
    
    // Reset T3 interrupt flag
    IFS0bits.T3IF = 0;
}
