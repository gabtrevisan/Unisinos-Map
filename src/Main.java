import java.util.Scanner;

//  Nós (Gabriela de Campos Trevisan, Paula Adriana Knob, Tais Felipe Rabello), garantimos que:
//
//  - Não utilizamos código fonte obtidos de outros estudantes,
//   ou fonte não autorizada, seja modificado ou cópia literal.
//  - Todo código usado em nosso trabalho é resultado do nosso
//   trabalho original, ou foi derivado de um
//   código publicado nos livros texto desta disciplina.
//  - Temos total ciência das consequências em caso de violarmos estes termos.

public class Main {
	private static Scanner read;

	public static void main(String[] args) {
		UnisinosMap map = new UnisinosMap();
		Graph g = map.getMap();
		Graph mstPrim = map.getMstPrim();
		Graph mstKruskal = map.getMstKruskal();

		System.out.println("Grafo preenchido:");
		System.out.println(g);
		//geoJson.toGeoJson(g);

		System.out.println("Prim:\n");
		System.out.println("MST: \n");
		System.out.println(mstPrim);
		//geoJson.toGeoJson(mstPrim);
		System.out.println("Quantidade mínima de tinta: " + map.minToPaint(mstPrim) + " litros\n");

		System.out.println("Kruskal:\n");
		System.out.println("MST: \n");
		System.out.println(mstKruskal);
		//geoJson.toGeoJson(mstKruskal);

		System.out.println("Quantidade mínima de tinta: " + map.minToPaint(mstKruskal) + " litros\n");

		int source;
		int dest;
		read = new Scanner(System.in);
		System.out.println("Caminho entre dois locais:");
		do {
			System.out.printf("Informe o ID do local de origem: ");
			try {
				source = Integer.parseInt(read.next());
			} catch (NumberFormatException e) {
				source = -1;
			}
		} while (g.vertexValue(source) == null);
		do {
			System.out.printf("Informe o ID do local de destino: ");
			try {
				dest = Integer.parseInt(read.next());
			} catch (NumberFormatException e) {
				dest = -1;
			}
		} while (g.vertexValue(dest) == null);

		System.out.println(map.minRoute(source, dest, "walk"));
		System.out.println(map.minRoute(source, dest, "car"));
		
		
		System.out.println("Locais próximos:");
		
		do {
			System.out.printf("Informe o ID do local de origem: ");
			try {
				source = Integer.parseInt(read.next());
			} catch (NumberFormatException e) {
				source = -1;
			}
		} while (g.vertexValue(source) == null);
		
		String type;
		System.out.printf("Informe o tipo de local (comida, sala_de_aula, auditorio, ginasio, adm): ");
		type = read.next();	
		
		int dist;		
		do {
			System.out.printf("Informe a distância máxima em metros que você quer percorrer: ");
			try {
				dist = Integer.parseInt(read.next());
			} catch (NumberFormatException e) {
				dist = -1;
			}
		} while (dist == -1);
		
		map.nearPlaces(source, type, dist);		
		
	}
}
