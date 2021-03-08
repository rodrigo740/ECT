  .data
msg1:  .asciiz "\nIntroduza um numero (sinal e módulo):"
msg2:  .asciiz "\nValor lido em base 2:"
msg3:  .asciiz "\nValor lido em base 16:"
msg4:  .asciiz "\nValor lido em base 10 (unsigned):"
msg5:  .asciiz "\nValor lido em base 10 (signed):"
  .text
  .globl main

main:

while:

  li $v0, 8
  la $a0, msg1
  syscall

  li $v0, 5
  syscall
  move $t0, $v0 # t0 = value

  li $v0, 8
  la $a0, msg2
  syscall

  li $v0, 6
  move $a0, $t0
  li $a1, 2
  syscall

  li $v0, 8
  la $a0, msg3
  syscall

  li $v0, 6
  move $a0, $t0
  li $a1, 16
  syscall

  li $v0, 8
  la $a0, msg4
  syscall

  li $v0, 6
  move $a0, $t0
  li $a1, 10
  syscall

  li $v0, 8
  la $a0, msg5
  syscall

  li $v0, 7
  move $a0, $t0
  syscall

j while

jr $ra
