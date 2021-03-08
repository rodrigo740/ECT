void delay(unsigned int n_intervals)
{
volatile unsigned int i;
for(; n_intervals != 0; n_intervals--) for(i = 6000; i != 0; i--)
; }
