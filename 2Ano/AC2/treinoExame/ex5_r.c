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
    int i;
    char c = '0',f=0;

    while (1)
    {
        c = inkey();
        for ( i = 0; i < 100; i++)
        {
            printInt10(i);
            putChar('\r');
            if (c == '0')
            {
                delay(1/(10*0.001));
            }else if (c == '1')
            {
                delay(1/(20*0.001));
            }else if (c == '2')
            {
                delay(1/(30*0.001));
            }else if (c == '3')
            {
                delay(1/(40*0.001));
            }else if (c == 4)
            {
                delay(1/(50*0.001));
            }else if (c == '\n')
            {
                f = i;
                printf("%d >> %d\r",i, f);
            }
        }
    }
    return 0;
}
