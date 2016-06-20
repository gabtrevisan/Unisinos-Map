import java.util.ArrayList;

//  N�s (Gabriela de Campos Trevisan, Paula Adriana Knob, Tais Felipe Rabello), garantimos que:
//
//  - N�o utilizamos c�digo fonte obtidos de outros estudantes,
//   ou fonte n�o autorizada, seja modificado ou c�pia literal.
//  - Todo c�digo usado em nosso trabalho � resultado do nosso
//   trabalho original, ou foi derivado de um
//   c�digo publicado nos livros texto desta disciplina.
//  - Temos total ci�ncia das consequ�ncias em caso de violarmos estes termos.

public class Place {
	private int id;
	private String name;
	private String type;
	private ArrayList<Place> adjacents;
	private double lat;
	private double lng;

	/** Local na Unisinos
	 * @param id Id num�rico
	 * @param type Tipo (sala_de_aula, adm, banheiro, comida, farmacia, etc)
	 */
	public Place(int id, String type, double lng, double lat) {
		this.id = id;
		this.name = "";
		this.type = type;
		this.adjacents = new ArrayList<>();
		this.lat = lat;
		this.lng = lng;
	}
	
	public Place(int id, String name, String type, double lng, double lat) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.adjacents = new ArrayList<>();
		this.lat = lat;
		this.lng = lng;
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
	
	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}
	
	public void insertAdjacent(Place p) {
		boolean contains = false;
		for (int i = 0; i < getAdjacents().size(); i++) {
			if(getAdjacents().get(i).getId() == p.getId()) {
				contains = true;
				break;
			}
		}
		if(!contains)
			this.adjacents.add(p);
	}

	public String toString() {
		return "V" + this.getId() + ", tipo="+this.getType();
	}
}
