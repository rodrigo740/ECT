package aula1.utils;

public class Rectângulo {
	private double comprimento;
	private double largura;
	private Ponto centro;
	
	public Rectângulo(double comprimento, double largura, Ponto centro) {
		this.comprimento = comprimento;
		this.largura = largura;
		this.centro = centro;
	}
	public double perimetro() {
		return 2*comprimento+2*largura;
	}
	public double area() {
		return comprimento*largura;
	}
	public String toString() {
		String p,a;
		
		p = String.valueOf(this.perimetro());
		a = String.valueOf(this.area());
		
		return ("Rectângulo com " + a + " de area e " + p + " de perimetro"); 
	}
}
