import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
	
public class Marco extends JFrame{
	
	public Marco(){
		super("Project Manager");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		//this.setUndecorated(true); Lo hace full pero no salen opciones
		
	    this.add(new PanelNodos(), BorderLayout.CENTER);
	    this.add(new PanelBotones(), BorderLayout.SOUTH);
	    
	    this.pack();
	    this.setVisible(true);
	}

	public static void main(String[] args) {
		Marco AbreVentana = new Marco();
	}
}