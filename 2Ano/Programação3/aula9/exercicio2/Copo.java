package aula9.exercicio2;

public class Copo extends GeladoDecorador{
	
	private String ingrediente;

	public Copo(Gelado g, String ingrediente) {
		super(g);
		this.ingrediente = ingrediente;
	}
	public Copo(Gelado g) {
		super(g);
	}

	@Override
	public void base(int n) {
		gelado.base(n);
		
		if (ingrediente == null) {
			System.out.print(" em copo");
		}else {
			System.out.print(" em copo com " + ingrediente);
		}
	}
}
