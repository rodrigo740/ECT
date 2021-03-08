package aula5.exercicio2;

public class CarroPolicia extends Automovel implements Policia{
	
	private String id;
	
	public CarroPolicia(String cor, int numRodas, String matricula, int velocidadeMax, int cilindrada, int ano,String id) {
		super(cor, numRodas, matricula, velocidadeMax, cilindrada, ano);
		this.id = id;
	}
	
	public Tipo getTipo() {
		return Tipo.GNR;
	}
	public String getID() {
		return this.id;
	}
	@Override
	public String toString() {
		return "Carro Policia com id: " + id + ", do tipo: " + getTipo();
	}
}
