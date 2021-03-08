#void main(void)
#{
# int counter = 0;
# while(1)
# {
# while(readCoreTimer() < 200000);
# resetCoreTimer();
# printInt(++counter, 10);
# putChar(' '); // space
# }
#} 

        .equ READ_CORE_TIMER,11
        .equ RESET_CORE_TIMER,12
        .equ PUT_CHAR,3
        .equ PRINT_INT,6
        .data
        .text
        .globl main
main:   li $t0,0
#wout:
#while:  #li $v0,READ_CORE_TIMER      # while (1) {
        #syscall                     #
        #blt $v0,20000000000,while          # while(readCoreTimer() < 200000);
        #li $v0, RESET_CORE_TIMER
        #syscall                     # resetCoreTimer();
        li $a0,1000
        jal delay
        li $a0,' ' 
        li $v0,PUT_CHAR 
        syscall                     # putChar(' ');
        addi $t0,$t0,1 
        move $a0,$t0 
        li $a1,10 
        li $v0, PRINT_INT
        syscall  
        li $a0,' '                    # printInt(++counter, 10);
        li $v0,PUT_CHAR 
        syscall

        #j wout

        jr $ra

delay:  subu $sp,$sp,4
        sw $ra,0($sp)
for:    bgt $a0,0, endfor
        li $v0, RESET_CORE_TIMER
        syscall 

while2:  li $v0,READ_CORE_TIMER      # while (1) {
        syscall                      #
        blt $v0,20000,while2
        subu $a0,$a0,1
        j for

        
endfor:  lw $ra,0($sp)
         addiu $sp,$sp,4
         jr $ra

