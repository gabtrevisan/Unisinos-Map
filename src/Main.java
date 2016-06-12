import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.json.*;

//  Nós (Gabriela de Campos Trevisan, Paula Adriana Knob, Tais Felipe Rabello), garantimos que:
//
//  - Não utilizamos código fonte obtidos de outros estudantes,
//   ou fonte não autorizada, seja modificado ou cópia literal.
//  - Todo código usado em nosso trabalho é resultado do nosso
//   trabalho original, ou foi derivado de um
//   código publicado nos livros texto desta disciplina.
//  - Temos total ciência das consequências em caso de violarmos estes termos.

public class Main {
	public static void main(String[] args) {
		Graph g = new Graph();
		String linha;
		String str = "";

		try {
			FileReader file = new FileReader("map.geojson");
			BufferedReader buffer = new BufferedReader(file);
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
		
		
		//System.out.println(Algorithms.Prim(g));
		
		System.out.println(g);	
		
	}
}
