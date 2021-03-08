package exercicio3.utils;

public class Passageiro {
	
	private String nome;
	private int cc;
	private Data dataNasc;
	
	public Passageiro(String nome,int cc,Data dataNasc) {
		this.nome = nome;
		this.cc = cc;
		this.dataNasc = dataNasc;
	}
	public String nome() {
		return this.nome;
	}
	public int cc() {
		return this.cc;
	}
	public Data dataNasc() {
		return this.dataNasc;
	}

}
