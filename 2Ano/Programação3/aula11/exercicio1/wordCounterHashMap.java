package aula11.exercicio1;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class wordCounterHashMap {

	public static void main(String[] args) throws IOException{
		
		Scanner sc = new Scanner(System.in);
		HashMap<String, Integer> hMap = new HashMap<String, Integer>();
		
		System.out.print("Nome do Ficheiro: ");
		File f = new File(sc.nextLine());
		
		Scanner fin = new Scanner(f);
		int count = 0;
		
		while (fin.hasNext()) {
			String s = fin.next();
			if (hMap.containsKey(s)) {
				hMap.put(s, hMap.get(s)+1);
			}else {
				hMap.put(s, 1);
			}	
		}
		
		Set<String> set = hMap.keySet();
		
		for (String string : set) {
			System.out.println(string + "\t\t" + hMap.get(string));
		}
		

	}

}
