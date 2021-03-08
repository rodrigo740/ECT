package aula9.exercicio3;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class VectorPessoas {
	
	private Pessoa pessoas[];
	private int size;
	private VectorIterator iterator;
	
	public VectorPessoas(int s) {
		pessoas = new Pessoa[s];
		size = 0;
		iterator = new VectorIterator();
	}
	
	public VectorPessoas() {
		pessoas = new Pessoa[10];
		size = 0;
		iterator = new VectorIterator();
	}

	public boolean addPessoa(Pessoa p) {
		
		canAdd(size+1);
		pessoas[size+1] = p;
		return true;
	}
	public boolean removePessoa(Pessoa p) {
		
		for (int i = 0; i < pessoas.length; i++) {
			if (pessoas[i].equals(p)) {
				Pessoa temp[] = pessoas;
				
				pessoas[i] = null;
				System.arraycopy(temp, i+1, pessoas, i+1, temp.length-i); 
				return true;
			}
		}
		
		return false;
	}
	public int totalPessoas() {
		return size;
	}
	
	public Iterator iterator() {
		return iterator;
	}
	
	public void canAdd(int s) {
		
		if (s >= pessoas.length) {
			int capacidadeMinima = pessoas.length;
			int tamanho = capacidadeMinima + 10;
			
			pessoas = Arrays.copyOf(pessoas, tamanho);
		}
	}
	
	private class VectorIterator implements Iterator{
		
		int p = 0;
		
		@Override
		public boolean hasNext() {
			return p < size;
		}

		@Override
		public Object next() {
			int i = p;
			if (i >= p) {
				throw new NoSuchElementException();
			}
			p = i+1;
			
			return (Pessoa) pessoas[i];
		}
	}
}


