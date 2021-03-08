package aula12.exercicio1;

public class Figura {
	
	private Ponto centro;
	
	public Figura(double x, double y) {
		Ponto c = new Ponto(x,y);
		centro = c;
	}
	public Ponto centro() {
		return this.centro;
	}
}
