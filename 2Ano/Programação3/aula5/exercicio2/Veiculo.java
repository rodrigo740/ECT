package aula5.exercicio2;

public abstract class Veiculo implements Comparable<Veiculo>{
	
	private String cor;
	private final int numRodas;
	
	public Veiculo(String cor,int numRodas) {
		this.cor = cor;
		this.numRodas = numRodas;
	}
	public String cor() {
		return cor;
	}
	public int numRodas() {
		return numRodas;
	}
	
	public abstract int ano();
	
	public int compareTo(Veiculo v) {
		if (this.ano() < v.ano()) {
			return -1;
		} else if(this.ano() == v.ano()){
			return 0;
		}else {
			return 1;
		}
	}
}
