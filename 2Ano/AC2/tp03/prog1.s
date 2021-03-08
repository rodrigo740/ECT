	 .equ SFR_BASE_HI, 0xBF88 	# 16 MSbits of SFR area
	 .equ TRISE, 0x6100 		# TRISE address is 0xBF886100
	 .equ PORTE, 0x6110 		# PORTE address is 0xBF886110
	 .equ LATE, 0x6120 		# LATE address is 0xBF886120
	 .equ TRISB, 0x6040		# TRISB address is 0xBF886040
	 .equ PORTB, 0x6050

	.data
	.text
	.globl main

main:	lui $t1, SFR_BASE_HI 		#
 	lw $t2, TRISE($t1)		# READ (Mem_addr = 0xBF880000 + 0x6100)
 	andi $t2, $t2, 0xFFFE 		# MODIFY (bit0=0 (0 means OUTPUT))
 	sw $t2, TRISE($t1) 		# WRITE (Write TRISE register)

	lw $t3, TRISB($t1)
	ori $t3, $t3, 0x0001		# MUDA O bit0 para 1 ($t1 + 0001)
	sw $t3, TRISB($t1)

loop:	lw $t4, PORTB($t1)
	andi $t4,$t4,0x0001
	xor $t4, $t4, 0x0001
	
	lw $t5,LATE($t1)
	andi $t5,$t5,0xFFFE
	or $t4,$t4,$t5			# todos os bits do late com o ultimo bit do portb

	sw $t4, LATE($t1)

	j loop

	jr $ra
