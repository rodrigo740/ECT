package aula2.utils;

import java.util.ArrayList;

public class Pessoa {
	
	private String nome;
	private int cc;
	private Data dataNasc;
	private int nSocio;
	private Data dataInscricao;
	private ArrayList<Video> requisicoes = new ArrayList<Video>();
	private int quota;
	
	public Pessoa(String nome,int cc,Data dataNasc,int nSocio,Data dataInscricao,int quota) {
		this.nome = nome;
		this.cc = cc;
		this.dataNasc = dataNasc;
		this.nSocio = nSocio;
		this.dataInscricao = dataInscricao;
		this.quota = quota;
		
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
	public int nSocio() {
		return nSocio;
	}
	public void registo(Video video) {
		requisicoes.add(video);
	}
	public void registoDelet(Video video) {
		requisicoes.remove(video);
	}
	public String requisicoes() {
		String temp = "";
		for (int i = 0; i < this.requisicoes.size(); i++) {
			String titulo = this.requisicoes.get(i).titulo();
			if (i==this.requisicoes.size()) {
				temp += titulo;
				break;
			}
			temp += titulo + ", ";
		}
		return temp;
	}
	public Boolean quotaAtingida(Pessoa user) {
		
		if (requisicoes.size() == this.quota) {
			return true;
		}
		
		return false;
	}
	public String toString() {
		
		String s = nome + " " + String.valueOf(cc) + " " + String.valueOf(dataNasc.dia()) + " " + String.valueOf(dataNasc.mes()) + " " + String.valueOf(dataNasc.ano()) + " " + requisicoes();
		
		return s;
	}

}
