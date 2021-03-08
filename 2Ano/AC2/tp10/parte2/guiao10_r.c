#include <detpic32.h>
#include "i2c.h"

void delay(unsigned int ms)
{
    for(; ms > 0; ms--)
    {
        resetCoreTimer();
        while(readCoreTimer() < 20000);
    }
}

int getTemperature(int *temperature){
    int ack;
    // Send Start event
    i2c1_start();
    ack = i2c1_send(ADDR_WR);
    ack += i2c1_send(RTR);
    i2c1_start();
    ack += i2c1_send(ADDR_RD);

    *temperature = (int)i2c1_receive(I2C_NACK);

    // Send Stop event
    i2c1_stop();
    return ack;
}

int main(void){

    i2c1_init(TC74_CLK_FREQ);
    int temperature, error;

    while (1)
    {
        error = getTemperature(&temperature);
        if (error != 0)
        {
            i2c1_stop();
            printStr("Ocorreu um erro!\n Terminando Programa!\n");  
            break; 
        }
        
        printStr("Temperatura: ");
        printInt10(temperature);
        putChar('\n');
        delay(250);
    }
    return 0;
}
