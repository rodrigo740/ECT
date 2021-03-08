package aula6.exercicio1;

public class Carne extends Alimento{

	private String nome;
	
	public Carne(double peso, double calorias, double proteinas,String nome) {
		super(peso, calorias, proteinas);
		this.nome = nome;
	}
	public String nome() {
		return this.nome;
	}

}
