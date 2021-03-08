#include<detpic32.h>

char hex = 0x00;

void delay(unsigned int ms){
    for (; ms > 0; ms--)
    {
        resetCoreTimer();
        while (readCoreTimer()<20000);
    }
}

void send2displays(unsigned char value){
    static const char display7Scodes[] = {0x3F, 0x06, 0x5B, 0x4F, 0x66, 0x6D, 0x7D, 0x07, 0x7F, 0x6F, 0x77, 0x7C, 0x39, 0x5E, 0x79, 0x71};
    static char displayFlag = 0;
    unsigned char dh = display7Scodes[value >> 4];
    unsigned char dl = display7Scodes[value & 0x0F];

    if (displayFlag)
    {
        LATDbits.LATD5 = 1;
        LATDbits.LATD6 = 0;
        LATB = (dh << 8) | (LATB & 0x00FF);
        if (value % 2 != 0)
        {
            LATBbits.LATB15 = 1;
        }
    }else
    {
        LATDbits.LATD5 = 0;
        LATDbits.LATD6 = 1;
        LATB = (dl << 8) | (LATB & 0x00FF);
        if (value % 2 != 0)
        {
            LATBbits.LATB15 = 1;
        }
    }
    
    displayFlag = !displayFlag;
}

int main(void){

    TRISB = TRISB & 0x80FF; // RB15-RB8 como saidas
    TRISE = TRISE & 0xFFF0; // LED3-LED0 como saidas

    // Configurar os displays como saidas
    TRISDbits.TRISD5 = 0;
    TRISDbits.TRISD6 = 0;

    LATE = LATE | 0x000F;

    


    T3CONbits.TCKPS = 256;
    PR3 = 39062;
    TMR3 = 0;
    T3CONbits.TON = 1;

    EnableInterrupts();

    IPC3bits.T3IP = 3;
    IEC0bits.T3IE = 1;
    IFS0bits.T3IF = 0;

    while (1);

    return 0;
}



void _int_(12) isrT3(void){

    hex++;
    printStr("A lucia é uma gaja muita fixe que vai passar a tudo\n");
    send2displays(hex);
    delay(20);
    IFS0bits.T3IF = 0;
}
