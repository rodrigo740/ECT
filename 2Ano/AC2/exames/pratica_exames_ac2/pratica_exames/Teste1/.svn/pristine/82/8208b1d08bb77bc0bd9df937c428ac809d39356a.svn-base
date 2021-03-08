#Para cada bit do dipswitch está associado uma sequência nos leds. Para o bit 0 está associado contagem crescente, para o bit 1 contagem decrescente da seguinte sequência: 0000, 1111, 1101, 1010, 0011.
#Para o bit 2 e 3 está associado esta sequência, também crescente e decrescente: 1100, 0011, 0110, 1001, 0110, 0001. Usando o core timer gere à frequência de 25Hz cada transição.

.equ SFR_BASE_HI, 0xBF88
.equ TRISE, 0x6100
.equ PORTE, 0x6110
.equ LATE, 0x6120


.data
seq1: .byte 0x0, 0xF, 0xD, 0xA, 0x3
seq2: .byte 0xB, 0x3, 0x6, 0x9, 0x6, 0x1
.text
.globl main

main:

  #definir entradas e saidas
  lui $t0, SFR_BASE_HI
  lw $t1, TRISE($t0)
  andi $t1, $t1, 0xFFF0
  ori $t1, $t1, 0x00F0
  sw $t1, TRISE($t0)

init:

  #ler o ds

  lw $t2, PORTE($t0)
  andi $t2, $t2, 0x00F0

  srl $t2, $t2, 4 #ajustar a direita

  andi $t3, $t2, 0x0001
  andi $t4, $t2, 0x0002
  srl $t4, $t4, 1
  andi $t5, $t2, 0x0003
  srl $t5, $t5, 2
  andi $t6, $t2, 0x0004
  srl $t6, $t6, 3

  beq $t3, 1, crescente_seq1
  beq $t4, 1, decrescente_seq1
  #beq $t5, 1, crescente_seq2
  #beq $t6, 1, decrescente_seq2

crescente_seq1:

  li $s0, 0
  la $s1, seq1

while:

  li $v0, 12
  syscall

  add $s2, $s1, $s0
  lb $s3, 0($s2)

  lw $t7, LATE($t0)
  andi $t7, $t7, 0xFFF0
  or $t7, $t7, $s3
  sw $t7, LATE($t0)

wait:
  li $v0, 11
  syscall

  blt $v0, 30000000, wait

  addi $s0, $s0, 1
  beq $s0, 5, init

  j while

decrescente_seq1:

  li $s0, 4
  la $s1, seq1

while2:

  li $v0, 12
  syscall

  add $s2, $s1, $s0
  lb $s3, 0($s2)

  lw $t7, LATE($t0)
  andi $t7, $t7, 0xFFF0
  or $t7, $t7, $s3
  sw $t7, LATE($t0)

wait2:
  li $v0, 11
  syscall

  blt $v0, 30000000, wait2

  addi $s0, $s0, -1
  beq $s0, -1, init

  j while2
