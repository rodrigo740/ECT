package aula2.utils;

public class Video {
	
	private int id;
	private String titulo;
	private String categoria;
	private String faixa;
	private boolean requisitado = false;
	private int rating = 0;
	private double ratingMedio = 0;
	private int utilizadores = 0;
	
	public Video(int id,String titulo,String categoria,String faixa) {
		this.id = id;
		this.titulo = titulo;
		this.categoria = categoria;
		this.faixa = faixa;
	}
	public int id() {
		return id;
	}
	public String titulo() {
		return titulo;
	}
	public String categoria() {
		return categoria;
	}
	public String faixa() {
		return faixa;
	}
	public boolean requisitado() {
		return requisitado;
	}
	public void requisitar(Video video) {
		video.requisitado = true;
		video.utilizadores++;
	}
	public void devolver(Video video) {
		video.requisitado = false;
	}
	public void addRating(Video video,int r) {
		video.rating += r;
		video.ratingMedio = rating/utilizadores;
	}
	public int rating() {
		return rating;
	}
	public double ratingMedio() {
		return ratingMedio;
	}
	public String toString(Video video) {
		
		String r;
		
		if (video.requisitado() == true) {
			r = "Requisitado";
		}else {
			r = "Disponivel";
		}
		return String.valueOf(video.id()) + "\t " + video.titulo() + "\t " + video.categoria() + "\t\t " + video.faixa() + "\t " + r;
	}
}
