//  N�s (Gabriela de Campos Trevisan, Paula Adriana Knob, Tais Felipe Rabello), garantimos que:
//
//  - N�o utilizamos c�digo fonte obtidos de outros estudantes,
//   ou fonte n�o autorizada, seja modificado ou c�pia literal.
//  - Todo c�digo usado em nosso trabalho � resultado do nosso
//   trabalho original, ou foi derivado de um
//   c�digo publicado nos livros texto desta disciplina.
//  - Temos total ci�ncia das consequ�ncias em caso de violarmos estes termos.

public class Edge<E> {
	private Vertex v1;
	private Vertex v2;
	private E data;

	public Edge(Vertex v1, Vertex v2, E data) {
		this.v1 = v1;
		this.v2 = v2;
		this.data = data;
	}

	public Vertex getV1() {
		return v1;
	}

	public void setV1(Vertex v1) {
		this.v1 = v1;
	}

	public Vertex getV2() {
		return v2;
	}

	public void setV2(Vertex v2) {
		this.v2 = v2;
	}

	public E getData() {
		return data;
	}

	public void setData(E data) {
		this.data = data;
	}

	/* Retorna verdadeiro se v est� na aresta, falso caso contr�rio */
	public boolean hasVertex(Vertex v) {
		if (v == getV1() || v == getV2())
			return true;
		return false;
	}
	
	public String toString() {
		return this.getV1()+", "+this.getV2()+", "+this.getData();
	}
}
