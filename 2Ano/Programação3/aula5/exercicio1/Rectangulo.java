package aula5.exercicio1;

public class Rectangulo extends Figura{
	
	static Ponto c = new Ponto(0,0);
	private double comprimento;
	private double largura;
	private double area;
	private double perimetro;
	
	public Rectangulo(double comprimento, double largura) {
		super(0,0);
		this.comprimento = comprimento;
		this.largura = largura;
		this.area = comprimento*largura;
		this.perimetro = 2*comprimento + 2*largura;
	}
	public Rectangulo(double x,double y,double comprimento, double largura) {
		super(x,y);
		this.comprimento = comprimento;
		this.largura = largura;
		this.area = comprimento*largura;
		this.perimetro = 2*comprimento + 2*largura;
	}
	public Rectangulo(Rectangulo r) {
		super(r.centro().x(),r.centro().y());
		this.comprimento = r.comprimento();
		this.largura = r.largura();
		this.area = r.area();
		this.perimetro = r.perimetro();
	}
	public double comprimento() {
		return this.comprimento;
	}
	public double largura() {
		return this.largura;
	}
	public double area() {
		return this.area;
	}
	public double perimetro() {
		return this.perimetro;
	}
	@Override
	public String toString() {
		return "Rectangulo de centro " + centro().toString() + " altura " + largura() + ", comprimento " + comprimento();
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
		
		Rectangulo other = (Rectangulo) o;
		
		if (this.centro() == null) {
			if (other.centro() != null) {
				return false;
			}
		}
		if (this.centro() != other.centro()) {
			return false;
		}
		if (this.largura() != other.largura()) {
			return false;
		}
		if (this.comprimento() != other.comprimento()) {
			return false;
		}
		
		return true;
	}
}
