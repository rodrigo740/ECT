package exercicio3.utils;

public class Condutor extends Passageiro{
	
	private Carta carta;
	
	public Condutor(String nome,int cc,Data dataNasc,Carta carta) {
		super(nome,cc,dataNasc);
		this.carta = carta;
	}
	public Carta carta() {
		return this.carta;
	}
}
