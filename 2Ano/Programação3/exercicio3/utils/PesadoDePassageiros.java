package exercicio3.utils;

import java.util.ArrayList;

public class PesadoDePassageiros extends Veiculo{
	
	private ArrayList<String> paragens;
	static Carta carta = new Carta("D");
	
	public PesadoDePassageiros(double cilindrada,double potencia,double peso,int lotacao,Condutor condutor) {
		super(carta,cilindrada,potencia,peso,lotacao,condutor);
		this.paragens = new ArrayList<>();
	}
	public ArrayList<String> paragens() {
		return this.paragens;
	}
	public void addParagens(String paragem) {
		this.paragens.add(paragem);
	}
	public void removeParagens(String paragem) {
		if (paragens.size()==0) {
			System.out.println("Não tem paragens para remover");
			return;
		}
		
		this.paragens.remove(paragem);
	}

}
