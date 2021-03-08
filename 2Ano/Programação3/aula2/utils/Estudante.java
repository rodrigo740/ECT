package aula2.utils;

public class Estudante extends Pessoa{
		
	private int nMec;
	private String curso;
	
	public Estudante(String nome,int cc,Data dataNasc,int nSocio,int nMec,String curso,Data dataInscricao,int quota) {
		super(nome,cc,dataNasc,nSocio,dataInscricao,quota);
		this.nMec = nMec;
		this.curso = curso;	
	}
	public int nMec() {
			return nMec;
	}
	public String curso() {
		return curso;
	}
}
