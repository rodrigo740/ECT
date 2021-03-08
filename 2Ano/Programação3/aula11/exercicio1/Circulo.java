package aula11.exercicio1;

public class Circulo extends Figura{
	
	private double raio;
	private double area;
	private double perimetro;
	
	public Circulo(double x,double y,double raio) {
		super(x,y);
		this.raio = raio;
		this.area = Math.PI*Math.pow(raio, 2);
		this.perimetro = 2*Math.PI*raio;
	}
	public Circulo(double raio) {
		super(0,0);
		this.raio = raio;
		this.area = Math.PI*Math.pow(raio, 2);
		this.perimetro = 2*Math.PI*raio;
	}
	public Circulo(Circulo c) {
		super(c.centro().x(),c.centro().y());
		this.raio = c.raio();
		this.area = c.area();
		this.perimetro = c.perimetro();
	}
	public double raio() {
		return this.raio;
	}
	public double area() {
		return this.area;
	}
	public double perimetro() {
		return this.perimetro;
	}
	@Override
	public String toString() {
		return "Circulo de centro " + centro().toString() + "e de raio: " + raio();
	}
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null) {
			return false;
		}
		if (getClass() != o.getClass()) {
			return false;
		}
		
		Circulo other = (Circulo) o;
		
		if (this.centro() == null) {
			if (other.centro() != null) {
				return false;
			}
		} else if (this.centro() != other.centro()) {
			return false;
		}
		if (this.raio() != other.raio()) {
			return false;
		}
		
		return true;
	}
}
