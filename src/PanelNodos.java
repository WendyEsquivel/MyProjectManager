import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class PanelNodos extends JPanel {
	private Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	private int x = (int) (screenSize.getWidth()-40);
	private int y = (int) (((screenSize.getHeight()-40)/8)*7);
	
	public PanelNodos(){
		super();
		//this.setPreferredSize(new Dimension (500,500));
		this.setBackground(new Color(242,242,242));		
	}
	private void AreaDeTrabajo(Graphics g) {
		g.setColor(new Color(255,255,255));
		g.fillRect(20, 15, x, y);
	}

	public void paintComponent(Graphics g) { //contexto grafico de la pantalla
		super.paintComponent(g);	
		this.AreaDeTrabajo(g);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
