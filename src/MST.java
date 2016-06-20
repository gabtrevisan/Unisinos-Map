import java.util.ArrayList;
import java.util.Comparator;

//  Nós (Gabriela de Campos Trevisan, Paula Adriana Knob, Tais Felipe Rabello), garantimos que:
//
//  - Não utilizamos código fonte obtidos de outros estudantes,
//   ou fonte não autorizada, seja modificado ou cópia literal.
//  - Todo código usado em nosso trabalho é resultado do nosso
//   trabalho original, ou foi derivado de um
//   código publicado nos livros texto desta disciplina.
//  - Temos total ciência das consequências em caso de violarmos estes termos.

public class MST {
	/**
	 * Algoritmo Prim: retorna a árvore geradora mínima.
	 * 
	 * @param g
	 *            Grafo
	 * @return MST
	 */
	public static Graph Prim(Graph g) {
		Graph mst = new Graph();
		if (g.getVertices().size() != 0) {
			ArrayList<Place> visited = new ArrayList<Place>();
			ArrayList<Road> known = new ArrayList<Road>();

			/*
			 * Marca como visitado o primeiro vértice e adiciona todos os seus
			 * adjacentes como conhecidos
			 */
			Place place = g.getVertices().get(0);
			visited.add(place);
			for (Road road : g.getVertexEdges(place)) {
				known.add(road);
			}

			/*
			 * Enquanto a MST ainda não possui todos os vértices do Grafo,
			 * executa o algoritmo
			 */
			while (mst.getVertices().size() != g.getVertices().size()) {
				Road closest = closestValidRoad(mst, known);

				// Verifica qual dos vértices de closest que não foi visitado
				if (!visited.contains(closest.getV1()))
					place = closest.getV1();
				else
					place = closest.getV2();

				// Marca-o como visitado
				visited.add(place);

				// Adiciona seus adjacentes como conhecidos
				for (Road road : g.getVertexEdges(place)) {
					if (!known.contains(road))
						known.add(road);
				}

				// Insere na MST os vértices e arestas
				mst.insertEdge(closest);

				// Remove dos conhecidos a aresta que já foi adiciona a MST
				known.remove(closest);
			}
		}
		return mst;
	}

	/**
	 * Algoritmo Kruskal: retorna a árvore geradora mínima.
	 * 
	 * @param g
	 *            Grafo
	 * @return MST
	 */
	public static Graph KruskalOld(Graph g) {
		Graph mst = new Graph();
		// Arestas ainda desconhecidas
		ArrayList<Road> unknown = new ArrayList<Road>();
		// Arestas conhecidas que ainda não foram utilizadas, mas poderão ser
		ArrayList<Road> known = new ArrayList<Road>();

		// Marca todas as arestas do grafo como desconhecidas
		unknown = (ArrayList<Road>) g.getEdges().clone();
		// Ordena as arestas pelas suas distâncias
		sortByDistance(unknown);

		// Insere a aresta com menor distância na MST
		mst.insertEdge(unknown.get(0));
		unknown.remove(0);

		Road road;
		while (mst.getVertices().size() != g.getVertices().size()) {
			road = unknown.get(0);
			// Se não formar ciclo
			if (!hasVertices(mst, road.getV1(), road.getV2())) {
				// Se tem pelo menos um dos vértices na MST, adicionará a
				// aresta
				if (mst.hasVertex(road.getV1()) || mst.hasVertex(road.getV2())) {
					mst.insertEdge(road);
					// Verifica se pode conectar mais arestas já
					// visitadas no vértice inserido
					ArrayList<Road> toRemove = new ArrayList<Road>();
					for (Road kRoad : known) {
						if (kRoad.hasVertex(road.getV1()) || kRoad.hasVertex(road.getV2())) {
							if (!hasVertices(mst, kRoad.getV1(), kRoad.getV2())) {
								mst.insertEdge(kRoad);
								toRemove.add(kRoad);
							}
						} else {
							toRemove.add(kRoad);
						}
					}
					for (Road r : toRemove) {
						known.remove(r);
					}
				}
				// Se os dois não estão na MST marca como conhecido
				else {
					known.add(road);
				}

			}
			// Remove a aresta que não será utilizada
			unknown.remove(0);
		}
		return mst;
	}

	public static Graph Kruskal(Graph g) {
		Graph mst = new Graph();
		// Arestas ainda desconhecidas
		ArrayList<Road> unknown = new ArrayList<Road>();

		// Marca todas as arestas do grafo como desconhecidas
		unknown = (ArrayList<Road>) g.getEdges().clone();
		// Ordena as arestas pelas suas distâncias
		sortByDistance(unknown);

		// Insere a aresta com menor distância na MST
		mst.insertEdge(unknown.get(0));
		unknown.remove(0);

		Road road;
		while (mst.getVertices().size() != g.getVertices().size()) {
			for (int i = 0; i < unknown.size(); i++) {
				road = unknown.get(i);
				// Se não formar ciclo
				if (!hasVertices(mst, road.getV1(), road.getV2())) {
					// Se tem pelo menos um dos vértices na MST, adicionará a
					// aresta
					if (mst.hasVertex(road.getV1()) || mst.hasVertex(road.getV2())) {
						mst.insertEdge(road);
						i = -1;
					}
				} else {
					// Remove a aresta que não será utilizada
					unknown.remove(i);
				}
			}
		}
		return mst;
	}

	/**
	 * Retorna o caminho que possui a menor distância e que não gera ciclo na
	 * MST
	 * 
	 * @param mst
	 *            MST
	 * @param known
	 *            Arestas
	 * @return caminho
	 */
	private static Road closestValidRoad(Graph mst, ArrayList<Road> known) {
		Road closest;
		int i = 0;
		do {
			closest = known.get(i);
			i++;
		} while (hasVertices(mst, closest.getV1(), closest.getV2()));

		for (Road road2 : known) {
			if ((road2.getDistance() < closest.getDistance()) && !hasVertices(mst, road2.getV1(), road2.getV2()))
				closest = road2;
		}
		return closest;
	}

	/**
	 * Retorna verdadeiro se os dois vértices estão no grafo
	 * 
	 * @param g
	 *            Grafo
	 * @param v1
	 *            Vértice 1
	 * @param v2
	 *            Vértice 2
	 * @return boolean
	 */
	private static boolean hasVertices(Graph g, Place v1, Place v2) {
		return g.hasVertex(v1) && g.hasVertex(v2);
	}

	/**
	 * Ordena arestas pelas suas distâncias
	 * 
	 * @param roads
	 *            Arestas
	 */
	private static void sortByDistance(ArrayList<Road> roads) {
		roads.sort(new Comparator<Road>() {
			public int compare(Road r1, Road r2) {
				if (r1.getDistance() < r2.getDistance())
					return -1;
				else if (r1.getDistance() == r2.getDistance())
					return 0;
				else
					return 1;
			}
		});
	}

}
