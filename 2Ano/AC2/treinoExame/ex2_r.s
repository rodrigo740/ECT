 .equ SFR_BASE_HI, 0XBF88  # 16 MSB of SFR area
 .equ TRISE, 0X6100        # TRIS-E address is 0XBF886100 -> '1' is input, '0' is output (E)  
 .equ LATE,  0X6120        # LAT-E address is 0XBF886120  -> value of output port in E


 .data
    mensagem: .asciiz "Inserir um numero ->"

 .text
 .globl main

main: 
    lui $t0, SFR_BASE_HI

    # make RE0-RE3 as outputs
    lw $t1, TRISE($t0)
    andi $t1,$t1, 0xFFF0
    sw $t1, TRISE($t0)

loop:

    la $a0,mensagem
    li $v0,8
    syscall

    li $v0,5
    syscall

    beq $v0,$0,tecla0
    beq $v0,1,tecla1
    beq $v0,2,tecla2
    beq $v0,3,tecla3
    
    j tecla4

    j loop

    jr $ra

tecla0:
    lw $t1, LATE($t0)
    andi $t1,$t1,0xFFF0
    or $t1,$t1,0x0001
    sw $t1, LATE($t0)

    j loop

tecla1:
    lw $t1, LATE($t0)
    andi $t1,$t1,0xFFF0
    or $t1,$t1,0x0002
    sw $t1, LATE($t0)

    j loop

tecla2:
    lw $t1, LATE($t0)
    andi $t1,$t1,0xFFF0
    or $t1,$t1,0x0004
    sw $t1, LATE($t0)

    j loop

tecla3:
    lw $t1, LATE($t0)
    andi $t1,$t1,0xFFF0
    or $t1,$t1,0x0008
    sw $t1, LATE($t0)

    j loop

tecla4:
    lw $t1, LATE($t0)
    andi $t1,$t1,0xFFF0
    or $t1,$t1,0x000F
    sw $t1, LATE($t0)

    li $a0,1000
    jal delay

    lw $t1, LATE($t0)
    andi $t1,$t1,0xFFF0
    sw $t1, LATE($t0)


    j loop

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
