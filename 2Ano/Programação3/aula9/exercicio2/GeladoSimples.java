package aula9.exercicio2;

public class GeladoSimples implements Gelado {

	private String tipo;
	
	public GeladoSimples(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public void base(int n) {
		System.out.println("\n" + n + " bolas de gelado de " + tipo);
	}
}
