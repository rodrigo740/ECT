package aula3.utils;

import java.time.LocalDate;

public class Estudante extends Pessoa{
	private static int contadorNMec = 100;
	
	private int nMec;
	private Data dataNasc;
	private Data dataInsc;
	
	public Estudante(String nome,int cc,aula3.utils.Data data) {
		super(nome,cc);
		this.dataNasc = data;
		this.nMec = contadorNMec++;
		
		LocalDate date = LocalDate.now();
		String[] p = date.toString().split("-");
		this.dataInsc = new Data(Integer.parseInt(p[2]),Integer.parseInt(p[1]),Integer.parseInt(p[0]));
	}
	public Estudante(String nome,int cc,Data dataNasc,Data dataInsc) {
		super(nome,cc);
		this.dataInsc = dataInsc;
		this.dataNasc = dataNasc;
		this.nMec = contadorNMec++;
	}
	public int nMec() {
		return this.nMec;
	}
	public Data dataNasc() {
		return this.dataNasc;
	}
	public Data dataInsc() {
		return this.dataInsc;
	}
	@Override
	public String toString() {
		return "Nome: " + this.nome() + "," + "CC: " + this.cc() + ",Nasc. Data: " + dataNasc.dia() + "/" + dataNasc.mes() + "/" + dataNasc.ano() + ",NMec: " + this.nMec() + ",Inscrito em Data: " + dataNasc.dia() + "/" + dataNasc.mes() + "/" + dataNasc.ano();
	}
}
