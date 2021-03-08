#include <stdio.h>
#include <assert.h>

typedef unsigned long long u64;

u64 P1(int n){

    assert( n>= 0);

    if (n == 0)
    {
        return 0;
    }else if (n == 1)
    {
        return 1;
    }else
    {
        return 3*P1(n-1)+2*P1(n-2);
    }
}

int main(void){

    for (int i = 0; i < 21; i++)
    {
        u64 a = P1(i);
        printf("%d    |   %ld\n",i,a);
    }

    return 0;
}