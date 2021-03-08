.equ SFR_BASE_HI, 0xBF88
.equ TRISE, 0x6100
.equ PORTE, 0x6110
.equ LATE, 0x6120

.data
str:  .asciiz "\nInsira um número entre 1 e 9: \n"
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

  li $v0, 8
  la $a0, str
  syscall

  li $v0, 5
  syscall

  bgt $v0, 9, invalido
  blt $v0, 1, invalido

  add $s0, $v0, $t1
  bgt $s0, 15, invalido

  lw $t2, LATE($t0)
  andi $t2, $t2, 0xFFF0
  or $t2, $t2, $s0
  sw $t2, LATE($t0)

  li $v0, 12
  syscall

wait:
  li $v0, 11
  syscall

  blt $v0, 20000000, wait

  j while

invalido:

  lw $t2, LATE($t0)
  andi $t2, $t2, 0xFFF0
  ori $t2, $t2, 0
  sw $t2, LATE($s0)

  li $v0, 12
  syscall

wait_b:
  li $v0, 11
  syscall

  blt $v0, 20000000, wait_b

  lw $t2, LATE($t0)
  andi $t2, $t2, 0xFFF0
  ori $t2, $t2, 15
  sw $t2, LATE($s0)

  li $v0, 12
  syscall

wait_c:
  li $v0, 11
  syscall

  blt $v0, 20000000, wait_c

  j invalido
