package aula4.exercicio1;

import java.util.*;

public class Disciplina {
	
	private String nomeDisciplina;
	private String area;
	private int ects;
	private Professor responsavel;
	private ArrayList<Estudante> alunos;
	
	public Disciplina(String n,String a, int e,Professor p) {
		nomeDisciplina = n;
		area = a;
		ects = e;
		responsavel = p;
		alunos = new ArrayList<Estudante>();
	}
	public String nomeDisciplina() {
		return this.nomeDisciplina;
	}
	public String area() {
		return this.area;
	}
	public int ects() {
		return this.ects;
	}
	public Professor responsavel() {
		return this.responsavel;
	}
	public List<Estudante> alunos() {
		return this.alunos;
	}
	public boolean addAluno(Estudante est) {
		if (alunos.contains(est)) {
			return false;
		}else {
			alunos.add(est);
			return true;
		}
	}
	public boolean delAluno(int nMec) {
		for (int i = 0; i < alunos.size(); i++) {
			if (alunos.get(i).nMec() == nMec) {
				alunos.remove(i);
				return true;
			}
		}
		return false;
	}
	public boolean alunoInscrito(int nMec) {
		for (int i = 0; i < alunos.size(); i++) {
			if (alunos.get(i).nMec() == nMec) {
				return true;
			}
		}
		return false;
	}
	public int numAlunos() {
		return alunos.size();
	}
	
	@Override
	public String toString() {
		return "Disciplina " + nomeDisciplina + "\nArea: " + area + "\nEcts: " + String.valueOf(ects) + "\nProfessor: " + responsavel.toString() + "\nNumero de alunos inscritos: " + String.valueOf(numAlunos());
	}
	public Estudante[] getAlunos() {
		return alunos.toArray(new Estudante[0]);
	}
	public Estudante[] getAlunos(String tipo) {
		ArrayList<Estudante> temp = new ArrayList<>();
		for (int i = 0; i < alunos.size(); i++) {
			if (alunos.get(i).toString().contains(tipo)) {
				temp.add(alunos.get(i));
			}
		}
		
		return temp.toArray(new Estudante[0]);
	}
}
