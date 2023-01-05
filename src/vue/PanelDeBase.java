package vue;

import java.awt.*;
import javax.swing.*;

public class PanelDeBase extends JPanel {
	
	public PanelDeBase(Color uneCouleur) {
		this.setBounds(100, 100, 750, 350);
		this.setLayout(null);
		this.setBackground(uneCouleur);
		this.setVisible(false);
	}

}
