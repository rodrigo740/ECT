  .data
msg:  .asciiz "Key Pressed\n"
  .text
  .globl main

main:

while:

while_wait:
  li $v0, 1
  syscall
  beqz $v0, while_wait

  beq $v0, '\n', end

  li $v0, 8
  la $a0, msg
  syscall

  j while

end:
  jr $ra
