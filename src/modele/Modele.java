package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Avion;
import controleur.Navigant;
import controleur.PAV;
import controleur.Pilote;
import controleur.User;
import controleur.Vol;

public class Modele {
	
	private static Bdd uneBdd = new Bdd ("localhost:3307", "airfrance_22", "root", "");
	
	// INSERTION D'UN PILOTE
	public static void insertPilote (Pilote unPilote) {
		String requete = "INSERT INTO pilote VALUES (null, '"
				+ unPilote.getNom() + "', '" 
				+ unPilote.getPrenom() + "', '"
				+ unPilote.getAdresse() + "', '"
				+ unPilote.getNbheuresvol() + "', '"
				+ unPilote.getBip() + "');";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
	}
	
	// SELECTION DE TOUS LES PILOTES
	public static ArrayList<Pilote> selectAllPilotes () {
		ArrayList<Pilote> lesPilotes = new ArrayList<Pilote>();
		String requete = "SELECT * FROM pilote;";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			while (desResultats.next()) {
				Pilote unPilote = new Pilote (
						desResultats.getInt("idpilote"),
						desResultats.getString("nom"),
						desResultats.getString("prenom"),
						desResultats.getString("adresse"),
						desResultats.getInt("nbheuresvol"),
						desResultats.getString("bip")
						);
				lesPilotes.add(unPilote);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesPilotes;
	}
	
	// SUPPRESSION D'UN PILOTE
	public static void deletePilote (int idpilote) {
		String requete = "DELETE FROM pilote WHERE idpilote = " + idpilote;
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
	}
	
	// SELECTION D'UN PILOTE (SELECT WHERE)
	public static Pilote selectWherePilote (int idpilote) {
		Pilote unPilote = null;
		String requete = "SELECT * FROM pilote WHERE idpilote = "+idpilote+";";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStat.executeQuery(requete);
			if (unResultat.next()) {
				unPilote = new Pilote (
						unResultat.getInt("idpilote"),
						unResultat.getString("nom"),
						unResultat.getString("prenom"),
						unResultat.getString("adresse"),
						unResultat.getInt("nbheuresvol"),
						unResultat.getString("bip")
						);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return unPilote;
	}
	
	// EDITION D'UN PILOTE
	public static void updatePilote (Pilote unPilote) {
		String requete = "UPDATE pilote SET nom = '"
				+ unPilote.getNom() + "', prenom = '" 
				+ unPilote.getPrenom() + "', adresse = '"
				+ unPilote.getAdresse() + "', nbheuresvol = '"
				+ unPilote.getNbheuresvol() + "', bip = '"
				+ unPilote.getBip() + "' WHERE idpilote = "+unPilote.getIdpers()+";";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
	}
	
	// NOMBRE DE PILOTES (COUNT())
	public static int countPilotes () {
		int nbpilotes = 0;
		String requete = "SELECT count(*) as nb FROM pilote;";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStat.executeQuery(requete);
			if (unResultat.next()) {
				nbpilotes = unResultat.getInt("nb");
			}
			unStat.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return nbpilotes;
	}
	
	// INSERTION D'UN AVION
	public static void insertAvion (Avion unAvion) {
		String requete = "INSERT INTO avion VALUES (null, '"
				+ unAvion.getDesignation() + "', '" 
				+ unAvion.getConstructeur() + "', '"
				+ unAvion.getNbplaces() + "');";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
	}
	
	// SELECTION DE TOUS LES AVIONS
	public static ArrayList<Avion> selectAllAvions () {
		ArrayList<Avion> lesAvions = new ArrayList<Avion>();
		String requete = "SELECT * FROM avion;";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			while (desResultats.next()) {
				Avion unAvion = new Avion (
						desResultats.getInt("idavion"),
						desResultats.getString("designation"),
						desResultats.getString("constructeur"),
						desResultats.getInt("nbplaces")
						);
				lesAvions.add(unAvion);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesAvions;
	}
	
	// SUPPRESSION D'UN AVION
	public static void deleteAvion (int idavion) {
		String requete = "DELETE FROM avion WHERE idavion = " + idavion;
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
	}
	
	// SELECTION D'UN AVION (SELECT WHERE)
	public static Avion selectWhereAvion (int idavion) {
		Avion unAvion = null;
		String requete = "SELECT * FROM avion WHERE idavion = "+idavion+";";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStat.executeQuery(requete);
			if (unResultat.next()) {
				unAvion = new Avion (
						unResultat.getInt("idavion"),
						unResultat.getString("designation"),
						unResultat.getString("constructeur"),
						unResultat.getInt("nbplaces")
						);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return unAvion;
	}
	
	// EDITION D'UN AVION
	public static void updateAvion (Avion unAvion) {
		String requete = "UPDATE avion SET designation = '"
				+ unAvion.getDesignation() + "', constructeur = '" 
				+ unAvion.getConstructeur() + "', nbplaces = '"
				+ unAvion.getNbplaces() + "' WHERE idavion = "+unAvion.getIdavion()+";";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
	}
	
	// NOMBRE D'AVIONS (COUNT())
	public static int countAvions () {
		int nbavions = 0;
		String requete = "SELECT count(*) as nb FROM avion;";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStat.executeQuery(requete);
			if (unResultat.next()) {
				nbavions = unResultat.getInt("nb");
			}
			unStat.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return nbavions;
	}
	
	// INSERTION D'UN VOL
	public static void insertVol (Vol unVol) {
		String requete = "INSERT INTO vol VALUES (null, '"
				+ unVol.getDesignation() + "', '" 
				+ unVol.getDatevol() + "', '"
				+ unVol.getHeurevol() + "', '"
				+ unVol.getIdpilote1() + "', '"
				+ unVol.getIdpilote2() + "', '"
				+ unVol.getIdavion() + "');";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
	}
	
	// SELECTION DE TOUS LES VOLS
	public static ArrayList<Vol> selectAllVols () {
		ArrayList<Vol> lesVols = new ArrayList<Vol>();
		String requete = "SELECT * FROM vol;";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			while (desResultats.next()) {
				Vol unVol = new Vol (
						desResultats.getInt("idvol"),
						desResultats.getString("designation"),
						desResultats.getString("datevol"),
						desResultats.getString("heurevol"),
						desResultats.getInt("idpilote1"),
						desResultats.getInt("idpilote2"),
						desResultats.getInt("idavion")
						);
				lesVols.add(unVol);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesVols;
	}
	
	// SUPPRESSION D'UN VOL
	public static void deleteVol (int idvol) {
		String requete = "DELETE FROM vol WHERE idvol = " + idvol;
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
	}
	
	// SELECTION D'UN VOL (SELECT WHERE)
	public static Vol selectWhereVol (int idvol) {
		Vol unVol = null;
		String requete = "SELECT * FROM vol WHERE idvol = "+idvol+";";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStat.executeQuery(requete);
			if (unResultat.next()) {
				unVol = new Vol (
						unResultat.getInt("idvol"),
						unResultat.getString("designation"),
						unResultat.getString("datevol"),
						unResultat.getString("heurevol"),
						unResultat.getInt("idpilote1"),
						unResultat.getInt("idpilote2"),
						unResultat.getInt("idavion")
						);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return unVol;
	}
	
	// EDITION D'UN VOL
	public static void updateVol (Vol unVol) {
		String requete = "UPDATE vol SET designation = '"
				+ unVol.getDesignation() + "', datevol = '" 
				+ unVol.getDatevol() + "', heurevol = '"
				+ unVol.getHeurevol() + "', idpilote1 = '"
				+ unVol.getIdpilote1() + "', idpilote2 = '"
				+ unVol.getIdpilote2() + "', idavion = '"
				+ unVol.getIdavion() + "' WHERE idavion = "+unVol.getIdvol()+";";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
	}
	
	// NOMBRE DE VOLS (COUNT())
	public static int countVols () {
		int nbvols = 0;
		String requete = "SELECT count(*) as nb FROM vol;";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStat.executeQuery(requete);
			if (unResultat.next()) {
				nbvols = unResultat.getInt("nb");
			}
			unStat.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return nbvols;
	}
	
	// SELECTION DE LA VUE : PAV
	public static ArrayList<PAV> selectAllPAV () {
		ArrayList<PAV> lesPAVs = new ArrayList<PAV>();
		String requete = "SELECT * FROM pav;";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			while (desResultats.next()) {
				PAV unPAV = new PAV (
						desResultats.getString("nom"),
						desResultats.getString("prenom"),
						desResultats.getString("avion"),
						desResultats.getString("vol"),
						desResultats.getString("datevol"),
						desResultats.getString("heurevol")
						);
				lesPAVs.add(unPAV);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesPAVs;
	}
	
	/*** Gestion des users ***/
	public static User selectWhereUser (String email, String mdp) {
		User unUser = null;
		String requete = "SELECT * FROM user WHERE email = '"+email
				+"' and mdp = '" + mdp + "';";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStat.executeQuery(requete);
			if (unResultat.next()) {
				unUser = new User (
						unResultat.getInt("iduser"),
						unResultat.getString("nom"),
						unResultat.getString("prenom"),
						unResultat.getString("email"),
						unResultat.getString("mdp"),
						unResultat.getString("role")
						);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return unUser;
	}

	public static Pilote selectWherePilote(String nom, String prenom, String bip) {
		// On vient de surcharger
		Pilote unPilote = null;
		String requete = "SELECT * FROM pilote WHERE "
				+ " nom = '"+nom+"' and prenom = '"+prenom+"' and bip = '"+bip+"';";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStat.executeQuery(requete);
			if (unResultat.next()) {
				unPilote = new Pilote (
						unResultat.getInt("idpilote"),
						unResultat.getString("nom"),
						unResultat.getString("prenom"),
						unResultat.getString("adresse"),
						unResultat.getInt("nbheuresvol"),
						unResultat.getString("bip")
						);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return unPilote;
	}

	public static Avion selectWhereAvion(String designation, String constructeur) {
		Avion unAvion = null;
		String requete = "SELECT * FROM avion WHERE "
				+ " designation = '"+designation+"' and constructeur = '"+constructeur+"';";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStat.executeQuery(requete);
			if (unResultat.next()) {
				unAvion = new Avion (
						unResultat.getInt("idavion"),
						unResultat.getString("designation"),
						unResultat.getString("constructeur"),
						unResultat.getInt("nbplaces")
						);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return unAvion;
	}

	public static Vol selectWhereVol(String designation, String datevol, String heurevol) {
		Vol unVol = null;
		String requete = "SELECT * FROM vol WHERE "
				+ " designation = '"+designation+"' and datevol = '"+datevol+"' and heurevol = '"+heurevol+"';";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStat.executeQuery(requete);
			if (unResultat.next()) {
				unVol = new Vol (
						unResultat.getInt("idvol"),
						unResultat.getString("designation"),
						unResultat.getString("datevol"),
						unResultat.getString("heurevol"),
						unResultat.getInt("idpilote1"),
						unResultat.getInt("idpilote2"),
						unResultat.getInt("idavion")
						);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return unVol;
	}

	public static ArrayList<Pilote> selectLikePilote(String mot) {
		ArrayList<Pilote> lesPilotes = new ArrayList<Pilote>();
		String requete = "SELECT * FROM pilote WHERE "
				+ "nom like '%"+mot+"%' or "
				+ "prenom like '%"+mot+"%' or "
				+ "adresse like '%"+mot+"%' or "
				+ "bip like '%"+mot+"%';";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			while (desResultats.next()) {
				Pilote unPilote = new Pilote (
						desResultats.getInt("idpilote"),
						desResultats.getString("nom"),
						desResultats.getString("prenom"),
						desResultats.getString("adresse"),
						desResultats.getInt("nbheuresvol"),
						desResultats.getString("bip")
						);
				lesPilotes.add(unPilote);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesPilotes;
	}
	
	public static void insertNavigant (Navigant unNavigant) {
		String requete = "INSERT INTO navigant VALUES (null, '"
				+ unNavigant.getNom() + "', '" 
				+ unNavigant.getPrenom() + "', '"
				+ unNavigant.getPoste() + "', '"
				+ unNavigant.getNbheureshol() + "');";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
	}
	
	public static Navigant selectWhereNavigant(String nom, String prenom, String poste) {
		// On vient de surcharger
		Navigant unNavigant = null;
		String requete = "SELECT * FROM navigant WHERE "
				+ " nom = '"+nom+"' and prenom = '"+prenom+"' and poste = '"+poste+"';";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStat.executeQuery(requete);
			if (unResultat.next()) {
				unNavigant = new Navigant (
						unResultat.getInt("idnavigant"),
						unResultat.getString("nom"),
						unResultat.getString("prenom"),
						unResultat.getString("poste"),
						unResultat.getInt("nbheuresvol")
						);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return unNavigant;
	}
	
}
