#include <detpic32.h>

void putc(char);
void send2display(unsigned char);

static const unsigned char val[] = {0x77,0x41,0x3b,0x6b,0x4d,0x6e,0x7e,0x43,0x7f,0x6f,0x5f,0x7c,0x36,0x79,0x3e,0x1e};
unsigned char v = 0x0;
unsigned int show = 1;

int main(void){
    
    // Digital ports configuration
    
    TRISE = TRISE | 0xF0;                           // RE7-4 configured as input
    TRISB = TRISB & 0xFC00;                         // RB9-0 configured as output

    // UART1 configuration

    U1BRG = ((PBCLK +8*115200)/(16*115200))-1;      // 115200 baudrate
    U1MODEbits.PDSEL = 0;                           // 8 bits, no parity
    U1MODEbits.STSEL = 0;                           // 1 stop bit
    U1STAbits.URXEN = 1;                            // Receiver enable
    U1STAbits.URXISEL = 0;                          // Receiver interrupt mode
    IEC0bits.U1RXIE = 1;                            // Enable receiver interrupts
    IPC6bits.U1IP = 4;                              // Receiver interrupt priority 
    IFS0bits.U1RXIF = 0;                            // Reset receiver interrupt flag
    U1MODEbits.ON = 1;                              // Enable UART
    
    // Timer 3 configuration
    
    T3CONbits.TCKPS = 7;                            // 1:256 prescaler
    PR3 = PBCLK/256/2-1;                            // 2Hz frequency
    TMR3 = 0;                                       // Reset T3 counter register    
    IEC0bits.T3IE = 1;                              // Enable T3 interrupts
    IPC3bits.T3IP = 5;                              // T3 interrupt priority              
    IFS0bits.T3IF = 0;                              // Reset T3 interupt flag
    T3CONbits.TON = 1;                              // Enable T3

    EnableInterrupts();                             // Global interrupt enable

    while(1);
        
    return 0;
}

void putc(char c){
    while(U1STAbits.UTXBF);                         // Wait while transmitter buffer full
    U1TXREG = c;                                    // Copy char to transmitter register
}

void send2display(unsigned char value){
    static int changeFlag = 0;
    LATBbits.LATB9 = 0;
    LATBbits.LATB8 = 0;
    LATB = LATB & 0xFF00;
    if(show || changeFlag){
        LATBbits.LATB9 = 1;
        LATB |= val[value]; 
    }
    changeFlag = !changeFlag;
}

void _int_(_UART_1_VECTOR) isr_uart1(void){
    if(IFS0bits.U1RXIF){
        char reg = U1RXREG; 
        if(reg == 'p'){
            show = !show;                           // Toggle show flag
        }else{
            unsigned char c = (reg+((PORTE & 0xF0) >> 4)) % 16;
            v = c;                                  // Update value
            c = c > 0x9 ? 'A'+(c - 0xA) : '0'+ c ;  // Convert RE7-4 to hexadecimal char 
            putc(c);                                // Send RE7-4 to serial port
            putc('\n');
        }
        IFS0bits.U1RXIF = 0;                        // Reset receiver interrupt flag
    }
}

void _int_(_TIMER_3_VECTOR) isr_t3(void){
    send2display(v);                                // Send value to display
    IFS0bits.T3IF = 0;                              // Reset T3 interupt flag
}
