#include <stdio.h>
#include <assert.h>
#include <math.h>

typedef unsigned long long u64;

long double P6(int n){

    assert( n>= 0);
    long double c1,c2;


    if (n == 0)
    {
        return 0;
    }else if (n == 1)
    {
        return 1;
    }

    c1 = 0.24253562503633297352;
    c2 = 1.27019663313689157536;

    return roundl(c1*expl(c2*n));
}

int main(void){

    for (int i = 0; i < 21; i++)
    {
        long double a = P6(i);
        printf("%d    |   %Lf\n",i,a);
    }

    return 0;
}