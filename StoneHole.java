
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;

import javax.swing.Icon;

/** This class is to design the stones for the Mancala Board
 * @author Rui Yang, Sandro Sallenbach, and Stefan Do
 * @version CS151 Dr. Kim
 */
public class StoneHole
{

	private int numberOfStones;
	private Color colorA;
	private Color colorB;
	private RectangularShape shape = new Ellipse2D.Double(18, 0, 45, 45);
	
//	private Color = Color.Red;
	
	/**
	 * Sets the design of the stones for each Mancala Board 
	 * @param a Color for MancalaA
	 * @param b Color for MancalaB
	 * @param s Shape for the stones 
	 */
	public void setSyle(Color a, Color b, RectangularShape s)
	{
		colorA = a;
		colorB = b;
		shape = s;
	}
	
	/**
	 * Initializing the number of stones to 0
	 */
	 public StoneHole()
	 {
		 numberOfStones = 0;																									/// CHANGE LATER
	 }
	 
	 /**
	  * Drawing the stones 
	  */
	 public void draw(Graphics2D g2)
	 {
         g2.setStroke(new BasicStroke(2));
         
         g2.setColor(colorA);
           
         g2.draw(shape);
           
         g2.setColor(colorB);



		 if(numberOfStones == 0)
		 {}
		 else if (numberOfStones == 1)
		 {
			 draw1(g2);
		 }
		 else if (numberOfStones == 2)
		 {
			 draw2(g2);
		 }
		 else if(numberOfStones == 3)
		 {
			 draw3(g2);
		 }
		 else if(numberOfStones == 4)
		 {
			 draw4(g2);
		 }
		 else if(numberOfStones == 5)
		 {
			 draw5(g2);
		 }
		 else if(numberOfStones == 6)
		 {
			 draw6(g2);
		 }
		 else if (numberOfStones > 6)
		 {
			 drawMore(g2);
		 }
		 
		 

	 }
	 
	 /**
	  * Getter for the number of stones
	  * @return Number of stones
	  */
	 public int getNbStones()
	 {
	
		 return numberOfStones;
	 }
	 
	 /**
	  * Setter for the number of stones
	  * @param nbStones Number of stones
	  */
	 public void setNbStones(int nbStones)
	 {
		 numberOfStones = nbStones;
	 }
	 
	 /**
	  * Increments the number of stones
	  */
	 public void addStone()
	 {
		 numberOfStones++;
	 }
	 
     /**
      * Draws 1 stone
      */
	 private void draw1(Graphics2D g2)
	 {
			Ellipse2D.Double point1 = new Ellipse2D.Double(28, 10, 4, 4);
			 g2.draw(point1);
			 g2.fill(point1);
	 }
	 
	 /**
	  * Draws 2 stones
	  */
	 private void draw2(Graphics2D g2)
	 {
			Ellipse2D.Double point1 = new Ellipse2D.Double(28, 10, 4, 4);
			 Ellipse2D.Double point2 = new Ellipse2D.Double(28, 25, 4, 4);

		 
			 g2.draw(point1);
			 g2.fill(point1);
			 g2.draw(point2);
			 g2.fill(point2);

	 }
	 
	 /**
	  * Draws 3 stones
	  */
	 private void draw3(Graphics2D g2)
	 {
			Ellipse2D.Double point1 = new Ellipse2D.Double(28, 10, 4, 4);
			 Ellipse2D.Double point2 = new Ellipse2D.Double(28, 25, 4, 4);
			 Ellipse2D.Double point3 = new Ellipse2D.Double(43, 10, 4, 4);
		 
			 g2.draw(point1);
			 g2.fill(point1);
			 g2.draw(point2);
			 g2.fill(point2);
			 g2.draw(point3);
			 g2.fill(point3);

	 }
	 
