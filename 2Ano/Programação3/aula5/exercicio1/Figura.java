package aula5.exercicio1;

public abstract class Figura implements Comparable<Figura>{
	
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
	
	
	public int compareTo(Figura f) {
		if (this.area() < f.area()) {
			return -1;
		} else if(this.area() == f.area()){
			return 0;
		}else {
			return 1;
		}
	}
}
