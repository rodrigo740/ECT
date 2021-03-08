import static java.lang.System.*;

import java.util.Random;

public class Test_BloomFilter {
	
	public static void main(String[] args) {
		
		int k=6; //hash function ---------melhor valor para menos falsos positivos (n/m=124)
		int n = (int)1e6; //tamanho do bloom filter
		String [] distritos = {"Aveiro", "Porto", "Lisboa", "Algarve","Santarem","Beja", "Bragança"};
		String [] distritos1 = {"Aveiro", "Alentejo","Algarve","Faro", "Setubal","Viseu", "Beja"};
		int m= distritos.length ;//numero de elementos a introduzir
		BloomFilter BF = new BloomFilter(n,k);
		 
		//inserir elementos
		out.println("---------------Inserir elementos------------------");
		out.println("Foram inseridos os distritos: ");
		for(int i =0;i<m;i++) {
			BF.addElement(distritos[i]);
			out.println(distritos[i]);
		}
		
		out.println();
		out.println("--------------Distritos a comparar-----------------");
		for(int i =0;i<distritos1.length;i++) {
			out.println(distritos1[i]);
		}
		
		//verificar se os elementos sao membros
		out.println();
		out.println("---------------Verificar membros------------------");
		for(int i=0;i<m;i++) {
			if(BF.membro(distritos1[i])) {
				out.println(distritos1[i]+" possivelmente e membro");
			}else {
				out.println(distritos1[i]+" nao e membro");
			}
		}
		
		
		//Verificar para strings aleatorias
		//as duas strings geradas vao ser sempre diferentes,logo se o teste de positivo e um falso positivo
		//a probabilidade de falsos positivos e o numero de falsos positivos a dividir pelo numero dos elementos todos *100
		out.println();
		out.println("--------Verificar com strings aleatorias-----------");
		
		// 1º inserimos no bloom filter e so depois testamos
		int count=0; //contagem positivos
		int M=10000;
		for (int i = 0; i < M; i++) {
			String aleatoria = RandomStrings();
			BF.addElement(aleatoria);
		}
		for(int j=0;j<M;j++) {
			String aleatoria1 = RandomStrings();
			if(BF.membro(aleatoria1)) {
				count++;
			}
		}
		out.printf("Probabilidade de falsos positivos: %.4f%%",100*((double)count/(double)M));
		

	} 
	
	//Gerar strings aleatorias
	//https://respostas.guj.com.br/44574-gerar-string-aleatoria-com-ramdom
	//https://www.devmedia.com.br/forum/funcao-que-gera-string-aleatoria/568082
	public static String RandomStrings() {
		String letras = "ABCDEFGHIJKLMNOPQRSTUVYWXZ";
		Random random = new Random();
		String s="";
		int index = -1;
		int length = (int)(Math.random()*100);  //tamanho das palavras geradas
		for(int i=0;i<length;i++) {
			index = random.nextInt(letras.length());
			s += letras.substring(index, index+1);
		}
		return s;
	}
}
