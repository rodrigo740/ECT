package aula10.exercicio1;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ListaGeneric<T> {
	
	private List<T> elems = new LinkedList<>();
	
	public boolean addElem(T elem) {
		return elems.add(elem);
	}
	public boolean removeElem(T elem) {
		return elems.remove(elem);
	}
	public int totalElem() {
		return elems.size();
	}
	
	public Iterator iterator() {
		return elems.iterator();
	}

}
