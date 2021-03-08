package aula1.utils;

public class Pessoa {
	
	private String nome;
	private int cc;
	private Data dataNasc;
	
	public Pessoa(String nome,int cc,Data dataNasc) {
		this.nome = nome;
		this.cc = cc;
		this.dataNasc = dataNasc;
		
	}
	public String nome() {
		return nome;
	}
	public int cc() {
		return cc;
	}
	public Data dataNasc() {
		return dataNasc;
	}
	public String toString() {
		
		String s = nome + " " + String.valueOf(cc) + " " + String.valueOf(dataNasc.dia()) + " " + String.valueOf(dataNasc.mes()) + " " + String.valueOf(dataNasc.ano());
		
		return s;
	}

}
