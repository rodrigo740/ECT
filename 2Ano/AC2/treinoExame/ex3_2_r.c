#include<detpic32.h>

void delay(unsigned int ms)
{
 for(; ms > 0; ms--)
 {
    resetCoreTimer();
    while(readCoreTimer() < 20000);
 }
} 

int main(void){

    // make RE0-RE3 outputs
    TRISE = TRISE & 0xFFF0;

    while(1){
        printStr("Insira um valor-> ");
        putChar('\n');
        char v = getChar();

        LATE = LATE & 0xFFF0;

        if (v == '0')
        {
            LATEbits.LATE0 = 1;
        }else if (v == '1')
        {
            LATEbits.LATE1 = 1;
        }else if (v == '2')
        {
            LATEbits.LATE2 = 1;
        }else if (v == '3')
        {
            LATEbits.LATE3 = 1;
        }else
        {
            LATE = LATE | 0x000F;
            delay(1000);
            LATE = LATE & 0xFFF0;
        }
    }
    return 0;
}
