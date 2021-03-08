package aula1.utils;

public class Quadrado {
	private double comprimento;
	private Ponto centro;
	
	public Quadrado(double comprimento, Ponto centro) {
		this.comprimento = comprimento;
		this.centro = centro;
	}
	public double perimetro() {
		return 4*comprimento;
	}
	public double area() {
		return comprimento*comprimento;
	}
	public String toString() {
		String p,a;
		
		p = String.valueOf(this.perimetro());
		a = String.valueOf(this.area());
		
		return ("Quadrado com " + a + " de area e " + p + " de perimetro"); 
	}
}
