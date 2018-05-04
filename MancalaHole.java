// CS 151 - Project Group SSR
// MancalaHole.java


import java.awt.*;
import java.awt.geom.Ellipse2D;
import javax.swing.Icon;


/**
 * A class to represent the mancala holes (pits). It is also responsible for drawing the stones.
 *
 * @author Rui Yang, Sandro sallenbach, Stefan Do
 * @version since 5/1/2018 CS151 Dr.kim
 */
public class MancalaHole
{
	int numberOfStones;
	private int offset = 30;
	
	/**
	* The constructor sets the number of stones to 0.
	*/
	 public MancalaHole()
	 {
		 numberOfStones = 0;
	 }
	 
	 
	/**
	 * The draw method to draw the stones.
	 * @param g2 a type Graphics2D
	 */
	 public void draw(Graphics2D g2)
	 {
         g2.setStroke(new BasicStroke(4));

		 Ellipse2D.Double hole = new Ellipse2D.Double(10, 20, 50, 180);
		
		 g2.draw(hole);
		 
		 if(numberOfStones > 4)
		 g2.drawString(Integer.toString(numberOfStones), 40, 110);
		 
		 for(int i = 0; i < numberOfStones; i++)
		 {
				Ellipse2D.Double point1 = new Ellipse2D.Double(offset, 30+9*i, 3, 3);

			 if(i > 18)
			 {
					point1 = new Ellipse2D.Double(offset+10, 30+9*(i-18), 3, 3);

			 }
			 
				g2.draw(point1);
				g2.fill(point1);
		 }

	 }
	 
	/**
	 * This method adds a stone.
	 */
	 public void addStone()
	 {
		 numberOfStones++;
	 }

}
