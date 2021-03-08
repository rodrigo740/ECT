import java.io.*;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Teste {

	
	public static void main(String[] args) throws IOException{
		
		/*
		PrintWriter sig = new PrintWriter("sig");
		//System.out.println("Done");
		
		for (int j = 0; j < numHash; j++) {
			for (int j2 = 0; j2 < count; j2++) {
				sig.print(sigMatrix[j][j2] + " ");
			}
			sig.println();
		}
		sig.close();
		*/
		/*
		int bandas = 10;
		int linhas = 7;

		//System.out.println(r);
		//System.out.println(count/7);
		//System.exit(1);
		
		//System.out.println(sigMatrix[0].length);
		int p = nextPrime(count*count);
		
		int k1 = (int)(Math.random() * count*count);
		int k2 = (int)(Math.random() * count*count);
		int k3 = (int)(Math.random() * count*count);
		int k4 = (int)(Math.random() * count*count);
		int k5 = (int)(Math.random() * count*count);
		int k6 = (int)(Math.random() * count*count);
		int k7 = (int)(Math.random() * count*count);
				
		Hashtable<Integer, LinkedList<Integer>> hashTable = new Hashtable<Integer, LinkedList<Integer>>(count*count);
		
		//int paresCandidatos[] = new int[numShingles*2*count];
		PrintWriter pt = new PrintWriter("hashV");
		int r = 0;
		//int pares = 0;
		
		for (int j = 0; j < bandas; j++) {
			
			r = j*7;
			for (int j2 = 0; j2 < sigMatrix[0].length; j2++) {
				
				int hashV = 0;
				int vectorAux[] = new int[linhas];
				
				for (int j3 = 0; j3 < linhas; j3++) {
					vectorAux[j3] = sigMatrix[r][j2];
					
					r++;
				}
				
				hashV = (k1*vectorAux[0] + k2*vectorAux[1] + k3*vectorAux[2] + k4*vectorAux[3] + k5*vectorAux[4] + k6*vectorAux[5] + k7*vectorAux[6]) % p;  
				
				r = j*7;
				
				
				
				if (hashTable.get(hashV) != null) {
					LinkedList<Integer> auxList = hashTable.get(hashV);
					auxList.add(j2);
					hashTable.remove(hashV);
					hashTable.put(hashV,auxList);
					
					//System.out.println(j2);
					
				}else {
					LinkedList<Integer> list = new LinkedList<>();
					list.add(j2);
					hashTable.put(hashV,list);
				}
			}
		}
		
		pt.close();
		//System.out.println(hashTable.elements().toString());
		PrintWriter pwf = new PrintWriter("Teste");
		
		*/
		/*
		int pares = 0;
		for (int j = 0; j < hashTable.size(); j++) {
			//System.out.println(j);
			if (hashTable.get(j) != null) {
				if (hashTable.get(j).size() > 1) {
					pares++;
				}
			}
		}
		
		//System.out.println(pares);
		int paresCandidatos[] = new int[pares];
		for (int j = 0; j < hashTable.size(); j++) {
			if (hashTable.get(j) != null) {
				if (hashTable.get(j).size() > 1) {
					paresCandidatos[j] = j;
				}
			}
		}
		
		*/
		/*
		int par[] = new int[hashTable.size()*2];
		
		for (int k = 0; k < hashTable.size(); k++) {
			LinkedList<Integer> temp = hashTable.get(k);
			if (temp != null && temp.size() > 1) {
				par[k] = k;
				System.out.println(k);
			}
		}
		
		System.out.println(hashTable.size());
		
		for (int j = 0; j < par.length; j++) {
			//System.out.println(j);
			double js = 0;
			
			if (par[j] != 0) {
				LinkedList<Integer> temp = hashTable.get(j);
				//System.out.println(hashTable.get(j).toString());
				
				for (int j3 = 0; j3 < temp.size(); j3++) {
					//System.out.println(j3);
					
					Set<String> set1 = ShingleParser.splitToShingles(lines[temp.get(j3)], 3);
					//System.out.println(j3);
					
					for (int k = j3+1; k < temp.size(); k++) {
						Set<String> set2 = ShingleParser.splitToShingles(lines[temp.get(k)], 3);
						
						js = jaccardSimilarity(set1, set2);
						pwf.println(js);
						System.out.println(js);
						//System.out.println(k);
						
						
						if (js >= 0.1) {
							pwf.println("------------------------");
							pwf.println(lines[temp.get(j3)]);
							
							pwf.println(lines[temp.get(k)]);
							pwf.println("------------------------");
						}
					}
				}
				
			}
		}
		*/
		/*
		for (int j = 0; j < hashTable.size(); j++) {
			System.out.println(j);
			double js = 0;
			LinkedList<Integer> temp = hashTable.get(j);
			if (temp != null && temp.size() > 1) {
				//System.out.println(hashTable.get(j).toString());
				
				for (int j3 = 0; j3 < temp.size(); j3++) {
					//System.out.println(j3);
					
					Set<String> set1 = ShingleParser.splitToShingles(lines[temp.get(j3)], 3);
					//System.out.println(j3);
					
					for (int k = j3+1; k < temp.size(); k++) {
						Set<String> set2 = ShingleParser.splitToShingles(lines[temp.get(k)], 3);
						
						js = jaccards(set1, set2);
						pwf.println(js);
						//System.out.println(js);
						//System.out.println(k);
						
						
						if (js >= 0.7) {
							pwf.println("------------------------");
							pwf.println(lines[temp.get(j3)]);
							
							pwf.println(lines[temp.get(k)]);
							pwf.println("------------------------");
						}
					}
				}
				
			}
		}
		
		*/
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*
		for (int j = 0; j < hashTable.size(); j++) {
			if (hashTable.get(j) != null) {
				LinkedList<Integer> temp = hashTable.get(j);
				
				if (temp.size() > 1) {
					for (int k = 0; k < temp.size(); k++) {
						
						Set<String> set1 = ShingleParser.splitToShingles(file[temp.get(k)], 3);
						for (int l = k; l < temp.size(); l++) {
							Set<String> set2 = ShingleParser.splitToShingles(file[temp.get(l)], 3);
							int a[] = minhash.hashArray(set1);
							int b[] = minhash.hashArray(set2);
							double js = minhash.JaccardSimilarity(a, b);
							if (js >= 0.7) {
								pwf.println(file[temp.get(l)] + "\n" + file[temp.get(k)]);
								pwf.println("------------------");
							}
						}
					}
				}
				
			}
		}
		*/
		/*
		Iterator<Integer> setIterator = hashTable.keySet().iterator();
		
		while (setIterator.hasNext()) {
			System.out.println(file[hashTable.get(setIterator.next())]);
			
		}
		*/
		
		
		
		
		
		
		
		
		
		
		
		

		//pwf.close();
		
		
			
		/*
		double js[][] = new double[count][count];
		
		for (int j = 0; j < minHashValues.length; j++) {
			int a[] = new int[count];
			int b[] = new int[count];
			if (j == minHashValues.length-1) {
				break;
			}
			for (int j2 = 0; j2 < minHashValues.length; j2++) {
				a[j2] = minHashValues[j2][j];
			}
			double value;
			for (int j2 = 0; j2 < minHashValues.length; j2++) {
				b[j2] = minHashValues[j2][j+1];
				
				value = minhash.JaccardSimilarity(a, b);
				js[j2][j] = value;
				
				if (value >= 0.0) {
					System.out.println(j2 + "		" + j);
				}
			}
			
		}
		*/
		
	}
	
	
	
	
	
	
	public static int[][] hashCalc(int numHash,int rows) {
		
		int hash[][] = new int[rows][numHash];
		
		
		for (int i = 0; i < numHash; i++) {
			int a = Integer.MAX_VALUE;
			int b = Integer.MAX_VALUE;
			
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
	
	public static double jaccards(Set<String> set1, Set<String> set2) {
		
		Set<String> interception = new TreeSet<String>();
		interception.addAll(set1);
		interception.retainAll(set2);
		
		Set<String> union = new TreeSet<String>();
		union.addAll(set1);
		union.addAll(set2);
		
		double js = (double)interception.size()/(double)union.size();
		
		return js;
	}
	
	
	
	
	
	
	
	
	

}
