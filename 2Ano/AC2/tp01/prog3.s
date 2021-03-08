# int main(void)
# {
# char c;
# do {
# 	while( (c = inkey()) == 0 );
# 		if( c != '\n' )
# 			putChar( c );
# } while( c != '\n' );
# return 0;
# }

	.equ inkey,1
	.equ putChar,3
	.data
	.text
	.globl main
main:

do:	li $v0,1
	syscall
	move $t0,$v0

while:	beq $t0,0,do

if:	beq $t0,'\n',whileOut
	move $a0,$t0	
	li $v0,putChar
	syscall

whileOut:	bne $a0,'\n',do

		li $v0,0
		jr $ra
