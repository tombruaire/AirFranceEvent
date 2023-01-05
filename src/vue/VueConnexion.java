package vue;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import controleur.*;

public class VueConnexion extends JFrame implements ActionListener, KeyListener {
	
	JPanel panelConnexion = new JPanel();
	private JButton btSeSconnecter = new JButton("Se connecter");
	private JButton btAnnuler = new JButton("Annuler");
	private JTextField txtEmail = new JTextField("a@gmail.com");
	private JPasswordField txtMdp = new JPasswordField("123");
	
	public VueConnexion () {
		this.setTitle("Air France Administration");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.getContentPane().setBackground(Color.cyan);
		this.setBounds(300, 300, 600, 250);
		this.setLayout(null);
		
		// Construction du panel connexion
		this.panelConnexion.setLayout(new GridLayout(3, 2));
		this.panelConnexion.setBounds(300, 40, 260, 150);
		this.panelConnexion.setBackground(Color.cyan);
		this.panelConnexion.add(new JLabel("Email : "));
		this.panelConnexion.add(this.txtEmail);
		
		this.panelConnexion.add(new JLabel("Mot de passe : "));
		this.panelConnexion.add(this.txtMdp);
		
		this.panelConnexion.add(this.btAnnuler);
		this.panelConnexion.add(this.btSeSconnecter);
		
		this.add(this.panelConnexion);
		
		// Installation du logo
		ImageIcon leLogo = new ImageIcon("src/images/logo.jpg");
		JLabel lbLogo = new JLabel(leLogo);
		lbLogo.setBounds(20, 40, 250, 150);
		this.add(lbLogo);
		
		// Rendre les boutons �coutables
		this.btAnnuler.addActionListener(this);
		this.btSeSconnecter.addActionListener(this);
		
		// Rendre les txt �coutables
		this.txtEmail.addKeyListener(this);
		this.txtMdp.addKeyListener(this);
		
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btAnnuler) {
			this.txtEmail.setText("");
			this.txtMdp.setText("");
		} else if (e.getSource() == this.btSeSconnecter) {
			traitement();
		}
	}
	
	public void traitement () {
		String email = this.txtEmail.getText();
		String mdp = new String (this.txtMdp.getPassword());
		
		// Hashage du MDP
		mdp = AirFrance.crypterMdp(mdp);
		
		// V�rification en BDD de l'user
		User unUser = AirFrance.selectWhereUser(email, mdp);
		
		if (unUser == null) {
			JOptionPane.showMessageDialog(this, "Veuillez v�rifier vos identifiants !");
			this.txtEmail.setText("");
			this.txtMdp.setText("");
		} else {
			JOptionPane.showMessageDialog(this, "Bienvenue M. " + unUser.getNom()
												+ "\n Vous avez le r�le : " + unUser.getRole());
			// Appel de la Vue G�n�rale
			// Instancier la Vue G�n�rale
			AirFrance.instancierVueGenerale(unUser);
			
			// Cacher la Vue Connexion
			AirFrance.rendreVisibleVueConnexion(false);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// 
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			traitement ();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// 
	}

}
