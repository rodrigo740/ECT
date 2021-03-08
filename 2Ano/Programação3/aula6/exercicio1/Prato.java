package aula6.exercicio1;

import java.util.LinkedList;

public class Prato implements Comparable<Prato>{
	
	private String nome;
	private LinkedList<Alimento> composicao;
	private double totalCalorias = 0;
	
	public Prato(String nome) {
		this.nome = nome;
		this.composicao = new LinkedList<>();
	}
	public double totalCalorias() {
		return this.totalCalorias;
	}
	public void addFood(Alimento a) {
		this.composicao.add(a);
		this.totalCalorias += a.calorias();
	}
	
	public int compareTo(Prato pra) {
		if (this.totalCalorias() < pra.totalCalorias()) {
			return -1;
		} else if(this.totalCalorias() == pra.totalCalorias()){
			return 0;
		}else {
			return 1;
		}
	}

}
