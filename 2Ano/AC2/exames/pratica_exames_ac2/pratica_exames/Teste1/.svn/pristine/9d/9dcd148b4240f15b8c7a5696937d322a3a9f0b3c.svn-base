#include <detpic32.h>

int pow(int, int);

int display7Scodes(){
	int binary[] = {PORTEbits.RE7, PORTEbits.RE6, PORTEbits.RE5, PORTEbits.RE4};
	int i;
	int n=0;

	for(i=3; i>=0; i--){
		if(binary[i]==1){
			n += pow (2, i);
		}
	}

	return n;
}

int pow(int base, int degree)
{
    int result = 1;
    int term = base;
    while (degree)
    {
        if (degree & 1)
            result *= term;
        term *= term;
        degree = degree >> 1;
    }
    return result;
}
