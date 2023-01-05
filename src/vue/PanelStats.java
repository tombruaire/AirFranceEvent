package vue;

import java.awt.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

import controleur.Tableau;
import modele.Modele;

public class PanelStats extends PanelDeBase {
	
	private JPanel panelStat = new JPanel();

	public PanelStats() {
		super(Color.lightGray); 
		
		this.panelStat.setLayout(new GridLayout(3,1));
		this.panelStat.setBounds(10, 10, 730, 200);
		// this.setBackground(Color.yellow);
		this.setVisible(false);
		
		String entetes[] = { "NB Pilotes", "NB Avions", "NB Vols "};
		Object matrice [][] = {{Modele.countPilotes(), Modele.countAvions(), Modele.countVols()}};
		Tableau unTableau = new Tableau (entetes, matrice);
		JTable uneTable = new JTable(unTableau);
		
		/* Center les valeurs du tableau */
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		for (int i=0; i<uneTable.getColumnCount(); i++) {
			uneTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
		
		// uneTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		// uneTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		
		JScrollPane uneScoll = new JScrollPane(uneTable);
		uneScoll.setBounds(40, 40, 250, 100);
		this.panelStat.add(uneScoll);
		
		/*
		this.panelStat.add(new JLabel(" Nombre de Pilotes : " + Modele.countPilotes()));
		this.panelStat.add(new JLabel(" Nombre d'Avions : " + Modele.countAvions()));
		this.panelStat.add(new JLabel(" Nombre de Vols : " + Modele.countVols()));
		*/
		this.add(this.panelStat);
	}

}
