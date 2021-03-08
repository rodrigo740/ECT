package aula9.exercicio1;

import java.io.*;
import java.util.*;

public class ScannerAbeirense implements Iterator<String>, Closeable{

	private static Scanner sc; 
	
	public ScannerAbeirense(File f) throws IOException {
		sc = new Scanner(f);
	}
	public ScannerAbeirense(String str) {
		sc = new Scanner(str);
	}
	public ScannerAbeirense(InputStream arg) {
		sc = new Scanner(arg);
	}

	@Override
	public boolean hasNext() {
		return sc.hasNext();
	}

	@Override
	public String next() {
		
		String next = sc.next();
		next = next.replace('v', 'b');
		next = next.replace('V', 'B');
		
		return next;
	}
	
	public String nextLine() {
		
		String nextLine = sc.nextLine();
		nextLine = nextLine.replace('v', 'b');
		nextLine = nextLine.replace('V', 'B');
		
		return nextLine;
	}

	@Override
	public void close() throws IOException {
		sc.close();
	}

}
  