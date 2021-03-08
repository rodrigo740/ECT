package aula6.exercicio1;

import java.util.LinkedList;

public class PratoVegetariano extends Prato {
	
	private LinkedList<AlimentoVegetal> composicao;
	private double totalCalorias = 0;
	
	public PratoVegetariano(String nome) {
		super(nome);
		this.composicao = new LinkedList<>();
	}

}
