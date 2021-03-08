package aula4.exercicio1;

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
		return super.toString() + ", Bolseiro com bolsa no valor de: " + String.valueOf(bolsa());
	}
}
