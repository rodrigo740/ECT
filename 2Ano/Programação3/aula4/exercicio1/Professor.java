package aula4.exercicio1;

import java.time.LocalDate;

public class Professor extends Pessoa{
	private static int n = 1;
	
	private int nfunc;
	private Data dataAdmissao;
	
	public Professor(String nome,int cc,Data dataNasc) {
		super(nome,cc,dataNasc);
		nfunc = n;
		
		LocalDate date = LocalDate.now();
		String[] p = date.toString().split("-");
		this.dataAdmissao = new Data(Integer.parseInt(p[2]),Integer.parseInt(p[1]),Integer.parseInt(p[0]));
		n++;
	}
	public int nfunc() {
		return this.nfunc;
	}
	public Data dataAdmissao() {
		return this.dataAdmissao;
	}
	
	
	public String toString() {
		return super.toString() + ", nMec: " + nfunc + ", Admitido em Data: " + dataAdmissao.toString();
	}
	
}
