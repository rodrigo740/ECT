.equ STR_MAX_SIZE, 20
.data
str1: .space 21
str2: .space 21
str3: .space 41
msg1:  .asciiz "Introduza 2 strings: "
msg2:  .asciiz "Resultados:\n"
msg3:  .asciiz "Passou:\n"
space:  .asciiz "\n"
.text
.globl main

main:
  addiu $sp, $sp, -4
  sw $ra, 0($sp)

  li $v0, 8
  la $a0, msg1
  syscall

  li $v0, 9
  la $a0, str1
  li $a1, 21
  syscall

  li $v0, 9
  la $a0, str2
  li $a1, 21
  syscall

  li $v0, 8
  la $a0, msg2
  syscall

  la $a0, str1
  jal strlen
  move $a0, $v0
  li $v0, 6
  li $a1, 10
  syscall

  li $v0, 8
  la $a0, space
  syscall

  la $a0, str2
  jal strlen
  move $a0, $v0
  li $v0, 6
  li $a1, 10
  syscall

  li $v0, 8
  la $a0, space
  syscall

  la $a0, str3
  la $a1, str1
  jal strcpy

  la $a0, str3
  la $a1, str2
  jal strcat
  move $a0, $v0
  li $v0,8
  syscall

  li $v0, 8
  la $a0, space
  syscall

  la $a0, str1
  la $a1, str2
  jal strcmp
  move $a0, $v0
  li $v0, 7
  syscall

  lw $ra, 0($sp)
  addiu $sp, $sp, 4
  jr $ra


strlen:
  addiu $sp, $sp, -12
  sw $ra, 0($sp)
  sw $s0, 4($sp)
  sw $s1, 8($sp)
  li $s1, 0 # -> len
  move $s0, $a0
for_strlen:
  lb $t0, 0($s0)
  beq $t0, $0, end_strlen
  addiu $s0, $s0, 1
  addiu $s1, $s1, 1

  j for_strlen

end_strlen:
  move $v0, $s1
  lw $ra, 0($sp)
  lw $s0, 4($sp)
  lw $s1, 8($sp)
  addiu $sp, $sp, 12

  jr $ra

  strcat:
  addiu $sp, $sp, -16
  sw $ra, 0($sp)
  sw $s0, 4($sp) #*dst
  sw $s1, 8($sp) #*src
  sw $s2, 12($sp) #*rp

  move $s0, $a0
  move $s1, $a1
  move $s2, $s0

  for_strcat:
  lb $t0, 0($s0)
  beq $t0, $0, endfor_strcat
  addi $s0, $s0, 1

  j for_strcat

  endfor_strcat:
  move $a0, $s0
  move $a1, $s1
  jal strcpy
  move $v0, $s2

  lw $ra, 0($sp)
  lw $s0, 4($sp) #*dst
  lw $s1, 8($sp) #*src
  lw $s2, 12($sp) #*rp
  addiu $sp, $sp, 16

  jr $ra


strcpy:

  addiu $sp, $sp, -16
  sw $ra, 0($sp)
  sw $s0, 4($sp)
  sw $s1, 8($sp)
  sw $s2, 12($sp)

  move $s0, $a0
  move $s1, $a1
  move $s2, $a0

for_strcpy:

  lb $t0, 0($s1)
  sb $t0, 0($s0)
  beqz $t0, end_strcpy
  addi $s0, $s0,1
  addi $s1, $s1, 1

  j for_strcpy

end_strcpy:
  move $v0, $s2
  lw $ra, 0($sp)
  lw $s0, 4($sp)
  lw $s1, 8($sp)
  lw $s2, 12($sp)
  addiu $sp, $sp, 16

  jr $ra

strcmp:

  addiu $sp, $sp, -12
  sw $ra, 0($sp)
  sw $s0, 4($sp)
  sw $s1, 8($sp)

  move $s0, $a0
  move $s1, $a1

for_strcmp:

  lb $t0, 0($s0)
  lb $t1, 0($s1)

  bne $t0, $t1, end_strcmp
  beq $t1, $0, end_strcmp

  addiu $s0, $s0, 1
  addiu $s1, $s1, 1

end_strcmp:
  lb $t0, 0($s0)
  lb $t1, 0($s1)

  sub $v0, $t0, $t1

  lw $ra, 0($sp)
  lw $s0, 4($sp)
  lw $s1, 8($sp)
  addiu $sp, $sp, 12

  jr $ra
