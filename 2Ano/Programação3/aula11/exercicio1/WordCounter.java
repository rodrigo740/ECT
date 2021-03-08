package aula11.exercicio1;

import java.io.File;
import java.io.*;
import java.util.HashSet;
import java.util.Scanner;

public class WordCounter {
	
	public static void main(String[] args) throws IOException{
		
		Scanner sc = new Scanner(System.in);
		HashSet<String> hSet = new HashSet<String>();
		
		System.out.print("Nome do Ficheiro: ");
		File f = new File(sc.nextLine());
		
		Scanner fin = new Scanner(f);
		int count = 0;
		
		while (fin.hasNext()) {
			hSet.add(fin.next());
			count++;
		}
		
		System.out.println("Número Total de Palavras: " + count);
		System.out.println("Número de Diferentes Palavras:" + hSet.size());
		
		
	}

}
