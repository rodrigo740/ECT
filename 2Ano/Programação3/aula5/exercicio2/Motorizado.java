package aula5.exercicio2;

public class Motorizado extends Veiculo{
	
	private final String matricula;
	private final int velocidadeMax;
	private final int cilindrada;
	private final int ano;
	private final int potencia;
	private final double consumo;
	private final double combustivel;
		
	public Motorizado(String cor,int numRodas,String matricula,int velocidadeMax,int cilindrada,int ano,int potencia,double consumo,double combustivel) {
		super(cor,numRodas);
		this.matricula = matricula;
		this.velocidadeMax = velocidadeMax;
		this.cilindrada = cilindrada;
		this.ano = ano;
		this.potencia = potencia;
		this.consumo = consumo;
		this.combustivel = combustivel;
	}
	public String matricula() {
		return matricula;
	}
	public int velocidadeMax() {
		return velocidadeMax;
	}
	public int cilindrada() {
		return cilindrada;
	}
	public int ano() {
		return ano;
	}
	public int potencia() {
		return potencia;
	}
	public double consumo() {
		return consumo;
	}
	public double combustivel() {
		return combustivel;
	}
}
