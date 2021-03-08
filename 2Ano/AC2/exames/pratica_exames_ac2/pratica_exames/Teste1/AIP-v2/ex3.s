.equ SFR_BASE_HI, 0xBF88
.equ TRISE, 0x6100
.equ PORTE, 0x6110
.equ LATE, 0x6120

.data
seq:  .byte 0x0, 0xF, 0xD, 0xA, 0x3
.text
.globl main

main:

  # CONFIGURAR
  lui $t0, SFR_BASE_HI
  lw $t1, TRISE($t0)
  andi $t1, $t1, 0xFFF0
  ori $t1, $t1, 0x00F0
  sw $t1, TRISE($t0)

  li $t2, 0

while:

  lw $t1, PORTE($t0)
  andi $t1, $t1, 0x00F0
  srl $t1, $t1, 4
  andi $s1, $t1, 0x0001
  andi $s2, $t1, 0x0002
  srl $s2, $s2, 1

  beq $s1, 1, crescente
  li $t2, 4
  beq $s2, 1, decrescente


  j while

decrescente:


  beq $t2, -1, up

  la $t4, seq
  add $t5, $t4, $t2
  lb $t6, 0($t5)

  lw $t3, LATE($t0)
  andi $t3, $t3, 0xFFF0
  or $t3, $t3, $t6
  sw $t3, LATE($s0)

  li $v0, 12
  syscall

waitb:
  li $v0, 11
  syscall

  blt $v0, 20000000, waitb

  addi $t2, $t2, -1

  j decrescente

up:
  li $t2, 4
  j while

crescente:

  beq $t2, 5, down

  la $t4, seq
  add $t5, $t4, $t2
  lb $t6, 0($t5)

  lw $t3, LATE($t0)
  andi $t3, $t3, 0xFFF0
  or $t3, $t3, $t6
  sw $t3, LATE($s0)

  li $v0, 12
  syscall

wait:
  li $v0, 11
  syscall

  blt $v0, 20000000, wait

  addi $t2, $t2, 1

  j crescente

down:
  li $t2, 0
  j while
