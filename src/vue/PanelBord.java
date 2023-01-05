package vue;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import controleur.PAV;
import controleur.Tableau;
import modele.Modele;

public class PanelBord extends PanelDeBase {
	
	private JPanel panelBord = new JPanel();
	
	private JTable uneTable = null;
	
	private static Tableau unTableau = null;

	public PanelBord() {
		super(Color.lightGray);
		
		this.panelBord.setBounds(10, 10, 727, 303);
		this.panelBord.setBackground(Color.yellow); 
		this.panelBord.setLayout(null);
		
		String entetes[] = {"Nom", "Prénom", "Avion", "Vol", "Date Vol", "Heure Vol"};
		Object donnees[][] = this.getLesDonnees();
		unTableau = new Tableau(entetes, donnees);
		this.uneTable = new JTable(unTableau);
		JScrollPane uneSroll = new JScrollPane(this.uneTable);
		uneSroll.setBounds(10, 10, 707, 280);
		this.panelBord.add(uneSroll);
		
		this.add(this.panelBord);
	}
	
	public Object[][] getLesDonnees() {
		ArrayList<PAV> lesPAVs = Modele.selectAllPAV();
		Object[][] matrice = new Object[lesPAVs.size()][6];
		int i = 0;
		for (PAV unPAV : lesPAVs) {
			matrice[i][0] = unPAV.getNom();
			matrice[i][1] = unPAV.getPrenom();
			matrice[i][2] = unPAV.getAvion();
			matrice[i][3] = unPAV.getVol();
			matrice[i][4] = unPAV.getDatevol();
			matrice[i][5] = unPAV.getHeurevol();
			i++;
		}
		return matrice;
	}

}
