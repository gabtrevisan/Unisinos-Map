import java.util.ArrayList;

//  Nós (Gabriela de Campos Trevisan, Paula Adriana Knob, Tais Felipe Rabello), garantimos que:
//
//  - Não utilizamos código fonte obtidos de outros estudantes,
//   ou fonte não autorizada, seja modificado ou cópia literal.
//  - Todo código usado em nosso trabalho é resultado do nosso
//   trabalho original, ou foi derivado de um
//   código publicado nos livros texto desta disciplina.
//  - Temos total ciência das consequências em caso de violarmos estes termos.

public class Place {
	private int id;
	private String name;
	private String type;
	private ArrayList<Place> adjacents;

	/** Local na Unisinos
	 * @param id Id numérico
	 * @param type Tipo (sala_de_aula, adm, banheiro, comida, farmacia, etc)
	 */
	public Place(int id, String type) {
		this.id = id;
		this.name = "";
		this.type = type;
		this.adjacents = new ArrayList<>();
	}
	
	public Place(int id, String name, String type) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.adjacents = new ArrayList<>();
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return this.type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public ArrayList<Place> getAdjacents() {
		return this.adjacents;
	}
	
	public void insertAdjacent(Place p) {
		if(!this.getAdjacents().contains(p))
			this.adjacents.add(p);
	}

	public String toString() {
		return "V" + this.getId() + ", tipo="+this.getType();
	}
}
