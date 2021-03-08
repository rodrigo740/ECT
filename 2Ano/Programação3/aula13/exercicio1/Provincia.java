package aula13.exercicio1;

public class Provincia extends Regiao{
	
	private String governador;

	public Provincia(String nome,int populacao, String governador) {
		super(nome, populacao);
		this.governador = governador;
	}
	public String getGovernador() {
		return this.governador;
	}
}
