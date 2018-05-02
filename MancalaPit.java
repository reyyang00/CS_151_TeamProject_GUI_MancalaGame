import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;
/**
 * This class contains the pits for the mancala board
 * @author Rui Yang, Sandro Sallenbach, and Stefan Do
 * @version CS151 Dr. Kim
 *
 */
public class MancalaPit extends JComponent
{
	MancalaHole mancala;

	

	 public MancalaPit()
	 {		 
		 mancala = new MancalaHole();
		 repaint();
	 
	 }
	 

	 public void paintComponent(Graphics g)
	 {
		 Graphics2D g2 = (Graphics2D) g;
		 mancala.draw(g2);
	 }

}
