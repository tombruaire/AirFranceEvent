package controleur;

public class Vol {
	
	private int idvol;
	private String designation, datevol, heurevol;
	private int idpilote1, idpilote2, idavion;
	
	public Vol(int idvol, String designation, String datevol, String heurevol, int idpilote1, int idpilote2,
			int idavion) {
		this.idvol = idvol;
		this.designation = designation;
		this.datevol = datevol;
		this.heurevol = heurevol;
		this.idpilote1 = idpilote1;
		this.idpilote2 = idpilote2;
		this.idavion = idavion;
	}
	
	public Vol(String designation, String datevol, String heurevol, int idpilote1, int idpilote2,
			int idavion) {
		this.idvol = 0;
		this.designation = designation;
		this.datevol = datevol;
		this.heurevol = heurevol;
		this.idpilote1 = idpilote1;
		this.idpilote2 = idpilote2;
		this.idavion = idavion;
	}

	public int getIdvol() {
		return idvol;
	}

	public void setIdvol(int idvol) {
		this.idvol = idvol;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
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

	public int getIdpilote1() {
		return idpilote1;
	}

	public void setIdpilote1(int idpilote1) {
		this.idpilote1 = idpilote1;
	}

	public int getIdpilote2() {
		return idpilote2;
	}

	public void setIdpilote2(int idpilote2) {
		this.idpilote2 = idpilote2;
	}

	public int getIdavion() {
		return idavion;
	}

	public void setIdavion(int idavion) {
		this.idavion = idavion;
	}

}
