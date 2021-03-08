package aula3.utils;

public class Pessoa {
	
	private String nome;
	private int cc;
	
	public Pessoa(String nome,int cc) {
		this.nome = nome;
		this.cc = cc;
	}
	public String nome() {
		return nome;
	}
	public int cc() {
		return cc;
	}
	
	@Override
	public String toString() {
		return "Nome: " + this.nome() + "," + "CC: " + this.cc();
	}
}
