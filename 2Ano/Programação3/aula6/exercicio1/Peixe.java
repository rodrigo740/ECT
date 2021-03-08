package aula6.exercicio1;

public class Peixe extends Alimento{
	
	private String nome;
	private TiposPeixe tipo;
	
	public Peixe(double peso, double calorias, double proteinas,String nome,TiposPeixe tipo) {
		super(peso, calorias, proteinas);
		this.nome = nome;
		this.tipo = tipo;
	}
	public String nome() {
		return this.nome;
	}
	public TiposPeixe tipo() {
		return this.tipo;
	}	
}
