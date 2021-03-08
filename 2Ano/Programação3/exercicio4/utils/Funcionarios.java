package exercicio4.utils;

public class Funcionarios extends Pessoa{
		
	private int nFuncionario;
	private int nFiscal;
	
	public Funcionarios(String nome,int cc,Data dataNasc,int nSocio,int nFuncionario,int nFiscal,Data dataInscricao,int quota) {
		super(nome,cc,dataNasc,nSocio,dataInscricao,quota);
		this.nFuncionario = nFuncionario;
		this.nFiscal = nFiscal;
		
	}
	public int nFuncionario() {
		return nFuncionario;
	}
	public int nFiscal() {
		return nFiscal;
	}
}
