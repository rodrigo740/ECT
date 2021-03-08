package aula2;

import aula2.utils.*;
import java.util.*;
import static java.lang.System.*;

public class listaClientes {
	
	static Scanner sc = new Scanner(in);
	
	public static void main(String[] args) {
		
	int op = 99;
		
		do {
			out.println("Lista de Clientes");
			out.println("----------------------------------");
			out.println("1 - Adicionar pessoa");
			out.println("2 - Listar pessoas");
			out.println("3 - Procurar pessoa");
			out.println("4 - Remover pessoa");
			out.println("0 - Voltar");
			out.println("----------------------------------");
			out.print("Opção: ");
			op = Integer.parseInt(sc.nextLine());
			
			switch (op) {
			case 1:
				
				out.print("1 - Estudante\n2 - Funcionário\n3 - voltar");
				int opcao = Integer.parseInt(sc.nextLine());
				
				if (opcao == 1) {
					
					out.print("Nome: ");
					String nome = sc.nextLine();
					
					out.print("CC: ");
					int cc = Integer.parseInt(sc.nextLine());
					
					out.print("Data de nascimento(dd/mm/aaaa): ");
					String s = sc.nextLine();
					
					String[] parts = s.split("/");
							
					Data dataNasc = new Data(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
					
					out.print("Número mecanografico: ");
					int nMec = Integer.parseInt(sc.nextLine());
					
					out.print("Curso: ");
					String curso = sc.nextLine();
					
					 
					
				}else if (opcao == 2) {
					
				}else {
					return;
				}
			
				break;
			case 2:
				
				break;
			case 3:
				
				break;
			case 4:
				
				break;
			case 0:
				
				break;

			default:
				out.println("Opção inválida");
				out.print("Opção");
				op = Integer.parseInt(sc.nextLine());
				break;
			}
			
		} while (op != 0);
		
		
		
		
		
	} 

}
