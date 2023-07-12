package it.polito.tdp.imdb.model;

public class Arco {
	
	private int id1;
	private int id2;
	private int attori;
	
	public Arco(int id1, int id2, int attori) {
		super();
		this.id1 = id1;
		this.id2 = id2;
		this.attori = attori;
	}

	public int getId1() {
		return id1;
	}

	public void setId1(int id1) {
		this.id1 = id1;
	}

	public int getId2() {
		return id2;
	}

	public void setId2(int id2) {
		this.id2 = id2;
	}

	public int getAttori() {
		return attori;
	}

	public void setAttori(int attori) {
		this.attori = attori;
	}

}
