.equ SFR_BASE_HI, 0xBF88
.equ TRISE, 0x6100
.equ PORTE, 0x6110
.equ LATE, 0x6120

.data
.text
.globl main

main:

  # configurar os portos RE0 = 0 e RE6, RE7 = 1

  lui $t1, SFR_BASE_HI
  lw $t2, TRISE($t1)
  andi $t2, $t2, 0xFFF0 # garantir que o último bit é 0
  ori $t2, $t2, 0xC0 #garantir que o sexto bit é 1
  sw $t2, TRISE($t1)

  while:

  lw $t2, PORTE($t1)
  andi $t2, $t2, 0x40 #escolher o bit a ler
  srl $t2, $t2, 6 #colocá-lo na primeira posição

  lw $t3, PORTE($t1)
  andi $t3, $t3, 0x80 #escolher o bit a ler
  srl $t3, $t3, 7 #colocá-lo na primeira posição

  and $t4, $t2, $t3
  or $t5, $t2, $t3
  xor $t6, $t2, $t3
  nor $t7, $t2, $t3

  lw $t8, LATE($t1)
  andi $t8, $t8, 0xFFF0
  or $t8, $t8, $t4
  sll $t5, $t5, 1
  or $t8, $t8, $t5
  sll $t6, $t6, 2
  or $t8, $t8, $t6
  sll $t7, $t7, 3
  or $t8, $t8, $t7
  sw $t8, LATE($t1)

j while
