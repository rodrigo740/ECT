# Leia do teclado o operador, do dipsitch 0 e 1 - 2 e 3 os operandos. Realize a operação e mostre o resultado nos LEDs.
# Quando lido o carater os LEDs devem piscar com a frequência de 1Hz, 5 vezes.
# Operações disponíveis, + e ^ (xor)

.equ SFR_BASE_HI, 0xBF88
.equ TRISE, 0x6100
.equ PORTE, 0x6110
.equ LATE, 0x6120

.data
str:  .asciiz "\nInsira o seu carater:\n"
.text
.globl main

main:

  lui $t0, SFR_BASE_HI
  lw $t1, TRISE($t0)
  andi $t1, $t1, 0xFFF0
  ori $t1, $t1, 0x00F0
  sw $t1, TRISE($t0)

while:

  li $v0, 8
  la $a0, str
  syscall

  # Ler do teclado

  li $v0, 2
  syscall
  move $s2, $v0

  li $s3, 0

blink:
  li $v0, 12
  syscall

  lw $t1, LATE($t0)
  andi $t1, $t1, 0xFFF0
  ori $t1, $t1, 0
  sw $t1, LATE($t0)

wait:
  li $v0, 11
  syscall

  blt $v0, 10000000, wait

  li $v0, 12
  syscall

  lw $t1, LATE($t0)
  andi $t1, $t1, 0xFFF0
  ori $t1, $t1, 15
  sw $t1, LATE($t0)

wait2:
  li $v0, 11
  syscall

  blt $v0, 10000000, wait2

  addi $s3, $s3, 1
  blt $s3, 5, blink


  # Ler para $s0 o número mais à direita e $s1 o número mais à esquerda

  lw $t1, PORTE($t0)
  andi $t1, $t1, 0x00F0

  srl $t1, $t1, 4

  andi $s0, $t1, 0x3
  andi $s1, $t1, 0xC
  srl $s1, $s1, 2

  beq $s2, '+', soma
  beq $s2, '^', xor

soma:

  add $s3, $s1, $s0

  lw $t1, LATE($t0)
  andi $t1, $t1, 0xFFF0
  or $t1, $t1, $s3
  sw $t1, LATE($t0)

  j while


xor:

  xor $s3, $s1, $s0

  lw $t1, LATE($t0)
  andi $t1, $t1, 0xFFF0
  or $t1, $t1, $s3
  sw $t1, LATE($t0)

  j while
