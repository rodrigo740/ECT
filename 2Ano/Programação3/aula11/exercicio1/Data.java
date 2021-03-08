package aula11.exercicio1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Data {
	
	private int dia;
	private int mes;
	private int ano;
	private static Data today = today();
	
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ano;
		result = prime * result + dia;
		result = prime * result + mes;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Data other = (Data) obj;
		if (ano != other.ano)
			return false;
		if (dia != other.dia)
			return false;
		if (mes != other.mes)
			return false;
		return true;
	}
	public static Data today() {
		String currentDate = DateTimeFormatter.ofPattern("dd-MM-yyyy").format(LocalDate.now());
		String []today = currentDate.split("-");
		
		int dia = Integer.parseInt(today[0]);
		int mes = Integer.parseInt(today[1]);
		int ano = Integer.parseInt(today[2]);
		
		Data data = new Data(dia,mes,ano);
		
		return data;
	}
}
