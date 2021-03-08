package aula5.exercicio2;

public class MotoPolicia extends Moto implements Policia{
	
	private String id;
	
	
	public MotoPolicia(String cor, int numRodas, String matricula, int velocidadeMax, int cilindrada, int ano,int potencia, double consumo, double combustivel, boolean atrelado,String id) {
		super(cor, numRodas, matricula, velocidadeMax, cilindrada, ano, potencia, consumo, combustivel, atrelado);
		this.id = id;
	}

	public Tipo getTipo() {	
		return Tipo.PSP;
	}
	public String getID() {
		return this.id;
	}
	@Override
	public String toString() {
		return "Moto Policia com id: " + id + ", do tipo: " + getTipo();
	}
}
