package exercicio3.utils;

public class Motociclo extends Veiculo{
	
	private boolean atrelado;
	static Carta carta = new Carta("A");
	
	public Motociclo(double cilindrada,double potencia,double peso,int lotacao,Condutor condutor,boolean atrelado) {
		super(carta,cilindrada,potencia,peso,lotacao,condutor);
		this.atrelado = atrelado;
	}
	public boolean atrelado() {
		return this.atrelado;
	}

}
