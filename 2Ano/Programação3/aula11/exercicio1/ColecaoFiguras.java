package aula11.exercicio1;

import java.util.*;

public class ColecaoFiguras {
	
	private ArrayList<Figura> figuras;
	private double areaMax;
	private double areaTotal;
	
	public ColecaoFiguras(double areaMax) {
		this.areaMax = areaMax;
		this.areaTotal = 0;
		this.figuras = new ArrayList<>();
	}
	public boolean addFigura(Figura f) {
		if ((areaTotal + f.area() > areaMax) || exists(f)) {
			return false;
		}
		
		areaTotal += f.area();
		return  figuras.add(f);
	}
	public boolean delFigura(Figura f) {
		if (!exists(f)) {
			return false;
		}
		
		areaTotal -= f.area();
		return figuras.remove(f);
	}
	public double areaTotal() {
		return this.areaTotal;
	}
	public boolean exists(Figura f) {
		return figuras.contains(f);
	}
	public Figura[] getFiguras() {
		return figuras.toArray(new Figura[0]);
	}
	public Ponto[] getCentros() {
		Ponto []centros = new Ponto[figuras.size()];
		for (int i = 0; i < figuras.size(); i++) {
			centros[i] = figuras.get(i).centro();
		}
		return centros;
	}
	
	@Override
	public String toString() {
		return "Area maxima: " + areaTotal() + "\nNumero de figuras: " + figuras.size();
	}
}
