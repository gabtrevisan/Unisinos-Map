import java.util.ArrayList;

//  Nós (Gabriela de Campos Trevisan, Paula Adriana Knob, Tais Felipe Rabello), garantimos que:
//
//  - Não utilizamos código fonte obtidos de outros estudantes,
//   ou fonte não autorizada, seja modificado ou cópia literal.
//  - Todo código usado em nosso trabalho é resultado do nosso
//   trabalho original, ou foi derivado de um
//   código publicado nos livros texto desta disciplina.
//  - Temos total ciência das consequências em caso de violarmos estes termos.

public class UnisinosMap {
	private Graph map;

	/** Cria um grafo a partir do arquivo GeoJson */
	public UnisinosMap() {
		this.map = geoJson.toGraph("GraphElements.json");
	}

	public Graph getMap() {
		return this.map;
	}

	/**
	 * Árvore geradora mínima utilizando o algoritmo Prim
	 * 
	 * @return Graph
	 */
	public Graph getMstPrim() {
		return MST.Prim(this.getMap());
	}

	/**
	 * Árvore geradora mínima utilizando o algoritmo Kruskal
	 * 
	 * @return Graph
	 */
	public Graph getMstKruskal() {
		return MST.Kruskal(this.getMap());
	}

	/**
	 * Quantidade mínima de tinta para pintar uma ligação entre todos os pontos
	 * da Unisinos
	 * 
	 * @param Graph
	 * @return double
	 */
	public double minToPaint(Graph mst) {
		return mst.minToPaint();
	}

	/**
	 * Rota mínima, distância em metros e tempo estimado do percurso entre dois
	 * pontos caminhando ou de carro
	 * 
	 * @param source
	 * @param dest
	 * @param transport
	 *            (walk, car)
	 * @return String
	 */
	public String minRoute(int source, int dest, String transport) {
		String s = "";
		Dijsktra d;
		d = new Dijsktra(this.getMap(), this.getMap().vertexValue(source), this.getMap().vertexValue(dest), transport);

		if (transport.equals("car"))
			s += "Menor percurso de carro entre V" + source + " e V" + dest + ": " + d.getFullPath() + "\n";
		else
			s += "Menor percurso caminhando entre V" + source + " e V" + dest + ": " + d.getFullPath() + "\n";

		if (d.minDistance() != Double.POSITIVE_INFINITY) {
			s += "Distância em metros: " + d.minDistance() + "\n";
			s += "Tempo estimado: " + d.estimatedTime() + " minutos" + "\n";
		}
		
		if(d.getPathGraph().getEdges().size() > 0)
			geoJson.toGeoJson(d.getPathGraph());
		
		return s;
	}

	public void nearPlaces(int source, String type, double distance) {
		Dijsktra d;
		ArrayList<?>[] nearPlaces;

		d = new Dijsktra(this.getMap(), this.getMap().vertexValue(source));
		nearPlaces = d.nearPlaces(type, distance);
		if (nearPlaces[0].size() == 0) {
			System.out.println("Nenhum local próximo!");
		} else {
			for (int i = 0; i < nearPlaces[0].size(); i++) {
				System.out.println(((Place) nearPlaces[0].get(i)).getName() + ": " + nearPlaces[1].get(i) + " metros");
			}
		}

	}
}
