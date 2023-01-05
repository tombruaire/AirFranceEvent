package controleur;

public class Navigant extends Personne {
	
	private String poste;
	private int nbheureshol;
	
	public Navigant(int idpers, String nom, String prenom, String poste, int nbheureshol) {
		super(idpers, nom, prenom);
		this.poste = poste;
		this.nbheureshol = nbheureshol;
	}
	
	public Navigant(String nom, String prenom, String poste, int nbheureshol) {
		super(nom, prenom);
		this.poste = poste;
		this.nbheureshol = nbheureshol;
	}

	public String getPoste() {
		return poste;
	}

	public void setPoste(String poste) {
		this.poste = poste;
	}

	public int getNbheureshol() {
		return nbheureshol;
	}

	public void setNbheureshol(int nbheureshol) {
		this.nbheureshol = nbheureshol;
	}

}
