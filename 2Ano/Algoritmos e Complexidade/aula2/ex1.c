#include <stdio.h>

int contadorF1,contadorF2,contadorF3,contadorF4;

unsigned int f1(unsigned int n){

    unsigned int i, j, r = 0;

    for (i = 1; i <= n; i++){
        for (j = 1; j <= n; j++){
            r += 1;
            contadorF1 += 1;
        }
    }
    return r;
}

unsigned int f2 (unsigned int n)
{
    unsigned int i, j, r = 0;

    for (i = 1; i <= n; i++){
        for (j = 1; j <= i; j++){
            r += 1;
            contadorF2 += 1;
        }
    }
    return r;
}

unsigned int f3 (unsigned int n)
{
    unsigned int i, j, r = 0;

    for (i = 1; i <= n; i++){
        for (j = i; j <= n; j++){
            r += j;
            contadorF3 += 1;
        }
    }
    return r;
}

unsigned int f4 (unsigned int n)
{
    unsigned int i, j, r = 0;

    for (i = 1; i <= n; i++){
        for (j = i; j >= 1; j /= 10){
            r += i;
            contadorF4 += 1;
        }
    }
    return r;
}


int main(void){

    for ( int i = 1; i <= 15; i++)
    {
        contadorF1 = 0;
        int resultado = f1(i);

        printf("%d  %d  %d\n",i,resultado,contadorF1);
    }

    puts("----------------------------------------------");

    for ( int i = 1; i <= 15; i++)
    {
        contadorF2 = 0;
        int resultado = f2(i);

        printf("%d  %d  %d\n",i,resultado,contadorF2);
    }

    puts("----------------------------------------------");

    for ( int i = 1; i <= 15; i++)
    {
        contadorF3 = 0;
        int resultado = f3(i);

        printf("%d  %d  %d\n",i,resultado,contadorF3);
    }

    puts("----------------------------------------------");

    for ( int i = 1; i <= 15; i++)
    {
        contadorF4 = 0;
        int resultado = f4(i);

        printf("%d  %d  %d\n",i,resultado,contadorF4);
    }
    
    return 0;
}