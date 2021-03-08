
import static java.lang.System.*;

import org.json.simple.parser.*; 
import java.util.Scanner;
import java.io.*;
public class projeto {
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) throws IOException,ParseException {
		
		int opcao=0;
		do {
			out.println("               Menu               ");
			out.println("----------------------------------");
			out.println("1 - Formatacao das reviews");
			out.println("2 - Reviews por jogo");
			out.println("3 - Detecao de spam - Blomm Filter");
			out.println("4 - Detecao de reviews similares - MinHash");
			out.println("5 - Terminar");
			out.print("Opção: ");
			opcao = sc.nextInt();
			switch (opcao) {
				case 1:
					new  JsonParser(); //formatar as reviews
					break;
				case 2:
					countreviews();
					break;
				case 3:
					spam();
					break;
				case 4:
					reviewsSilimares();
					break;
				case 5:
					break;
				default:
					break;
			}
		}while(opcao!=5);
	}

	
	//contar reviews por jogo
	public static void countreviews() throws IOException{
		int opcao1=0;
		String nome=""; 
		String jogo="";
		do {
			out.println("               Jogos              ");
			out.println("----------------------------------");
			out.println("1 - Arma 3");
			out.println("2 - Counter Strike");
			out.println("3 - Counter Strike Global Offensive");
			out.println("4 - Dota 2");
			out.println("5 - Football Manager 2015");
			out.println("6 - Garrys Mod");
			out.println("7 - Grand Theft Auto V");
			out.println("8 - Sid Meiers Civilization 5");
			out.println("9 - Team Fortress 2");
			out.println("10 - The Elder Scrolls V");
			out.println("11 - Warframe");
			out.println("12 - Voltar");
			out.print("Opção: ");
			opcao1 = sc.nextInt();
			switch (opcao1) {
				case 1:
					nome="reviews/Arma_3";
					jogo="Arma 3";
					jogo(nome,jogo);
					break;
				case 2:
					nome="reviews/Counter_Strike";
					jogo="Counter Strike";
					jogo(nome,jogo);
					break;
				case 3:
					nome="reviews/Counter_Strike_Global_Offensive";
					jogo="Counter Strike Global Offensive";
					jogo(nome,jogo);
					break;
				case 4:
					nome="reviews/Dota_2";
					jogo="Dota 2";
					jogo(nome,jogo);
					break;
				case 5:
					nome="reviews/Football_Manager_2015";
					jogo="Football Manager 2015";
					jogo(nome,jogo);
					break;
				case 6: 
					nome="reviews/Garrys_Mod";
					jogo="Garrys Mod";
					jogo(nome,jogo);
					break;
				case 7:
					nome="reviews/Grand_Theft_Auto_V";	
					jogo="Grand Theft Auto V";
					jogo(nome,jogo);
					break;
				case 8:
					nome="reviews/Sid_Meiers_Civilization_5";
					jogo="Sid Meiers Civilization 5";
					jogo(nome,jogo);
					break;
				case 9:
					nome="reviews/Team_Fortress_2";
					jogo="Team Fortress 2";
					jogo(nome,jogo);
					break;
				case 10:
					nome="reviews/The_Elder_Scrolls_V";
					jogo="The Elder Scrolls V";
					jogo(nome,jogo);
					break;
				case 11:
					nome="reviews/Warframe";
					jogo="Warframe";
					jogo(nome,jogo);
					break;
				case 12:
					break;
				default:
					break;
			}
			
		}while(opcao1!=12);
		
		
	}
	
	public static void jogo(String nome, String jogo) throws IOException{
		Scanner lerfich = new Scanner(new File (nome));
		int count=0;
		while(lerfich.hasNextLine()) {
			String s = lerfich.nextLine();
			count++;
		}
		lerfich.close();
		out.println();
		out.println("O jogo " + jogo + " tem "+ count + " reviews");
		out.println();
		
	}
	
	//Deteta spam 
	public static void spam() throws IOException{
		int opcao2=0;
		String nome=""; 
		String nomec="";
		do {
			out.println("               Jogos              ");
			out.println("----------------------------------");
			out.println("1 - Arma 3");
			out.println("2 - Counter Strike");
			out.println("3 - Counter Strike Global Offensive");
			out.println("4 - Dota 2");
			out.println("5 - Football Manager 2015");
			out.println("6 - Garrys Mod");
			out.println("7 - Grand Theft Auto V");
			out.println("8 - Sid Meiers Civilization 5");
			out.println("9 - Team Fortress 2");
			out.println("10 - The Elder Scrolls V");
			out.println("11 - Warframe");
			out.println("12 - Voltar");
			out.print("Opção: ");
			opcao2 = sc.nextInt();
			switch (opcao2) {
				case 1:
					nome="reviews/Arma_3";
					nomec="reviewscopiadas/Arma_3_copy.txt";
					spam s = new spam(nome,nomec);
					s.lerficheiros();
					s.detetarspam();
					break;
				case 2:
					nome="reviews/Counter_Strike";
					nomec="reviewscopiadas/Counter_Strike_copy.txt";
					spam s1 = new spam(nome,nomec);
					s1.lerficheiros();
					s1.detetarspam();
					break;
				case 3:
					nome="reviews/Counter_Strike_Global_Offensive";
					nomec="reviewscopiadas/Counter_Strike_Global_Offensive_copy.txt";
					spam s2 = new spam(nome,nomec);
					s2.lerficheiros();
					s2.detetarspam();
					break;
				case 4:
					nome="reviews/Dota_2";
					nomec="reviewscopiadas/Dota_2_copy.txt";
					spam s3 = new spam(nome,nomec);
					s3.lerficheiros();
					s3.detetarspam();
					break;
				case 5:
					nome="reviews/Football_Manager_2015";
					nomec="reviewscopiadas/Football_Manager_2015_copy.txt";
					spam s4 = new spam(nome,nomec);
					s4.lerficheiros();
					s4.detetarspam();
					break;
				case 6: 
					nome="reviews/Garrys_Mod";
					nomec="reviewscopiadas/Garrys_Mod_copy.txt";
					spam s5 = new spam(nome,nomec);
					s5.lerficheiros();
					s5.detetarspam();
					break;
				case 7:
					nome="reviews/Grand_Theft_Auto_V";	
					nomec="reviewscopiadas/Grand_Theft_Auto_V_copy.txt";
					spam s6 = new spam(nome,nomec);
					s6.lerficheiros();
					s6.detetarspam();
					break;
				case 8:
					nome="reviews/Sid_Meiers_Civilization_5";
					nomec="reviewscopiadas/Sid_Meiers_Civilization_5_copy.txt";
					spam s7 = new spam(nome,nomec);
					s7.lerficheiros();
					s7.detetarspam();
					break;
				case 9:
					nome="reviews/Team_Fortress_2";
					nomec="reviewscopiadas/Team_Fortress_2_copy.txt";
					spam s8 = new spam(nome,nomec);
					s8.lerficheiros();
					s8.detetarspam();
					break;
				case 10:
					nome="reviews/The_Elder_Scrolls_V";
					nomec="reviewscopiadas/The_Elder_Scrolls_V_copy.txt";
					spam s9 = new spam(nome,nomec);
					s9.lerficheiros();
					s9.detetarspam();
					break;
				case 11:
					nome="reviews/Warframe";
					nomec="reviewscopiadas/Warframe_copy.txt";
					spam s10 = new spam(nome,nomec);
					s10.lerficheiros();
					s10.detetarspam();
					break;
				case 12:
					break;
				default:
					break;
			}
			
		}while(opcao2!=12);
	}
	
	//Deteta reviews similares
	public static void reviewsSilimares() throws IOException {
		
		int opcao = 0;
		double indice = 0.7;
		MinHash min;
		String []s;
		String str = "";
		
		do {
			sc.nextLine();
			System.out.println("Introduza o indice de similaridade desejado:");
			indice = Double.parseDouble(sc.nextLine());
			System.out.println(indice);
			
		}while (indice <= 0 || indice > 1);
		
		do {
			out.println("               Jogos              ");
			out.println("----------------------------------");
			out.println("1 - Arma 3");
			out.println("2 - Counter Strike");
			out.println("3 - Counter Strike Global Offensive");
			out.println("4 - Dota 2");
			out.println("5 - Football Manager 2015");
			out.println("6 - Garrys Mod");
			out.println("7 - Grand Theft Auto V");
			out.println("8 - Sid Meiers Civilization 5");
			out.println("9 - Team Fortress 2");
			out.println("10 - The Elder Scrolls V");
			out.println("11 - Warframe");
			out.println("12 - Voltar");
			out.print("Opção: ");
			opcao = sc.nextInt();
			
			switch (opcao) {
				case 1:
					min = new MinHash("Arma_3", indice);
					s = min.compareSignatures();
					str = "";
					
					for (int i = 0; i < s.length; i++) {
						if (s[i] != null && s[i] != str) {
							System.out.println(s[i]);
							str = s[i];
						}
					}
					
					
					System.out.println("O ficheiro com os comentarios similares pode ser encontrado em /comentarios_similares/comentarios_Arma_3\n");
					System.out.println("O ficheiro com a matriz de assinaturas pode ser encontrado em /matriz_Assinaturas/matriz_Arma_3\n");

					break;
				case 2:
					min = new MinHash("Counter_Strike", indice);
					s = min.compareSignatures();
					
					str = "";
					for (int i = 0; i < s.length; i++) {
						if (s[i] != null && s[i] != str) {
							System.out.println(s[i]);
							str = s[i];
						}
					}
					
					
					System.out.println("O ficheiro com os comentarios similares pode ser encontrado em /comentarios_similares/comentarios_Counter_Strike\n");
					System.out.println("O ficheiro com a matriz de assinaturas pode ser encontrado em /matriz_Assinaturas/matriz_Counter_Strike\n");

					break;
				case 3:
					min = new MinHash("Counter_Strike_Global_Offensive", indice);
					s = min.compareSignatures();
					
					str = "";
					for (int i = 0; i < s.length; i++) {
						if (s[i] != null && s[i] != str) {
							System.out.println(s[i]);
							str = s[i];
						}
					}
					
					
					System.out.println("O ficheiro com os comentarios similares pode ser encontrado em /comentarios_similares/comentarios_Counter_Strike_Global_Offensive\n");
					System.out.println("O ficheiro com a matriz de assinaturas pode ser encontrado em /matriz_Assinaturas/matriz_Counter_Strike_Global_Offensive\n");

					break;
				case 4:
					min = new MinHash("Dota_2", indice);
					s = min.compareSignatures();
					
					str = "";
					for (int i = 0; i < s.length; i++) {
						if (s[i] != null && s[i] != str) {
							System.out.println(s[i]);
							str = s[i];
						}
					}
					
					
					System.out.println("O ficheiro com os comentarios similares pode ser encontrado em /comentarios_similares/comentarios_Dota_2\n");
					System.out.println("O ficheiro com a matriz de assinaturas pode ser encontrado em /matriz_Assinaturas/matriz_Dota_2\n");

					break;
				case 5:
					min = new MinHash("Football_Manager_2015", indice);
					s = min.compareSignatures();
					
					str = "";
					for (int i = 0; i < s.length; i++) {
						if (s[i] != null && s[i] != str) {
							System.out.println(s[i]);
							str = s[i];
						}
					}
					
					
					System.out.println("O ficheiro com os comentarios similares pode ser encontrado em /comentarios_similares/comentarios_Football_Manager_2015\n");
					System.out.println("O ficheiro com a matriz de assinaturas pode ser encontrado em /matriz_Assinaturas/matriz_Football_Manager_2015\n");

					break;
				case 6: 
					min = new MinHash("Garrys_Mod", indice);
					s = min.compareSignatures();
					
					str = "";
					for (int i = 0; i < s.length; i++) {
						if (s[i] != null && s[i] != str) {
							System.out.println(s[i]);
							str = s[i];
						}
					}
					
					
					System.out.println("O ficheiro com os comentarios similares pode ser encontrado em /comentarios_similares/comentarios_Garrys_Mod\n");
					System.out.println("O ficheiro com a matriz de assinaturas pode ser encontrado em /matriz_Assinaturas/matriz_Garrys_Mod\n");

					break;
				case 7:
					min = new MinHash("Grand_Theft_Auto_V", indice);
					s = min.compareSignatures();
					
					str = "";
					for (int i = 0; i < s.length; i++) {
						if (s[i] != null && s[i] != str) {
							System.out.println(s[i]);
							str = s[i];
						}
					}
					
					
					System.out.println("O ficheiro com os comentarios similares pode ser encontrado em /comentarios_similares/comentarios_Grand_Theft_Auto_V\n");
					System.out.println("O ficheiro com a matriz de assinaturas pode ser encontrado em /matriz_Assinaturas/matriz_Grand_Theft_Auto_V\n");

					break;
				case 8:
					min = new MinHash("Sid_Meiers_Civilization_5", indice);
					s = min.compareSignatures();
					
					str = "";
					for (int i = 0; i < s.length; i++) {
						if (s[i] != null && s[i] != str) {
							System.out.println(s[i]);
							str = s[i];
						}
					}
					
					
					System.out.println("O ficheiro com os comentarios similares pode ser encontrado em /comentarios_similares/comentarios_Sid_Meiers_Civilization_5\"\n");
					System.out.println("O ficheiro com a matriz de assinaturas pode ser encontrado em /matriz_Assinaturas/matriz_Sid_Meiers_Civilization_5\n");
					break;
				case 9:
					min = new MinHash("Team_Fortress_2", indice);
					s = min.compareSignatures();
					
					str = "";
					for (int i = 0; i < s.length; i++) {
						if (s[i] != null && s[i] != str) {
							System.out.println(s[i]);
							str = s[i];
						}
					}
					
					
					System.out.println("O ficheiro com os comentarios similares pode ser encontrado em /comentarios_similares/comentarios_Team_Fortress_2\n");
					System.out.println("O ficheiro com a matriz de assinaturas pode ser encontrado em /matriz_Assinaturas/matriz_Team_Fortress_2\n");

					break;
				case 10:
					min = new MinHash("The_Elder_Scrolls_V", indice);
					s = min.compareSignatures();
					
					str = "";
					for (int i = 0; i < s.length; i++) {
						if (s[i] != null && s[i] != str) {
							System.out.println(s[i]);
							str = s[i];
						}
					}
					
					
					System.out.println("O ficheiro com os comentarios similares pode ser encontrado em /comentarios_similares/comentarios_The_Elder_Scrolls_V\n");
					System.out.println("O ficheiro com a matriz de assinaturas pode ser encontrado em /matriz_Assinaturas/matriz_The_Elder_Scrolls_V\n");

					break;
				case 11:
					min = new MinHash("Warframe", indice);
					s = min.compareSignatures();
					
					str = "";
					for (int i = 0; i < s.length; i++) {
						if (s[i] != null && s[i] != str) {
							System.out.println(s[i]);
							str = s[i];
						}
					}
					
					
					System.out.println("O ficheiro com os comentarios similares pode ser encontrado em /comentarios_similares/comentarios_Warframe\n");
					System.out.println("O ficheiro com a matriz de assinaturas pode ser encontrado em /matriz_Assinaturas/matriz_Warframe\n");

					break;
				case 12:
					return;
				default:
					break;
			}
		} while (opcao != 12);
	}
}
