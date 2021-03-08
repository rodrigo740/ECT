/* O segundo exercício é para contar de 0 a 20 hexadecimal
usando os displays para treinar como se configura (10 portos) quais são os portos para ligar (8 e 9) o resto é para os códigos.
Ver a função send2displays porque pode ser pedida */

#include<detpic32.h>
void delay(unsigned int);
void send2displays(unsigned char);

void main(void) {
  char counter = 0;
  while(1){

  int i = 0;
    do{
      delay(1000);
      send2displays(counter);
    } while(++i < 4);
    if(counter==20){
      counter = 0;
    }else{
      counter++;
    }

  }
}
