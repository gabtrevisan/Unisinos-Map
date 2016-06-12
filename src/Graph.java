import java.util.ArrayList;

//  Nós (Gabriela de Campos Trevisan, Paula Adriana Knob, Tais Felipe Rabello), garantimos que:
//
//  - Não utilizamos código fonte obtidos de outros estudantes,
//   ou fonte não autorizada, seja modificado ou cópia literal.
//  - Todo código usado em nosso trabalho é resultado do nosso
//   trabalho original, ou foi derivado de um
//   código publicado nos livros texto desta disciplina.
//  - Temos total ciência das consequências em caso de violarmos estes termos.

public class Graph {
	private ArrayList<Road> edges;
	private ArrayList<Place> vertices;
	
	public Graph() {
		this.edges = new ArrayList<>();
		this.vertices = new ArrayList<>();
	}	
	
	public ArrayList<Road> getEdges() {
		return edges;
	}
	
	public ArrayList<Place> getVertices() {
		return vertices;
	}

	/* Retorna referências para os dois vértices finais da aresta e */
	public Place[] endVertices(Road e) {
		Place[] v = new Place[2];
		v[0] = e.getV1();
		v[1] = e.getV2();
		return v;
	}
	 
	
	/* Retorna uma referência para o vértice oposto a v na aresta e */
	public Place opposite(Place v, Road e) {
		if(v != e.getV1())
			return e.getV1();
		return e.getV2();
	}
	
	/* Retorna verdadeiro se os vértices v e w forem adjacentes, falso caso contrário. */
	public boolean areAdjacent(Place v, Place w) {
		for (Road edge : edges) {
			if(edge.hasVertex(v) && edge.hasVertex(w)) 
				return true;			
		}
		return false;
	}
	
	/* Substitui o elemento da aresta e por o */
	public void replaceEdge(Road e, Road o) {
		e.setV1(o.getV1());
		e.setV2(o.getV2());
		e.setDisplacement(o.getDisplacement());
		e.setDistance(o.getDistance());
	}
	
	/* Substitui o elemento do vértice v por o */
	public void replaceVertex(Place v, Place o) {
		v.setId(o.getId());
		v.setName(o.getName());
		v.setType(o.getType());
	}
	
	/* Insere um novo vértice isolado, armazenando nele o elemento o, e retorna uma referência para esse vértice */
	public Place insertVertex(Place o) {
		Place v = new Place(o.getId(), o.getName(), o.getType());
		vertices.add(v);
		return v;
	}
	
	/* Insere uma aresta (v,w), armazenando nela o elemento o, e retorna uma referência para essa aresta */
	public Road insertEdge(Road o) {
		Road e = new Road(o.getV1(), o.getV2(), o.getDisplacement(), o.getDistance());
		edges.add(e);
		return e;
	}
	
	/* Remove o vértice v (e suas arestas) e retorna o elemento armazenado nele */
	public Place removeVertex(Place v) {
		for (Road edge : edges) {
			if(edge.hasVertex(v)) 
				edges.remove(edge);
		}
		vertices.remove(v);
		return v;
	}
	
	/* Remove a aresta e, retornando o elemento armazenado nela */
	public Road removeEdge(Road e) {
		for (Road edge : edges) {
			if(edge == e) 
				edges.remove(edge);
		}
		return e;
	}
	
	/* Retorna o elemento armazenado na aresta e */
	/*public <E> E edgeValue(Road e) {
		return (E) e.getData();
	}*/
	
	/* Retorna o elemento armazenado no vértice v */
	public Place vertexValue(int id) {
		for (int i = 0; i < vertices.size(); i++) {
			if(vertices.get(i).getId() == id)
				return vertices.get(i);
		}
		return null;
	}	
	
	public ArrayList<Road> getVertexEdges(Place p) {
		ArrayList<Road> edges = new ArrayList<Road>();
		for (int i = 0; i < this.getEdges().size(); i++) {
			if(this.getEdges().get(i).hasVertex(p))
				edges.add(this.getEdges().get(i));
		}
		return edges;
	}
	
	public boolean hasVertex(Place p){
		for (Place place : vertices) {
			if(place.getId() == p.getId()){
				return true;
			}
		}
		return false;
	}
	
	public String toString() {
		String s = "";
		for (int i = 0; i < this.getEdges().size(); i++) {
			s += this.getEdges().get(i)+"\n";
		}	
		return s;
	}
}
