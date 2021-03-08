#include<stdio.h>
#include<assert.h>

int operacoes = 0;
int deslocamentos = 0;
int comparacoes = 0;

void removeDuplicates(int* a,int* n){

    int i,j,h;

    for ( i = 0; i < *n; i++)
    {
        for ( j = i+1; j < *n; j++)
        {
            comparacoes++;
            if (a[i] == a[j])
            {
                for (h = j; h < (*n)-1; h++)
                {
                    a[h] = a[h+1];
                    deslocamentos++;
                }
                j--;
                (*n)--;
            }
        }
    }


    for ( i = 0; i < *n; i++)
    {
        printf("%d\n",a[i]);
    }
    printf("Comparações: %d\n",comparacoes);
    printf("Deslocamentos: %d\n",deslocamentos);
}




int isValid(int* a,int n){
    assert(n > 1);
    int i;

    for ( i = 0; i < n-1; i++)
    {
        operacoes++;
        if (a[i+1] - a[i] != 1)
        {
            return 0;
        }
    }
    return 1;
}

int main(void){

    int array[] = {3, 3, 3,3,3};

    //int res = isValid(array,10);

    //printf("Resultado: %d\n", res);
    //printf("Operacoes: %d\n", operacoes);

    int n = 5;

    removeDuplicates(array,&n);

    return 0;
}
