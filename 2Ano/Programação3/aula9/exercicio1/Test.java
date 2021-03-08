package aula9.exercicio1;

import java.io.*;

public class Test {

	public static void main(String[] args) throws IOException{
		
		ScannerAbeirense scannerTeclado = new ScannerAbeirense(System.in);
		
		
		
		System.out.print("Frase para trocar os caracteres: ");
		String string = scannerTeclado.nextLine();
		
		System.out.println("String com os caracteres alterados: " + string);
		
		System.out.print("Insira o nome do ficheiro: ");
		String s = scannerTeclado.nextLine();
		
		scannerTeclado.close();
		
		try {
			File f = new File(s);
			ScannerAbeirense fin = new ScannerAbeirense(f);
			
			System.out.println("Conteudo do ficheiro com os caracteres alterados: ");
			
			while (fin.hasNext()) {
				String str = fin.next();
				
				System.out.println(str);	
			}
			
			fin.close();
		} catch (Exception e) {
			System.err.println(e);
		}
		
		
		

	}

}
