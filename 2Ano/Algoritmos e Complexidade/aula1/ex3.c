#include <stdio.h>
#include <math.h>

int main(void){
    
    int linhas;

    printf("How many lines: ");
    scanf("%d", &linhas);

    printf("Number  |  Squared Numbers  |  Square Root");

    for (int i = 0; i <= linhas; i++)
    {
        double p = pow(i,2);
        double sqroot = sqrt(i);
        printf("%d  |  %f  | %f\n",i,p,sqroot);
    }
   


    int menor;
    int maior;

    printf("Menor valor do angulo: ");
    scanf("%d",&menor);

    printf("Maior valor do angulo: ");
    scanf("%d",&maior);

    printf("ang sin(ang) cos(ang) \n --- ------------- -------------\n");

    double seno;
    double cosseno;

    for(int i = menor; i <= maior; i=i+5){
        
        seno = sin(i);
        cosseno = cos(i);
        
        printf(" %d %f %f\n",i,seno,cosseno);

    }

    return 0;
    
}