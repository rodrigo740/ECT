.equ SFR_BASE_HI, 0xBF88
.equ TRISE, 0x6100
.equ PORTE, 0x6110
.equ LATE, 0x6120

.data
str:  .asciiz "Inisira um carater: "
c:  .space 1
.text
.globl main

main:
  lui $t1, SFR_BASE_HI
  lw $t2, TRISE($t1)
  andi $t2, $t2, 0xFFF0
  ori $t2, $t2, 0x00F0
  sw $t2, TRISE($t1)

contador:

  li $v0, 8
  la $a0, str
  syscall

  li $v0, 9
  la $a0, c
  li $a1, 1
  syscall

  la $a3, c
  lb $a2, 0($a3)

  li $v0, 3
  move $a0, $a2
  syscall

  bne $a2, 'i', notfromds

fromds:
  lw $t2, PORTE($t1)
  andi $t2, $t2, 0x00F0

  srl $t2, $t2, 4

while2:
  beq $t2, -1, contador
  addi $t2, $t2, -1

  li $v0, 12
  syscall

wait2:
  li $v0, 11
  syscall

  blt $v0, 20000000, wait2

  lw $t5, LATE($t1)
  andi $t5, $t5, 0xFFF0
  or $t5, $t5, $t2
  sw $t5, LATE($t1)

  j while2

notfromds:
  li $t0, 15

begin:
  li $v0, 12
  syscall

wait:

  li $v0, 11
  syscall

  blt $v0, 20000000, wait

while:
  beq $t0, -1, contador
  addi $t0, $t0, -1

show:

  lw $t2, LATE($t1)
  andi $t2, $t2, 0xFFF0
  or $t2, $t2, $t0
  sw $t2, LATE($t1)

j begin
