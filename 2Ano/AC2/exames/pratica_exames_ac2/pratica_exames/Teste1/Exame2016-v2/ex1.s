  .equ SFR_BASE_HI, 0xBF88
  .equ TRISE, 0x6100
  .equ PORTE, 0x6110
  .equ LATE, 0x6120

  .data
  .text
  .globl main

main:
  lui $t1, SFR_BASE_HI
  lw $t2, TRISE($t1)
  andi $t2, $t2, 0xFFF0
  sw $t2, TRISE($t1)

up:
  li $t0, 15
  j show

contador:
  li $v0, 12
  syscall

wait:
  li $v0, 11
  syscall

  blt $v0, 20000000, wait

while:
  beq $t0, -1, up
  addi $t0, $t0, -1

show:

  lw $t2, LATE($t1)
  andi $t2, $t2, 0xFFF0
  or $t2, $t2, $t0
  sw $t2, LATE($t1)

  j contador
