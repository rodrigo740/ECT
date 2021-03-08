package aula13.exercicio1;

import java.util.ArrayList;
import java.util.List;

public class Pais {
	
	private String nome;
	private Localidade capital;
	private List<Regiao> regioes;
	private int totalPopulacao = 0;
	
	public Pais(String nome, Localidade capital) {
		this.nome = nome;
		this.capital = capital;
		regioes = new ArrayList<>();
	}
	
	public Pais(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return this.nome;
	}
	public Localidade getCapital() {
		return this.capital;
	}
	public List<Regiao> getRegioes() {
		return this.regioes;
	}
	public void addRegiao(Regiao r) {
		regioes.add(r);
		totalPopulacao += r.getPopulacao();
	}
	
	
	@Override
	public String toString() {
		return "Pais: " + nome + ", População: " + totalPopulacao + " (Capital: Cidade " + (capital!=null?capital:"*Indefinida*") + ")";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((capital == null) ? 0 : capital.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((regioes == null) ? 0 : regioes.hashCode());
		result = prime * result + totalPopulacao;
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
		Pais other = (Pais) obj;
		if (capital == null) {
			if (other.capital != null)
				return false;
		} else if (!capital.equals(other.capital))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (regioes == null) {
			if (other.regioes != null)
				return false;
		} else if (!regioes.equals(other.regioes))
			return false;
		if (totalPopulacao != other.totalPopulacao)
			return false;
		return true;
	}
	
	
	
}
