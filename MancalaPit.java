// CS 151 - Project Group SSR
// MancalaPit.java


import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;


/**
 * This class holds the MancalaHole objects. It delegates the drawing to those objects.
 * @author Rui Yang, Sandro Sallenbach, and Stefan Do
 * @version CS151 Dr. Kim
 *
 */
public class MancalaPit extends JComponent
{
	MancalaHole mancala;

	
	/**
	 * The constructor for MancalaPit
	 *
	 */
	 public MancalaPit()
	 {		 
		 mancala = new MancalaHole();
		 repaint();
	 
	 }
	 
	/**
	 * This method calls the MancalaHoles to draw themselves
	 */
	 public void paintComponent(Graphics g)
	 {
		 Graphics2D g2 = (Graphics2D) g;
		 mancala.draw(g2);
	 }

}
