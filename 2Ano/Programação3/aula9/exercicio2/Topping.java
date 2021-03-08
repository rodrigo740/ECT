package aula9.exercicio2;

public class Topping extends GeladoDecorador{
	
	private String tipo;
	
	Topping(Gelado g,String tipo) {
		super(g);
		this.tipo = tipo;
	}
	
	@Override
	public void base(int n) {
		gelado.base(n);
		System.out.print(" com " + tipo);
	}
}
