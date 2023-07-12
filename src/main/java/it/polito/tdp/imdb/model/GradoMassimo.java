package it.polito.tdp.imdb.model;

public class GradoMassimo {
	
	Movie m;
	int somma;
	
	
	public GradoMassimo(Movie m, int somma) {
		super();
		this.m = m;
		this.somma = somma;
	}


	public Movie getM() {
		return m;
	}


	public void setM(Movie m) {
		this.m = m;
	}


	public int getSomma() {
		return somma;
	}


	public void setSomma(int somma) {
		this.somma = somma;
	}
	
	
	
	

}
