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
	public static void main(String[] args) {
		Graph g = geoJson.toGraph("map.geojson");

		/*
		 * for (Place place : g.getVertices()) { System.out.print(place.getId()+
		 * ": "); for (Place adj : place.getAdjacents()) {
		 * System.out.print(adj.getId()+" "); } System.out.println(); }
		 */

		System.out.println("Grafo preenchido:");
		System.out.println(g);

		System.out.println("Prim:\n");
		System.out.println("MST: \n");
		System.out.println(Algorithms.Prim(g));
		System.out.println("Quantidade mínima de tinta: " + Algorithms.Prim(g).minToPaint() + " litros\n");

		System.out.println("Kruskal:\n");
		System.out.println("MST: \n");
		System.out.println(Algorithms.Kruskal(g));
		System.out.println("Quantidade mínima de tinta: " + Algorithms.Kruskal(g).minToPaint() + " litros\n");

		int source;
		int dest;
		Scanner read = new Scanner(System.in);

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

		Dijsktra d;
		d = new Dijsktra(g, g.vertexValue(source), g.vertexValue(dest), "walk");
		System.out.println("\nMenor percurso caminhando entre V" + source + " e V" + dest + ": " + d.getFullPath());
		if (d.minDistance() != Double.POSITIVE_INFINITY) {
			System.out.println("Distância em metros: " + d.minDistance());
			System.out.println("Tempo estimado: " + d.estimatedTime() + " minutos");
		}
		d = new Dijsktra(g, g.vertexValue(source), g.vertexValue(dest), "car");
		System.out.println("\nMenor percurso de carro entre V" + source + " e V" + dest + ": " + d.getFullPath());
		if (d.minDistance() != Double.POSITIVE_INFINITY) {
			System.out.println("Distância em metros " + d.minDistance());
			System.out.println("Tempo estimado: " + d.estimatedTime());
		}
	}
}
