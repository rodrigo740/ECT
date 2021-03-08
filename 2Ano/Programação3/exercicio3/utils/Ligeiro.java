package exercicio3.utils;

public class Ligeiro extends Veiculo{
	
	private String cor;
	static Carta carta = new Carta("B");
	
	public Ligeiro(double cilindrada,double potencia,double peso,int lotacao,Condutor condutor,String cor) {
		super(carta,cilindrada,potencia,peso,lotacao,condutor);
		this.cor = cor;
	}
	public String cor() {
		return this.cor;
	}

}
