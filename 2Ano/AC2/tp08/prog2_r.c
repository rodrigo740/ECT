#include<detpic32.h>

int baudrate = 115200;

void delay(unsigned int ms)
{
    for(; ms > 0; ms--)
    {
        resetCoreTimer();
        while(readCoreTimer() < 20000);
    }
}

void UARTConfig(void){
    // Configure UART1:
    // 1 - Configure BaudRate Generator
    U1BRG= (PBCLK + 8 * baudrate) / (16 * baudrate) - 1;

    // 2 – Configure number of data bits, parity and number of stop bits (see U1MODE register)
    U1MODEbits.BRGH = 0;    //divisao por 16
    U1MODEbits.PDSEL = 0;   //8 bits sem paridade
    U1MODEbits.STSEL = 0;  //1 stop bit

    // 3 – Enable the trasmitter and receiver modules (see register U1STA)
    U1STAbits.UTXEN = 1;    //transmitter enable
    U1STAbits.URXEN = 1;    //receiver enable

    // 4 – Enable UART1 (see register U1MODE)
    U1MODEbits.ON = 1;
}

void putc(char byte2send){
    while (U1STAbits.UTXBF == 1);
    U1TXREG = byte2send;
}


int main(void)
{
    UARTConfig();
    
    while(1){
        putc('+');
        delay(1000);
    };
    return 0;
} 
