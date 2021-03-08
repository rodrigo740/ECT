#Para cada bit do dipswitch está associado uma sequência nos leds.
#Para o bit 0 está associado contagem crescente, para o bit 1 contagem decrescente da seguinte sequência: 0000, 1111, 1101, 1010, 0011.
#Para o bit 2 e 3 está associado esta sequência, também crescente e decrescente: 1100, 0011, 0110, 1001, 0110, 0001.
#Usando o core timer gere à frequência de 25Hz cada transição.

  .equ SFR_BASE_HI, 0xBF88
  .equ TRISE, 0x6100
  .equ PORTE, 0x6110
  .equ LATE, 0x6120
  .data
seq1: .byte 0x0,0xF, 0xD, 0xA, 0x3
seq2: .byte 0xC, 0x3, 0x6, 0x9, 0x6, 0x1
  .text
  .globl main

main:

  lui $t1, SFR_BASE_HI
  lw $t2, TRISE($t1)
  andi $t2, $t2, 0xFFF0 #deifinir saídas = 0
  ori $t2, $t2, 0x00F0 # definir como entradas = 1
  sw $t2, TRISE($t1)

  la $s0, seq1
  la $s2, seq2
  la $s5, seq2
  addi $s5, $s5, 5
  li $s4, 5
  la $s6, seq1
  addi $s6, $s6, 4
  li $s7, 4

  # Ler do switch

  while:
    lw $t3, PORTE($t1)
    andi $t4, $t3, 0x0010 # crescente seq1
    andi $t5, $t3, 0x0020 # decrescente seq1
    andi $t6, $t3, 0x0040 # crescente seq2
    andi $t7, $t3, 0x0080 # decrescente seq2

    srl $t4, $t4, 4
    srl $t5, $t5, 5
    srl $t6, $t6, 6
    srl $t7, $t7, 7

    beq $t4, 1, crescenteSeq1
    beq $t5, 1, decrescenteSeq1
    beq $t6, 1, crescenteSeq2
    beq $t7, 1, decrescenteSeq2

    j while

  crescenteSeq1:

  beq $s1, 5, reset

  # apagar LEDS
  lw $t9, LATE($t1)
  andi $t9, $t9, 0xFFF0
  sw $t9, LATE($t1)

  li $v0, 12
  syscall #reset core timer

  lb $t8, 0($s0)

  # apresentar nos displays

  lw $t9, LATE($t1)
  andi $t9, $t9, 0xFFF0
  or $t9, $t9, $t8
  sw $t9, LATE($t1)

  addi $s0, $s0, 1
  addi $s1, $s1, 1

wait:
    li $v0, 11
    syscall

    blt $v0, 40000000, wait

    j while

reset:
  la $s0, seq1
  li $s1, 0

  j while

decrescenteSeq1:

  beq $s7, -1, reset_4

  # apagar LEDS
  lw $t9, LATE($t1)
  andi $t9, $t9, 0xFFF0
  sw $t9, LATE($t1)

  li $v0, 12
  syscall #reset core timer

  lb $t8, 0($s6)

  # apresentar nos displays

  lw $t9, LATE($t1)
  andi $t9, $t9, 0xFFF0
  or $t9, $t9, $t8
  sw $t9, LATE($t1)

  addi $s6, $s6, -1
  addi $s7, $s7, -1

wait_4:
  li $v0, 11
  syscall

  blt $v0, 40000000, wait

  j while

reset_4:
  la $s6, seq1
  addi $s6, $s6, 4
  li $s7, 4

  j while


crescenteSeq2:
  beq $s3, 6, reset_2

  # apagar LEDS
  lw $t9, LATE($t1)
  andi $t9, $t9, 0xFFF0
  sw $t9, LATE($t1)

  li $v0, 12
  syscall #reset core timer

  lb $t8, 0($s2)

  # apresentar nos displays

  lw $t9, LATE($t1)
  andi $t9, $t9, 0xFFF0
  or $t9, $t9, $t8
  sw $t9, LATE($t1)

  addi $s2, $s2, 1
  addi $s3, $s3, 1

wait_2:
  li $v0, 11
  syscall

  blt $v0, 40000000, wait

  j while

reset_2:
  la $s2, seq2
  li $s3, 0

  j while

decrescenteSeq2:

  beq $s4, -1, reset_3

  # apagar LEDS
  lw $t9, LATE($t1)
  andi $t9, $t9, 0xFFF0
  sw $t9, LATE($t1)

  li $v0, 12
  syscall #reset core timer

  lb $t8, 0($s5)

  # apresentar nos displays

  lw $t9, LATE($t1)
  andi $t9, $t9, 0xFFF0
  or $t9, $t9, $t8
  sw $t9, LATE($t1)

  addi $s4, $s4, -1
  addi $s5, $s5, -1

wait_3:
  li $v0, 11
  syscall

  blt $v0, 40000000, wait

  j while

reset_3:
  la $s5, seq1
  addi $s5, $s5, 4
  li $s4, 4

  j while
