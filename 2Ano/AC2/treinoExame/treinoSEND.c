#include<detpic32.h>

void send2displays(unsigned char value){
    static const char displays7Scodes[] =;
    static char displayFlag = 0;
    unsigned char dh = displays7Scodes[value >> 4];
    unsigned char dl = displays7Scodes[value & 0x0F];

    if (displayFlag)
    {
        LATDbits.LATD5 = 1;
        LATDbits.LATD6 = 0;
        LATB = (dh << 8) | (LATB & 0x00FF);
        if (value % 2 == 0)
        {
            LATBbits.LATB15 = 1;
        }
    }else
    {
        LATDbits.LATD5 = 0;
        LATDbits.LATD6 = 1;
        LATB = (dh << 8) | (LATB & 0x00FF);
        if (value % 2 == 0)
        {
            LATBbits.LATB15 = 1;
        }
    }
    
    displayFlag = !displayFlag;
}










void send2displays(unsigned char value){
    static char const display7Scodes = {};
    static char displayFlag = 0;

    unsigned char dh = display7Scodes[value >> 4];
    unsigned char dh = display7Scodes[value & 0x0F];

    if (displayFlag)
    {
        LATDbits.LATD5 = 1;
        LATDbits.LATD6 = 0;
        LATB = (dh << 8) | (LATB & 0x00FF);
        if (value % 2 == 0)
        {
            LATBbits.LATB15 = 1;
        }
    }else
    {
        LATDbits.LATD5 = 0;
        LATDbits.LATD6 = 1;
        LATB = (dh << 8) | (LATB & 0x00FF);
        if (value % 2 == 0)
        {
            LATBbits.LATB15 = 1;
        }
    }
    
    displayFlag = !displayFlag;
}




void send2displays(unsigned char value){
    static const char display7Scode = {};
    static char displayFlag = 0;
    unsigned char dh = display7Scode[value >> 4];
    unsigned char dl = display7Scode[value & 0x0F];

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
        LATDbits.LATD5 = 1;
        LATDbits.LATD6 = 0;
        LATB = (dh << 8) | (LATB & 0x00FF);
        if (value % 2 == 0)
        {
            LATBbits.LATB15 = 1;
        }
    }

    displayFlag = !displayFlag;
}




void send2displays(unsigned char value){
    static const display7Scodes = {};
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
            LATDbits.LATD15 = 1;
        }
    }else
    {
        LATDbits.LATD5 = 0;
        LATDbits.LATD6 = 1;
        LATB = (dh << 8) | (LATB & 0x00FF);
        if (value % 2 == 0)
        {
            LATDbits.LATD15 = 1;
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