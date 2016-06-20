import java.util.ArrayList;
import java.util.Collections;

//Nós (Gabriela de Campos Trevisan, Paula Adriana Knob, Tais Felipe Rabello), garantimos que:
//
//- Não utilizamos código fonte obtidos de outros estudantes,
//ou fonte não autorizada, seja modificado ou cópia literal.
//- Todo código usado em nosso trabalho é resultado do nosso
//trabalho original, ou foi derivado de um
//código publicado nos livros texto desta disciplina.
//- Temos total ciência das consequências em caso de violarmos estes termos.

public class Dijsktra {
	private Graph graph;
	private Place source;
	private Place dest;
	private String disp;
	private double distance[];
	private Place parent[];

	public Dijsktra(Graph g, Place s, Place d, String disp) {
		this.graph = g;
		this.source = s;
		this.dest = d;
		this.disp = disp;
		this.distance = new double[g.getVertices().size()];
		this.parent = new Place[g.getVertices().size()];
		this.run();
	}

	public Dijsktra(Graph g, Place s) {
		this.graph = g;
		this.source = s;
		this.disp = "walk";
		this.distance = new double[g.getVertices().size()];
		this.parent = new Place[g.getVertices().size()];
		this.run();
	}

	public void run() {
		boolean car = false;
		if (disp.equals("car"))
			car = true;

		ArrayList<Place> unvisited = new ArrayList<Place>();
		unvisited = (ArrayList<Place>) graph.getVertices().clone();

		for (int i = 0; i < graph.getVertices().size(); i++) {
			distance[i] = Double.POSITIVE_INFINITY;
		}
		distance[graph.getVertices().indexOf(source)] = 0;

		for (int i = 0; i < graph.getVertices().size(); i++) {
			Place v = minVertex(unvisited);
			unvisited.remove(v);
			if (distance[graph.getVertices().indexOf(v)] == Double.POSITIVE_INFINITY)
				return;

			for (Road road : graph.getVertexEdges(v)) {
				if (!car || (car && road.getDisplacement().equals("car"))) {
					Place opposite = graph.opposite(v, road);
					Double distV = distance[graph.getVertices().indexOf(v)];
					Double distO = distance[graph.getVertices().indexOf(opposite)];

					if (distO > (distV + road.getDistance())) {
						distance[graph.getVertices().indexOf(opposite)] = (distV + road.getDistance());
						parent[graph.getVertices().indexOf(opposite)] = v;
					}
				}
			}
		}
	}

	public Place minVertex(ArrayList<Place> unvisited) {
		Place v = unvisited.get(0);
		for (int i = 0; i < graph.getVertices().size(); i++) {
			if (unvisited.contains(graph.getVertices().get(i))
					&& distance[i] < distance[graph.getVertices().indexOf(v)])
				v = graph.getVertices().get(i);
		}
		return v;
	}

	public String getFullPath() {
		String caminho = "";
		ArrayList<Place> path = new ArrayList<Place>();
		Place tmp = parent[graph.getVertices().indexOf(dest)];
		while (tmp != null) {
			path.add(tmp);
			tmp = parent[graph.getVertices().indexOf(tmp)];
		}
		Collections.reverse(path);
		for (Place place : path) {
			caminho += place.getName() + " > ";
		}
		if (path.size() > 0) {
			caminho += dest.getName();
			return caminho;
		} else {
			return "Não há caminhos possíveis!";
		}
	}

	public Graph getPathGraph() {
		Graph g = new Graph();
		Place a = dest;
		Place p = parent[graph.getVertices().indexOf(dest)];
		while (p != null) {
			if (!g.hasVertex(a))
				g.insertVertex(a);
			if (!g.hasVertex(p))
				g.insertVertex(p);
			g.insertEdge(graph.edgeValue(a, p));
			a = p;
			p = parent[graph.getVertices().indexOf(p)];
		}

		return g;
	}

	public double minDistance() {
		int index = graph.getVertices().indexOf(dest);
		return distance[index];
	}

	public double estimatedTime() {
		double tempo;
		if (disp.equals("car")) {
			tempo = minDistance() / 500; // 30km/h = 500 metros/minuto
		} else {
			tempo = minDistance() / 100; // 6km/h = 100 metros/minuto
		}
		return tempo;
	}

	/**
	 * Retorna a lista de locais próximos a origem que satisfazem o tipo (ex:
	 * comida, sala_de_aula) e distância máxima buscado
	 * 
	 * @param type
	 * @param maxDistance
	 * @return ArrayList<Place> places, ArrayList<Double> distances
	 */
	public ArrayList<?>[] nearPlaces(String type, double maxDistance) {
		ArrayList<Place> places = new ArrayList<Place>();
		ArrayList<Double> placesDistance = new ArrayList<Double>();

		ArrayList<?>[] ret = new ArrayList[2];
		ret[0] = places;
		ret[1] = placesDistance;

		for (int i = 0; i < distance.length; i++) {
			if (distance[i] <= maxDistance && graph.getVertices().get(i).getType().equals(type)) {
				places.add(graph.getVertices().get(i));
				placesDistance.add(distance[i]);
			}
		}

		this.bubbleSort(placesDistance, places);

		Graph g = new Graph();
		for (Place place : places) {
			if (!g.hasVertex(place))
				g.insertVertex(place);
		}

		if (g.getVertices().size() > 0)
			geoJson.toGeoJson(g);

		return ret;
	}

	/**
	 * Ordena o array de distâncias e locais de forma crescente
	 * 
	 * @param placesDistance
	 * @param places
	 */
	private void bubbleSort(ArrayList<Double> placesDistance, ArrayList<Place> places) {
		Double temp;
		Place tempP;
		int cont = 1;

		do {
			for (int i = 0; i < placesDistance.size() - 1; i++) {
				if (placesDistance.get(i) > placesDistance.get(i + 1)) {
					temp = placesDistance.get(i);
					placesDistance.set(i, placesDistance.get(i + 1));
					placesDistance.set(i + 1, temp);
					tempP = places.get(i);
					places.set(i, places.get(i + 1));
					places.set(i + 1, tempP);
				}
			}
			cont++;
		} while (cont < placesDistance.size());
	}
	
	

}
