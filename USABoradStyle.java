
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RectangularShape;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Concrete strategy that implements a different style for the Mancala Board
 * @author Rui Yang, Sandro Sallenbach, and Stefan Do 
 *
 */
public class USABoradStyle implements BoardFormatter {
	   @Override
	    public void setBoardStyle(MancalaBoard board) {
	        board.setTitle("USA!");
	        board.getMancaA().setBackground(Color.RED);
	        board.getMancaB().setBackground(Color.BLUE);

	    }
	   /**
	    * Gets the color blue for MancalaA
	    */
	    public Color getColorA()
	    {
	    	return Color.BLUE;
	    }
	    
	    /**
	     * Gets the color red for MancalaB
	     */
	    public Color getColorB()
	    {
	    	return Color.RED;
	    }
	    
	    /**
	     * Get a reference of the shape for the Mancala Board
	     */
	    public RectangularShape getShape()
	    {
	    	return new Ellipse2D.Double(18, 0, 45, 45);
	    }



}

