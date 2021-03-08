#include<stdio.h>

int multiplicacoes = 0;
int chamado = 0;

int motzkin(int n){
    if (n <= 1)
    {
        return 1;
    }

    int soma = motzkin(n-1);
        
    for (int k = 0; k <= n-2; k++)
    {
        multiplicacoes++;
        soma += (motzkin(k) * motzkin(n-2-k));
    }
    
    return soma;
}

int dinamicMotzkin(int n){

    int valores[n+1];
    valores[0] = 1;
    valores[1] = 1;

    for (int i = 2; i <= n; i++)
    {
        valores[i] = valores[i-1];

        for (int k = 0; k <= i-2; k++)
        {
            multiplicacoes++;
            valores[i] += valores[k] * valores[i-2-k];
        }
    }

    return valores[n];
}


int main(void){

    int n = 15;

    for (int i = 0; i <= n; i++)
    {
        multiplicacoes = 0;
        int h = motzkin(i);
        printf("n: %d \t %d \t multiplicações: %d\n",i,h,multiplicacoes);
    }

    printf("\n ------------------------------ \n");

    

    for (int i = 0; i <= n; i++)
    {
        multiplicacoes = 0;
        int c = dinamicMotzkin(i);
        printf("n: %d \t %d \t multiplicações: %d\n",i,c,multiplicacoes);
    }
    
    return 0;
}
