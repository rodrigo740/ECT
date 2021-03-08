

    .equ SFR_BASE_HI, 0xBF88 	# 16 MSbits of SFR area
    .equ TRISE, 0x6100 		    # TRISE address is 0xBF886100
    .equ PORTE, 0x6110 		    # PORTE address is 0xBF886110
    .equ LATE, 0x6120 		    # LATE address is 0xBF886120
    .equ TRISB, 0x6040		    # TRISB address is 0xBF886040
    .equ PORTB, 0x6050
    .equ RESET_CORE_TIMER,12
    .equ READ_CORE_TIMER,11

    .data
    .text
    .globl main

main:   li $t0,0                    # v = 0

        #RE0 como entrada(meter o bit 0 como 0)

        lui $t1,SFR_BASE_HI
        lw $t2, TRISE($t1)
        andi $t2,$t2,0xFFFE         # and a3 a2 a1 a0*0=0, logo apenas o ultimo bit fica a 0
        sw $t2, TRISE($t1) 		    # WRITE (Write TRISE register)

while: 
        
        lw $t3, LATE($t1)
        andi $t3,$t3, 0xFFF0
        or $t3,$t3,$t0              # meter o bit 0 de LATE = v
        sw $t3, LATE($t1)

        li $a0,20
        jal delay

        xor $t0,$t0,1

        j while

        jr $ra



delay:  subu $sp,$sp,4
        sw $ra,0($sp)

for:    ble $a0,0, endfor
        li $v0, RESET_CORE_TIMER
        syscall 

while2:  li $v0,READ_CORE_TIMER      # while (1) {
        syscall                      #
        blt $v0,20000,while2
        subu $a0,$a0,1
        j for

        
endfor:  lw $ra,0($sp)
         addiu $sp,$sp,4
         jr $ra

