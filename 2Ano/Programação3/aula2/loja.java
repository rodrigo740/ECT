package aula2;

import aula2.utils.*;
import java.util.*;
import static java.lang.System.*;
import java.time.*;

public class loja {
	
	static Scanner sc = new Scanner(in);
	
	public static void main(String[] args) {
		int op = 99;
		
		ArrayList<Pessoa> clientes = new ArrayList<>();
		ArrayList<Video> videos = new ArrayList<>();
		
		out.print("Quota maxima de video por utilizador: ");
		int quota = Integer.parseInt(sc.nextLine());
		
		
		
		do {
			out.println("VideoClube");
			out.println("----------------------------------");
			out.println("1 - Lista de clientes");
			out.println("2 - Catálogo de videos");
			out.println("3 - Requisitos/Devoluções");
			out.println("4 - Listar video por Rating");
			out.println("0 - sair");
			out.println("----------------------------------");
			out.print("Opção: ");
			op = Integer.parseInt(sc.nextLine());
			
			switch (op) {
			case 1:
				listaClientes(clientes,quota);
				break;
			case 2:
				catalogo(videos);
				break;
			case 3:
				reqDev(videos, clientes);
				break;
			case 4:
				listarRating(videos);
				break;
			case 0:
				exit(1);
	
			default:
				out.println("Opção inválida");
				out.print("Opção: ");
				op = Integer.parseInt(sc.nextLine());
				break;
			}
			
		} while (op != 0);
		
	}
	public static void listaClientes(ArrayList<Pessoa> clientes, int quota) {
		int op = 99;
		
		do {
			out.println();
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
				
				out.print("1 - Estudante\n2 - Funcionário\n3 - voltar\n");
				out.println("Opção: ");
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
					
					int nSocio = clientes.size()+1;
					
					LocalDate date = LocalDate.now();
					
					String[] p = date.toString().split("-");
					
					Data dataInscricao = new Data(Integer.parseInt(p[2]),Integer.parseInt(p[1]),Integer.parseInt(p[0]));
					
					Estudante c = new Estudante(nome,cc,dataNasc,nSocio,nMec,curso,dataInscricao,quota);
					
					clientes.add(c);
					
					out.printf("O seu número de sócio é: %d\n",c.nSocio());
					
				}else if (opcao == 2) {
					
					out.print("Nome: ");
					String nome = sc.nextLine();
					
					out.print("CC: ");
					int cc = Integer.parseInt(sc.nextLine());
					
					out.print("Data de nascimento(dd/mm/aaaa): ");
					String s = sc.nextLine();
					
					String[] parts = s.split("/");
							
					Data dataNasc = new Data(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
					
					out.print("Número de funcionário: ");
					int nFuncionario = Integer.parseInt(sc.nextLine());
					
					out.print("Número Fiscal: ");
					int nFiscal = Integer.parseInt(sc.nextLine());
					
					int nSocio = clientes.size()+1;
					
					
					LocalDate date = LocalDate.now();
					
					String[] p = date.toString().split("-");
					
					Data dataInscricao = new Data(Integer.parseInt(p[2]),Integer.parseInt(p[1]),Integer.parseInt(p[0]));
					
					Funcionarios c = new Funcionarios(nome,cc,dataNasc,nSocio,nFuncionario,nFiscal,dataInscricao,quota);
					
					clientes.add(c);
					
					out.printf("O seu número de sócio é: %d\n",c.nSocio());
					
				}else {
					return;
				}
			
				break;
			case 2:
				for (int i = 0; i < clientes.size(); i++) {
					out.println(clientes.get(i));
				}
				break;
			case 3:
				Pessoa temp;
				
				out.print("Número de sócio da pessoa a procurar: ");
				int n = Integer.parseInt(sc.nextLine());
				
				for (int i = 0; i < clientes.size(); i++) {
					temp = clientes.get(i);
					if (temp.nSocio() == n) {
						out.println(temp);
					}
				}
				break;
			case 4:
				Pessoa temp2;
				
				out.print("Número de sócio da pessoa a procurar: ");
				int n2 = Integer.parseInt(sc.nextLine());
				
				for (int i = 0; i < clientes.size(); i++) {
					temp2 = clientes.get(i);
					if (temp2.nSocio() == n2) {
						clientes.remove(i);
					}
				}
				break;
			case 0:
				return;
	
			default:
				out.println("Opção inválida");
				out.print("Opção");
				op = Integer.parseInt(sc.nextLine());
				break;
			}
			
		} while (op != 0);
	}
	public static void catalogo(ArrayList<Video> videos) {
		int op = 99;
		
		do {
			out.println("Catalogo");
			out.println("----------------------------------");
			out.println("1 - Adicionar video");
			out.println("2 - Remover video");
			out.println("3 - Listar videos");
			out.println("4 - Procurar video");
			out.println("0 - Voltar");
			out.println("----------------------------------");
			out.print("Opção: ");
			op = Integer.parseInt(sc.nextLine());
			
			switch (op) {
			case 1:
				
				out.print("Titulo do video: ");
				String titulo = sc.nextLine();
				
				out.print("Categoria do video: ");
				String categoria = sc.nextLine();
				
				out.print("Faixa etária: ");
				String faixa = sc.nextLine();
				
				int id = videos.size()+1;
				
				Video v = new Video(id,titulo,categoria,faixa);
				
				videos.add(v);
				
				break;
			case 2:
				
				out.print("Titulo do video a remover: ");
				String str = sc.nextLine();
				
				for (int i = 0; i < videos.size(); i++) {
					Video video = videos.get(i);
					if (video.titulo().equals(str)) {
						videos.remove(i);
						return;
					}
				}
				
				out.println("O video especificado não existe");
				break;
			case 3:
				out.println("-------------------------------------");
				out.println("Id \t Titulo \t Categoria \t Faixa \t Disponibilidade");
				for (int i = 0; i < videos.size(); i++) {
					Video temp = videos.get(i);
					out.println(temp.toString(temp));
				}
				break;
			case 4:
				out.print("Titulo do video a procurar: ");
				String t = sc.nextLine();
				
				for (int i = 0; i < videos.size(); i++) {
					Video video = videos.get(i);
					if (video.titulo().equals(t)) {
						out.println("Id \t Titulo \t Categoria \t Faixa \t Disponibilidade");
						out.println(video.toString(video));
						break;
					}
				}
				
				break;
			case 0:
				return;
	
			default:
				out.println("Opção inválida");
				out.print("Opção: ");
				op = Integer.parseInt(sc.nextLine());
				break;
			}
			
		} while (op != 0);
	}
	public static void reqDev(ArrayList<Video> videos, ArrayList<Pessoa> clientes) {
		
		out.println();
		out.println("Catalogo de Videos");
		out.println("--------------------------------");
		out.println("1 - Requisitar");
		out.println("2 - Devolver");
		out.println("3 - Listar videos requisitados");
		out.println("4 - Listar videos disponiveis");
		out.println("0 - Voltar");
		out.print("Opção: ");
		int op = Integer.parseInt(sc.nextLine());
		out.println();
		
		if (op == 1) {
			
			out.print("Número de Socio: ");
			int n = Integer.parseInt(sc.nextLine());
			
			out.print("Titulo do video a requisitar: ");
			String titulo = sc.nextLine();
			
			out.println();
			
			
			for (int i = 0; i < videos.size(); i++) {
				Video video = videos.get(i);
				if (video.titulo().equals(titulo)) {
					
					if (video.requisitado() == true) {
						out.println("Video ja requisitado");
						out.println("");
						return;
					}
					
					out.println("Id \t Titulo \t Categoria \t Faixa \t Disponibilidade");
					out.println(video.toString(video));
					
					video.requisitar(video);
					
					Pessoa a = clientes.get(0);
					
					for (int j = 0; j < clientes.size(); j++) {
						a = clientes.get(j);
						if (a.nSocio() == n) {
							a.registo(video);
							break;
						}
					}
					
					if (a.quotaAtingida(a)) {
						out.println("Quota atingida!");
						return;
					}
					
					out.println("Video requisitado com sucesso!");
					out.println();
					return;
				}
			}
			
		} else if(op == 2){
			
			out.print("Número de Socio: ");
			int n = Integer.parseInt(sc.nextLine());
			
			out.print("Titulo do video a devolver: ");
			String titulo = sc.nextLine();
			
			out.println();
			
			for (int i = 0; i < videos.size(); i++) {
				Video video = videos.get(i);
				if (video.titulo().equals(titulo)) {
					out.println("Id \t Titulo \t Categoria \t Faixa \t Disponibilidade");
					out.println(video.toString(video));
					
					video.devolver(video);
					
					for (int j = 0; j < clientes.size(); j++) {
						if (clientes.get(j).nSocio() == n) {
							clientes.get(j).registoDelet(video);
							break;
						}
					}
					
					out.println();
					out.println("Video devolvido com sucesso!");
					out.println();
					
					
					out.print("Rating do video de 1 a 10: ");
					out.println();
					int r = Integer.parseInt(sc.nextLine());
					video.addRating(video,r);
					return;
				}
			}

		}else if(op == 3){
			
			out.print("Número de Socio: ");
			int n = Integer.parseInt(sc.nextLine());
			
			out.println();
			
			for (int j = 0; j < clientes.size(); j++) {
				Pessoa a = clientes.get(j);
				if (a.nSocio() == n) {
					out.print(a.requisicoes());
				}
			}
		}else if(op == 4){
			
			out.println("Id \t Titulo \t Categoria \t Faixa \t Disponibilidade");
			
			for (int i = 0; i < videos.size(); i++) {
				Video video = videos.get(i);
				if (video.requisitado() == false) {
					out.println(video.toString(video));
				}
			}
		}else if(op == 0) {
				return;
		}else {
			out.println("Não é uma opção");
			reqDev(videos, clientes);
		}	
	}
	public static void listarRating(ArrayList<Video> videos) {
		
		ArrayList<Video> temp = new ArrayList<Video>();
		
		for (int i = 0; i < videos.size(); i++) {
			Video v1 = videos.get(i);
			
			if (i== videos.size()-1) {
				break;
			}
			
			if (v1.rating()<videos.get(i+1).rating()) {
				Video t = v1;
				Video v2 = videos.get(i+1);
				v1 = v2;
				v2 = t;
				temp.add(v1);
			}
		}
		String s = "";
		
		for (int i = 0; i < videos.size(); i++) {
			String titulo = temp.get(i).titulo();
			s += titulo + ", ";
		}
		out.println();
		out.print(s);
		out.println();
	}
}
