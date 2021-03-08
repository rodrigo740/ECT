#Exercicio adapatdo por falta de LEDS -> quando o valor é negativo, todos os LEDS acendem

.equ SFR_BASE_HI, 0xBF88
.equ TRISE, 0x6100
.equ PORTE, 0x6110
.equ LATE, 0x6120

.data
str:  .asciiz "\n Valor: \n"
.text
.globl main

main:

# LER DO DIPSWITCH E ESCREVER NOS LEDS

  lui $t0, SFR_BASE_HI
  lw $t1, TRISE($t0)
  ori $t1, $t1, 0x00F0
  andi $t1, $t1, 0xFFF0
  sw $t1, TRISE($t0)

while:

  # LER DO DS
  lw $t1, PORTE($t0)
  andi $t1, $t1, 0x00F0
  srl $t1, $t1, 4

  andi $t2, $t1, 0x0008 #bit mais significativo

  srl $t2, $t2, 3

  li $v0, 8
  la $a0, str
  syscall

  beq $t2, 1, negativo

  # print do numero
  li $v0, 7
  move $a0, $t1
  syscall

  j while

negativo:

  # print do numero
  li $t4, -8
  andi $t5, $t1, 0x0007

  add $t4, $t4, $t5
  li $v0, 7
  move $a0, $t4
  syscall

  li $v0, 12
  syscall     #reset core timer

  lw $t3, LATE($t0)
  andi $t1, $t1, 0xFFF0
  ori $t1, $t1, 15
  sw $t1, LATE($t0)

wait:
  li $v0, 11
  syscall

  blt $v0, 5000000, wait

  li $v0, 12
  syscall     #reset core timer

  lw $t3, LATE($t0)
  andi $t1, $t1, 0xFFF0
  ori $t1, $t1, 0
  sw $t1, LATE($t0)

wait2:
  li $v0, 11
  syscall

  blt $v0, 5000000, wait2

  j while
