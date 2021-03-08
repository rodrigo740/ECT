package exercicio3.utils;

import java.util.ArrayList;
import static java.lang.System.*;

public class Veiculo {
	
	private Carta cartaNecessaria;
	private final double cilindrada;
	private final double potencia;
	private final double peso;
	private final int lotacao;
	private Condutor condutor;
	private ArrayList<Passageiro> passageiros;
	
	public Veiculo(Carta cartaNecessaria,double cilindrada,double potencia,double peso,int lotacao,Condutor condutor) {
		this.cartaNecessaria = cartaNecessaria;
		this.cilindrada = cilindrada;
		this.potencia = potencia;
		this.peso = peso;
		this.lotacao = lotacao;
		this.condutor = condutor;
		this.passageiros = new ArrayList<>();
	}
	public void addPassageiro(Passageiro passageiro) {
		if (lotacao == passageiros.size()) {
			out.println("Lotação esgotada");
		}else {
			this.passageiros.add(passageiro);
		}
	}
	public void removePassageiro(Passageiro passageiro) {
		if (passageiros.size() == 0) {
			out.println("O carro está vazio");
		}else {
			this.passageiros.remove(passageiro);
		}
	}
	public void trocarCondutor(Condutor condutor) {
		this.condutor = condutor;
	}
	public boolean validarCarta(Carta c) {
		if (!cartaNecessaria.equals(c)) {
			return false;
		}
		return true;
	}
	public double cilindrada() {
		return this.cilindrada;
	}
	public double potencia() {
		return this.potencia;
	}
	public double peso() {
		return this.peso;
	}
	public int lotacao() {
		return this.lotacao;
	}
	public Condutor condutor() {
		return this.condutor;
	}
	public int numeroPassageiros() {
		return this.passageiros.size();
	}

}
