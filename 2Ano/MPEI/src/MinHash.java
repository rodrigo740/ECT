import java.io.*;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class MinHash {
	
	private String fileName;
	private double indice;
	
	public MinHash(String fileName, double indice) {
		this.fileName = fileName;
		this.indice = indice;
	}
		
	public String[] compareSignatures() throws IOException{
		
		// Guarda num array todos os usernames do jogo
		File f = new File("reviews/" + this.fileName);
		Scanner fin = new Scanner(f);
		
		int count1 = 0;
		String a[] = new String[90000];
		
		while (fin.hasNextLine()) {
			String s = fin.nextLine();
			a[count1] = s;
			count1++;
		}
		
		fin = new Scanner(f);
		String usernames[] = new String[count1];
		int po = 0;
		
		while (fin.hasNextLine()) {
			String temp[] = fin.nextLine().split(";-;");
			usernames[po] = temp[0];
			po++;
		}
		
		
		// Guarda os comentarios num ficheiro de texto
		
		long start = System.currentTimeMillis();
		
		String lines[] = readFile(this.fileName);
		
		PrintWriter p = new PrintWriter("Comentarios_" + fileName);
		
		for (int i = 0; i < lines.length; i++) {
			p.println(lines[i]);
		}
		p.close();
		
		
		System.out.println("Ficheiro lido! - 5%");
		
		int count = lines.length;
		
		int sigMatriz[][] = matrizAssinaturas(lines, count);
		
		System.out.println("Matriz de assinaturas concluida! - 25%");
		
		PrintWriter pw = new PrintWriter("comentarios_similares/comentarios_" + fileName);
		
		String[] similarComments = new String[count*2];
		int index = 0;
		
		for (int j = 0; j < count; j++) {
			if (j == count/2) {
				System.out.println("Comparações a 50%");
			}
			for (int k = j+1; k < count; k++) {
				int similar = 0;
				for (int j2 = 0; j2 < sigMatriz.length; j2++) {
					if (sigMatriz[j2][j] == sigMatriz[j2][k]) {
						similar++;
					}
				}
				
				double similaridade = (double)similar/sigMatriz.length;
				
				
				if (similaridade >= this.indice) {
					String s = ("Comentário 1 \nUsername: "  + usernames[j] + "\nReview: " + lines[j] + "\n------\n" + "Comentário 2 \nUsername: "  + usernames[k] + "\nReview: " + lines[k] + "\nSimilaridade: " + similaridade + "\n-----------------------------------------\n");
					pw.print(s);
					similarComments[index] = s;
					index++;
				}	
			}	
		}
		pw.close();
		
		
		System.out.println("Reviews analisadas! - 100%");
		long finish = System.currentTimeMillis();
		long timeElapsed = finish - start;
		System.out.println("Tempo decorrido em segundos: " + timeElapsed/1000 + "\n");
		
		
		return similarComments;
	}
		
	public String[] readFile(String filename) throws IOException{
		
		
		// Guarda num array os comentários do jogo, para depois ser feita a comparação
		
		File f = new File("reviews/" + filename);
		Scanner fin = new Scanner(f);
		
		int count = 0;
		String file[] = new String[90000];
		
		while (fin.hasNextLine()) {
			String s = fin.nextLine();
			file[count] = s;
			count++;
		}
		
		fin = new Scanner(f);
		String lines[] = new String[count];
		int i = 0;
		
		while (fin.hasNextLine()) {
			String temp[] = fin.nextLine().split(";-;");
			lines[i] = temp[1];
			i++;
		}
		return lines;
	}
	
	
	public int[][] matrizAssinaturas(String[] lines, int count) throws IOException{
		
		int numShingles = 0;
		Set<String> set = new TreeSet<String>();
		PrintWriter pwf = new PrintWriter("matriz_Assinaturas/matriz_" + fileName);
		
		for (int j = 0; j < lines.length; j++) {
			Set<String> set1 = ShingleParser.splitToShingles(lines[j], 3);
			
			set.addAll(set1);
		}
		
		numShingles = set.size();
		
		
		// Criação da matriz de '0' e '1' que representa a existencia('1') 
		// de um determinado shingle no comentario ou nao('0')
		int boolArray[][] = new int[numShingles][count];
				
		for (int j = 0; j < lines.length; j++) {
			Iterator<String> setIterator = set.iterator();
			
			for (int j2 = 0; j2 < set.size(); j2++) {
				if (lines[j].contains(setIterator.next())) {
					boolArray[j2][j] = 1;
				}else {
					boolArray[j2][j] = 0;
				}
			}
		}
		
		
		
		
		// Criação da Matriz de Assinaturas
		int numHash = 70;
		int hashRows[][] = hashCalc(numHash, numShingles);
		int sigMatrix[][] = new int[numHash][count];
		
		for (int j = 0; j < numHash; j++) {
			for (int j2 = 0; j2 < count; j2++) {
				sigMatrix[j][j2] = Integer.MAX_VALUE;
			}
		}
		
		for (int j = 0; j < numShingles; j++) {
			for (int j2 = 0; j2 < count; j2++) {
				if (boolArray[j][j2] == 1) {
					for (int k = 0; k < numHash; k++) {
						if (hashRows[j][k] < sigMatrix[k][j2]) {
							sigMatrix[k][j2] = hashRows[j][k];
						}
					}
				}
			}
		}
		
		for (int i = 0; i < sigMatrix.length; i++) {
			for (int j = 0; j < sigMatrix[0].length; j++) {
				pwf.print(sigMatrix[i][j] + " ");
			}
			pwf.println();
		}
		
		return sigMatrix;
	}
		
	
	
	public static int[][] hashCalc(int numHash,int rows) {
		
		int hash[][] = new int[rows][numHash];
		
		
		for (int i = 0; i < numHash; i++) {
			int a = rows+1;
			int b = rows+1;
			
			while (a >= rows || b >= rows) {
				a = (int) (Math.random()*rows);
				b = (int) (Math.random()*rows);
			}
			
			int c = nextPrime(rows);
			
			for (int j = 0; j < rows; j++) {
				hash[j][i] = (a*j+b)%c;
			}
		}
		
		return hash;
	}
	
	
	
	public  static int nextPrime(int input) {

		input++;
		//now find if the number is prime or not

		for(int i=2;i<input;i++) {
			if(input % i ==0  ) {
				input++;
				i=2;
			}
		}
		return input;
	}
	
	
}
