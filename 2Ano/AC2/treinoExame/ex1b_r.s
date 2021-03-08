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
        lw $t2, TRISE($t1)
        and $t2,$t2,0xFFF0
        sw $t2, TRISE($t1)

while: 
        lw $t3, PORTB($t1)
        andi $t3,$t3,0x000F

        andi $t7,$t3,0x0001
        sll $t4,$t7,3

        andi $t7,$t3,0x0002
        sll $t5,$t7,1

        andi $t7,$t3,0x0004
        sra $t6,$t7,1

        andi $t7,$t3,0x0008
        sra $t8,$t7,3

        or $t8,$t8,$t6
        or $t8,$t8,$t5
        or $t8,$t8,$t4

        # igualar a 0 os bits que quer mandar para a saida aka os ultimos 4
        lw $t5, LATE($t1)
        andi $t5,$t5,0xFFF0
        or $t6,$t5,$t8

        sw $t6, LATE($t1)

        j while

jr $ra
