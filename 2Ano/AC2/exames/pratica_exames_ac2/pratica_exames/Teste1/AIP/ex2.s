#Ler os 4 bits do dipswitch somar com o valor introduzido no teclado e apresentar nos leds,
#o valor tem de estar entre 1 e 9 caso o número não seja válido o valor introduzido é colocado nos led’s.

  .equ SFR_BASE_HI, 0xBF88
  .equ TRISE, 0x6100
  .equ PORTE, 0x6110
  .equ LATE, 0x6120
  .data
sms:  .asciiz "Invalido!"
  .text
  .globl main

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

  li $s0, 0
  li $s1, 0

  lw $t3, PORTE($t1)
  andi $t4, $t3, 0x00F0 #ler todos os bits

  srl $t4, $t4, 4

  li $v0, 7
  move $a0, $t4
  syscall

  li $v0, 5
  syscall # ler do teclado

  move $s1, $v0

  bgt $s1, 9, invalid
  blt $s1, 1, invalid

  add $s0, $s1, $t4
  bgt $s0, 15, invalid

  lw $t5, LATE($t1)
  andi $t5, $t5, 0xFFF0
  or $t5, $t5, $s0
  sw $t5, LATE($t1)

  j while

invalid:
  li $v0, 8
  la $a0, sms
  syscall

  #esrever nos LEDS o valor lido do teclado

  lw $t5, LATE($t1)
  andi $t5, $t5, 0xFFF0
  or $t5, $t5, $s1
  sw $t5, LATE($t1)

  j while
