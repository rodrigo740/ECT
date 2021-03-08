package aula5.exercicio2;

public class Moto extends Motorizado{
	
	private boolean atrelado;
	
	public Moto(String cor,int numRodas,String matricula,int velocidadeMax,int cilindrada,int ano,int potencia,double consumo,double combustivel,boolean atrelado) {
		super(cor,numRodas,matricula,velocidadeMax,cilindrada,ano,potencia,combustivel,combustivel);
		this.atrelado = atrelado;
	}
	
	public boolean atrelado() {
		return atrelado;
	}
	
	@Override
	public String toString() {
		return "Moto de " + numRodas() + " rodas, de cor: " + cor() + ", com velocidade maxima de: " + velocidadeMax() + "com cilindrada de: " + cilindrada() + ", do ano: " + ano() + ", com a matricula: " + matricula(); 
	}
}
