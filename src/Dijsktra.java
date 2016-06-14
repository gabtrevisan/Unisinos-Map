import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

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

	private void initialize_single_source(Graph g) {
		for (int i = 0; i < distance.length; i++) {
			distance[i] = Double.POSITIVE_INFINITY;
		}
		distance[g.getVertices().indexOf(source)] = 0;
	}

	private void run() {
		this.initialize_single_source(graph);
		double distPlace, distAdj;
		boolean car = false;
		Road minAdj;
		Place v;
		ArrayList<Place> unvisited = new ArrayList<Place>();
		unvisited = (ArrayList<Place>) graph.getVertices().clone();

		if (disp.equals("car"))
			car = true;

		v = source;
		unvisited.remove(v);

		for (int k = 0; k < graph.getVertices().size() - 1; k++) {
			minAdj = minAdjacent(unvisited, v);

			for (Road road : graph.getVertexEdges(v)) {
				if (!car || (car && road.getDisplacement() == "car")) {
					Place opposite = graph.opposite(v, road);
					distPlace = distance[graph.getVertices().indexOf(v)];
					distAdj = distance[graph.getVertices().indexOf(opposite)];

					if (unvisited.contains(opposite) && (road.getDistance() < minAdj.getDistance()))
						minAdj = road;

					if (distAdj > (distPlace + road.getDistance())) {
						distance[graph.getVertices().indexOf(opposite)] = (distPlace + road.getDistance());
						parent[graph.getVertices().indexOf(opposite)] = v;
					}
				}
			}
			unvisited.remove(v);
			v = graph.opposite(v, minAdj);
		}
	}

	private Road minAdjacent(ArrayList<Place> unvisited, Place v) {
		Road minAdj;
		minAdj = graph.getVertexEdges(v).get(0);
		int i = 1;
		while (!unvisited.contains(graph.opposite(v, minAdj)) && i <= graph.getVertexEdges(v).size() - 1) {
			minAdj = graph.getVertexEdges(v).get(i);
			i++;
		}
		return minAdj;
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
			caminho += "V" + place.getId() + " > ";
		}
		if (path.size() > 0) {
			caminho += "V" + dest.getId();
			return caminho;
		} else {
			return "Não há caminhos possíveis!";
		}
	}
	 

	public double minDistance() {
		int index = graph.getVertices().indexOf(dest);
		return distance[index];
	}
	

	public double estimatedTime() {
		double tempo;
		if(disp.equals("car")) {
			tempo = minDistance()/500; // 30km/h = 500 metros/minuto
		} else {
			tempo = minDistance()/100; //6km/h = 100 metros/minuto
		}
		return tempo;
	}
	

	
}
