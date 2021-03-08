#include <detpic32.h>

void configUart(unsigned int baud, char parity, unsigned int Stopbits){
	if(baud<600 || baud > 115200){
		baud = 115200;
	}

	if(parity!='N' && parity!='E' && parity!='O'){
		parity = 'N';
	}else if(Stopbits!=1 && Stopbits!=2){
		Stopbits = 1;
	}

	U1BRG = ((20000000 + 8*baud)/ (16*baud))-1;
	U1MODEbits.BRGH = 0; // divisão por 16

	if(parity=='N'){
		U1MODEbits.PDSEL = 00;
	}else if(parity=='E'){
		U1MODEbits.PDSEL = 01;
	}else if(parity=='O'){
		U1MODEbits.PDSEL = 10;
	}
	/*
	Neste caso, número fixo de 8 bits.
	10 = 8-bit data, odd parity
	01 = 8-bit data, even parity
	00 = 8-bit data, no parity
	*/

  // Stopbits
	U1MODEbits.STSEL = (Stopbits - 1);

	/*
	STSEL: Stop Selection bit
	1 = 2 Stopbits
	0 = 1 Stopbit
	*/

	U1STAbits.UTXEN = 1;
	U1STAbits.URXEN = 1;

	U1MODEbits.ON = 1;

	/*** UART configure interrupts ***/
	U1STAbits.URXISEL = 00;
	IEC0bits.U1RXIE = 1;
	IPC6bits.U1IP=3;
	IEC0bits.U1EIE = 1;
}
