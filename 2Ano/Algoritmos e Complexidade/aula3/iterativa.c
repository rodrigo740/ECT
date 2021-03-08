#include <stdio.h>
#include <assert.h>

typedef unsigned long long u64;

u64 P2(int n){

    assert( n>= 0);
    u64 a=0,b=1,c;


    if (n == 0)
    {
        return 0;
    }else if (n == 1)
    {
        return 1;
    }

    for (int i = 2; i <= n; i++)
    {
        c = 3*b + 2*a;
        a = b;
        b = c;
    }

    return c;
}

int main(void){

    for (int i = 0; i < 21; i++)
    {
        u64 a = P2(i);
        printf("%d    |   %llu\n",i,a);
    }

    return 0;
}