package vue;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import controleur.Navigant;
import controleur.Pilote;
import controleur.Tableau;
import controleur.User;
import modele.Modele;

public class PanelPilote extends PanelDeBase implements ActionListener {

	private JPanel panelForm = new JPanel();
	private JPanel panelForm1 = new JPanel();
	private JPanel panelForm2 = new JPanel();
	private JPanel panelForm3 = new JPanel();
	
	private JPanel panelTable = new JPanel();
	
	private JButton btEnregistrer = new JButton("Enregistrer");
	private JButton btAnnuler = new JButton("Annuler");
	
	private JTextField txtNom = new JTextField();
	private JTextField txtPrenom = new JTextField();
	private JTextField txtAdresse = new JTextField();
	private JTextField txtNbHeuresVol = new JTextField();
	private JTextField txtBip = new JTextField();
	private JTextField txtPoste = new JTextField();
	
	private JRadioButton radioPilote = new JRadioButton();
	private JRadioButton radioNavigant = new JRadioButton();

	private JTable uneTable = null;

	private static Tableau unTableau = null;
	
	// Zone de recherche - par requete like
	private JTextField txtMot = new JTextField();
	private JButton btRechercher = new JButton("Rechercher"); 

	public PanelPilote(User unUser) {
		super(Color.gray); // Couleur de fond

		this.panelForm.setLayout(new GridLayout(3, 2));

		this.panelForm.add(new JLabel("Nom : "));
		this.panelForm.add(this.txtNom);

		this.panelForm.add(new JLabel("Prénom : "));
		this.panelForm.add(this.txtPrenom);

		this.panelForm.add(new JLabel("NB heures vol : "));
		this.panelForm.add(this.txtNbHeuresVol);
		
		this.panelForm1.setLayout(new GridLayout(2, 2));
		this.panelForm1.add(new JLabel("Adresse Pilote : "));
		this.panelForm1.add(this.txtAdresse);

		this.panelForm1.add(new JLabel("Bip Pilote : "));
		this.panelForm1.add(this.txtBip);
		
		this.panelForm2.setLayout(new GridLayout(1, 2));
		this.panelForm2.add(new JLabel("Poste navigant : "));
		this.panelForm2.add(this.txtPoste);

		this.panelForm3.setLayout(new GridLayout(1, 2));
		this.panelForm3.add(this.btAnnuler);
		this.panelForm3.add(this.btEnregistrer);

		// this.panelForm.setBackground(Color.gray);
		// this.panelForm1.setBackground(Color.gray);
		// this.panelForm2.setBackground(Color.gray);
		// this.panelForm3.setBackground(Color.gray);

		this.panelForm.setBounds(40, 20, 300, 100); // 100 + 20 = 120 + 50 = 270
		this.panelForm1.setBounds(40, 170, 300, 60); 
		this.panelForm2.setBounds(40, 250, 300, 30);
		this.panelForm3.setBounds(40, 300, 300, 40);
		if (unUser.getRole().equals("admin")) {
			this.add(this.panelForm);
			this.add(this.panelForm1);
			this.add(this.panelForm2);
			this.add(this.panelForm3);
			
			JLabel lbPilote = new JLabel("Pilote");
			lbPilote.setBounds(65, 130, 80, 20); // Dimension du texte
			lbPilote.setForeground(Color.WHITE);
			this.add(lbPilote);
			this.radioPilote.setBounds(40, 130, 20, 20); // Dimension du fonds
			this.add(this.radioPilote);
			
			JLabel lbNavigant = new JLabel("Navigant");
			lbNavigant.setBounds(205, 130, 80, 20);
			lbNavigant.setForeground(Color.WHITE);
			this.add(lbNavigant);
			this.radioNavigant.setBounds(180, 130, 20, 20);
			this.add(this.radioNavigant);
			
			// Par défaut : on prend le PanelPilote
			this.radioPilote.setSelected(true); // Bouton radio sélectionné
			this.radioNavigant.setSelected(false); // Bouton radio non sélectionné
			this.panelForm2.setVisible(false);
			
			//
		} else {
			// Modifier les tailles des tableaux
		}
	
		// Construction du panel Table
		this.panelTable.setBounds(345, 20, 400, 303);
		this.panelTable.setBackground(Color.yellow);
		this.panelTable.setLayout(null);
		String entetes[] = { "ID", "Nom", "Prénom", "Adresse", "NB heures Vol", "Bip" };
		Object donnees[][] = this.getLesDonnees("");
		unTableau = new Tableau(entetes, donnees); // Appel du constructeur Tableau
		this.uneTable = new JTable(unTableau);
		JScrollPane uneScroll = new JScrollPane(this.uneTable);
		uneScroll.setBounds(0, 60, 405, 280);
		this.panelTable.add(uneScroll);
		
		// Placement de la zone de recherche
		this.txtMot.setBounds(50, 20, 120, 20);
		this.panelTable.add(this.txtMot);
		this.btRechercher.setBounds(190, 20, 120, 20);
		this.panelTable.add(this.btRechercher);

		this.add(this.panelTable);
		
		this.uneTable.addMouseListener(new MouseListener() { 
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if (unUser.getRole().equals("admin")) {
					int nbclic = e.getClickCount();
					if (nbclic == 2) {
						int numLigne = uneTable.getSelectedRow();
						int retour = JOptionPane.showConfirmDialog(null, "Voulez-vous supptimer ce pilote ?", "suppression pilore", JOptionPane.YES_NO_OPTION);
						if(retour == 0) {
							int idpilote = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
							Modele.deletePilote(idpilote);
							unTableau.supprimerLigne(numLigne);
							viderChamps();
						}
					} else if(nbclic == 1) {
						int numLigne = uneTable.getSelectedRow();
						String nom = unTableau.getValueAt(numLigne, 1).toString();
						txtNom.setText(nom);

						String prenom = unTableau.getValueAt(numLigne, 2).toString();
						txtPrenom.setText(prenom);

						String adresse = unTableau.getValueAt(numLigne, 3).toString();
						txtAdresse.setText(adresse);

						String nbHeuresVol = unTableau.getValueAt(numLigne, 4).toString();
						txtNbHeuresVol.setText(nbHeuresVol);

						String bip = unTableau.getValueAt(numLigne, 5).toString();
						txtBip.setText(bip);

						btEnregistrer.setText("Modifier");
						//unTableau
					}
				}
			}
		});

