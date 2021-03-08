package aula1.utils;

public class Circulo {
	private double raio;
	private Ponto centro;
	
	public Circulo(double x, double y, double r) {
		Ponto c = new Ponto(x, y);
		centro = c;
		raio = r;
	}
	public Ponto centro() {
		return centro;
	}
	public double raio() {
		return raio;
	}
	public Circulo(Ponto centro, double r) {
		raio = r;
		this.centro = centro;
	}
	public double perimetro() {
		return 2*Math.PI*raio;
	}
	public double area() {
		return Math.pow(Math.PI, 2);
	}
	public String toString() {
		String p,a;
		
		p = String.valueOf(this.perimetro());
		a = String.valueOf(this.area());
		
		return ("Circulo com " + a + " de area e " + p + " de perimetro"); 
	}
}
