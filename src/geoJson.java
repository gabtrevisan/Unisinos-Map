import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class geoJson {
	private static BufferedReader buffer;

	/**
	 * Gera um grafo a partir de um arquivo GeoJson
	 * 
	 * @param filename
	 *            Caminho do arquivo .geoJson
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
				JSONObject coordinates = new JSONObject(geometry.toString());
				JSONArray coords = coordinates.getJSONArray("coordinates");
				Place p = new Place(properties.getInt("id"), properties.getString("name"),properties.getString("tipo"), coords.getDouble(0), coords.getDouble(1));
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

	public static void toGeoJson(Graph g) {
		String geoJson = "";
		
	    JSONObject featureCollection = new JSONObject();
	    try {
	        featureCollection.put("type", "FeatureCollection");
	        JSONArray featureList = new JSONArray();
	        for (Place place : g.getVertices()) {
	            JSONObject feature = new JSONObject();
	        	feature.put("type", "Feature");
	        	
	            JSONObject point = new JSONObject();
	            point.put("type", "Point");
	            JSONArray coord = new JSONArray("["+place.getLng()+","+place.getLat()+"]");
	            point.put("coordinates", coord);
	            feature.put("geometry", point);
	            
	            JSONObject properties = new JSONObject();	            
	            properties.put("id", place.getId());
	            properties.put("tipo", place.getType());   
	            if(!place.getName().isEmpty()) 
	            	properties.put("name", place.getName()); 
	            feature.put("properties", properties);
	            
	            featureList.put(feature);
	            featureCollection.put("features", featureList);
	        }
	        for (Road road : g.getEdges()) {
	            JSONObject feature = new JSONObject();
	        	feature.put("type", "Feature");
	        	
	            JSONObject linestring = new JSONObject();
	            linestring.put("type", "LineString");
	            JSONArray coord = new JSONArray("[["+road.getV1().getLng()+","+road.getV1().getLat()+"]"+",["+road.getV2().getLng()+","+road.getV2().getLat()+"]]");
	            linestring.put("coordinates", coord);
	            feature.put("geometry", linestring);
	            
	            JSONObject properties = new JSONObject();	            
	            properties.put("v1", road.getV1().getId());
	            properties.put("v2", road.getV2().getId()); 
	            properties.put("deslocamento", road.getDisplacement()); 
	            feature.put("properties", properties);
	            
	            featureList.put(feature);
	            featureCollection.put("features", featureList);
	        }
	    } catch (JSONException e) {
	        e.printStackTrace();
	    }
	    
	    geoJson = featureCollection.toString();	
		generateFile(geoJson);		
		openMap();
	}
	
	private static File generateFile(String geoJson) {
		File file = new File("data.js");
		String data = "var data = " + geoJson + ";";		
		try {
			file.createNewFile();
			FileWriter writer = new FileWriter(file);
			writer.write(data);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;
	}
	
	public static void openMap() {
		File htmlFile = new File("map.html");
		try {
			Desktop.getDesktop().browse(htmlFile.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
