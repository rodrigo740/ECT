package aula7.exercicio1;

import java.io.*;
import java.util.Scanner;
import java.time.LocalTime;

public class Test {

	public static void main(String[] args) throws IOException{
		
		File fileV = new File("aula7_material/voos.txt");
		Scanner finV = new Scanner(fileV);
		
		String str = "";
		int i = 0;
		
		String lines[] = new String[94];
		String atrasos[] = new String[94];
		
		//System.out.println("Hora Voo Companhia Origem Atraso Obs");
		
		while (finV.hasNextLine()) {
			
			str = finV.nextLine();
			
			lines[i] = str;
			
			//System.out.println(lines[i]);
				
			
			
			String parts[] = lines[i].split("\t");
			if (parts.length == 4) {
				atrasos[0] = parts[3];
			}
			
			
			System.out.println(parts[0] + "\t" + parts[1] +  " Sigla " +"\t" + parts[2]);
			
			
			i++;
			
			//System.out.println(parts[0] + "\t" + parts[1] + "\t" + siglaCheck(parts[2]) + "\t" + parts[3] + "\t" + parts[4]);
			
		}
		
		finV.close();
		
	}
	public static String siglaCheck(String sigla) throws IOException{
		
		String companhia = "";
		String s;
		
		File fileC = new File("aula7_material/companhias.txt");
		Scanner finC = new Scanner(fileC);
		
		while (finC.hasNextLine()) {
			
			s = finC.next();
			if (s.contentEquals(sigla)) {
				companhia = finC.nextLine();
				return companhia;
			}
			
		}
		
		finC.close();
		return companhia;
	}

}
