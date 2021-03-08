#O programa deve aceitar uma sequência de caracteres (string e armazenar num buffer), depois vai transforma-lo somando com o valor obtido no dipswitch caracter a caracter e guardar no buffer.
#Esse valor final deve ser enviado para o terminal. Aquando da leitura os leds devem acender com uma frequência de 30HZ usando a função delay.

  .equ SFR_BASE_HI, 0xBF88
  .equ TRISE, 0x6100
  .equ PORTE, 0x6110
  .equ LATE, 0x6120
  .equ CALIBRATION_VALUE, 6000
  .data
  buf:.space 21
  sms: .asciiz "cheguei"
  .text
  .globl main

main:

  lui $t0, SFR_BASE_HI
  lw $t1, TRISE($t0)
  ori $t1, $t1, 0xF0
  andi $t1, $t1, 0xF0
  sw $t1, TRISE($t0)

while:
  #ler a sequencia de caracteres
  la $v0, 9
  la $a0, buf
  li $a1, 20
  syscall
  #
  li $a0, '\n'
  li $v0, 3
  syscall

light_up:

  lui $t0, SFR_BASE_HI
  lw $t2, LATE($t0)
  andi $t2, $t2, 0xFFF0
  ori $t2, $t2, 0xFFFF
  sw $t2, LATE($t0)

  li $s0, 1000

for:
  beq $s0, $0, endfor
  li $t0, 6000 # CALIBRATION VALUE, t0 -> i
for_2:
  beq $t0, $0, endfor2
  addi $t0, $t0, -1
  j for_2

endfor2:
  addi $s0, $s0, -1
  j for

endfor:

  # apagar
  lui $t0, SFR_BASE_HI
  lw $t2, LATE($t0)
  andi $t2, $t2, 0xFFF0
  sw $t2, LATE($t0)

somar:

  #ler do switch
  lw $t3, PORTE($t0)
  andi $t3, $t3, 0x00F0

  srl $t3, $t3, 4

  li $t6, 0

for_soma:
  beq $t6, 20, end
  lb $t5, buf($t6)
  add $t5, $t5, $t3
  sb $t5, buf($t6)

  addi $t6, $t6, 1

  j for_soma

end:
  li $v0, 8
  la $a0, buf
  syscall

  j while
