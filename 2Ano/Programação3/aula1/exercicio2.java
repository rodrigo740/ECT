package aula1;

import static java.lang.System.*;
import java.util.*;

import aula1.utils.Data;
import aula1.utils.Pessoa;

public class exercicio2 {
	
	static List<Pessoa> pessoas = new ArrayList<>();
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		int op = 0;
		
		out.println("\tGerir lista de pessoas");
		out.println("------------------------------------------");
		out.println("  1 - Introduzir pessoa na lista        ");
		out.println("  2 - Apagar pessoa da lista            ");
		out.println("  3 - Desenhar lista de pessoas       ");
		out.println("  4 - Ordenar lista de pessoas pelo nome");
		out.println("  5 - Ordenar lista de pessoas pelo CC  ");
		out.println("  6 - Fechar o programa                  ");
		out.println("------------------------------------------");
		
		
		while(true) {
			
			while(true) {
				try {
					out.print("Opção: ");
					op = Integer.parseInt(sc.nextLine());
					break;
				}catch(NumberFormatException e) {
					out.println("Erro: Não introduziu um numero válido");
				}
			}
			
			switch(op) {
			case 1:
				peopleAdd();
				break;
			case 2:
				deletPeople();
				break;
			case 3:
				printList();
				break;
			case 4:
				sortListByName();
				break;
			case 5:
				sortListByCC();
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
	public static void peopleAdd() {
		String data;
		int cc;
		
		out.println("Nome: ");
		String nome = sc.nextLine();
		
		while(true) {
			try {
				out.println("CC: ");
				cc = Integer.parseInt(sc.nextLine());
				break;
			}catch(NumberFormatException e) {
				out.println("Erro: Não introduziu um CC válido");
			}
		}
		
		while(true) {
			try {
				out.println("Data de nascimento(DD MM AAAA): ");
				data = sc.nextLine();
				break;
			}catch(NumberFormatException e) {
				out.println("Erro: Não introduziu uma data válida(DD MM AAAA)");
			}
		}
		
		String[] d;
		Data dataNasc;
		
		while(true) {
			try {
				d = data.split(" ");	
				dataNasc = new Data(Integer.parseInt(d[0]),Integer.parseInt(d[1]),Integer.parseInt(d[2]));
				break;
			}catch(ArrayIndexOutOfBoundsException e) {
				out.println("Erro: Não introduziu uma data válida(DD MM AAAA)");
				return;
			}
		}
		
		Pessoa pessoa = new Pessoa(nome, cc, dataNasc);
		pessoas.add(pessoa);
		return;
	}
	public static void deletPeople() {
		if(pessoas.size()==0) {
			out.println("Nao pode remover pessoas porque a lista está vazia\n");
			return;
		}
		String nome;
		
		while(true) {
			try {
				out.print("Nome da pessoa que pretende remover: ");
				nome = sc.nextLine();
				break;
			}catch(NumberFormatException e) {
				out.print("Nao foi introduzido o tipo correto de dados");
				return;
			}
		}
		
		List<Pessoa> temp = new ArrayList<>();
		
		for(int i=0;i<pessoas.size();i++) {
			if(!pessoas.get(i).nome().equals(nome)) {
				temp.add(pessoas.get(i));
			}
		}
		if(pessoas.size() == temp.size()) {
			out.println("O nome introduzido não foi encontrado");
		}
		else {
			out.println("Pessoa removida com sucesso!");
		}
		pessoas = temp;
	}
	public static void printList() {
		
		if(pessoas.size()==0) {
			out.println("A lista está vazia");
			return;
		}
		for(int i=0;i<pessoas.size();i++) {
			out.println("Nome \t CC \t Data de Nascimento");
			out.printf("%s \t \t %d \t \t %d-%d-%d\n",pessoas.get(i).nome(), pessoas.get(i).cc(), pessoas.get(i).dataNasc().dia(), pessoas.get(i).dataNasc().mes(), pessoas.get(i).dataNasc().ano());
		}
	}
	public static void sortListByName() {
		 
		Pessoa temp;
		
		for (int i = 0; i < pessoas.size(); i++) 
	        {
	            for (int j = i + 1; j < pessoas.size(); j++) { 
	                if (pessoas.get(i).nome().compareTo(pessoas.get(j).nome())>0) {
	             
	                	temp = pessoas.get(i);;
	                	
	                	pessoas.set(i, pessoas.get(j));
	                	pessoas.set(j, temp);
	                }
	            }
	        }
	}
	public static void sortListByCC() {
		 
		Pessoa temp;
		
		for (int i = 0; i < pessoas.size(); i++) 
	        {
	            for (int j = i + 1; j < pessoas.size(); j++) { 
	                if (pessoas.get(i).cc() > (pessoas.get(j).cc())) {
	             
	                	temp = pessoas.get(i);;
	                	
	                	pessoas.set(i, pessoas.get(j));
	                	pessoas.set(j, temp);
	                }
	            }
	        }
	}
}
