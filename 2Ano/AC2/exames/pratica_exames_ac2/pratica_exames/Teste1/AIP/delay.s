  .data
  .text
  .globl main

main:
  # recebe n_intervals
  move $s0, $a0 #saving

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
  jr $ra
