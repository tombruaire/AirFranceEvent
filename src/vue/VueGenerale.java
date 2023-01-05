package vue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import controleur.AirFrance;
import controleur.User;

public class VueGenerale extends JFrame implements ActionListener {
	
	private JButton btQuitter = new JButton("Déconnexion");
	private JButton btProfil = new JButton("Mon Profil");
	private JButton btPilotes = new JButton("Pilotes");
	private JButton btAvions = new JButton("Avions");
	private JButton btVols = new JButton("Vols");
	private JButton btStats = new JButton("Statistiques");
	private JButton btBoard = new JButton("Tableau de bord");
	
	/*** Les Panels ***/
	private JPanel panelMenu = new JPanel();
	private JPanel panelProfil = new JPanel();
	private static PanelPilote unPanelPilote;
	private static PanelAvion unPanelAvion = new PanelAvion();
	private static PanelVol unPanelVol = new PanelVol();
	private static PanelStats unPanelStats = new PanelStats();
	private static PanelBord unPanelBord = new PanelBord();
	
	public VueGenerale (User unUser) {
		
		// Instancier le panel Pilotes
		this.unPanelPilote = new PanelPilote(unUser);
		
		this.setTitle("Air France Administration");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.getContentPane().setBackground(Color.cyan);
		this.setBounds(300, 300, 950, 500);
		this.setLayout(null);
		
		// Construction du panel menu
		this.panelMenu.setLayout(new GridLayout(1,7));
		this.panelMenu.setBounds(20, 10, 900, 40);
		this.panelMenu.setBackground(Color.cyan);
		this.panelMenu.add(btProfil);
		this.panelMenu.add(btPilotes);
		this.panelMenu.add(btAvions);
		this.panelMenu.add(btVols);
		this.panelMenu.add(btStats);
		this.panelMenu.add(btBoard);
		this.panelMenu.add(btQuitter);
		this.add(this.panelMenu);
		
		// Construction du panel Profil
		this.panelProfil.setLayout(new GridLayout(4,1));
		this.panelProfil.setBounds(260, 100, 400, 300);
		this.panelProfil.setBackground(Color.yellow);
		this.panelProfil.setVisible(false);
		this.panelProfil.add(new JLabel("Nom de l'user : " + unUser.getNom()));
		this.panelProfil.add(new JLabel("Prénom de l'user : " + unUser.getPrenom()));
		this.panelProfil.add(new JLabel("Email de l'user : " + unUser.getEmail()));
		this.panelProfil.add(new JLabel("Rôle de l'user : " + unUser.getRole()));
		this.add(this.panelProfil);
		
		// Insertion des panels d'administration : Pilote, Avion, Vol
		this.add(unPanelPilote);
		this.add(unPanelAvion);
		this.add(unPanelVol);
		this.add(unPanelStats);
		this.add(unPanelBord);
		
		// Rendre les boutons cliquables
		this.btQuitter.addActionListener(this);
		this.btPilotes.addActionListener(this);
		this.btProfil.addActionListener(this);
		this.btAvions.addActionListener(this);
		this.btVols.addActionListener(this);
		this.btStats.addActionListener(this);
		this.btBoard.addActionListener(this);
		
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btQuitter) {
			AirFrance.fermerVueGenerale();
			AirFrance.rendreVisibleVueConnexion(true);
		} else if (e.getSource() == this.btProfil) {
			this.panelProfil.setVisible(true);
			unPanelPilote.setVisible(false);
			unPanelAvion.setVisible(false);
			unPanelVol.setVisible(false);
			unPanelStats.setVisible(false);
			unPanelBord.setVisible(false);
		} else if (e.getSource() == this.btPilotes) {
			this.panelProfil.setVisible(false);
			unPanelPilote.setVisible(true);
			unPanelAvion.setVisible(false);
			unPanelVol.setVisible(false);
			unPanelStats.setVisible(false);
			unPanelBord.setVisible(false);
		} else if (e.getSource() == this.btAvions) {
			this.panelProfil.setVisible(false);
			unPanelPilote.setVisible(false);
			unPanelAvion.setVisible(true);
			unPanelVol.setVisible(false);
			unPanelStats.setVisible(false);
			unPanelBord.setVisible(false);
		} else if (e.getSource() == this.btVols) {
			this.panelProfil.setVisible(false);
			unPanelPilote.setVisible(false);
			unPanelAvion.setVisible(false);
			unPanelVol.setVisible(true);
			unPanelStats.setVisible(false);
			unPanelBord.setVisible(false);
		} else if (e.getSource() == this.btStats) {
			this.panelProfil.setVisible(false);
			unPanelPilote.setVisible(false);
			unPanelAvion.setVisible(false);
			unPanelVol.setVisible(false);
			unPanelStats.setVisible(true);
			unPanelBord.setVisible(false);
		} else if (e.getSource() == this.btBoard) {
			this.panelProfil.setVisible(false);
			unPanelPilote.setVisible(false);
			unPanelAvion.setVisible(false);
			unPanelVol.setVisible(false);
			unPanelStats.setVisible(false);
			unPanelBord.setVisible(true);
		}
	}

}
