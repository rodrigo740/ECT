package aula4.exercicio1;

import java.time.LocalDate;

public class Estudante extends Pessoa{
	private static int contadorNMec = 100;
	
	private int nMec;
	private Data dataInsc;
	
	public Estudante(String nome,int cc,Data dataNasc) {
		super(nome,cc,dataNasc);
		this.nMec = contadorNMec++;
		
		LocalDate date = LocalDate.now();
		String[] p = date.toString().split("-");
		this.dataInsc = new Data(Integer.parseInt(p[2]),Integer.parseInt(p[1]),Integer.parseInt(p[0]));
	}
	public Estudante(String nome,int cc,Data dataNasc,Data dataInsc) {
		super(nome,cc,dataNasc);
		this.dataInsc = dataInsc;
		this.nMec = contadorNMec++;
	}
	public int nMec() {
		return this.nMec;
	}
	public Data dataInsc() {
		return this.dataInsc;
	}
	@Override
	public String toString() {
		return "Nome: " + this.nome() + "," + "CC: " + this.cc() + ",NMec: " + this.nMec() + ",Inscrito em Data: " + dataInsc.toString();
	}
}
