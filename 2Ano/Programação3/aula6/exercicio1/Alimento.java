package aula6.exercicio1;

public abstract class Alimento implements Comparable<Alimento>{
	
	private double peso;
	private double calorias;
	private double proteinas;
	
	public Alimento(double peso,double calorias,double proteinas) {
		this.peso = peso;
		this.calorias = calorias;
		this.proteinas = proteinas;
	}
	public double peso() {
		return this.peso;
	}
	public double calorias() {
		return this.calorias;
	}

	public double proteinas() {
		return this.proteinas;
	}
	
	public int compareTo(Alimento ali) {
		if (this.calorias() < ali.calorias()) {
			return -1;
		} else if(this.calorias() == ali.calorias()){
			return 0;
		}else {
			return 1;
		}
	}
}
