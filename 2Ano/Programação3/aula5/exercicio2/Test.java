package aula5.exercicio2;

public class Test {

	public static void main(String[] args) {
		
		System.out.println("-------------------------------Teste do carro da policia-------------------------------\n\n");
		
		System.out.println("Adição de 3 carros");
		
		// Criação de carros da policia
		
		CarroPolicia c1 = new CarroPolicia("azul", 4, "AA-01-AA", 300, 400, 2019, "viatura 2019");
		CarroPolicia c2 = new CarroPolicia("preto", 4, "BB-01-BB", 300, 400, 2018, "viatura 2018");
		CarroPolicia c3 = new CarroPolicia("cinzento", 4, "CC-01-CC", 300, 400, 2016, "viatura 2016");
		
		System.out.println("\n------------------Teste do toString carroPolicia-----------------------------\n");
		System.out.println(c1.toString());
		System.out.println(c2.toString());
		System.out.println(c3.toString());
		
		System.out.println("\n------------------Teste do sortArray carroPolicia-----------------------------\n");

		CarroPolicia[] c = new CarroPolicia[3];
		
		c[0] = c1;
		c[1] = c2;
		c[2] = c3;
		
		UtilCompare.sortArray(c);
		
		for (int i = 0; i < c.length; i++) {
			System.out.println(c[i].toString());
		}
		
		System.out.println("\n-------------------------------Teste da Moto da policia-------------------------------\n\n");
		
		System.out.println("Adição de 3 motos");
		
		
		MotoPolicia m1 = new MotoPolicia("azul", 2, "AA-01-AA", 200, 250, 2010, 222, 5, 150, false, "moto2rodas 2010");
		MotoPolicia m2 = new MotoPolicia("branco", 4, "BB-01-BB", 200, 150, 2001, 222, 15, 150, false, "moto4rodas 2001");
		MotoPolicia m3 = new MotoPolicia("preto", 2, "CC-01-CC", 200, 250, 2019, 228, 10, 150, true, "moto2rodasAtrelado 2019");
		System.out.println("------------------Teste do toString  MotoPolicia-----------------------------\n");
		System.out.println(c1.toString());
		System.out.println(c2.toString());
		System.out.println(c3.toString());
		
		System.out.println("\n------------------Teste do sortArray MotoPolicia-----------------------------\n");

		MotoPolicia[] m = new MotoPolicia[3];
		
		m[0] = m1;
		m[1] = m2;
		m[2] = m3;
		
		UtilCompare.sortArray(m);
		
		for (int i = 0; i < m.length; i++) {
			System.out.println(m[i].toString());
		}
		
		
		System.out.println("\n-------------------------------Teste da Bicicleta da policia-------------------------------\n\n");
		
		System.out.println("Adição de 3 bicicletas");
		
		BicicletaPolicia b1 = new BicicletaPolicia("azul", 2, "asfalto", "cidade 2019", 2019);
		BicicletaPolicia b2 = new BicicletaPolicia("cinzenta", 2, "btt", "btt 2013", 2013);
		BicicletaPolicia b3 = new BicicletaPolicia("preta", 2, "btt", "btt 2002", 2002);
		
		System.out.println("------------------Teste do toString BicicletaPolicia-----------------------------\n");
		System.out.println(c1.toString());
		System.out.println(c2.toString());
		System.out.println(c3.toString());
		
		System.out.println("\n------------------Teste do sortArray BicicletaPolicia-----------------------------\n");

		BicicletaPolicia[] b = new BicicletaPolicia[3];
		
		b[0] = b1;
		b[1] = b2;
		b[2] = b3;
		
		UtilCompare.sortArray(b);
		
		for (int i = 0; i < b.length; i++) {
			System.out.println(b[i].toString());
		}
	}
}
