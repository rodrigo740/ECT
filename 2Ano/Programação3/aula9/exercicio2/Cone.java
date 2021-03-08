package aula9.exercicio2;

public class Cone extends GeladoDecorador{

	private String sabor;
	
	public Cone(Gelado g,String sabor) {
		super(g);
		this.sabor = sabor;
	}
	
	public Cone(Gelado g) {
		super(g);
	}

	@Override
	public void base(int n) {
		gelado.base(n);
		
		if (sabor == null) {
			System.out.print(" em cone");
		}else {
			System.out.print(" em cone com " + sabor);
		}
	}

}
