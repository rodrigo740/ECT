  .equ SFR_BASE_HI, 0xBF88			#16 Msbits of SFR area
  .equ TRISE, 0x6100				#TRISE address is 0xBF886100
  .equ PORTE, 0x6110				#PORTE address is 0xBF886110
  .equ LATE, 0x6120				#LATE address is 0xBF886120

  .text
  .globl main

main:

  lui $t1, SFR_BASE_HI
  lw $t2, TRISE($t1)
  ori $t2, $t2, 0x00C0
  andi $t2, $t2, 0xFFF0
  sw $t2, TRISE($t1)

  li $v0, 12
  syscall #timer reset

  li $s0, 0
  li $t5, 0
  li $t6, 0

while:

  li $v0, 11
  syscall
  blt $v0, 5000000, while

  lw $t4, PORTE($t1)
  andi $t5, $t4, 0x40
  andi $t6, $t4, 0x80

  beqz $t6, contador_binario
  bne $t6, $0, contador_johnson

contador_johnson:

  beqz $t5, contador_johnson_crescente
  bne $t5, $0, contador_johnson_decrescente

contador_johnson_crescente:
  li $t9, 15
  beq $s0, $t9, zero
  beqz $s0, sum
  sll $s0, $s0, 1
  addi $s0, $s0, 1

  j show

sum:
  addi $s0, $s0, 1
  j show

contador_johnson_decrescente:
  beqz $s0, max
  srl $s0, $s0, 1

  j show

max:
  li $s0, 15
  j show

zero:
  li $s0, 0
  j show

contador_binario:
  beqz $t5, contador_binario_crescente
  bne $t5, $0, contador_binario_decrescente

contador_binario_crescente:

while_contador_binario_crescente:

  addi $s0, $s0, 1
  li $t0, 15
  beq $s0, $t0, reset_contador_binario_crescente

  j show

reset_contador_binario_crescente:
  li $s0, 0
  j contador_binario_crescente

contador_binario_decrescente:

while_contador_binario_decrescente:
  beqz $s0, reset_contador_binario_decrescente
  addi $s0, $s0, -1

  j show

reset_contador_binario_decrescente:
  li $s0, 0xF
  j show

show:

  lw $t3, LATE($t1)
  andi $t3, $t3, 0xFFF0
  or $t3, $t3, $s0
  sw $t3, LATE($t1)

  li $v0, 12
  syscall

  j while
