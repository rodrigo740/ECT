#include<detpic32.h>

void delay(unsigned int ms){
    for (; ms > 0; ms--)
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

    TRISB = TRISB & 0x80FF; // RB15-RB8 como saidas
    TRISE = TRISE & 0xFFF0; // LED3-LED0 como saidas

    // Configurar os displays como saidas
    TRISDbits.TRISD5 = 0;
    TRISDbits.TRISD6 = 0;

    LATE = LATE | 0x000F;
    int decimasA = 70,decimasB = 70;
    
    char hexA = 0x90;
    char hexB = 0x90;
    send2displays(hexA);
    char c = 'a',old = 'a';

    while (1)
    {
        c = inkey();
        if (c == 'a' || old == 'a')
        {
            printStr("\nJogador A a jogar\n");
            old = 'a';
            c = inkey();
            //delay(1000);
            send2displays(hexA);
            hexA--;
            if (hexA < 0x00)
            {
                hexA = 0x90;
                decimasA -= 10;
                if (decimasA <= 0)
                {
                    printStr("\nJogador B ganhou\n");
                    return 0;
                }
            }
        }else if (c == 'b' || old == 'b')
        {
            printStr("\nJogador B a jogar\n");
            old = 'b';
            c = inkey();
            //delay(1000);
            send2displays(hexB);
            hexB--;
            if (hexB < 0x00)
            {
                hexB = 0x90;
                decimasB -= 10;
                if (decimasB <= 0)
                {
                    printStr("\nJogador A ganhou\n");
                    return 0;
                }
            }
        }
        delay(20);

        if (c == 0)
        {
            c = old;
        }
        old = c;
    }
    
    return 0;
}
