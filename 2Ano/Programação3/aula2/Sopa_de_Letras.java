package aula2;

import static java.lang.System.*;
import java.util.Scanner;
import java.io.*;

public class Sopa_de_Letras {
	public static void main(String[] args) throws IOException{
		
		Scanner sc = new Scanner(in);
		
		out.print("Nome do ficheiro: ");
		String nome = sc.nextLine();
		
		Game jogo = new Game(nome);
		
	}
}
