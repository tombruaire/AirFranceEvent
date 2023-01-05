package vue;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import controleur.Avion;
import controleur.Tableau;
import modele.Modele;

public class PanelAvion extends PanelDeBase implements ActionListener {
	
	private JPanel panelForm = new JPanel();
	private JPanel panelTable = new JPanel();
	private JButton btEnregistrer = new JButton("Enregistrer");
	private JButton btAnnuler = new JButton("Annuler");
	private JTextField txtDesignation = new JTextField();
	private JTextField txtConstructeur = new JTextField();
	private JTextField txtNbPlaces = new JTextField();
	
	private JTable uneTable = null;
	
	private static Tableau unTableau = null;

	public PanelAvion() {
		super(Color.gray);
		
		this.panelForm.setLayout(new GridLayout(4,2));
		
		this.panelForm.add(new JLabel("Désignation Avion : "));
		this.panelForm.add(this.txtDesignation);
		
		this.panelForm.add(new JLabel("Constructeur Avion : "));
		this.panelForm.add(this.txtConstructeur);
		
		this.panelForm.add(new JLabel("NB places Avion : "));
		this.panelForm.add(this.txtNbPlaces);
		
		this.panelForm.add(this.btAnnuler);
		this.panelForm.add(this.btEnregistrer);
		
		// this.panelForm.setBackground(Color.gray);
		
		this.panelForm.setBounds(40, 20, 300, 300);
		this.add(this.panelForm);
		
		// Construction du panel Table
		this.panelTable.setBounds(345, 20, 400, 320);
		this.panelTable.setBackground(Color.yellow);
		this.panelTable.setLayout(null);
		String entetes[] = {"ID", "Designation", "Constructeur", "NB places"};
		Object donnees[][] = this.getLesDonnees();
		unTableau = new Tableau (entetes, donnees); // Appel du constructeur Tableau
		this.uneTable = new JTable(unTableau);
		JScrollPane uneScroll = new JScrollPane(this.uneTable);
		uneScroll.setBounds(10, 10, 380, 280);
		this.panelTable.add(uneScroll);
		
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
				int nbclic = e.getClickCount();
				if (nbclic == 2) {
					int numLigne = uneTable.getSelectedRow();
					int retour = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer cet Avion ?", "Suppression d'un Avion", JOptionPane.YES_NO_OPTION);
					if (retour == 0) {
						int idavion = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
						Modele.deleteAvion(idavion);
						unTableau.supprimerLigne(numLigne);
						viderChamps();
					}
				} else if (nbclic == 1) {
					int numLigne = uneTable.getSelectedRow();
					
					String designation = unTableau.getValueAt(numLigne, 1).toString();
					txtDesignation.setText(designation);
					
					String constructeur = unTableau.getValueAt(numLigne, 2).toString();
					txtConstructeur.setText(constructeur);
					
					String nbplaces = unTableau.getValueAt(numLigne, 3).toString();
					txtNbPlaces.setText(nbplaces);
					
					btEnregistrer.setText("Modifier");
				}
			}
		});
		
		// Rendre les boutons écoutables
		this.btAnnuler.addActionListener(this);
		this.btEnregistrer.addActionListener(this);
	}
	
	public Object [] [] getLesDonnees () {
		ArrayList<Avion> lesAvions = Modele.selectAllAvions();
		Object [] [] matrice = new Object[lesAvions.size()][4];
		int i = 0;
		for (Avion unAvion : lesAvions) {
			matrice[i][0] = unAvion.getIdavion();
			matrice[i][1] = unAvion.getDesignation();
			matrice[i][2] = unAvion.getConstructeur();
			matrice[i][3] = unAvion.getNbplaces();
			i++;
		}
		return matrice;
	}
	
	public void viderChamps () {
		this.txtDesignation.setText("");
		this.txtConstructeur.setText("");
		this.txtNbPlaces.setText("");
		this.btEnregistrer.setText("Enregister");
	}
	
	public Avion saisirAvion () {
		Avion unAvion = null;
		String designation = this.txtDesignation.getText();
		String constructeur = this.txtConstructeur.getText();
		int nbplaces = 0;
		try {
			nbplaces = Integer.parseInt(this.txtNbPlaces.getText());
		} catch (NumberFormatException exp) {
			JOptionPane.showMessageDialog(this, "Attention au format du nombre");
			this.txtNbPlaces.setBackground(Color.red);
		}
		
		if (designation.equals("")) {
			this.txtDesignation.setBackground(Color.red);
		} else {
			this.txtDesignation.setBackground(Color.white);
		}
		
		if (constructeur.equals("")) {
			this.txtConstructeur.setBackground(Color.red);
		} else {
			this.txtConstructeur.setBackground(Color.white);
		}
		
		if (!designation.equals("") && !constructeur.equals("") && nbplaces > 0) {
			unAvion = new Avion (designation, constructeur, nbplaces);
		} else {
			return null;
		}
		return unAvion;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btAnnuler) {
			this.viderChamps();
		} else if (e.getSource() == this.btEnregistrer && e.getActionCommand().equalsIgnoreCase("Enregistrer")) {
			Avion unAvion = this.saisirAvion();
			Modele.insertAvion(unAvion);
			
			// Récupérer l'ID auto_increment de la BDD
			unAvion = Modele.selectWhereAvion(unAvion.getDesignation(), unAvion.getConstructeur());
			
			// Mettre à jour l'affichage
			Object ligne[] = {unAvion.getIdavion(), unAvion.getDesignation(), unAvion.getConstructeur(), unAvion.getNbplaces()};
			unTableau.ajouterLigne(ligne);
			
			JOptionPane.showMessageDialog(this, "Insertion de l'avion réussi !");
			unTableau.fireTableDataChanged();
			this.viderChamps();
		} else if(e.getSource() == this.btEnregistrer && e.getActionCommand().equalsIgnoreCase("Modifier")) {
			Avion unAvion = this.saisirAvion();
			int numLigne = this.uneTable.getSelectedRow();
			int idpilote = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
			unAvion.setIdavion(idpilote);
			Modele.updateAvion(unAvion);
			JOptionPane.showMessageDialog(this, "Modification effectuée !");
			Object ligne[] = {unAvion.getIdavion(), unAvion.getDesignation(), unAvion.getConstructeur(), unAvion.getNbplaces()};
			unTableau.modifierLigne(numLigne, ligne);
			this.viderChamps();
			this.btEnregistrer.setText("Enregistrer");
		}
	}

}