		// Rendre les boutons écoutables
		this.btAnnuler.addActionListener(this);
		this.btEnregistrer.addActionListener(this);
		this.btRechercher.addActionListener(this);
		this.radioPilote.addActionListener(this);
		this.radioNavigant.addActionListener(this);
	}

	public Object[][] getLesDonnees(String mot) {
		ArrayList<Pilote> lesPilotes = null;
		if (mot.equals("")) {
			lesPilotes = Modele.selectAllPilotes();
		} else {
			lesPilotes = Modele.selectLikePilote(mot);
		}
		Object[][] matrice = new Object[lesPilotes.size()][6];
		int i = 0;
		for (Pilote unPilote : lesPilotes) {
			matrice[i][0] = unPilote.getIdpers();
			matrice[i][1] = unPilote.getNom();
			matrice[i][2] = unPilote.getPrenom();
			matrice[i][3] = unPilote.getAdresse();
			matrice[i][4] = unPilote.getNbheuresvol();
			matrice[i][5] = unPilote.getBip();
			i++;
		}
		return matrice;
	}
	
	public void viderChamps () {
		this.txtNom.setText("");
		this.txtPrenom.setText("");
		this.txtAdresse.setText("");
		this.txtNbHeuresVol.setText("");
		this.txtBip.setText("");
		this.txtPoste.setText("");
		this.btEnregistrer.setText("Enregistrer");
	}
	
	public Pilote saisirPilote () {
		Pilote unPilote = null;
		String nom = this.txtNom.getText();
		String prenom = this.txtPrenom.getText();
		String adresse = this.txtAdresse.getText();
		int nbheuresvol = 0;
		try {
			nbheuresvol = Integer.parseInt(this.txtNbHeuresVol.getText());
		} catch (NumberFormatException exp) {
			JOptionPane.showMessageDialog(this, "Attention au format du nombre");
			this.txtNbHeuresVol.setBackground(Color.red);
		}
		String bip = this.txtBip.getText();
		
		boolean ok = true;

		if (nom.equals("") || !nom.matches("^[A-Z][a-zA-Z]+$")) {
			this.txtNom.setBackground(Color.red);
			ok = false;
		} else {
			this.txtNom.setBackground(Color.white);
		}

		if (prenom.equals("") || !prenom.matches("^[A-Z][a-zA-Z]+$")) {
			this.txtPrenom.setBackground(Color.red);
			ok = false;
		} else {
			this.txtPrenom.setBackground(Color.white);
		}

		if (adresse.equals("")) {
			this.txtAdresse.setBackground(Color.red);
			ok = false;
		} else {
			this.txtAdresse.setBackground(Color.white);
		}

		if (nbheuresvol < 0) {
			this.txtNbHeuresVol.setBackground(Color.red);
			ok = false;
		} else {
			this.txtNbHeuresVol.setBackground(Color.white);
		}

		if (bip.equals("") || !bip.matches("^[0-9]+$")) {
			this.txtBip.setBackground(Color.red);
			ok = false;
		} else {
			this.txtBip.setBackground(Color.white);
		}

		if (ok == true && nbheuresvol > 0) {
			unPilote = new Pilote(nom, prenom, adresse, nbheuresvol, bip);
		} else {
			return null;
		}
		return unPilote;
	}
	
	public Navigant saisirNavigant () {
		Navigant unNavigant = null;
		String nom = this.txtNom.getText();
		String prenom = this.txtPrenom.getText();
		String poste = this.txtPoste.getText();
		int nbheuresvol = 0;
		try {
			nbheuresvol = Integer.parseInt(this.txtNbHeuresVol.getText());
		} catch (NumberFormatException exp) {
			JOptionPane.showMessageDialog(this, "Attention au format du nombre");
			this.txtNbHeuresVol.setBackground(Color.red);
		}

		if (nom.equals("")) {
			this.txtNom.setBackground(Color.red);
		} else {
			this.txtNom.setBackground(Color.white);
		}

		if (prenom.equals("")) {
			this.txtPrenom.setBackground(Color.red);
		} else {
			this.txtPrenom.setBackground(Color.white);
		}

		if (poste.equals("")) {
			this.txtPoste.setBackground(Color.red);
		} else {
			this.txtPoste.setBackground(Color.white);
		}

		if (nbheuresvol > 0) {
			this.txtNbHeuresVol.setBackground(Color.white);
		}

		if (!nom.equals("") && !prenom.equals("") && !poste.equals("") && nbheuresvol > 0) {
			unNavigant = new Navigant(nom, prenom, poste, nbheuresvol);
		} else {
			return null;
		}
		return unNavigant;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btAnnuler) {
			this.viderChamps();
		} else if (e.getSource() == this.btEnregistrer && e.getActionCommand().equalsIgnoreCase("Enregistrer")) {
			
			if (this.radioPilote.isSelected()) {
				Pilote unPilote = this.saisirPilote();
				if (unPilote != null) {
					Modele.insertPilote(unPilote);
					// Récupérer l'ID auto_increment de la BDD
					unPilote = Modele.selectWherePilote(unPilote.getNom(), unPilote.getPrenom(), unPilote.getBip());

					// Mettre à jour l'affichage
					Object ligne[] = { unPilote.getIdpers(), unPilote.getNom(), unPilote.getPrenom(),
							unPilote.getAdresse(), unPilote.getNbheuresvol(), unPilote.getBip() };
					unTableau.ajouterLigne(ligne);

					JOptionPane.showMessageDialog(this, "Insertion du pilote réussi !");
					unTableau.fireTableDataChanged();
					this.viderChamps();
				} else {
					JOptionPane.showMessageDialog(this, "Echec de l'insertion !");
				}
			} else if (this.radioNavigant.isSelected()) {
				Navigant unNavigant = this.saisirNavigant();
				if (unNavigant != null) {
					Modele.insertNavigant(unNavigant);
					JOptionPane.showMessageDialog(this, "Insertion du navigant réussi !");
					this.viderChamps();
					/*
					unNavigant = Modele.selectWhereNavigant(unNavigant.getNom(), unNavigant.getPrenom(), unNavigant.getPoste());
					Object ligne[] = {unNavigant.getIdpers(), unNavigant.getNom(), unNavigant.getPrenom(), unNavigant.getPoste(),
							unNavigant.getNbheureshol()};
					unTableau.ajouterLigne(ligne);
					unTableau.fireTableDataChanged();
					*/
				} else {
					JOptionPane.showMessageDialog(this, "Echec de l'insertion !");
				}
				
			}
			
		} else if(e.getSource() == this.btEnregistrer && e.getActionCommand().equalsIgnoreCase("Modifier")) {
			Pilote unPilote = this.saisirPilote();
			int numLigne = this.uneTable.getSelectedRow();
			int idpilote = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
			unPilote.setIdpers(idpilote);
			Modele.updatePilote(unPilote);
			JOptionPane.showMessageDialog(this, "Modification effectuée !");
			Object ligne[] = { unPilote.getIdpers(), unPilote.getNom(), unPilote.getPrenom(),
					unPilote.getAdresse(), unPilote.getNbheuresvol(), unPilote.getBip() };
			unTableau.modifierLigne(numLigne, ligne);
			this.viderChamps();
			this.btEnregistrer.setText("Enregistrer");
		} else if (e.getSource() == this.btRechercher) {
			String mot = this.txtMot.getText();
			
			// Récupérer la matrice des données pour actualiser l'affichage
			Object matrice [][] = this.getLesDonnees(mot);
			
			unTableau.setDonnees(matrice); 
		} else if (e.getSource() == this.radioNavigant) {
			this.radioPilote.setSelected(false);
			this.radioNavigant.setSelected(true);
			this.panelForm1.setVisible(false);
			this.panelForm2.setVisible(true);
		} else if (e.getSource() == this.radioPilote) {
			this.radioPilote.setSelected(true);
			this.radioNavigant.setSelected(false);
			this.panelForm1.setVisible(true);
			this.panelForm2.setVisible(false);
		}
	}

}
