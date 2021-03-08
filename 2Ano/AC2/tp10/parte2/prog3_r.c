#include<detpic32.h>
#include "i2c.h"

volatile unsigned int temperature = 0;

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

void configureAll(){

    // hexDisp configs
    LATB = LATB & 0x00FF;
    TRISB = TRISB & 0x00FF;
    TRISDbits.TRISD5 = 0;
    TRISDbits.TRISD6 = 0;

    //ADC
    TRISBbits.TRISB4 = 1;
    AD1PCFGbits.PCFG4 = 0;
    AD1CON1bits.SSRC = 7;
    AD1CON1bits.CLRASAM = 1;
    AD1CHSbits.CH0SA = 4;
    AD1CON2bits.SMPI = 7;
    AD1CON3bits.SAMC = 16;
    AD1CON1bits.ON = 1;
    IPC6bits.AD1IP = 2;
    IEC1bits.AD1IE = 1;
    IFS1bits.AD1IF = 0;

    //T3
    TRISDbits.TRISD5 = 0;
    T3CONbits.TCKPS = 2;
    PR3 = 49999;
    TMR3 = 0; // Reset timer T3 count register

    IPC3bits.T3IP = 3; // Interrupt priority (must be in range [1..6])
    IEC0bits.T3IE = 1; // Enable timer T3 interrupts
    IFS0bits.T3IF = 0; // Reset timer T3 interrupt flag 
    T3CONbits.TON = 1; // Enable timer T3 (must be the last command of the timer configuration sequence

    //T1
    TRISDbits.TRISD5 = 0;
    T1CONbits.TCKPS = 3;
    PR1 = 19530;
    TMR1 = 0;

    IPC1bits.T1IP = 2;
    IEC0bits.T1IE = 1;
    IFS0bits.T1IF = 0;
    T1CONbits.TON = 1;

    EnableInterrupts();
}

int getTemperature(int *temperature){
    int ack;
    // Send Start event
    i2c1_start();
    ack = i2c1_send(ADDR_WR);
    ack += i2c1_send(RTR);
    i2c1_start();
    ack += i2c1_send(ADDR_RD);

    *temperature = (int)i2c1_receive(I2C_NACK);

    // Send Stop event
    i2c1_stop();
    return ack;
}


int main(void)
{
    configureAll();
    i2c1_init(TC74_CLK_FREQ);

    while(1){

        if (PORTBbits.RB0 == 1 && PORTBbits.RB1 == 1)
        {
            IEC0bits.T1IE = 1;
        }else
        {
            IEC0bits.T1IE = 0;
        }
    }
    return 0;
}


void _int_(27) isr_adc(void)
{
    int error;
    error = getTemperature(&temperature);
    if (error != 0)
    {
        i2c1_stop();
        printStr("Ocorreu um erro!\n Terminando Programa!\n");  
        exit;
    }

    IFS1bits.AD1IF = 0;
}

void _int_(4) isr_T1(void){
    
    AD1CON1bits.ASAM = 1;
    // Reset T1IF flag
    IFS0bits.T1IF = 0;
}
void _int_(12) isr_T3(void){
    
    printStr("Temperatura: ");
    printInt10(temperature);
    putChar('\n');

    send2displays(temperature);

    // Reset T3IF flag
    IFS0bits.T3IF = 0;
}
