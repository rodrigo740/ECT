package aula2;

import static java.lang.System.*;
import java.util.*;
import java.io.*;

public class Game {
	
	private char[][] grelha;
	private ArrayList<String> palavras;
	private int larguraGrelha;
	
	public Game(String file) throws FileNotFoundException {
		
		grelha = new char[80][80];
		palavras = new ArrayList<String>();
		
		File f = new File(file);
		Scanner fin = new Scanner(f);
		
		String fl = fin.nextLine();
		larguraGrelha = fl.length();
		
		int a = 0;
		for(int i = 0; i < larguraGrelha;i++) {
			grelha[a][i] = fl.charAt(i);
		}
		
		int linha = 0;
		
		while (fin.hasNextLine()) {
			String l = fin.nextLine();
			
			if (l.contains(",")) {
				for (String str : l.split(",")) {
					palavras.add(str.toUpperCase());
				}
			}else {
				for(int i = 0; i < larguraGrelha; i++) {
					grelha[linha][i] = l.charAt(i);
				}
				linha++;
			}
		}
	}
}
