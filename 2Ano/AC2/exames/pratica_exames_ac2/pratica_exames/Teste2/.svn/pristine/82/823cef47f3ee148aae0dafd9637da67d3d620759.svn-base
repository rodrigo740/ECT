#include<detpic32.h>
#define BUF_SIZE 64
#define INDEX_MASK (BUF_SIZE - 1)

typedef struct
{
unsigned char data[BUF_SIZE];
unsigned int head;
unsigned int tail;
unsigned int count;
} circularBuffer;
int j = 0, V = 0, sum = 0, i = 0;
volatile circularBuffer buffer;
void buffer_flush(void);
unsigned char toBcd(unsigned char);

void putc(char byte2send) {
    while(U1STAbits.UTXBF == 1);    // wait while UTXBF == 1
    U1TXREG = byte2send;    // Copy byte2send to the UxTXREG register
}

void puts(char *str) {
    int i = 0;
    while (str[i]!='\0') {
        putc(str[i]);
        i++;
    }
}

void buffer_putc(char ch){
    while(buffer.count == BUF_SIZE);
    buffer.data[buffer.tail] = ch;
    buffer.tail = (buffer.tail + 1) & INDEX_MASK;
    buffer.count++;
}

char buffer_getc(char *pchar) {
    if(!buffer.count) return 0;
    *pchar = buffer.data[buffer.head];
    buffer.count--;
    buffer.head = (buffer.head + 1) & INDEX_MASK;
    return 1;
}

int main(void){
    //UART 1
    U1BRG = ((20000000 + 8 * 57600) / (16 * 57600)) - 1;
    U1MODEbits.BRGH = 0;
    U1MODEbits.PDSEL = 2;
    U1MODEbits.STSEL = 0; // 1
    U1STAbits.UTXEN = 1;
    U1STAbits.URXEN = 1;
    U1MODEbits.ON = 1;

    //uart interrupts
    U1STAbits.URXISEL = 00;
    IEC0bits.U1RXIE = 1;
    IPC6bits.U1IP = 3;
    EnableInterrupts();

    //ADC
    TRISBbits.TRISB14 = 1;
    AD1PCFGbits.PCFG14 = 0;
    AD1CHSbits.CH0SA = 14;
    AD1CON2bits.SMPI = 3;
    AD1CON1bits.SSRC = 7;
    AD1CON1bits.CLRASAM = 1;
    AD1CON3bits.SAMC = 16;
    AD1CON1bits.ON = 1;


    //interrupts ADC
    IPC6bits.AD1IP = 3;
    IEC1bits.AD1IE = 1;

    //Timer 3
    PR3 = 62499;
    T3CONbits.TCKPS = 3;
    TMR3 = 0;

    // timer 3 interrupts
    IFS0bits.T3IF = 0;
    IPC3bits.T3IP = 2;
    IEC0bits.T3IE = 1;

    EnableInterrupts();

    buffer_flush();

    while(1);

}

void buffer_flush(void) {
    buffer.count = 0;
    buffer.tail = 0;
    buffer.head = 0;

    int i;
    for (i = 0; i < BUF_SIZE; i++) {
        buffer.data[i] = 0;
    }
}

void _int_(24) isr_uart1(void) {
    if(IFS0bits.U1RXIF){
        char c = U1RXREG;
        if(c == 'r') {
            T3CONbits.TON = 1;

        }else if( c == 'l'){
            T3CONbits.TON = 0;
            char pchar;
            while (buffer_getc(&pchar)) {
                putc(((toBcd(pchar) & 0xF0) >> 4)  + '0');
                putc((toBcd(pchar) & 0x0F)  + '0');
            }
        }
    }

    IFS0bits.U1RXIF = 0;
}


void _int_(27) isr_adc(void){

    int *p = (int *)(&ADC1BUF0);
    for( i = 0; i < 16; i++ ){
        sum += p[i*4];
    }

     V=((sum/4)*33+511)/1023;

     sum = 0;
     IFS1bits.AD1IF = 0;
}

void _int_(12) isr_T3(void){
    AD1CON1bits.ASAM = 1;
    if(++j == 80){
        puts("Valor: ");
        putc('0' + (toBcd(V) & 0xF0)>>4);
        putc('0' + (toBcd(V) & 0x0F));
        j = 0;
        buffer_putc(V);
    }
    IFS0bits.T3IF = 0;
}
