package aula6.exercicio2;

public class Quadrado extends Rectangulo{
	
	private double lado;
	private double area;
	private double perimetro;
	
	public Quadrado(double lado) {
		super(0,0);
		this.lado = lado;
		this.area = lado*lado;
		this.perimetro = 4*lado;
	}
	public Quadrado(double x,double y,double lado) {
		super(x,y);
		this.lado = lado;
		this.area = lado*lado;
		this.perimetro = 4*lado;
	}
	public Quadrado(Quadrado q) {
		super(q.centro().x(),q.centro().y());
		this.lado = q.lado();
		this.area = q.area();
		this.perimetro = q.perimetro();
	}
	public double lado() {
		return this.lado;
	}
	public double area() {
		return this.area;
	}
	public double perimetro() {
		return this.perimetro;
	}
	@Override
	public String toString() {
		return "Quadrado " + centro().toString() + "e de lado " + lado();
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
		Quadrado other = (Quadrado) o;
		
		if (this.centro() == null) {
			if (other.centro() != null) {
				return false;
			}
		} else if (this.centro() != other.centro()) {
			return false;
		}
		if (this.lado() != other.lado()) {
			return false;
		}
		
		return true;
	}
}