	 /**
	  * Draws 4 stones
	  */
	 private void draw4(Graphics2D g2)
	 {
		Ellipse2D.Double point1 = new Ellipse2D.Double(28, 10, 4, 4);
		 Ellipse2D.Double point2 = new Ellipse2D.Double(28, 25, 4, 4);
		 Ellipse2D.Double point3 = new Ellipse2D.Double(43, 10, 4, 4);
		 Ellipse2D.Double point4 = new Ellipse2D.Double(43, 25, 4, 4);
	 
		 g2.draw(point1);
		 g2.fill(point1);
		 g2.draw(point2);
		 g2.fill(point2);
		 g2.draw(point3);
		 g2.fill(point3);
		 g2.draw(point4);
		 g2.fill(point4);
	 }
	 
	 /**
	  * Draws 5 stones
	  * @param g2
	  */
	 private void draw5(Graphics2D g2)
	 {
			Ellipse2D.Double point1 = new Ellipse2D.Double(28, 10, 4, 4);
			 Ellipse2D.Double point2 = new Ellipse2D.Double(28, 25, 4, 4);
			 Ellipse2D.Double point3 = new Ellipse2D.Double(43, 10, 4, 4);
			 Ellipse2D.Double point4 = new Ellipse2D.Double(43, 25, 4, 4);
			 Ellipse2D.Double point5 = new Ellipse2D.Double(32, 17, 4, 4);

			 g2.draw(point1);
			 g2.fill(point1);
			 g2.draw(point2);
			 g2.fill(point2);
			 g2.draw(point3);
			 g2.fill(point3);
			 g2.draw(point4);
			 g2.fill(point4);
			 g2.draw(point5);
			 g2.fill(point5);
	 }
	 
	 /**
	  * Draws 6 stones 
	  */
	 private void draw6(Graphics2D g2)
	 {
			Ellipse2D.Double point1 = new Ellipse2D.Double(25, 10, 4, 4);
			 Ellipse2D.Double point2 = new Ellipse2D.Double(25, 20, 4, 4);
			 Ellipse2D.Double point3 = new Ellipse2D.Double(25, 30, 4, 4);
			 Ellipse2D.Double point4 = new Ellipse2D.Double(43, 10, 4, 4);
			 Ellipse2D.Double point5 = new Ellipse2D.Double(43, 20, 4, 4);
			 Ellipse2D.Double point6 = new Ellipse2D.Double(43, 30, 4, 4);

			 g2.draw(point1);
			 g2.fill(point1);
			 g2.draw(point2);
			 g2.fill(point2);
			 g2.draw(point3);
			 g2.fill(point3);
			 g2.draw(point4);
			 g2.fill(point4);
			 g2.draw(point5);
			 g2.fill(point5);
			 g2.draw(point6);
			 g2.fill(point6);
	 }

	 /**
	  * Draws more than 6 stones
	  */
	 private void drawMore(Graphics2D g2)
	 {
			Ellipse2D.Double point1 = new Ellipse2D.Double(25, 10, 4, 4);
			 Ellipse2D.Double point2 = new Ellipse2D.Double(25, 18, 4, 4);
			 Ellipse2D.Double point3 = new Ellipse2D.Double(25, 26, 4, 4);
			 Ellipse2D.Double point4 = new Ellipse2D.Double(35, 10, 4, 4);
			 Ellipse2D.Double point5 = new Ellipse2D.Double(35, 18, 4, 4);
			 Ellipse2D.Double point6 = new Ellipse2D.Double(35, 26, 4, 4);

			 g2.draw(point1);
			 g2.fill(point1);
			 g2.draw(point2);
			 g2.fill(point2);
			 g2.draw(point3);
			 g2.fill(point3);
			 g2.draw(point4);
			 g2.fill(point4);
			 g2.draw(point5);
			 g2.fill(point5);
			 g2.draw(point6);
			 g2.fill(point6);
			 
			 g2.drawString(Integer.toString(numberOfStones), 45, 30);

	 }

	 
	 
	 

}
