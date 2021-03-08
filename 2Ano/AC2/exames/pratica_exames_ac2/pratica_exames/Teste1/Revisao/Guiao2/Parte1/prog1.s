  .equ SFR_BASE_HI, 0xBF88
  .equ TRISE, 0x6100
  .equ PORTE, 0x6110
  .equ LATE, 0x6120

  .data
  .text
  .globl main

 main:

 # configurar os portos RE0 = 0 e RE6 = 1

  lui $t1, SFR_BASE_HI
  lw $t2, TRISE($t1)
  andi $t2, $t2, 0xFFFE # garantir que o último bit é 0
  ori $t2, $t2, 0x40 #garantir que o sexto bit é 1
  sw $t2, TRISE($t1)

while: 

  lw $t2, PORTE($t1)
  andi $t2, $t2, 0x40 #escolher o bit a ler
  srl $t2, $t2, 6 #colocá-lo na primeira posição

  lw $t3, LATE($t1)
  andi $t3, $t3, 0xFFFE
  or $t3, $t3, $t2
  sw $t3, LATE($t1)


j while
