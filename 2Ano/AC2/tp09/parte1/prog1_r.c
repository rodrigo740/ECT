#include<detpic32.h>

#define DisableUart1RxInterrupt() IEC0bits.U1RXIE = 0
#define EnableUart1RxInterrupt() IEC0bits.U1RXIE = 1
#define DisableUart1TxInterrupt() IEC0bits.U1TXIE = 0
#define EnableUart1TxInterrupt() IEC0bits.U1TXIE = 1

#define BUF_SIZE 8
#define INDEX_MASK (BUF_SIZE - 1)

typedef struct
{
    unsigned char data[BUF_SIZE];
    unsigned int head;
    unsigned int tail;
    unsigned int count;
} circularBuffer; 

volatile circularBuffer txb; // Transmission buffer
volatile circularBuffer rxb; // Reception buffer

void comDrv_flushRx(void)
{
    // Initialize variables of the reception buffer
    rxb.tail = 0;
    rxb.head = 0;
    rxb.count = 0;
    int i;
    for ( i = 0; i < BUF_SIZE; i++)
    {
        rxb.data[i] = 0;
    }  
}

void comDrv_flushTx(void)
{
    // Initialize variables of the transmission buffer
    txb.tail = 0;
    txb.head = 0;
    txb.count = 0;
    int i;
    for ( i = 0; i < BUF_SIZE; i++)
    {
        txb.data[i] = 0;
    }
}




void comDrv_putc(char c)
{
    while(txb.count == BUF_SIZE);
    txb.data[txb.tail] = c;
    txb.tail = (txb.tail + 1) & INDEX_MASK;
    DisableUart1TxInterrupt();
    txb.count++;
    EnableUart1TxInterrupt();
}

void comDrv_puts(char *s)
{
    while ((*s) != '\0')
    {
        comDrv_putc(*s);
        (s)++;
    }   
}

void _int_(24) isr_UART1(void){
    // if U1TXIF is set
    if (IFS0bits.U1TXIF)
    {
        // if "count" variable (transmission buffer, txb) is greater than 0
        if(txb.count>0)
        {
            // Copy character pointed by "head" to U1TXREG register
            U1TXREG= txb.data[txb.head];
            // Increment "head" variable (mod BUF_SIZE)
            txb.head = (txb.head+1) & INDEX_MASK;
            // Decrement "count" variable
            txb.count--;    
        }
        // if "count" variable is 0 then
        if (txb.count == 0)
        {
            DisableUart1TxInterrupt();
        }
        // Reset UART1 TX interrupt flag
        IFS0bits.U1TXIF = 0;
    } 
}

void comDrv_config(int baudrate,char paridade,int stopBits){

    // Configure UART1:
    // 1 - Configure BaudRate Generator
    U1BRG = (PBCLK + 8 * baudrate) / (16 * baudrate) - 1;

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

    IEC0bits.U1RXIE = 1;
    IPC6bits.U1IP = 2;
    IFS0bits.U1RXIF = 0;
    IFS0bits.U1TXIF = 0;

    // 4 – Enable UART1 (see register U1MODE)
    U1MODEbits.ON = 1;
}

int main(void)
{
    comDrv_config(115200,'N',1); // default "pterm" parameters with TX and RX interrupts disabled
    comDrv_flushRx();
    comDrv_flushTx();
    EnableInterrupts();
    while(1)
        comDrv_puts("Teste do bloco de transmissao do device driver!...");

    return 0;
} 
