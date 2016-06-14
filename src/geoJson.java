import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

public class geoJson {
	private static BufferedReader buffer;

	/** Gera um grafo a partir de um arquivo GeoJson
	 * @param filename Caminho do arquivo .geoJson
	 * @return Graph Grafo com as informações dos locais
	 */
	public static Graph toGraph(String filename) {
		Graph g = new Graph();
		String linha;
		String str = "";

		try {
			FileReader file = new FileReader(filename);
			buffer = new BufferedReader(file);
			while ((linha = buffer.readLine()) != null) {
				str += linha;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		JSONObject map = new JSONObject(str);
		JSONArray features = map.getJSONArray("features");

		for (int i = 0; i < features.length(); i++) {
			JSONObject feature = new JSONObject(features.get(i).toString());
			JSONObject geometry = feature.getJSONObject("geometry");
			JSONObject properties = feature.getJSONObject("properties");

			if (geometry.getString("type").equals("Point")) { // Place
				Place p = new Place(properties.getInt("id"), properties.getString("tipo"));
				g.insertVertex(p);
			}

			if (geometry.getString("type").equals("LineString")) { // Road
				JSONObject coordinates = new JSONObject(geometry.toString());
				JSONArray coords1 = coordinates.getJSONArray("coordinates").getJSONArray(0);
				JSONArray coords2 = coordinates.getJSONArray("coordinates").getJSONArray(1);

				Double distance = DistanceCalculator.distance(coords1.getDouble(0), coords1.getDouble(1),
						coords2.getDouble(0), coords2.getDouble(1), "K") * 1000;
				Place v1 = g.vertexValue(properties.getInt("v1"));
				Place v2 = g.vertexValue(properties.getInt("v2"));
				Road r = new Road(v1, v2, properties.getString("deslocamento"), distance);
				
				g.insertEdge(r);
			}

		}
		return g;
	}
}
