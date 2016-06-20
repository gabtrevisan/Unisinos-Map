import java.util.ArrayList;

//  N�s (Gabriela de Campos Trevisan, Paula Adriana Knob, Tais Felipe Rabello), garantimos que:
//
//  - N�o utilizamos c�digo fonte obtidos de outros estudantes,
//   ou fonte n�o autorizada, seja modificado ou c�pia literal.
//  - Todo c�digo usado em nosso trabalho � resultado do nosso
//   trabalho original, ou foi derivado de um
//   c�digo publicado nos livros texto desta disciplina.
//  - Temos total ci�ncia das consequ�ncias em caso de violarmos estes termos.

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

	/** Retorna refer�ncias para os dois v�rtices finais da aresta e */
	public Place[] endVertices(Road e) {
		Place[] v = new Place[2];
		v[0] = e.getV1();
		v[1] = e.getV2();
		return v;
	}

	/** Retorna uma refer�ncia para o v�rtice oposto a v na aresta e */
	public Place opposite(Place v, Road e) {
		if (v != e.getV1())
			return e.getV1();
		return e.getV2();
	}

	/**
	 * Retorna verdadeiro se os v�rtices v e w forem adjacentes, falso caso
	 * contr�rio.
	 */
	public boolean areAdjacent(Place v, Place w) {
		if (v.getAdjacents().contains(w))
			return true;
		return false;
	}

	/** Substitui o elemento da aresta e por o */
	public void replaceEdge(Road e, Road o) {
		e.setV1(o.getV1());
		e.setV2(o.getV2());
		e.setDisplacement(o.getDisplacement());
		e.setDistance(o.getDistance());
	}

	/** Substitui o elemento do v�rtice v por o */
	public void replaceVertex(Place v, Place o) {
		v.setId(o.getId());
		v.setName(o.getName());
		v.setType(o.getType());
	}

	/**
	 * Insere um novo v�rtice isolado, armazenando nele o elemento o, e retorna
	 * uma refer�ncia para esse v�rtice
	 */
	public Place insertVertex(Place o) {
		Place v = new Place(o.getId(), o.getName(), o.getType(), o.getLng(), o.getLat());
		vertices.add(v);
		return v;
	}

	/**
	 * Insere uma aresta (v,w), armazenando nela o elemento o, e retorna uma
	 * refer�ncia para essa aresta
	 */
	public Road insertEdge(Road o) {
		Place v1, v2;
		if (this.hasVertex(o.getV1()))
			v1 = o.getV1();
		else
			v1 = this.insertVertex(o.getV1());

		if (this.hasVertex(o.getV2()))
			v2 = o.getV2();
		else
			v2 = this.insertVertex(o.getV2());
		
		v1.insertAdjacent(v2);
		v2.insertAdjacent(v1);

		edges.add(o);

		return o;
	}

	/**
	 * Remove o v�rtice v (e suas arestas) e retorna o elemento armazenado nele
	 */
	public Place removeVertex(Place v) {
		ArrayList<Road> edgesToRemove = new ArrayList<Road>();

		// Remove todas as arestas que possuem V
		for (Road edge : edges) {
			if (edge.hasVertex(v))
				edgesToRemove.add(edge);
		}
		for (Road road : edgesToRemove) {
			edges.remove(road);
		}

		// Remove v dos adjacentes de cada um dos seus adjacentes
		for (Place adj : v.getAdjacents()) {
			adj.getAdjacents().remove(v);
		}
		;

		// Remove v da lista de v�rtices
		vertices.remove(v);

		return v;
	}

	/** Remove a aresta e, retornando o elemento armazenado nela */
	public Road removeEdge(Road e) {
		Road roadToRemove = null;
		for (Place place : vertices) {
			if (place.getId() == e.getV1().getId()) {
				place.getAdjacents().remove(e.getV2());
			}
			if (place.getId() == e.getV2().getId()) {
				place.getAdjacents().remove(e.getV1());
			}
		}

		for (Road edge : edges) {
			if (edge == e)
				roadToRemove = edge;
		}
		if (roadToRemove != null)
			edges.remove(roadToRemove);
		return e;
	}

	/** Retorna o elemento armazenado na aresta e */
	public Road edgeValue(Place v1, Place v2) {
		for (Road road : this.getVertexEdges(v1)) {
			if(this.opposite(v1, road).getId() == v2.getId())
				return road;
		}
		return null;
	}

	/** Retorna o elemento armazenado no v�rtice v */
	public Place vertexValue(int id) {
		for (int i = 0; i < vertices.size(); i++) {
			if (vertices.get(i).getId() == id)
				return vertices.get(i);
		}
		return null;
	}

	/** Retorna todas as arestas que o v�rtice est� */
	public ArrayList<Road> getVertexEdges(Place p) {
		ArrayList<Road> edges = new ArrayList<Road>();
		for (int i = 0; i < this.getEdges().size(); i++) {
			if (this.getEdges().get(i).hasVertex(p))
				edges.add(this.getEdges().get(i));
		}
		return edges;
	}

	/**
	 * Retorna a quantidade m�nima de tinta (em litros) necess�ria para pintar
	 * uma faixa ligando os locais da universidade. Para cada 10 metros �
	 * necess�rio 1L de tinta.
	 */
	public double minToPaint() {
		return this.totalDistance() / 10;
	}

	/**
	 * Retorna a soma das dist�ncias, em metros, de todas as arestas do grafo
	 */
	private double totalDistance() {
		double distance = 0;
		for (Road road : edges) {
			distance += road.getDistance();
		}
		return distance;
	}

	/**
	 * Verifica se o grafo possui o v�rtice
	 * 
	 * @param p
	 * @return boolean
	 */
	public boolean hasVertex(Place p) {
		for (Place place : vertices) {
			if (place.getId() == p.getId()) {
				return true;
			}
		}
		return false;
	}

	public String toString() {
		String s = "";
		for (int i = 0; i < this.getEdges().size(); i++) {
			s += this.getEdges().get(i) + "\n";
		}
		return s;
	}
}
