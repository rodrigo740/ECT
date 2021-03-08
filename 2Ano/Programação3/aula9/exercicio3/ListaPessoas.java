package aula9.exercicio3;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ListaPessoas {
	
	private List<Pessoa> pessoas = new LinkedList<>();
	
	public boolean addPessoa(Pessoa pessoa) {
		return pessoas.add(pessoa);
	}
	public boolean removePessoa(Pessoa pessoa) {
		return pessoas.remove(pessoa);
	}
	public int totalPessoas() {
		return pessoas.size();
	}
	
	public Iterator iterator() {
		return pessoas.iterator();
	}

}
