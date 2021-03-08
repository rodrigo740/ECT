package aula4.exercicio2;

public abstract class Figura {
	
	private Ponto centro;
	
	public Figura(double x, double y) {
		Ponto c = new Ponto(x,y);
		centro = c;
	}
	public Ponto centro() {
		return this.centro;
	}
	public abstract double area();
	public abstract double perimetro();
}
