package exercicio3.utils;

import java.util.ArrayList;

public class PesadoDeMercadorias extends Veiculo{
	
	private ArrayList<String> mercadorias;
	static Carta carta = new Carta("C");
	
	public PesadoDeMercadorias(double cilindrada,double potencia,double peso,int lotacao,Condutor condutor) {
		super(carta,cilindrada,potencia,peso,lotacao,condutor);
		this.mercadorias = new ArrayList<>();
	}
	public ArrayList<String> mercadorias() {
		return this.mercadorias;
	}
	public void addMercadoria(String mercadoria) {
		this.mercadorias.add(mercadoria);
	}
	public void removeMercadoria(String mercadoria) {
		if (mercadorias.size()==0) {
			System.out.println("Não tem mercadorias");
			return;
		}
		
		this.mercadorias.remove(mercadoria);
	}
}
