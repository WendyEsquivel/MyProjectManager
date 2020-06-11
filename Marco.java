import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
/*
 * NOMBRE: WENDY ESQUIVEL FLORES A01633483
 * 	CLASE: Marco.java
 * 	FECHA: 01/06/2020
 */	
public class Marco extends JFrame{
	
	public Marco(){
		super("Project Manager");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		//this.setUndecorated(true); Lo hace full pero no salen opciones
		
	    this.add(new Interface(), BorderLayout.CENTER);
	    
	    this.pack();
	    this.setVisible(true);
	}

	public static void main(String[] args) {
		Marco AbreVentana = new Marco();
	}
	
	//LINKS DE APOYO
	/* https://stackoverflow.com/questions/27706197/how-can-i-center-graphics-drawstring-in-java
	 * https://docs.oracle.com/javase/7/docs/api/java/awt/FontMetrics.html
	 * https://docs.oracle.com/javase/7/docs/api/java/util/LinkedList.html
	 * CLASS JComboBox
	 * CLASS Stack
	 * https://mkyong.com/java8/java-8-lambda-comparator-example/
	 */
}