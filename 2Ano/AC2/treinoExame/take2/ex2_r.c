#include<detpic32.h>

void delay(unsigned int ms){
    for (; ms > 0; ms--)
    {
        resetCoreTimer();
        while (readCoreTimer()<20000);
    }
}

int main(void){

    printStr("\nRodrigo Lopes Martins\n nMec:93264\n");

    TRISE = TRISE & 0xFFF0;
    
    while (1)
    {
        printStr("\nPressione uma tecla-> ");
        char c = getChar();

        if (c == '0')
        {
            LATE = LATE & 0xFFF0;
            LATEbits.LATE0 = 1;   
        }else if (c == '1')
        {
            LATE = LATE & 0xFFF0;
            LATEbits.LATE1 = 1; 
        }else if (c == '2')
        {
            LATE = LATE & 0xFFF0;
            LATEbits.LATE2 = 1; 
        }else if (c == '3')
        {
            LATE = LATE & 0xFFF0;
            LATEbits.LATE3 = 1; 
        }else{
            LATE = LATE & 0xFFF0;
            LATE = LATE | 0x000F;
            delay(1000);
            LATE = LATE & 0xFFF0;
        }
    }

    return 0;
}
