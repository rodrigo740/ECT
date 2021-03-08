# O programa deve aceitar uma sequência de caracteres (string e armazenar num buffer), depois vai transforma-lo somando com o valor obtido no dipswitch caracter a caracter e guardar no buffer.
#Esse valor final deve ser enviado para o terminal. A quando da leitura os leds devem acender com uma frequência de 30HZ usando a função delay.

.equ SFR_BASE_HI, 0xBF88
.equ TRISE, 0x6100
.equ PORTE, 0x6110
.equ LATE, 0x6120

.data
buffer: .space 100
str:  .asciiz "\Insira a string:\n"
show_str: .asciiz "\nResultado:\n"
.text
.globl main

main:

  addiu $sp, $sp, -4
  sw $ra, 0($sp)

  #definir entradas e saidas

  lui $t0, SFR_BASE_HI
  lw $t1, TRISE($t0)
  andi $t1, $t1, 0xFFF0
  ori $t1, $t1, 0x00F0
  sw $t1, TRISE($t0)

init:

  # ---------------- LER DO TECLADO --------------------

  li $v0, 8
  la $a0, str
  syscall

  li $v0, 9
  la $a0, buffer
  li $a1, 100
  syscall

li $t6, 0

blink:

  lw $t1, LATE($t0)
  andi $t1, $t1, 0xFFF0
  ori $t1, $t1, 15
  sw $t1, LATE($t0)

  li $a0, 33

for1:
  li $s4, 6000
  beq $a0, 0, end
  for2:
    beq $s4, 0, endfor
    addi $s4, $s4, -1

    j for2

endfor:
  addi $a0, $a0, -1
  j for1

end:

  lw $t1, LATE($t0)
  andi $t1, $t1, 0xFFF0
  ori $t1, $t1, 0
  sw $t1, LATE($t0)

  addi $t6, $t6, 1
  blt $t6, 4, blink


  # ------------- LER DO DS --------------------

  lw $t1, PORTE($t0)
  andi $t1, $t1, 0x00F0

  srl $t1, $t1, 4

  # --------------- SOMAR ---------------

  li $s0, 0
  la $s1, buffer

for:

  add $s2, $s1, $s0
  lb $s3, 0($s2)
  beq $s3, $0, show

  add $s3, $s3, $t1

  sb $s3, 0($s2)

  addi $s0, $s0, 1

  j for

show:

  li $v0, 8
  la $a0, show_str
  syscall

  li $v0,8
  la $a0, buffer
  syscall

  j init

  lw $ra, 0($sp)
  addiu $sp, $sp, 4

  jr $ra
