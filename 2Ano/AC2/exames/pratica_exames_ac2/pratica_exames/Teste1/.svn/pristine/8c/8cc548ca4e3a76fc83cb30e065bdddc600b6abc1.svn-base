  .equ SFR_BASE_HI, 0xBF88
  .equ TRISE, 0x6100
  .equ PORTE, 0x6110
  .equ LATE, 0x6120
  .data
  .text
  .globl main

#Ler 4 bits do dipswitch fazer as operações or, xor, mult e xnor. bit0+bit1(or) e bit2+bit3(or), apresentando o resultado nos leds[X][X]
main:

  # Definir os Portos de entrada e de saída
  # De entrada: de 4 a 7
  # De saída: 0, 1, 2 e 3

  lui $t1, SFR_BASE_HI
  lw $t2, TRISE($t1)
  andi $t2, $t2, 0xFFF0 #deifinir saídas = 0
  ori $t2, $t2, 0x00F0 # definir como entradas = 1
  sw $t2, TRISE($t1)

  # Ler do switch

while:

  lw $t3, PORTE($t1)
  andi $t4, $t3, 0x0010 #1º bit
  andi $t5, $t3, 0x0020 #3º bit
  andi $t6, $t3, 0x0040 #3º bit
  andi $t7, $t3, 0x0080 #4º bit

  srl $t4, $t4, 4
  srl $t5, $t5, 5
  srl $t6, $t6, 6
  srl $t7, $t7, 7

  or $s0, $t4, $t5
  or $s1, $t6, $t7
  or $s0, $s1, $s0

  xor $s2, $t4, $t5
  xor $s3, $t6, $t7
  xor $s2, $s3, $s2

  mul $s4, $t4, $t5
  mul $s5, $t6, $t7
  mul $s4, $s5, $s4

  or $s6, $t4, $t5
  or $s7, $t6, $t7
  or $s6, $s7, $s6
  not $s6, $s6

  #escrever

  lw $t8, LATE($t1)
  andi $t8, $t8, 0xFFF0 #escolher os bits (=0)
  or $t8, $t8, $s0
  sll $s2, $s2, 1
  or $t8, $t8, $s2
  sll $s4, $s4, 2
  or $t8, $t8, $s4
  sll $s6, $s6, 3
  or $t8, $t8, $s6
  sw $t8, LATE($t1)

  j while

  jr $ra
