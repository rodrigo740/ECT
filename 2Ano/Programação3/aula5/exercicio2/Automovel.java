package aula5.exercicio2;

public class Automovel extends Veiculo{
	
	private final String matricula;
	private int velocidadeMax;
	private int cilindrada;
	private final int ano;
	
	public Automovel(String cor,int numRodas,String matricula,int velocidadeMax,int cilindrada,int ano) {
		super(cor,numRodas);
		this.matricula = matricula;
		this.velocidadeMax = velocidadeMax;
		this.cilindrada = cilindrada;
		this.ano = ano;
	}
	
	public int ano() {
		return ano;
	}

	public int cilindrada() {
		return cilindrada;
	}

	public int velocidadeMax() {
		return velocidadeMax;
	}

	public String matricula() {
		return matricula;
	}
	
	@Override
	public String toString() {
		return "Automóvel de " + numRodas() + " rodas, de cor: " + cor() + ", com velocidade maxima de: " + velocidadeMax() + "com cilindrada de: " + cilindrada() + ", do ano: " + ano() + ", com a matricula: " + matricula(); 
	}
	
}
