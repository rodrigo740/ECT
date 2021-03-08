
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

main:   lui $t1, SFR_BASE_HI
        lw $t2, TRISE($t1)
        andi $t2,$t2, 0xFFF0
        sw $t2, TRISE($t1)

        lw $t3, TRISB($t1)
        ori $t3,$t3,0x000F
        sw $t3, TRISB($t1)

        addiu $sp,$sp,-4
        sw $ra, 0($sp)
        
        #jal countUP
        #jal countDown
        #jal countUPDown
        jal johnsonD
        
        lw $ra, 0($sp)
        jr $ra


### FUNÇÂO COUNT CRESCENTE ###
countUP:  move $t0, $0

while:  bge $t0,15,endWhile
        addi $t0,$t0,1

        lw $t4, LATE($t1)
        andi $t4,$t4, 0xFFF0
        or $t4,$t4,$t0              #
        sw $t4, LATE($t1)

        li $a0, 500

        jal delay
        j while

endWhile: move $t0,$0
          j while


### FUNÇÂO COUNT DECRESCENTE ###
countDown:  li $t0, 16

while3:  ble $t0,0,endWhile2
         addiu $t0,$t0,-1

        lw $t4, LATE($t1)
        andi $t4,$t4, 0xFFF0
        or $t4,$t4,$t0              #
        sw $t4, LATE($t1)

        li $a0, 500

        jal delay
        j while3

endWhile2: li $t0, 16
           j while3


### FUNÇÃO COUNT CRESCENTE/DECRESCENTE ###
countUPDown:  lw $t5, PORTB($t1)		# valor de todos os bitss
		      andi $t5,$t5,0x0008

              beq $t5,0x0008,countUP
              beq $t5,0,countDown

### FUNÇÃO D JOHNSON ###

johnsonD:   move $t6, $0

shift:      andi $t7,$t6,0x0008

            nor $t8,$t7,0xFFF7

            beq $t8,0,shiftZero
            beq $t8,0x0008,shiftOne

shiftZero:  sll $t6,$t6,1
            andi $t6,$t6,0x000F

            lw $t4, LATE($t1)
            andi $t4,$t4, 0xFFF0
            or $t4,$t4,$t6                    #
            sw $t4, LATE($t1)

            li $a0, 500

            jal delay

            j shift
        
shiftOne:   sll $t6,$t6,1
            or $t6,$t6,$t8

            andi $t6,$t6,0x000F

            lw $t4, LATE($t1)
            andi $t4,$t4, 0xFFF0
            or $t4,$t4,$t6              #
            sw $t4, LATE($t1)

            li $a0, 500

            jal delay

            j shift


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