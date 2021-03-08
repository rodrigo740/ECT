#include<detpic32.h>

int main(void)
{
    // Configure UART1:
    // 1 - Configure BaudRate Generator
    U1BRG = (PBCLK + 8 * 115200) / (16 * 115200) -1;

    // 2 – Configure number of data bits, parity and number of stop bits (see U1MODE register)
    U1MODEbits.BRGH = 0;    //divisao por 16
    U1MODEbits.PDSEL = 0;   //8 bits sem paridade
    U1MODEbits.STSEL = 0;  //1 stop bit

    // 3 – Enable the trasmitter and receiver modules (see register U1STA)
    U1STAbits.UTXEN = 1;    //transmitter enable
    U1STAbits.URXEN = 1;    //receiver enable

    // 4 – Enable UART1 (see register U1MODE)
    U1MODEbits.ON = 1;

    return 0;
} 
