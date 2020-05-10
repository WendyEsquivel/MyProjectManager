import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelBotones extends JPanel{	
	
	private JButton btExpCot;
	private JButton btExpRuta;
		
	public PanelBotones() {	

		this.btExpCot = new JButton(new ImageIcon("expcot.jpg"));
		this.btExpRuta = new JButton(new ImageIcon("expruta.jpg"));
		
		btExpCot.setBorder(null);
		btExpRuta.setBorder(null);
		
		add(this.btExpCot);
		add(this.btExpRuta);
	}
	
}
