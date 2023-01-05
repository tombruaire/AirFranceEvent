package vue;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import controleur.Avion;
import controleur.Pilote;
import controleur.Tableau;
import controleur.Vol;
import modele.Modele;

public class PanelVol extends PanelDeBase implements ActionListener {

	private JPanel panelForm = new JPanel();
	private JPanel panelTable = new JPanel();
	
	private JButton btEnregistrer = new JButton("Enregistrer");
	private JButton btAnnuler = new JButton("Annuler");
	
	private JTextField txtDesignation = new JTextField();
	private JTextField txtDateVol = new JTextField();
	private JTextField txtHeureVol = new JTextField();
	
	private JComboBox<String> cbxIdpilote1 = new JComboBox<String>();
	private JComboBox<String> cbxIdpilote2 = new JComboBox<String>();
	private JComboBox<String> cbxIdAvion = new JComboBox<String>();

	private JTable uneTable = null;

	private static Tableau unTableau = null;

	public PanelVol() {
		super(Color.gray);

		this.panelForm.setLayout(new GridLayout(7, 2));

		this.panelForm.add(new JLabel("Désignation Vol : "));
		this.panelForm.add(this.txtDesignation);

		this.panelForm.add(new JLabel("Date vol (AAAA-MM-JJ) : "));
		this.panelForm.add(this.txtDateVol);

		this.panelForm.add(new JLabel("Heure vol (HH:MM) : "));
		this.panelForm.add(this.txtHeureVol);

		this.panelForm.add(new JLabel("ID Pilote 1 : "));
		this.panelForm.add(this.cbxIdpilote1);

		this.panelForm.add(new JLabel("ID Pilote 2 : "));
		this.panelForm.add(this.cbxIdpilote2);

		this.panelForm.add(new JLabel("ID Avion : "));
		this.panelForm.add(this.cbxIdAvion);

		this.panelForm.add(this.btAnnuler);
		this.panelForm.add(this.btEnregistrer);

		// this.panelForm.setBackground(Color.gray);

		this.panelForm.setBounds(40, 20, 300, 300);
		this.add(this.panelForm);

		// Construction du panel Table
		this.panelTable.setBounds(345, 20, 400, 320);
		this.panelTable.setBackground(Color.yellow);
		this.panelTable.setLayout(null);
		String entetes[] = { "ID", "Désignation", "Date vol", "Heure vol", "ID Pilote 1", "ID Pilote 2", "ID Avion" };
		Object donnees[][] = this.getLesDonnees();
		unTableau = new Tableau(entetes, donnees); // Appel du constructeur Tableau
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
					int retour = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer ce vol ?", "Suppression d'un Vol", JOptionPane.YES_NO_OPTION);
					if (retour == 0) {
						int idvol = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
						Modele.deleteVol(idvol);
						unTableau.supprimerLigne(numLigne);
						viderChamps();
					}
				} else if (nbclic == 1) {
					int numLigne = uneTable.getSelectedRow();
					
					String designation = unTableau.getValueAt(numLigne, 1).toString();
					txtDesignation.setText(designation);
					
					String datevol = unTableau.getValueAt(numLigne, 2).toString();
					txtDateVol.setText(datevol);
					
					String heurevol = unTableau.getValueAt(numLigne, 3).toString();
					txtHeureVol.setText(heurevol);
					
					String idpilote1 = unTableau.getValueAt(numLigne, 4).toString();
					cbxIdpilote1.setSelectedItem(idpilote1);
					
					String idpilote2 = unTableau.getValueAt(numLigne, 5).toString();
					cbxIdpilote2.setSelectedItem(idpilote2);
					
					String idavion = unTableau.getValueAt(numLigne, 6).toString();
					cbxIdAvion.setSelectedItem(idavion);
					
					btEnregistrer.setText("Modifier");
				}
			}
		});
		
		// Remplir les CBX ID
		this.remplirCBX();

		// Rendre les boutons écoutables
		this.btAnnuler.addActionListener(this);
		this.btEnregistrer.addActionListener(this);
	}
	
	public void remplirCBX () {
		ArrayList<Pilote> lesPilotes = Modele.selectAllPilotes();
		for (Pilote unPilote : lesPilotes) {
			this.cbxIdpilote1.addItem(unPilote.getIdpers()+"-"+unPilote.getNom());
			this.cbxIdpilote2.addItem(unPilote.getIdpers()+"-"+unPilote.getNom());
		}
		ArrayList<Avion> lesAvions = Modele.selectAllAvions();
		for (Avion unAvion : lesAvions) {
			this.cbxIdAvion.addItem(unAvion.getIdavion()+"-"+unAvion.getDesignation());
		}
	}

	public Object[][] getLesDonnees() {
		ArrayList<Vol> lesVols = Modele.selectAllVols();
		Object[][] matrice = new Object[lesVols.size()][7];
		int i = 0;
		for (Vol unVol : lesVols) {
			matrice[i][0] = unVol.getIdvol();
			matrice[i][1] = unVol.getDesignation();
			matrice[i][2] = unVol.getDatevol();
			matrice[i][3] = unVol.getHeurevol();
			matrice[i][4] = unVol.getIdpilote1();
			matrice[i][5] = unVol.getIdpilote2();
			matrice[i][6] = unVol.getIdavion();
			i++;
		}
		return matrice;
	}
	
	public void viderChamps () {
		this.txtDesignation.setText("");
		this.txtDateVol.setText("");
		this.txtHeureVol.setText("");
		this.btEnregistrer.setText("Enregistrer");

	
	public Vol saisirVol () {
		Vol unVol = null;
		
		String designation = this.txtDesignation.getText();
		String datevol = this.txtDateVol.getText();
		String heurevol = this.txtHeureVol.getText();
		
		String tab[] = this.cbxIdpilote1.getSelectedItem().toString().split("-");
		int idpilote1 = Integer.parseInt(tab[0]);
		tab = this.cbxIdpilote2.getSelectedItem().toString().split("-");
		int idpilote2 = Integer.parseInt(tab[0]);
		tab = this.cbxIdAvion.getSelectedItem().toString().split("-");
		int idavion = Integer.parseInt(tab[0]);

		if (designation.equals("")) {
			this.txtDesignation.setBackground(Color.red);
		} else {
			this.txtDesignation.setBackground(Color.white);
		}

		if (datevol.equals("")) {
			this.txtDateVol.setBackground(Color.red);
		} else {
			this.txtDateVol.setBackground(Color.white);
		}

		if (heurevol.equals("")) {
			this.txtHeureVol.setBackground(Color.red);
		} else {
			this.txtHeureVol.setBackground(Color.white);
		}

		if (!designation.equals("") && !datevol.equals("") && !heurevol.equals("")) {
			unVol = new Vol(designation, datevol, heurevol, idpilote1, idpilote2, idavion);
		} else {
			return null;
		}
		return unVol;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btAnnuler) {
			this.viderChamps();
		} else if (e.getSource() == this.btEnregistrer && e.getActionCommand().equalsIgnoreCase("Enregistrer")) {
			Vol unVol = this.saisirVol();
			Modele.insertVol(unVol);
			
			// Récupérer l'ID auto_increment de la BDD
			unVol = Modele.selectWhereVol(unVol.getDesignation(), unVol.getDatevol(), unVol.getHeurevol());

			// Mettre à jour l'affichage
			Object ligne[] = { unVol.getIdvol(), unVol.getDesignation(), unVol.getDatevol(), unVol.getHeurevol(),
					unVol.getIdpilote1(), unVol.getIdpilote2(), unVol.getIdavion() };
			unTableau.ajouterLigne(ligne);

			JOptionPane.showMessageDialog(this, "Insertion du vol réussi !");
			unTableau.fireTableDataChanged();
			this.viderChamps();
		} else if(e.getSource() == this.btEnregistrer && e.getActionCommand().equalsIgnoreCase("Modifier")) {
			Vol unVol = this.saisirVol();
			int numLigne = this.uneTable.getSelectedRow();
			int idvol = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
			unVol.setIdvol(idvol);
			Modele.updateVol(unVol);
			JOptionPane.showMessageDialog(this, "Modification effectuée !");
			Object ligne[] = { unVol.getIdvol(), unVol.getDesignation(), unVol.getDatevol(), unVol.getHeurevol(),
					unVol.getIdpilote1(), unVol.getIdpilote2(), unVol.getIdavion() };
			unTableau.modifierLigne(numLigne, ligne);
			this.viderChamps();
			this.btEnregistrer.setText("Enregistrer");
		}
	}

}
