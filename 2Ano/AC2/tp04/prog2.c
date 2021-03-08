#include <detpic32.h>

void delay(unsigned int ms)
{
    for(; ms > 0; ms--)
    {
        resetCoreTimer();
        while(readCoreTimer() < 20000);
    }
}

int main(void){

    TRISEbits.TRISE0 = 0;
    TRISEbits.TRISE1 = 0;
    TRISEbits.TRISE2 = 0;
    TRISEbits.TRISE3 = 0;

    LATE = LATE & 0xFFF0;


    while(1){

        LATE = LATE + 1;
        delay(500);

        if (LATE == 16)
        {
            LATE = 0;
        }
    }
}