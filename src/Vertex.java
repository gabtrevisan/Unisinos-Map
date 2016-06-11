//  Nós (Gabriela de Campos Trevisan, Paula Adriana Knob, Tais Felipe Rabello), garantimos que:
//
//  - Não utilizamos código fonte obtidos de outros estudantes,
//   ou fonte não autorizada, seja modificado ou cópia literal.
//  - Todo código usado em nosso trabalho é resultado do nosso
//   trabalho original, ou foi derivado de um
//   código publicado nos livros texto desta disciplina.
//  - Temos total ciência das consequências em caso de violarmos estes termos.

public class Vertex<E> {	
	private int id;
	private Place place;	
	
	public Vertex(int id, Place place) {
		this.id = id;
		this.place = place;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}	
	
	public Place getPlace() {
		return this.place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}	
	
	public String toString(){
		return this.getId() + ": " + this.getPlace();
	}
}
