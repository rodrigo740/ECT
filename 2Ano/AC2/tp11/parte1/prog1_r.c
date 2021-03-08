#include<detpic32.h>

#define WRITE 0x02
#define RDSR 0x05
#define READ 0x03
#define WRSR 0x01
#define WRDI 0x04
#define WREN 0x06
#define EEPROM_CLOCK 500000 

void spi2_setClock(unsigned clock_freq){
    SPI2BRG = (PBCLK + clock_freq) / (2 * clock_freq) - 1;
}

void spi2_init(void)
{
    volatile char trash;
    // Disable SPI2 module
    SPI2CONbits.ON = 0;
    // Configure clock idle state as logic level 0 (see introduction)
    SPI2CONbits.CKP = 0;
    // Configure the clock active transition: from active state to idle state (1 > 0)
    SPI2CONbits.CKE = 1;
    // Configure SPI data input sample phase bit (middle of data output time)
    SPI2CONbits.SMP = 0;
    // Configure word length (8 bits)
    SPI2CONbits.MODE32 = 0;
    SPI2CONbits.MODE16 = 0;
    // Enable Enhanced buffer mode (this allows the usage of FIFOs RX,TX)
    SPI2CONbits.ENHBUF = 1;
    // Enable slave select support (Master Mode Slave Select)
    SPI2CONbits.MSSEN = 1;
    // Enable master mode
    SPI2CONbits.MSTEN = 1;
    // Clear RX FIFO:
    while(SPI2STATbits.SPIRBE == 0){ // while RX FIFO not empty read
        trash = SPI2BUF; // FIFO (discard data)
    }
    // Clear overflow error flag
    SPI2STATbits.SPIROV = 0;
    // Enable SPI2 module
    SPI2CONbits.ON = 1;
}

char eeprom_readStatus(void)
{
    volatile char trash;
    // Clear RX FIFO (discard all data in the reception FIFO)
     while (SPI2STATbits.SPIRBE == 0){
        trash = SPI2BUF;
    }
    // Clear overflow error flag bit
    SPI2STATbits.SPIROV = 0;
    SPI2BUF = RDSR; // Send RDSR command
    SPI2BUF = 0; // Send anything so that EEPROM clocks data into SO
    while(SPI2STATbits.SPIBUSY); // wait while SPI module is working
    trash = SPI2BUF; // First char received is garbage (received while sending command)
    return SPI2BUF; // Second received character is the STATUS value
}

void eeprom_writeStatusCommand(char command)
{
    while( eeprom_readStatus() & 0x01 ); // Wait while WIP is true
    // (write in progress)
    // Copy "command" value to SPI2BUF (TX FIFO)
    SPI2BUF = command;
    // Wait while SPI module is working (SPIBUSY set)
    while (SPI2STATbits.SPIBUSY == 1);
}

void eeprom_writeData(int address, char value)
{
    // Apply a mask to limit address to 9 bits
    address &= 0x01FF;
    // Read STATUS and wait while WIP is true (write in progress)
    while(eeprom_readStatus() & 0x01);
    // Enable write operations (activate WEL bit in STATUS register, using eeprom_writeStatusCommand() function )
    eeprom_writeStatusCommand(WREN);
    // Copy WRITE command and A8 address bit to the TX FIFO:
    SPI2BUF = WRITE | ((address & 0x100) >> 5);
    // Copy address (8 LSBits) to the TX FIFO
    SPI2BUF = address & 0x00FF;
    // Copy "value" to the TX FIFO
    SPI2BUF = value;
    // Wait while SPI module is working (SPIBUSY)
    while(SPI2STATbits.SPIBUSY == 1);
}

char eeprom_readData(int address)
{
    volatile char trash;
    // Clear RX FIFO
    trash = SPI2BUF;
    // Clear overflow error flag bit
    SPI2STATbits.SPIROV = 0;
    // Apply a mask to limit address to 9 bits
    address &= 0x001FF;
    // Read STATUS and wait while WIP is true (write in progress)
    while(eeprom_readStatus() & 0x01);
    // Copy READ command and A8 address bit to the TX FIFO
    SPI2BUF = READ | ((address & 0x100) >> 5);
    // Copy address (8 LSBits) to the TX FIFO
    SPI2BUF = address & 0x00FF;
    // Copy any value (e.g. 0x00) to the TX FIFO
    SPI2BUF = 0;
    // Wait while SPI module is working (SPIBUSY)
    while(SPI2STATbits.SPIBUSY == 1);
    // Read and discard 2 characters from RX FIFO (use "trash" variable)
    trash = SPI2BUF;
    trash = SPI2BUF;

    // Read RX FIFO and return the corresponding value
    return SPI2BUF;
}

int main(void)
{
    char status;
    int address, val;
    spi2_init();
    spi2_setClock(EEPROM_CLOCK);
    eeprom_writeStatusCommand(WREN); // Activate write operations 
    for(;;)
    {
        printStr("\nRead (R) Write (W) -> ");
        status = getChar();

        if (status == 'W')
        {
            printStr("\nEndereço -> ");
            address = readInt10();
            printStr("\nValor -> ");
            val = readInt10() & 0x00FF;
            eeprom_writeData(address, val);
        }
        else if (status == 'R')
        {
            printStr("\nEndereço -> ");
            address = readInt10();
            printStr("\nValor -> ");
            val = eeprom_readData(address);
            printInt10(val);
        }
    }
}
