package aula9.exercicio2;

public abstract class GeladoDecorador implements Gelado {
	
	protected Gelado gelado;

	public GeladoDecorador(Gelado g) {
		gelado = g;
	}
}
