// CS 151 - Project Group SSR
// USABoardStyle.java


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RectangularShape;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Concrete strategy containing a more advanced style for the Mancala board
 * Implements the BoardFormatter interface.
 *
 * @author Rui Yang, Sandro Sallenbach, and Stefan Do
 * @version CS151 Dr. Kim
 */
public class USABoradStyle implements BoardFormatter {
	   @Override
	    public void setBoardStyle(MancalaBoard board) {
	        board.setTitle("USA!");
	        board.getMancaA().setBackground(Color.RED);
	        board.getMancaB().setBackground(Color.BLUE);

	    }

	    public Color getColorA()
	    {
	    	return Color.BLUE;
	    }
	    
	    public Color getColorB()
	    {
	    	return Color.RED;
	    }
	    
	    public RectangularShape getShape()
	    {
	    	return new Ellipse2D.Double(18, 0, 45, 45);
	    }



}

