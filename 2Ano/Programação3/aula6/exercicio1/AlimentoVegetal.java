package aula6.exercicio1;

public class AlimentoVegetal extends Alimento{
	
	private String nome;
	
	public AlimentoVegetal(double peso, double calorias, double proteinas,String nome) {
		super(peso, calorias, proteinas);
		this.nome = nome;
	}
	public String nome() {
		return this.nome;
	}
}
