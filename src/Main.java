import java.util.Scanner;

//  N�s (Gabriela de Campos Trevisan, Paula Adriana Knob, Tais Felipe Rabello), garantimos que:
//
//  - N�o utilizamos c�digo fonte obtidos de outros estudantes,
//   ou fonte n�o autorizada, seja modificado ou c�pia literal.
//  - Todo c�digo usado em nosso trabalho � resultado do nosso
//   trabalho original, ou foi derivado de um
//   c�digo publicado nos livros texto desta disciplina.
//  - Temos total ci�ncia das consequ�ncias em caso de violarmos estes termos.

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
		System.out.println("Quantidade m�nima de tinta: " + map.minToPaint(mstPrim) + " litros\n");

		System.out.println("Kruskal:\n");
		System.out.println("MST: \n");
		System.out.println(mstKruskal);
		//geoJson.toGeoJson(mstKruskal);

		System.out.println("Quantidade m�nima de tinta: " + map.minToPaint(mstKruskal) + " litros\n");

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
		
		
		System.out.println("Locais pr�ximos:");
		
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
			System.out.printf("Informe a dist�ncia m�xima em metros que voc� quer percorrer: ");
			try {
				dist = Integer.parseInt(read.next());
			} catch (NumberFormatException e) {
				dist = -1;
			}
		} while (dist == -1);
		
		map.nearPlaces(source, type, dist);		
		
	}
}
