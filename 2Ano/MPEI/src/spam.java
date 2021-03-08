import java.io.*;
import java.util.Scanner;
public class spam {
	
		private String nome; // nome do ficheiro original
		private String nomec; // nome do ficheiro que contem os comentarios copiados a usar  
		private String lines[]; // array de comentarios a posicao do array e a linha no ficheiro
		private String linescopy[]; // array de comentarios copiados a posicao do array e a linha no ficheiro
		private String pessoas[]; // array com as pessoas que fizeram os comentarios origirais
		private String pessoascopy[]; // array com as pessoas que tem comentarios iguais
		private int count=0; // contar as linhas que existe - ficheiro origiral
		private int cont=0; //contador das linhas do ficheiro que contem comentarios copiados
		private int k=6; //hash function
		private int n = (int)1e6; //tamanho do bloom filter
		private BloomFilter element = new BloomFilter(n,k);
		
		public spam(String nome, String nomec) {
			this.nome=nome;
			this.nomec=nomec;
		}
		
		//ler os ficheiros
		public void lerficheiros() throws IOException{
			//Ficheiro com todas reviews (originais)
			Scanner lerfich = new Scanner(new File (nome));
			
			while(lerfich.hasNextLine()) {
				String s = lerfich.nextLine();
				count++;
			}
			lines=new String [count];
			pessoas = new String[count];
			
			lerfich = new Scanner(new File (nome));
			
			for(int i=0 ;i<lines.length;i++) {
				
				String aux[] = lerfich.nextLine().split(";-;");
				lines[i]=aux[1];
				pessoas[i]=aux[0];
			}
			
			lerfich.close();
			
			
			// Ficheiro das reviews iguais as outras (copiadas) - para comparar (spam) 
			Scanner lercopy = new Scanner(new File(nomec));
			
			while(lercopy.hasNextLine()) {
				String s = lercopy.nextLine();
				cont++;
			}
			
			linescopy=new String [cont];
			pessoascopy = new String[cont];
			lercopy = new Scanner(new File (nomec));
			
			for(int i=0 ;i<linescopy.length;i++) {
				
				String aux[] = lercopy.nextLine().split(";-;");
				linescopy[i]=aux[1];
				pessoascopy[i] =aux[0];
			}
			
			lercopy.close();
		}
		
		// Detetar o spam
		public void detetarspam() throws IOException{
			System.out.println();
			PrintWriter print1= new PrintWriter("Spam");
			
			for(int i =0;i<lines.length;i++) {
				element.addElement(lines[i]);			
			}
			System.out.println("Elementos inseridos com sucesso");
			System.out.println();
			System.out.println("Resultado de spam: ");
			int num=0;
			for(int i = 0;i<linescopy.length;i++) {
				if(element.membro(linescopy[i])) {
					num++;
					print1.println("Comentario criado por " + pessoascopy[i] + ": \n" + linescopy[i]+" -> possivelmente spam ");
					System.out.println("Comentario criado por " + pessoascopy[i] + ": \n" + linescopy[i]+" -> possivelmente spam ");
				}
			}
			System.out.println();
			System.out.println("Elementos verificados com sucesso");
			System.out.println();
			System.out.printf("Probabilidade de falsos positivos: %.4f%%\n",100*((double)num/(double)lines.length));
			System.out.println();
			print1.close();

		}
}