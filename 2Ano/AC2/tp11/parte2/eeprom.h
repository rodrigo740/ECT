// eeprom.h
#ifndef EEPROM_H
#define EEPROM_H

// Declare symbols here (READ, WRITE, ...)
#define WRITE 0x02
#define RDSR 0x05
#define READ 0x03
#define WRSR 0x01
#define WRDI 0x04
#define WREN 0x06
#define EEPROM_CLOCK 500000

// Declare function prototypes here
void spi2_setClock(unsigned clock_freq);
void spi2_init(void);
char eeprom_readStatus(void);
void eeprom_writeStatusCommand(char command);
void eeprom_writeData(int address, char value);
char eeprom_readData(int address);

#endif
