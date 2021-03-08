 .equ SFR_BASE_HI, 0XBF88  # 16 MSB of SFR area
 .equ TRISE, 0X6100        # TRIS-E address is 0XBF886100 -> '1' is input, '0' is output (E)  
 .equ LATE,  0X6120        # LAT-E address is 0XBF886120  -> value of output port in E
 .equ TRISB, 0x6040        # TRIS-B address is 0XBF886040 -> '1' is input, '0' is output (B)
 .equ PORTB, 0X6050        # PORT-B address is 0XBF886050 -> value of input port in E
 .data
 .text
 .globl main

main:
        lui $t1, SFR_BASE_HI

        #make RB0-RB3 as inputs
        lw $t2, TRISB($t1)
        ori $t2,$t2,0x000F
        sw $t2, TRISB($t1)

        #make RE0-RE3 as outputs
        lw $t3, TRISE($t1)
        and $t3,$t3,0xFFF0
        sw $t3, TRISE($t1)

while: 
        lw $t4, PORTB($t1)
        andi $t4,$t4,0x000F
        lw $t5, LATE($t1)
        andi $t5,$t5,0xFFF0
        or $t6,$t5,$t4

        sw $t6, LATE($t1)

        j while

jr $ra