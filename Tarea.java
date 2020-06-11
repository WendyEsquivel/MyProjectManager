import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;
import java.util.Stack;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
/*
 * NOMBRE: WENDY ESQUIVEL FLORES A01633483
 * 	CLASE: Marco.java
 * 	FECHA: 01/06/2020
 */
public class Tarea{
	private String nombre;
	private Tarea next; 		//Nodo
	private boolean visitado;
	private int duracion,		
		earlyStart,
		earlyFinish,
		lastStart,
		lastFinish,
		
		Cx, 		//Centro en x
		Cy; 		//Centro en y
	
	private Color color;
	private Font fuente = new Font("Calibri Light", Font.PLAIN, 13);
	
	public Tarea(int Cx, int Cy){
		this.Cx = Cx;
		this.Cy = Cy;
		this.visitado = false;
		
		
		Random ran = new Random();
		this.color=new Color(ran.nextInt(256),ran.nextInt(256),ran.nextInt(256));
	}
	
	public Tarea(String nombre, int duracion, Tarea next) {
		this.nombre = nombre;
		this.duracion = duracion;
		this.next = next;
	}
	
	public Tarea(String nombre, int duracion) {
		this(nombre, duracion,null);
	}
	
	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public int getDuracion() {
		return duracion;
	}
	
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	
	public int getEarlyStart() {
		return earlyStart;
	}

	public void setEarlyStart(int earlyStart) {
		this.earlyStart = earlyStart;
	}

	public int getEarlyFinish() {
		return earlyFinish;
	}

	public void setEarlyFinish(int earlyFinish) {
		this.earlyFinish = earlyFinish;
	}

	public int getLastStart() {
		return lastStart;
	}

	public void setLastStart(int lastStart) {
		this.lastStart = lastStart;
	}

	public int getLastFinish() {
		return lastFinish;
	}

	public void setLastFinish(int lastFinish) {
		this.lastFinish = lastFinish;
	}

	public int getCx() {
		return Cx;
	}

	public int getCy() {
		return Cy;
	}
	
	public Tarea getNext() {
		return next;
	}

	public void setNext(Tarea next) {
		this.next = next;
	}

	public boolean isVisitado() {
		return visitado;
	}

	public void setVisitado(boolean visitado) {
		this.visitado = visitado;
	}

	public void pintate(Graphics g){
		
		g.setColor(this.color);
		g.fillOval(Cx-40, Cy-40, 75, 75);
		//System.out.println(this.Cx+","+this.Cy);
		
		String texto = this.nombre + ": " + this.duracion + " días";
		FontMetrics metrics = g.getFontMetrics();
		double wTexto = metrics.getStringBounds(texto,g).getWidth();
		
		g.setColor(new Color(242,242,242));
		g.fillRect((int) ((int) this.Cx-wTexto/2), ((int) this.Cy+metrics.getMaxAscent()/2), (int) wTexto, metrics.getMaxAscent());
		
		g.setFont(this.fuente);
		g.setColor(color.BLACK);
		g.drawString(texto, (int) ((int) this.Cx-wTexto/2)+3, ((int) this.Cy+metrics.getMaxAscent()+3));
		
	}
	
	public String toString() {
		return this.nombre;
	}
    
}