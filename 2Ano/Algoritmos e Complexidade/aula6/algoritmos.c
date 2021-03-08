#include<stdio.h>

int contagens = 0;


double t1(int n){
    if (n == 0)
    {
        return 0;
    }

    contagens++;
    int r = t1(n/3) + n; 
    return r;
}

double t2(int n){

    if (n==0 || n==1 || n==2)
    {
        return n;
    }

    contagens+=2;
    int r = t2(n/3) + t2((n+2)/3) + n;
    return r;
}

double t3(int n){

    if (n <= 2)
    {
        return n;
    }

    if (n%3 == 0)
    {
        contagens++;
        int r = 2*t3(n/3)+n;
        return r;
    }

    contagens+=2;
    int r = t3(n/3) + t3((n+2)/3) + n;
    return r;
}



int main(void){

    for (int i = 0; i <= 28; i++)
    {
        double result1 = t1(i);
        printf("i:%d    %f  Contagens: %d\n",i,result1,contagens);
        contagens = 0;
    }

    printf("\n");
    printf("\n");
    printf("\n");
    

    for (int i = 0; i <= 28; i++)
    {
        double result2 = t2(i);
        printf("i:%d    %f  Contagens: %d\n",i,result2,contagens);
        contagens = 0;
    }


    printf("\n");
    printf("\n");
    printf("\n");

    for (int i = 0; i <= 28; i++)
    {
        double result3 = t3(i);
        printf("i:%d    %f  Contagens: %d\n",i,result3,contagens);
        contagens = 0;
    }

    

   

    

    return 0;
}