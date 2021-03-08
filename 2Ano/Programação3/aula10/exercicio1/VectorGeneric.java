package aula10.exercicio1;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class VectorGeneric<T> implements Iterable<T>{
	
	private Object elems[];
	private int size;
	private VectorIterator iterator;
	
	public VectorGeneric(int s) {
		elems = new Object[s];
		size = 0;
		iterator = new VectorIterator();
	}
	
	public VectorGeneric() {
		elems = new Object[10];
		size = 0;
		iterator = new VectorIterator();
	}

	public boolean addElem(T elem) {
		
		canAdd(size+1);
		elems[size+1] = elem;
		return true;
	}
	public boolean removeElem(T elem) {
		
		for (int i = 0; i < elems.length; i++) {
			if (elems[i].equals(elem)) {
				Object temp[] = elems;
				
				elems[i] = null;
				System.arraycopy(temp, i+1, elems, i+1, temp.length-i); 
				return true;
			}
		}
		
		return false;
	}
	public int totalElem() {
		return size;
	}
	
	public Iterator iterator() {
		return iterator;
	}
	
	public void canAdd(int s) {
		
		if (s >= elems.length) {
			int capacidadeMinima = elems.length;
			int tamanho = capacidadeMinima + 10;
			
			elems = Arrays.copyOf(elems, tamanho);
		}
	}
	
	private class VectorIterator implements Iterator<T>{
		
		int p = 0;
		
		@Override
		public boolean hasNext() {
			return p < size;
		}

		@Override
		public T next() {
			int i = p;
			if (i >= p) {
				throw new NoSuchElementException();
			}
			p = i+1;
			
			return (T) elems[i];
		}
	}
}


