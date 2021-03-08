package aula12.exercicio1;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Test {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println(" ---------- Inicio do teste da Classe Figura ----------\n");
		
		Class reflectClass = Figura.class;
		String className = reflectClass.getName();
		System.out.println("Nome da classe: " + className);
		
		System.out.println("\n ---------- Inicio do teste dos modificadores ----------\n");
		
		int classModifier = reflectClass.getModifiers();
		System.out.println("É uma interface: " + Modifier.isInterface(classModifier));
		System.out.println("É uma classe final: " + Modifier.isFinal(classModifier));
		System.out.println("É uma classe abstrata: " + Modifier.isAbstract(classModifier));
		System.out.println("É uma classe pública: " + Modifier.isPublic(classModifier));
		
		System.out.println("\n ---------- Inicio do teste dos métodos ----------\n");
		
		Method classMethods[] = reflectClass.getMethods();
		System.out.println("Nome dos métodos da class " + className + ":\n");
		
		for (Method method : classMethods) {
			System.out.println("Nome do método: " + method.getName());
			System.out.println("Tipo de retorno: " + method.getReturnType() + "\n");
			
			Class[] parameterType = method.getParameterTypes();
			System.out.println("Parametros: ");
			
			for (Class parameter : parameterType) {
				System.out.println(parameter.getName());
			}
		}
		
		System.out.println(" ---------- Inicio do teste da Classe Quadrado ----------\n");
		
		
		//Criar objetos de teste
		Quadrado q1 = new Quadrado(10);
		Quadrado q2 = new Quadrado(3);
		Quadrado q3 = new Quadrado(5);
		List<Quadrado> lista = new LinkedList<>(); 
		
		reflectClass = Quadrado.class;
		className = reflectClass.getName();
		System.out.println("Nome da classe: " + className);
		
		System.out.println("\n ---------- Inicio do teste dos modificadores ----------\n");
		
		classModifier = reflectClass.getModifiers();
		System.out.println("É uma interface: " + Modifier.isInterface(classModifier));
		System.out.println("É uma classe final: " + Modifier.isFinal(classModifier));
		System.out.println("É uma classe abstrata: " + Modifier.isAbstract(classModifier));
		System.out.println("É uma classe pública: " + Modifier.isPublic(classModifier));
		
		System.out.println("\n ---------- Inicio do teste dos métodos ----------\n");
		
		classMethods = reflectClass.getMethods();
		System.out.println("Nome dos métodos da class " + className + ":\n");
		
		for (Method method : classMethods) {
			System.out.println("Nome do método: " + method.getName());
			System.out.println("Tipo de retorno: " + method.getReturnType() + "\n");
			
			Class[] parameterType = method.getParameterTypes();
			System.out.println("Parametros: ");
			
			for (Class parameter : parameterType) {
				System.out.println(parameter.getName());
			}
		}
		
		System.out.println("\n ---------- Inicio do teste dos construtores ----------\n");
		
		Constructor[] constructor = reflectClass.getConstructors();
		int i = 1;
		
		for (Constructor constructor2 : constructor) {
			Class[] parameters = constructor2.getParameterTypes();
			
			System.out.println("Construtor " + i + " com parametros: ");
			
			for (Class parameter : parameters) {
				System.out.print(parameter.getName()+" ");
			}
			System.out.println("\n");
			i++;
		}
		
		int op = 0;
		
		do {
			
			System.out.println("1- Criar quadrado");
			System.out.println("2- sair");
			System.out.println("Opcao: ");
			op = sc.nextInt();
			switch (op) {
			case 1:
				System.out.println("Qual construtor pretende usar: ");
				int n = sc.nextInt();
				
				if (n == 1) {
					
					System.out.println("1- " + q1.toString());
					System.out.println("2- " + q2.toString());
					System.out.println("3- " + q3.toString());
					
					System.out.println("Qual destes quadrados pretende utilizar no construtor: ");
					int escolha = sc.nextInt();
					
					if (escolha == 1) {
						
						Object obj = null;
						
						try {
							obj = reflectClass.getConstructor(new Class[]{Quadrado.class}).newInstance(q1);
						} catch (InstantiationException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							e.printStackTrace();
						} catch (NoSuchMethodException e) {
							e.printStackTrace();
						} catch (SecurityException e) {
							e.printStackTrace();
						}
						
						
						
						
						System.out.println("Objeto criado com sucesso!\n");
						lista.add((Quadrado)obj);
						
					} else if(escolha == 2){
						
						Object obj = null;
						
						try {
							obj = reflectClass.getConstructor(new Class[]{Quadrado.class}).newInstance(q2);
						} catch (InstantiationException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							e.printStackTrace();
						} catch (NoSuchMethodException e) {
							e.printStackTrace();
						} catch (SecurityException e) {
							e.printStackTrace();
						}
						
						System.out.println("Objeto criado com sucesso!\n");
						lista.add((Quadrado)obj);
						
					}else {
						
						Object obj = null;
						
						try {
							obj = reflectClass.getConstructor(new Class[]{Quadrado.class}).newInstance(q3);
						} catch (InstantiationException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							e.printStackTrace();
						} catch (NoSuchMethodException e) {
							e.printStackTrace();
						} catch (SecurityException e) {
							e.printStackTrace();
						}
						
						System.out.println("Objeto criado com sucesso!\n");
						lista.add((Quadrado)obj);
					}
					
				}else if (n == 2) {
					
					Object obj = null;
					
					System.out.println("Coordenada x do centro: ");
					double x = sc.nextDouble();
					System.out.println("Coordenada y do centro: ");
					double y = sc.nextDouble();
					System.out.println("Tamanho do lado do quadrado: ");
					double lado = sc.nextDouble();
					
					try {
						obj = reflectClass.getConstructor(double.class, double.class, double.class).newInstance(x,y,lado);
					} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
							| InvocationTargetException | NoSuchMethodException | SecurityException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					lista.add((Quadrado) obj);
					System.out.println("Objeto criado com sucesso!\n");
					
				}else if(n == 3){
					Object obj = null;
					
					System.out.println("Tamanho do lado do quadrado: ");
					double lado = sc.nextDouble();
					
					try {
						obj = reflectClass.getConstructor(double.class).newInstance(lado);
					} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
							| InvocationTargetException | NoSuchMethodException | SecurityException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					lista.add((Quadrado) obj);
					System.out.println("Objeto criado com sucesso!\n");
					
				}
				break;
			default:
				break;
			}
		} while (op != 2);
		sc.close();
		
		System.out.println("\n ---------- Utilizando o método toString() em todos os quadrados ----------\n");
		for (Quadrado q : lista) {
			System.out.println(q.toString());
		}
	}

	
	

}
