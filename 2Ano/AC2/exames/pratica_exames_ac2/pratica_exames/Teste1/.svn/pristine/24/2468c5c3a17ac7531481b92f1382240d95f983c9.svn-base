#Exercício de treino: Ler os 4 bits do dipswitch somar com o valor introduzido no teclado e apresentar nos leds,
#caso o valor da soma seja > 15, os leds devem piscar com a frequência de 1Hz usando o CoreTimer, durante 5 segundos. LEDS (RE0 a RE3)

.equ SFR_BASE_HI, 0xBF88
.equ TRISE, 0x6100
.equ PORTE, 0x6110
.equ LATE, 0x6120

.data
str:  .asciiz "Insira o valor \n"
.text
.globl main

main:

  lui $t0, SFR_BASE_HI
  lw $t1, TRISE($t0)
  andi $t1, $t1, 0xFFF0 #saidas LEDS =0
  ori $t1, $t1, 0x00F0 #entradas = 1 DS
  sw $t1, TRISE($t0)


while:

  # inicializacoes
  li $s0, 0 #counter

  # frase
  li $v0, 8
  la $a0, str
  syscall

  # ler do teclado
  li $v0, 5
  syscall
  move $t2, $v0

  # ler do DS

  lw $t3, PORTE($t0)
  andi $t3, $t3, 0x00F0

  srl $t3, $t3, 4 #ajustar a direita


sum:
  add $t5, $t3, $t2
  bgt $t5, 15, blink

  lw $t6, LATE($t0)
  andi $t6, $t6, 0xFFF0
  or $t6, $t6, $t5
  sw $t6, LATE($t0)

  j while


blink:

li $v0, 12
syscall


  lw $t6, LATE($t0)
  andi $t6, $t6, 0xFFF0
  ori $t6, $t6, 15
  sw $t6, LATE($t0)

wait:

  li $v0, 11
  syscall

  blt $v0, 10000000, wait

  lw $t6, LATE($t0)
  andi $t6, $t6, 0xFFF0
  ori $t6, $t6, 0
  sw $t6, LATE($t0)

  li $v0, 12
  syscall

  wait2:

    li $v0, 11
    syscall

    blt $v0, 10000000, wait2

    addi $s0, $s0, 1
    beq $s0, 5, while

  j blink
