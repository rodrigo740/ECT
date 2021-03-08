package aula1;

import static java.lang.System.*;
import java.util.*;
import aula1.utils.Quadrado;
import aula1.utils.Rectângulo;
import aula1.utils.Circulo;
import aula1.utils.Ponto;

public class exercicio3 {
	
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		int op = 0;
		
		out.println("\tFiguras Geométricas");
		out.println("------------------------------------------");
		out.println("  1 - Criar um quadrado");
		out.println("  2 - Criar um retângulo");
		out.println("  3 - Criar um circulo");
		out.println("  4 - Testar se 2 circulos se interceptam");
		out.println("  5 - Testar se 2 circulos são iguais");
		out.println("  6 - Sair do programa");
		out.println("------------------------------------------");
		
		
		while(true) {
			
			while(true) {
				try {
					out.print("Opção: ");
					op = Integer.parseInt(sc.nextLine());
					break;
				}catch(NumberFormatException e) {
					out.println("Erro: Não introduziu um número");
				}
			}
			
			switch(op) {
			case 1:
				makeSquare();
				break;
			case 2:
				makeRectangle();
				break;
			case 3:
				makeCircle();
				break;
			case 4:
				intercept();
				break;
			case 5:
				sameC();
				break;
			case 6:
				exit(1);
				break;
			default:
				out.println("Opção inválida");
				out.print("Opção: ");
				op = Integer.parseInt(sc.nextLine());
			}
		}
	}
	public static void makeSquare() {
		String s;
		double comp;
		
		while(true) {
			try {
				out.print("Coordenadas do centro do quadrado separadas por \";\": ");
				s = sc.nextLine();
				break;
			}catch(NumberFormatException e) {
				out.println("Erro: Não introduziu as coordenadas com o formato correto");
			}
		}
		
		String[] temp = s.split(";");
		Ponto centro = new Ponto(Double.parseDouble(temp[0]),Double.parseDouble(temp[1]));
		
		out.print("Comprimento do quadrado: ");
		comp = Double.parseDouble(sc.nextLine());
		
		Quadrado q = new Quadrado(comp, centro);
		out.println("Quadrado criado com sucesso");
		out.printf("Perimetro: %f\n", q.perimetro());
		out.printf("Area: %f\n", q.area());
	}
	public static void makeRectangle() {
		String s;
		double comp;
		double larg;
		
		while(true) {
			try {
				out.print("Coordenadas do centro do rectângulo separadas por \";\": ");
				s = sc.nextLine();
				break;
			}catch(NumberFormatException e) {
				out.println("Erro: Não introduziu as coordenadas com o formato correto");
			}
		}
		
		String[] temp = s.split(";");
		Ponto centro = new Ponto(Double.parseDouble(temp[0]),Double.parseDouble(temp[1]));
		
		out.print("Comprimento do rectângulo: ");
		comp = Double.parseDouble(sc.nextLine());
		
		out.print("Largura do rectângulo: ");
		larg = Double.parseDouble(sc.nextLine());
		
		Rectângulo r = new Rectângulo(comp, larg, centro);
		out.println("Rectângulo criado com sucesso");
		out.printf("Perimetro: %f\n", r.perimetro());
		out.printf("Area: %f\n", r.area());
	}
	public static void makeCircle() {
		String s;
		double r;
		
		while(true) {
			try {
				out.print("Coordenadas do centro do circulo separadas por \";\": ");
				s = sc.nextLine();
				break;
			}catch(NumberFormatException e) {
				out.println("Erro: Não introduziu as coordenadas com o formato correto");
			}
		}
		
		String[] temp = s.split(";");
		Ponto centro = new Ponto(Double.parseDouble(temp[0]),Double.parseDouble(temp[1]));
		
		out.print("Raio do circulo: ");
		r = Double.parseDouble(sc.nextLine());
		
		Circulo c = new Circulo(centro, r);
		out.println("Circulo criado com sucesso");
		out.printf("Perimetro: %f\n", c.perimetro());
		out.printf("Area: %f\n", c.area());
	}
	public static void intercept() {
		String s1;
		double r1;
		String s2;
		double r2;
		
		out.print("Coordenadas do centro do circulo separadas por \";\": ");
		s1 = sc.nextLine();
		
		String[] temp1 = s1.split(";");
		Ponto centro1 = new Ponto(Double.parseDouble(temp1[0]),Double.parseDouble(temp1[1]));
		
		out.print("Raio do circulo: ");
		r1 = Double.parseDouble(sc.nextLine());
		
		Circulo c1 = new Circulo(centro1, r1);
		out.println("Circulo criado com sucesso");
		
		out.print("Coordenadas do centro do circulo separadas por \";\": ");
		s2 = sc.nextLine();
		
		String[] temp2 = s2.split(";");
		Ponto centro2 = new Ponto(Double.parseDouble(temp2[0]),Double.parseDouble(temp2[1]));
		
		out.print("Raio do circulo: ");
		r2 = Double.parseDouble(sc.nextLine());
		
		Circulo c2 = new Circulo(centro2, r2);
		out.println("Circulo criado com sucesso");
		
		if(interception(c1, c2)) {
			out.println("Os circulos interceptam-se");
		}
		else {
			out.println("Os circulos não se interceptam");
		}
		return;
	}
	public static void sameC() {
		String s1;
		double r1;
		String s2;
		double r2;
		
		out.print("Coordenadas do centro do circulo separadas por \";\": ");
		s1 = sc.nextLine();
		
		String[] temp1 = s1.split(";");
		Ponto centro1 = new Ponto(Double.parseDouble(temp1[0]),Double.parseDouble(temp1[1]));
		
		out.print("Raio do circulo: ");
		r1 = Double.parseDouble(sc.nextLine());
		
		Circulo c1 = new Circulo(centro1, r1);
		out.println("Circulo criado com sucesso");
		
		out.print("Coordenadas do centro do circulo separadas por \";\": ");
		s2 = sc.nextLine();
		
		String[] temp2 = s2.split(";");
		Ponto centro2 = new Ponto(Double.parseDouble(temp2[0]),Double.parseDouble(temp2[1]));
		
		out.print("Raio do circulo: ");
		r2 = Double.parseDouble(sc.nextLine());
		
		Circulo c2 = new Circulo(centro2, r2);
		out.println("Circulo criado com sucesso");
		
		if(equal(c1, c2)) {
			out.println("Os circulos são iguais");
		}
		else {
			out.println("Os circulos não são iguais");
		}
		return;
	}
	
	public static boolean interception(Circulo a, Circulo b) {
			
		double distC = Math.sqrt((Math.pow((a.centro().x()-b.centro().x()), 2)+(Math.pow((a.centro().y()-b.centro().y()), 2))));
		double rSum = a.raio() + b.raio();
		
		if(distC <= rSum) {
			return true;
		}
		return false;
	}
	public static boolean equal(Circulo a, Circulo b) {
		if((a.centro().x()==(b.centro().x()) && (a.centro().y()==(b.centro().y()) && (a.raio() == b.raio())))) {
			return true;
		}
		return false;
	}
}
