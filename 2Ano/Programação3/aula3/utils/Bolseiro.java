package aula3.utils;

public class Bolseiro extends Estudante{
	
	private int valorBolsa;

	public Bolseiro(String nome, int cc, Data dataNasc) {
		super(nome, cc, dataNasc);
	}
	public void setBolsa(int v) {
		this.valorBolsa = v;
	}
	public int bolsa() {
		return this.valorBolsa;	
	}
	@Override
	public String toString() {
		return super.toString() + ", Bolsa: " + String.valueOf(bolsa());
	}
}
