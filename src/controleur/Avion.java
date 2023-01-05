package controleur;

public class Avion {
	
	private int idavion;
	private String designation, constructeur;
	private int nbplaces;
	
	public Avion(int idavion, String designation, String constructeur, int nbplaces) {
		this.idavion = idavion;
		this.designation = designation;
		this.constructeur = constructeur;
		this.nbplaces = nbplaces;
	}
	
	public Avion(String designation, String constructeur, int nbplaces) {
		this.idavion = 0;
		this.designation = designation;
		this.constructeur = constructeur;
		this.nbplaces = nbplaces;
	}

	public int getIdavion() {
		return idavion;
	}

	public void setIdavion(int idavion) {
		this.idavion = idavion;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getConstructeur() {
		return constructeur;
	}

	public void setConstructeur(String constructeur) {
		this.constructeur = constructeur;
	}

	public int getNbplaces() {
		return nbplaces;
	}

	public void setNbplaces(int nbplaces) {
		this.nbplaces = nbplaces;
	}

}
