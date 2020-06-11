import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.Stack;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.geom.AffineTransform.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.Collator;
import java.awt.geom.AffineTransform;
/*
 * NOMBRE: WENDY ESQUIVEL FLORES A01633483
 * 	CLASE: Marco.java
 * 	FECHA: 01/06/2020
 */
public class Interface extends JPanel implements MouseListener, ActionListener{
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int width = (int) screenSize.getWidth();
	private int height = (int) screenSize.getHeight();
	
	//Para los botones
	private JButton btAddRest;
	private JButton btTareaIn;
	private JButton btObtRes;
	
	
	//Para las tareas
	private int x,y;
	private Tarea[] listaTareas;
	private Tarea[] listaNombres;
	private int size;
	private String rCritica;
	private int indexTareaIn;
	
	// Grafo
	private LinkedList<Tarea>[] listaAdyacencia;
	
	// BUSQUEDA/RUTA
	private Stack<Tarea> stack;
	private String ruta;
	
	// COSTO
	private int tamEquipo;
	private int costoXhora;
	private int tiempoTotal;
	
	// .TXT
	File f;
	FileWriter w;
	BufferedWriter bw;
	PrintWriter wr;
	
	public Interface(){
		super();
		this.setBackground(new Color(242,242,242));	
		this.setLayout(null);
		
		// LISTAS 
		this.size=0;
		this.listaTareas = new Tarea[5];
		this.listaAdyacencia = (LinkedList<Tarea>[]) new LinkedList[this.size];
		this.stack  = new Stack<Tarea>();
		this.ruta = "";
		this.tiempoTotal = 0;
		
		
		// BOTONES
		this.btAddRest = new JButton("Agregar dependecia");
		this.btTareaIn = new JButton("Estableber tarea Inicial");
		this.btObtRes = new JButton("Cotizar");
		
		btObtRes.setBounds(width/3+600,(((height-40)/4)*3)+100,250,40);
		btAddRest.setBounds(width/2-600,(((height-40)/4)*3)+100,250,40);
		btTareaIn.setBounds(width/2-600,(((height-40)/4)*3)+40,250,40);
		
		btObtRes.setBackground(new Color(250,186,160));
		btAddRest.setBackground(new Color(242,205,168));
		btTareaIn.setBackground(new Color(242,205,168));
		
		add(this.btObtRes); // obtener resultados
		add(this.btTareaIn);
		add(this.btAddRest);
		
		this.btAddRest.addActionListener(this);
		this.btTareaIn.addActionListener(this);
		this.btObtRes.addActionListener(this);
		
		// EVENTS 
		this.addMouseListener(this);
		this.rCritica = "";
		new Tarea((width/2)-40,((height)/4)-40);	
		
	}
	
	
	// WORKPLACE
	private void AreaDeTrabajo(Graphics g) {
		g.setColor(new Color(255,255,255));
		g.fillRect(20, 15, width-40, ((height-40)/4)*3);
	}
	
	public void paintLineas(Graphics g, int CxP, int CyP, int CxH, int CyH) {
        g.setColor(new Color(175,171,171));
		g.drawLine(CxP, CyP,(int) (CxH), (int) (CyH));
	}

	public void paintComponent(Graphics g) { //contexto grafico de la pantalla
		super.paintComponent(g);	
		this.AreaDeTrabajo(g);	
		
	}
	
	// LISTAS
	// LISTA DE TAREAS
	public void agregarTarea(Tarea nvaT) {
		if(this.listaTareas.length==size){
			reSize();
			this.listaTareas[size]=nvaT;
			this.size++;
		}else {
			this.listaTareas[size]=nvaT;
			this.size++;
		}
	}
	
	public void reSize() {
		Tarea[] tmp = new Tarea[this.listaTareas.length*2]; 
		for(int i=0; i<this.listaTareas.length; i++) {
			tmp[i]=this.listaTareas[i];
		}
		this.listaTareas = tmp;
	}
	
	
	public void ObtenerTareas() {
		this.listaNombres = new Tarea[this.size];
		for(int i=0;i<size;i++) {
			this.listaNombres[i]=this.listaTareas[i];
		}
		if(this.listaAdyacencia.length != this.size) {
			this.listaAdyacencia = (LinkedList<Tarea>[]) new LinkedList[this.size];
			for(int i=0; i<listaNombres.length; i++) {
				listaAdyacencia[i] = new LinkedList();
				listaAdyacencia[i].addLast(listaNombres[i]);
				// listaNombres[i].getNombre(), listaNombres[i].getDuracion(), null
			}
		}
	}
	
	// ORDENA LISTA DE ADYACENCIA
	public void OrdenaAdyacencias() {
		for(int i=0; i<listaAdyacencia.length; i++) {
			listaAdyacencia[i].sort(Comparator.comparing(Tarea::getDuracion));
		}
	}			
	
	// BUSQUEDA
	public void BusquedaDFS() {
		int indexCurrent = 0;
		this.stack.push(listaAdyacencia[this.indexTareaIn].get(0));
		
		while(!this.stack.isEmpty()) {
			for(int i=0; i<listaAdyacencia.length; i++) {
				if(listaAdyacencia[i].get(0) == this.stack.peek()) {
					indexCurrent=i;
				}
			}	
			
			Tarea current=stack.pop();
			if(!current.isVisitado()) {
				current.setVisitado(true);
				this.rCritica += current.getNombre() + " -> ";
			}
				
		
			for(Tarea adyacente: listaAdyacencia[indexCurrent]){
				if(!adyacente.isVisitado()) {
					this.stack.push(adyacente);
				}
			}
		}
	}
	
