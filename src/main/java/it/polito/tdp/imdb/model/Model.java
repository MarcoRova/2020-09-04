package it.polito.tdp.imdb.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.imdb.db.ImdbDAO;

public class Model {
	
	private ImdbDAO dao;
	private Graph<Movie, DefaultWeightedEdge> grafo;
	private List<Movie> vertici;
	private Map<Integer,  Movie> moviesIdMap;
	
	public Model() {
		this.dao = new ImdbDAO();
		this.moviesIdMap = new HashMap<>();
		
		
	}
	
	
	public void creaGrafo(Double rank) {
		
		this.grafo = new SimpleWeightedGraph<Movie, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		this.vertici = this.dao.getVertici();
		
		for(Movie m:vertici) {
			this.moviesIdMap.put(m.getId(), m);
		}
		
		Graphs.addAllVertices(this.grafo, vertici);
		
		for(Arco a : this.dao.getArchi(rank)) {
			
			Graphs.addEdgeWithVertices(this.grafo, this.moviesIdMap.get(a.getId1()), this.moviesIdMap.get(a.getId2()), a.getAttori());
			
		}
		
	}
	
	public GradoMassimo gradoMassimo() {
		
		Movie mTemp = null;
		int sommaBest = 0;
		int sommaP = 0;
		
		for(Movie m : vertici) {
			
			sommaP = 0;
			
			for(DefaultWeightedEdge e : this.grafo.edgesOf(m)) {
				
				sommaP += this.grafo.getEdgeWeight(e);
			}
			
			if(sommaP>sommaBest) {
				sommaBest = sommaP;
				mTemp = m;
			}
		}
		
		return new GradoMassimo(mTemp, sommaBest);	
	}
	
	
	public List<Movie> calcolaCammino(Movie partenza){
		
		List<Movie> cammino = new ArrayList<>();
		
		List<Movie> parziale = new ArrayList<>();
		
		double pesoP = 0;
		
		parziale.add(partenza);
		
		ricorsione(parziale, pesoP);
		
		cammino = new ArrayList<>(parziale);		
		return cammino;
		
	}
	
	
	
	public void ricorsione(List<Movie> parziale, double pesoP) {
		
		List<Movie> adiacenti = Graphs.neighborListOf(this.grafo, parziale.get(parziale.size()-1));
		
		for(Movie m : adiacenti) {
			
			DefaultWeightedEdge e = this.grafo.getEdge(parziale.get(parziale.size()-1), m);
			
			double peso = this.grafo.getEdgeWeight(e);
			
			if(peso>=pesoP && !parziale.contains(m)) {
				parziale.add(m);
				pesoP = peso;
				ricorsione(parziale, pesoP);
				parziale.remove(parziale.size()-1);
			}
		}
	}


	public Graph<Movie, DefaultWeightedEdge> getGrafo() {
		return grafo;
	}

	public List<Movie> getVertici() {
		return vertici;
	}
	
	
	public String infoGrafo() {
		return "Grafo creato!\n#Vertici: "+this.grafo.vertexSet().size()+"\n#Archi: "+this.grafo.edgeSet().size();
	}

	
	
	
	
}
