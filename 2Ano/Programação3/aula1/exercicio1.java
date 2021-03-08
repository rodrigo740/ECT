package aula1;

import static java.lang.System.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class exercicio1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s;
		
		while(true) {
			try {
				out.print("Introduza a frase para analisar:");
				s = sc.nextLine();
				break;
			}catch(InputMismatchException e) {
				out.println("Erro: Não introduziu uma frase válida");
			}
		}
		
		int numCount = 0;
		for(int i=0; i<s.length();i++) {
			char c = s.charAt(i);
			if(Character.isDigit(c)) {
				numCount++;
			}
		}
		
		out.printf("A frase possui %d caracteres numéricos\n",numCount);
		
		if(LowerCheck(s)) {
			out.println("A frase está escrita em minúsculas");
		}
		else if(UpperCheck(s)) {
			out.println("A frase está escrita em maiúsculas");
		}
		
		out.printf("A frase \"%s\" tem %d palavras\n",s, WordCount(s));
		out.printf("Frase com os caracteres trocados: %s\n",Scramble(s));
		
		
	}
	public static boolean LowerCheck(String str) {
			
		for(int i=0; i<str.length();i++) {
			char c = str.charAt(i);
			if(!Character.isLowerCase(c)) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean UpperCheck(String str) {
		
		for(int i=0; i<str.length();i++) {
			char c = str.charAt(i);
			if(!Character.isUpperCase(c)) {
				return false;
			}
		}
		return true;
	}
	
	public static int WordCount(String str) {
		String[] parts = str.split(" ");
		int words = parts.length;
		return words;
	}
	
	public static String Scramble(String str) {
		String[] parts = str.split(" ");
		String temp = "";
		
		for(String s : parts) {	
			for(int i=0; i<s.length()-1;i+=2) {
				temp += String.valueOf(s.charAt(i+1)) + String.valueOf(s.charAt(i));
			}
			if(s.length()%2 != 0) {
				temp += s.charAt(s.length()-1);
			}
		}
		return temp;
	}
}
