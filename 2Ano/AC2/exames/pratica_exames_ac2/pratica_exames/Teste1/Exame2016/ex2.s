.equ SFR_BASE_HI, 0xBF88
.equ TRISE, 0x6100
.equ PORTE, 0x6110
.equ LATE, 0x6120

.data
c:  .space 1
str:  .asciiz "\n Insira o carater: \n"
.text
.globl main

main:

  lui $t0, SFR_BASE_HI
  lw $t1, TRISE($t0)
  andi $t1, $t1, 0xFFF0
  sw $t1, TRISE($t0)

decide:

  lw $t2, LATE($t0)
  andi $t2, $t2, 0xFFF0
  ori $t2, $t2, 0
  sw $t2, LATE($t0)

  li $v0, 8
  la $a0, str
  syscall

  li $v0, 9
  la $a0, c
  li $a1, 1
  syscall

  la $a0, c
  lb $a1, 0($a0)

  beq $a1, 'i', ds

counter:
  li $s0, 15

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

  beq $s0, -1, decide
  addi $s0, $s0, -1

  j while

ds:

  lw $t3, PORTE($t0)
  andi $t3, $t3, 0x00F0

  srl $t3, $t3, 4

while_ds:
  li $v0, 12
  syscall

wait_ds:
  li $v0, 11
  syscall

  blt $v0, 20000000, wait_ds

  lw $t2, LATE($t0)
  andi $t2, $t2, 0xFFF0
  or $t2, $t2, $t3
  sw $t2, LATE($t0)

  beq $t3, -1, decide
  addi $t3, $t3, -1

  j while_ds
