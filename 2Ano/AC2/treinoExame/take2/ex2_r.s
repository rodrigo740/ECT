    .equ SFR_BASE_HI, 0xBF88
    .equ TRISE, 0x6100
    .equ LATE, 0x6120
    .equ RESET_CORE_TIMER,12
    .equ READ_CORE_TIMER,11
    .data
    mensagem: .asciiz "Insira um numero-> "
    id: .asciiz "Nome: Rodrigo Lopes Martins\n nMec: 93264\n"

    .text
    .globl main

main:   la $a0, id
        li $v0,8
        syscall  

        lui $t0,SFR_BASE_HI

        lw $t1, TRISE($t0)
        and $t1,$t1,0xFFF0
        sw $t1, TRISE($t0)

do:     la $a0, mensagem
        li $v0,8
        syscall

        li $v0,5
        syscall

        beq $v0,$0,tecla0
        beq $v0,1,tecla1
        beq $v0,2,tecla2
        beq $v0,3,tecla3

        j tecla4

    j do

jr $ra

tecla0:     lw $t2, LATE($t0)
            and $t2,$t2,0xFFF0
            or $t2,$t2,0x0001
            sw $t2, LATE($t0)

            j do

tecla1:     lw $t2, LATE($t0)
            and $t2,$t2,0xFFF0
            or $t2,$t2,0x0002
            sw $t2, LATE($t0)

            j do

tecla2:     lw $t2, LATE($t0)
            and $t2,$t2,0xFFF0
            or $t2,$t2,0x0004
            sw $t2, LATE($t0)

            j do

tecla3:     lw $t2, LATE($t0)
            and $t2,$t2,0xFFF0
            or $t2,$t2,0x0008
            sw $t2, LATE($t0)

            j do

tecla4:     lw $t2, LATE($t0)
            and $t2,$t2,0xFFF0
            or $t2,$t2,0x000F
            sw $t2, LATE($t0)

            li $a0,1000
            jal delay

            lw $t2, LATE($t0)
            and $t2,$t2,0xFFF0
            sw $t2, LATE($t0)

            j do

delay:   subu $sp,$sp,4
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
