    .equ SFR_BASE_HI, 0xBF88
    .equ TRISE, 0x6100
    .equ TRISB, 0x6040
    .equ PORTB, 0X6050
    .equ LATE, 0x6120
    .data
    .text
    .globl main

main:   lui $t0,SFR_BASE_HI

        lw $t1, TRISB($t0)
        or $t1,$t1,0x000F
        sw $t1, TRISB($t0)

        lw $t2, TRISE($t0)
        and $t2,$t2,0xFFF0
        sw $t2, TRISE($t0)

do:     lw $t3, PORTB($t0)
        and $t3,$t3,0x000F

        lw $t4, LATE($t0)
        and $t4,$t4,0xFFF0

        move $t4,$t3
        move $t5,$t3
        move $t6,$t3
        move $t7,$t3

        and $t4,$t4,0x0001 # BIT RB0
        sll $t4,$t4,3
        or $t9,$t9,$t4

        and $t5,$t5,0x0002 # BIT RB1
        sll $t5,$t5,1
        or $t9,$t9,$t5

        and $t6,$t6,0x0004 # BIT RB2
        srl $t6,$t6,1
        or $t9,$t9,$t6

        and $t7,$t7,0x0008 # BIT RB3    
        srl $t7,$t7,3
        or $t9,$t9,$t7

        sw $t9, LATE($t0)

        j do
jr $ra
