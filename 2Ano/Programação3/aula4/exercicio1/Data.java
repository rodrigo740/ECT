package aula4.exercicio1;

public class Data {
	
	private int dia;
	private int mes;
	private int ano;
	
	public Data(int dia, int mes, int ano) {
		assert dataCheck(dia,mes,ano);
		this.dia = dia;
		this.mes = mes;
		this.ano = ano;
	}
	public int dia() {
		return dia;
	}
	public int mes() {
		return mes;
	}
	public int ano() {
		return ano;
	}
	
	@Override
	public String toString() {
		return dia + "/" + mes + "/" + ano;
	}
	
	private static boolean dataCheck(int dia, int mes, int ano) {
			if(dia<1 || dia >31) {
				return false;
			}
			else if(mes<1 || mes>12) {
				return false;
			}
			else if(mes<1 || mes>12) {
				return false;
			}
			else if(ano<=0) {
				return false;
			}
			else if(mes==2 && dia>29) {
				return false;
			}
		return true;
	}
}
