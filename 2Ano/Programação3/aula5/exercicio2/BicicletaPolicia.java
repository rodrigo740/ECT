package aula5.exercicio2;

public class BicicletaPolicia extends Bicicleta implements Policia{

	private String id;
	
	public BicicletaPolicia(String cor, int numRodas, String terreno,String id,int ano) {
		super(cor, numRodas, ano);
		this.id = id;	
	}
	public Tipo getTipo() {
		return Tipo.PJ;
	}
	public String getID() {
		return this.id;
	}
	@Override
	public String toString() {
		return "Bicicleta Policia com id: " + id + ", do tipo: " + getTipo();
	}
}
