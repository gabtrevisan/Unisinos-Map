//  Nós (Gabriela de Campos Trevisan, Paula Adriana Knob, Tais Felipe Rabello), garantimos que:
//
//  - Não utilizamos código fonte obtidos de outros estudantes,
//   ou fonte não autorizada, seja modificado ou cópia literal.
//  - Todo código usado em nosso trabalho é resultado do nosso
//   trabalho original, ou foi derivado de um
//   código publicado nos livros texto desta disciplina.
//  - Temos total ciência das consequências em caso de violarmos estes termos.

public class Road {
	private Place v1;
	private Place v2;
	private String displacement;
	private double distance;

	public Road(Place v1, Place v2, String displacement, double distance) {
		this.v1 = v1;
		this.v2 = v2;
		this.displacement = displacement;
		this.distance = distance;
	}

	public Place getV1() {
		return this.v1;
	}

	public void setV1(Place v1) {
		this.v1 = v1;
	}

	public Place getV2() {
		return this.v2;
	}

	public void setV2(Place v2) {
		this.v2 = v2;
	}

	public String getDisplacement() {
		return this.displacement;
	}

	public void setDisplacement(String displacement) {
		this.displacement = displacement;
	}

	public double getDistance() {
		return this.distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	/* Retorna verdadeiro se v está na aresta, falso caso contrário */
	public boolean hasVertex(Place v) {
		if (v == getV1() || v == getV2())
			return true;
		return false;
	}

	public String toString() {
		return v1 + ", " + v2 + ", " + "deslocamento=" + this.getDisplacement() + ", distance=" + this.getDistance();
	}
}
