    .equ SFR_BASE_HI, 0xBF88
    .equ TRISE, 0x6100
    .equ TRISB, 0x6040
    .equ PORTB, 0X6050
    .equ LATE, 0x6120
    .data
    .text
    .globl main

main:   lui $t0,SFR_BASE_HI
        
        lw $t1, TRISE($t0) # READ
        and $t1,$t1,0xFFF0 # MODIFY
        sw $t1, TRISE($t0) # WRITE
        
        lw $t2, TRISB($t0)
        ori $t2,$t2,0x000F
        sw $t2, TRISB($t0)

do:     lw $t3, PORTB($t0)
        and $t3,$t3,0x000F
        lw $t4, LATE($t0)
        and $t4,$t4,0xFFF0

        or $t5,$t3,$t4

        sw $t5, LATE($t0)

        j do
jr $ra
