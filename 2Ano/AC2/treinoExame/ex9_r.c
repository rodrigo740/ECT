#include<detpic32.h>

void delay(unsigned int ms){
    for (; ms > 0; ms--)
    {
        resetCoreTimer();
        while (readCoreTimer() < 20000);
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

void putc(char c){
    while(U1STAbits.UTXBF == 1);
    U1TXREG = c;
}

void putS(char *str){
    int i = 0;
    while (str[i] != '\0')
    {
        putc(str[i]);
        i++;
    }
    
}

void UARTConfig(void){
    U1BRG = (PBCLK + 8 *1200)/(16 *1200) - 1;
    U1MODEbits.BRGH = 0;
    U1MODEbits.PDSEL = 0;
    U1MODEbits.STSEL = 0;
    U1STAbits.UTXEN = 1;
    U1STAbits.URXEN = 1;

    U1MODEbits.ON = 1;
}

int main(void){

    UARTConfig();

    TRISB = TRISB & 0x00FF;
    
    while(1){
        putS("RB3: ");
        putc(PORTBbits.RB3 + '0');
        putc('\n');
        putS("RB2: ");
        putc(PORTBbits.RB2 + '0');
        putc('\n');
        putS("RB1: ");
        putc(PORTBbits.RB1 + '0');
        putc('\n');
        putS("RB0: ");
        putc(PORTBbits.RB0 + '0');
        putc('\n');

        send2displays(PORTBbits.RB0 + '0');
        delay(1000);

        
    }

    return 0;
}
