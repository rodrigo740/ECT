#include <stdio.h>
#include <assert.h>

int count = 0;
int comp = 0;

int funcao1(int a[], int size){

    assert(size >1);

    for (int i = 1; i < size; i++)
    {
        comp++;
        if (a[i] != a[i-1])
        {
            count++;
        }   
    }

    printf("Comparacoes: %d\n",comp);

    return count;
}

int funcao2(int a[], int size){

    assert(size > 1);

    int maior = 0;
    int pos = 0;

    for (int k = 1; k < size; k++)
    {
        for (int j = 0; j < k; j++)
        {
            comp++;
            if (a[k] > a[j])
            {
                count++;
            }
            
        }
        if (count > maior)
        {
            pos = k;
            maior = count;
        }
        count = 0;
        
    }

    printf("Comparacoes: %d\n",comp);

    if (maior == 0)
    {
        return (-1);
    }
    
    return pos;
}






int main(void){

    int array[] = {1,9,2,8,3};
    //int arrayb[] = {2,2,2,2,2,2,2,2,2,2};

    int b = funcao2(array,5);

    printf("Contagem: %d\n",b);

    return 0;
}