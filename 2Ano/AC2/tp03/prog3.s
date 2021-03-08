	 .equ SFR_BASE_HI, 0xBF88 	# 16 MSbits of SFR area
	 .equ TRISE, 0x6100 		# TRISE address is 0xBF886100
	 .equ PORTE, 0x6110 		# PORTE address is 0xBF886110
	 .equ LATE, 0x6120 		# LATE address is 0xBF886120
	 .equ TRISB, 0x6040		# TRISB address is 0xBF886040
	 .equ PORTB, 0x6050

	.data
	.text
	.globl main

main:	# RE0 a RE3 como saidas	

		lui $t1, SFR_BASE_HI 		
		lw $t2, TRISE($t1)			# READ (Mem_addr = 0xBF880000 + 0x6100)
		andi $t2, $t2, 0xFFF0 		# meter todos os bits a 0(saida)
		sw $t2, TRISE($t1) 			# WRITE (Write TRISE register)

		#RB0 a RB3 como entradas

		lw $t3, TRISB($t1)
		ori $t3, $t3, 0x000F		# mudar os ultimos 4 bits para 1
		sw $t3, TRISB($t1)

loop:	lw $t4, PORTB($t1)		# valor de todos os bitss
		andi $t4,$t4,0x000F

		xor $t4,$t4, 0x0009
		
		lw $t5,LATE($t1)
		andi $t5,$t5,0xFFF0
		or $t4,$t4,$t5			# todos os bits do late com todos os bits do PORTB

		sw $t4, LATE($t1)


		j loop

		jr $ra
