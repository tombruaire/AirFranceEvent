package controleur;

public class Pilote extends Personne {
	
	private String adresse;
	private int nbheuresvol;
	private String bip;
	
	public Pilote(int idpilote, String nom, String prenom, String adresse, int nbheuresvol, String bip) {
		super(idpilote, nom, prenom);
		this.adresse = adresse;
		this.nbheuresvol = nbheuresvol;
		this.bip = bip;
	}
	
	public Pilote(String nom, String prenom, String adresse, int nbheuresvol, String bip) {
		super(nom, prenom);
		this.adresse = adresse;
		this.nbheuresvol = nbheuresvol;
		this.bip = bip;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public int getNbheuresvol() {
		return nbheuresvol;
	}

	public void setNbheuresvol(int nbheuresvol) {
		this.nbheuresvol = nbheuresvol;
	}

	public String getBip() {
		return bip;
	}

	public void setBip(String bip) {
		this.bip = bip;
	}

}
