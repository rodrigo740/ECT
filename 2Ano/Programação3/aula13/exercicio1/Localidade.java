package aula13.exercicio1;

public class Localidade {
	
	private String nome;
	private int populacao;
	private TipoLocalidade tipo;
	
	public Localidade(String nome, int populacao, TipoLocalidade tipo) {
		this.nome = nome;
		this.populacao = populacao;
		this.tipo = tipo;
	}
	public String getNome() {
		return this.nome;
	}
	public int getPopulacao() {
		return this.populacao;
	}
	public TipoLocalidade getTipoLocalidade() {
		return this.tipo;
	}
	
	@Override
	public String toString() {
		return tipo + " "  + nome + ", populaçao " + populacao;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + populacao;
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		Localidade other = (Localidade) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (populacao != other.populacao)
			return false;
		if (tipo != other.tipo)
			return false;
		return true;
	}
}
