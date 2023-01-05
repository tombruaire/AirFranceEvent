package controleur;

public class PAV {
	
	private String nom, prenom, avion, vol, datevol, heurevol;

	public PAV(String nom, String prenom, String avion, String vol, String datevol, String heurevol) {
		this.nom = nom;
		this.prenom = prenom;
		this.avion = avion;
		this.vol = vol;
		this.datevol = datevol;
		this.heurevol = heurevol;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getAvion() {
		return avion;
	}

	public void setAvion(String avion) {
		this.avion = avion;
	}

	public String getVol() {
		return vol;
	}

	public void setVol(String vol) {
		this.vol = vol;
	}

	public String getDatevol() {
		return datevol;
	}

	public void setDatevol(String datevol) {
		this.datevol = datevol;
	}

	public String getHeurevol() {
		return heurevol;
	}

	public void setHeurevol(String heurevol) {
		this.heurevol = heurevol;
	}

}
