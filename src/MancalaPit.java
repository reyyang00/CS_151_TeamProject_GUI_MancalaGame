import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

/**
 * This class paints the pit
 * @author Rui Yang, Sandro Sallenbach, and Stefan Do
 * @version CS151 Dr. Kim
 *
 */
public class MancalaPit extends JComponent
{
	MancalaHole mancala;


	/**
	 * a constructor for MancalaPit
	 * and also calls the repint()
	 *
	 */
	 public MancalaPit()
	 {		 
		 mancala = new MancalaHole();
		 repaint();
	 
	 }

	/**
	 * a method to paint the look of pit
	 */
	 public void paintComponent(Graphics g)
	 {
		 Graphics2D g2 = (Graphics2D) g;
		 mancala.draw(g2);
	 }

}