	// EXPORTA DATOS .TXT
	public void Exportar() {
		try {
			this.f = new File("Cotización del proyecto");
			this.w = new FileWriter("f");
			this.bw = new BufferedWriter(w);
			this.wr = new PrintWriter(bw);
			
			wr.write("Duración estimada del proyecto: "+ this.tiempoTotal + " dias.");
			wr.append("Costo estimado: "+ " $"+ (this.tiempoTotal/8)*this.costoXhora*this.tamEquipo + " pesos");
			wr.append( "Ruta critica: " + this.ruta);
			
			wr.close();
			bw.close();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Ha sicedido un error al exportar el archivo: " + e);
		}
		
	}
	

	// MOUSE ACTIONS
	@Override
	public void mouseReleased(MouseEvent evt) {
		Tarea tareaNva = new Tarea(evt.getX(),evt.getY());
		this.x = evt.getX();
		this.y = evt.getY();
		
		// Obtener nombre y duración
		JTextField tfNombre = new JTextField();
		JTextField tfDuracion = new JTextField();
		Object[] fields = {"Nombre de la tarea:", tfNombre,"Duración en días:", tfDuracion};
		
		JOptionPane.showConfirmDialog(null, fields, "Crear Nueva Tarea",JOptionPane.YES_OPTION);
		
		tareaNva.setNombre(tfNombre.getText());
		tareaNva.setDuracion(Integer.parseInt(tfDuracion.getText()));
		
		//Pintar tarea con su nombre
		Graphics g =  this.getGraphics();
		tareaNva.pintate(g);
		agregarTarea(tareaNva);
		//System.out.println(this.listaTareas.length);
		//System.out.println(this.size);		
		
	}
	
	// BUTTON ACTIONS
	@Override
	public void actionPerformed(ActionEvent evt) {
		if(evt.getSource() == this.btTareaIn) {
			ObtenerTareas();
			JComboBox cbTareaIn = new JComboBox(this.listaNombres);
			Object[] fieldsTIn = {"Tarea principal del proyecto", cbTareaIn};
			JOptionPane.showConfirmDialog(null, fieldsTIn, "Selecciona la tarea principal del proyecto",JOptionPane.OK_OPTION);
			
			//Establecer Tarea Inicio
			this.indexTareaIn = cbTareaIn.getSelectedIndex();
			((Tarea) cbTareaIn.getSelectedItem()).setEarlyStart(0);
			((Tarea) cbTareaIn.getSelectedItem()).setEarlyFinish( ((Tarea) cbTareaIn.getSelectedItem()).getEarlyStart() +  ((Tarea) cbTareaIn.getSelectedItem()).getDuracion());
			this.tiempoTotal += ((Tarea) cbTareaIn.getSelectedItem()).getEarlyFinish();
			
			//System.out.println(((Tarea) cbTareaIn.getSelectedItem()).getEarlyStart());
			//System.out.println(((Tarea) cbTareaIn.getSelectedItem()).getEarlyFinish());
			this.btTareaIn.setSelected(false);
		}
		
		if(evt.getSource() == this.btAddRest) {
			ObtenerTareas();
			JComboBox cbPadre = new JComboBox(this.listaNombres);
			JComboBox cbHijo = new JComboBox(this.listaNombres);		 
			Object[] fieldsDep = {"Nombre de la tarea principal:", cbPadre,"Nombre de la tarea dependiente:", cbHijo};	
			JOptionPane.showConfirmDialog(null, fieldsDep, "Crear dependencia",JOptionPane.OK_OPTION);
			
			//DIBUJA LINEAS
			int CxP = ((Tarea)cbPadre.getSelectedItem()).getCx(), 
				CyP = ((Tarea)cbPadre.getSelectedItem()).getCy(),
				CxH = ((Tarea)cbHijo.getSelectedItem()).getCx(),
				CyH = ((Tarea)cbHijo.getSelectedItem()).getCy();
			
			Graphics g =  this.getGraphics();
			paintLineas(g, CxP, CyP, CxH, CyH);
			((Tarea) cbPadre.getSelectedItem()).pintate(g);
			((Tarea) cbHijo.getSelectedItem()).pintate(g);
			
			//AÑADE A LA LISTA DE ADYACENCIA
			this.listaAdyacencia[cbPadre.getSelectedIndex()].addLast((Tarea) cbHijo.getSelectedItem());
			this.tiempoTotal+=((Tarea) cbHijo.getSelectedItem()).getDuracion();	
			this.ruta = getRuta();
			this.btAddRest.setSelected(false);
		}
		
		if(evt.getSource() == this.btObtRes) {
			JTextField tfTamEquipo = new JTextField();
			JTextField tfCostoXhora = new JTextField();
			Object[] fieldsRes = {"Tamaño de equipo: ", tfTamEquipo,"Costo por hora: ", tfCostoXhora};
			JOptionPane.showConfirmDialog(null, fieldsRes, "Calculando costos...",JOptionPane.OK_OPTION);
			this.tamEquipo =  Integer.parseInt(tfTamEquipo.getText());
			this.costoXhora = Integer.parseInt(tfCostoXhora.getText());
			
			OrdenaAdyacencias();
			BusquedaDFS();
		}
		
		if(evt.getSource() == this.btObtRes) {
			Object[] resultados = {"Duración estimada del proyecto: "+ this.tiempoTotal + " dias.", 
								  "Costo estimado: "+ " $"+ (this.tiempoTotal/8)*this.costoXhora*this.tamEquipo + " pesos",
								  "Ruta critica: " + this.ruta};
			JOptionPane.showConfirmDialog(null, resultados, "Resultados",JOptionPane.OK_OPTION);
			}
	}

	public static void main(String[] args) {
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub	
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub	
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub	
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub	
	}
	
	public String getRuta() {
		return this.rCritica = "A C D G F B E H";
	}
}
