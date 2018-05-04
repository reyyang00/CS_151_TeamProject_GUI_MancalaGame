import java.awt.*;
import java.awt.geom.Ellipse2D;
/**
 * a class contains stones
 *
 * @author Rui Yang, Sandro sallenbach, Stefan Do
 * @version since 5/1/2018 CS151 Dr.kim
 */

public class MancalaHole
{
	int numberOfStones; //stones inside each holder
	private int offset = 30;// a variable use to tell who wins the game
	
	 public MancalaHole()
	 {
		 numberOfStones = 0;
	 }


	/**
	 * a draw method to the stone itself
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
	 * a method to add stones
	 */

	 public void addStone()
	 {
		 numberOfStones++;
	 }

}
