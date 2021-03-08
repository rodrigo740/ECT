.data
.text
.globl main

main:

while:

  li $v0, 2
  syscall

  beq $v0, '\n', end

  move $a0, $v0
  li $v0, 3
  syscall

  j while

end:
  jr $ra
