#include<detpic32.h>

void delay(unsigned int ms)
{
    for(; ms > 0; ms--)
    {
        resetCoreTimer();
        while(readCoreTimer() < 20000);
    }
}

void UARTConfig(int baudrate,char paridade,int stopBits){

    // Configure UART1:
    // 1 - Configure BaudRate Generator
    U1BRG= (PBCLK + 8 * baudrate) / (16 * baudrate) - 1;

    // 2 – Configure number of data bits, parity and number of stop bits (see U1MODE register)
    U1MODEbits.BRGH = 0;    //divisao por 16

    if (paridade == 'N')
    {
        U1MODEbits.PDSEL = 0;
    }else if (paridade == 'E')
    {
        U1MODEbits.PDSEL = 1;
    }else if(paridade == 'O')
    {
        U1MODEbits.PDSEL = 2;
    }else
    {
        U1MODEbits.PDSEL = 0; // default value
    }
    

    //Stop bits
    if (stopBits == 1 || stopBits == 2)
    {
        U1MODEbits.STSEL = stopBits-1;
    }else
    {
        U1MODEbits.STSEL = 0;
    }

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

void puts(char *str){
    
    int i = 0;
    while(str[i] != '\0'){
        putc(str[i]);
        i++;
    }
}

int main(void)
{
    UARTConfig(115200,'N',1);
    
    while(1){
        puts("Arquitetura de Computadores II - Universidade de Aveiro\n");
        delay(1000);
    };
    return 0;
} 
