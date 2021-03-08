.equ SFR_BASE_HI, 0xBF88
.equ TRISE, 0x6100
.equ PORTE, 0x6110
.equ LATE, 0x6120

.data
.text
.globl main

main:

  lui $t0, SFR_BASE_HI
  lw $t1, TRISE($t0)
  andi $t1, $t1, 0xFFF0
  sw $t1, TRISE($t0)

counter:

  li $s0, 15
  lw $t2, LATE($t0)
  andi $t2, $t2, 0xFFF0
  ori $t2, $t2, 0
  sw $t2, LATE($t0)

while:

  li $v0, 12
  syscall

  wait:
  li $v0, 11
  syscall

  blt $v0, 20000000, wait

  lw $t2, LATE($t0)
  andi $t2, $t2, 0xFFF0
  or $t2, $t2, $s0
  sw $t2, LATE($t0)

  beq $s0, -1, counter
  addi $s0, $s0, -1

  j while
