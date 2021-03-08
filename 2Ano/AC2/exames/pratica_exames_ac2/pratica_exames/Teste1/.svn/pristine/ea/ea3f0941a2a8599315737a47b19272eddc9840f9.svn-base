  .equ SFR_BASE_HI, 0xBF88
  .equ TRISE, 0x6100
  .equ PORTE, 0x6110
  .equ LATE, 0x6120

  .data
  .text
  .globl main

main:

  # CONFIGURAR
  lui $t0, SFR_BASE_HI
  lw $t1, TRISE($t0)
  andi $t1, $t1, 0xFFF0
  ori $t1, $t1, 0x00F0
  sw $t1, TRISE($t0)

while:

  lw $t1, PORTE($t0)
  andi $t1, $t1, 0x00F0
  srl $t1, $t1, 4

  andi $s0, $t1, 0x0001
  andi $s1, $t1, 0x0002
  srl $s1, $s1, 1
  andi $s2, $t1, 0x0004
  srl $s2, $s2, 2
  andi $s3, $t1, 0x0008
  srl $s3, $s3, 3

  or $s0, $s0, $s1
  or $s0, $s0, $s2
  or $s0, $s0, $s3

  lw $t2, LATE($t0)
  andi $t2, $t2, 0xFFF0
  or $t2, $t2, $s0
  sw $t2, LATE($t0)

  # o mesmo processo para as restantes operacoes

  j while
