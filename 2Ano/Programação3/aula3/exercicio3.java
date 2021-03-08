package aula3;

import exercicio3.utils.*;
import static java.lang.System.*;

public class exercicio3 {
	
	public static void main(String args[]) {
		
		Carta cartaMotociclos = new Carta("A");
		Carta cartaLigeiros = new Carta("B");
		Carta cartaMercadorias = new Carta("C");
		Carta cartaPassageiros = new Carta("D");
		
		Data data1 = new Data(7,4,2000);
		Data data2 = new Data(23,5,1993);
		Data data3 = new Data(21,9,1975);
		Data data4 = new Data(3,1,1998);
		Data data5 = new Data(1,12,1978);
		
		Condutor condutorMotociclos = new Condutor("Isabel",19465872,data1,cartaMotociclos);
		Condutor condutorLigeiro = new Condutor("Maria",26435981,data2,cartaLigeiros);
		Condutor condutorMercadoria = new Condutor("Bruno",78452359,data3,cartaMercadorias);
		Condutor condutorPassageiro = new Condutor("João",95468726,data4,cartaPassageiros);
		
		Passageiro passageiro1 = new Passageiro("Manuel",54976812,data5);
		Passageiro passageiro2 = new Passageiro("Joaquim",98761325,data2);
		Passageiro passageiro3 = new Passageiro("Mariana",12345678,data1);
		Passageiro passageiro4 = new Passageiro("Margarida",87654321,data3);
		Passageiro passageiro5 = new Passageiro("Rodrigo",98746512,data2);
		Passageiro passageiro6 = new Passageiro("Ana",16485234,data1);
		
		Ligeiro ligeiro = new Ligeiro(300,500,100,4,condutorLigeiro,"vermelho");
		Motociclo motociclo = new Motociclo(100,400,50,2,condutorMotociclos,true);
		PesadoDeMercadorias pesadoMercadoria = new PesadoDeMercadorias(800,600,700,2,condutorMercadoria);
		PesadoDePassageiros pesadoPassageiros = new PesadoDePassageiros(500,600,200,18,condutorPassageiro);
		
		//Teste ao veiculo ligeiro
		
		out.println("Adicionar passageiros ao ligeiro");
		out.println("------------------------------------------------");
		
		ligeiro.addPassageiro(passageiro1); //Deve de adicionar
		ligeiro.addPassageiro(passageiro2);	//Deve de adicionar
		ligeiro.addPassageiro(passageiro3);	//Deve de adicionar
		ligeiro.addPassageiro(passageiro4);	//Deve de adicionar
		ligeiro.addPassageiro(passageiro5);	//Não deve de adicionar e deve de imprimir mensagem de erro
		
		ligeiro.removePassageiro(passageiro4);
		ligeiro.addPassageiro(passageiro5);	//Ja deve adicionar
		
		out.println("Teste do ligeiro concluido");
		out.println("------------------------------------------------");
		out.println();
		out.println();
		out.println();
		
		//Teste ao motociclo
		
		out.println("Adicionar passageiros ao motociclo");
		out.println("------------------------------------------------");
		
		motociclo.addPassageiro(passageiro1); //Deve de adicionar
		motociclo.addPassageiro(passageiro2);	//Deve de adicionar
		motociclo.addPassageiro(passageiro3);	//Não deve de adicionar e deve de imprimir mensagem de erro
		
		motociclo.removePassageiro(passageiro2);
		motociclo.addPassageiro(passageiro3);	//Ja deve adicionar
		
		out.println("Teste do motociclo concluido");
		out.println("------------------------------------------------");
		out.println();
		out.println();
		out.println();
		
		//Teste ao pesado de mercadorias
		
		out.println("Adicionar passageiros ao pesado de mercadorias");
		out.println("------------------------------------------------");
		
		pesadoMercadoria.addPassageiro(passageiro1);	//Deve de adicionar
		pesadoMercadoria.addPassageiro(passageiro2);	//Deve de adicionar
		pesadoMercadoria.addPassageiro(passageiro3);	//Não deve de adicionar e deve de imprimir mensagem de erro
		
		pesadoMercadoria.removePassageiro(passageiro2);
		pesadoMercadoria.addPassageiro(passageiro3);	//Ja deve adicionar
		
		out.println("Adicionar mercadoria ao pesado de mercadorias");
		out.println("------------------------------------------------");
		
		pesadoMercadoria.removeMercadoria("fruta"); //Deve de imprimir mensagem de erro

		
		pesadoMercadoria.addMercadoria("tomates");	//Deve de adicionar
		pesadoMercadoria.addMercadoria("alfaces");	//Deve de adicionar
		pesadoMercadoria.addMercadoria("batatas");	//Deve de adicionar
		pesadoMercadoria.addMercadoria("couves");	//Deve de adicionar
		
		pesadoMercadoria.removeMercadoria("alfaces");	//Deve de remover as alfaces

		
		out.println("Mercadorias: " + pesadoMercadoria.mercadorias());
		
		out.println("Teste do pesado de mercadorias concluido");
		out.println("------------------------------------------------");
		out.println();
		out.println();
		out.println();
		
		//Teste do pesado de passageiros
		
		out.println("Adicionar passageiros ao pesado de passageiros");
		
		pesadoPassageiros.addPassageiro(passageiro1);	//Deve de adicionar
		pesadoPassageiros.addPassageiro(passageiro2);	//Deve de adicionar
		pesadoPassageiros.addPassageiro(passageiro3);	//Deve de adicionar
		pesadoPassageiros.addPassageiro(passageiro4);	//Deve de adicionar
		pesadoPassageiros.addPassageiro(passageiro5);	//Deve de adicionar
		

		
		pesadoPassageiros.removeParagens("Aveiro"); //Deve de imprimir mensagem de erro

		
		pesadoPassageiros.addParagens("Aveiro");		//Deve de adicionar
		pesadoPassageiros.addParagens("Fátima");		//Deve de adicionar
		pesadoPassageiros.addParagens("Porto");			//Deve de adicionar
		pesadoPassageiros.addParagens("Braga");			//Deve de adicionar
		
		pesadoPassageiros.removeParagens("Aveiro");	//Deve de remover a paragem "Aveiro"
		
		out.println("Mercadorias: " + pesadoPassageiros.paragens());
		
		out.println("Teste do pesado de passageiros concluido");
	}
	
}
