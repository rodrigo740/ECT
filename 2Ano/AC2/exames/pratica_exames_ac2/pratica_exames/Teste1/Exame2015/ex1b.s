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
  andi $t1, $t1, 0xFFF0
  sw $t1, TRISE($t0)

while:

  li $v0, 8
  la $a0, str
  syscall

  li $v0, 5
  syscall

  blt $v0, 0, negativo

  j while

negativo:

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
