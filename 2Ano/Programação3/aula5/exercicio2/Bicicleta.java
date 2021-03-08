package aula5.exercicio2;

public class Bicicleta extends Veiculo{

	private int ano;
	
	public Bicicleta(String cor, int numRodas,int ano) {
		super(cor, numRodas);
		this.ano = ano;
	}
	public int ano() {
		return this.ano;
	}
	
	@Override
	public String toString() {
		return "Bicicleta do ano: " + ano;
	}
}
