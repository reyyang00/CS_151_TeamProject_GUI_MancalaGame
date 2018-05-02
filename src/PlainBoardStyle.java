
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class PlainBoardStyle implements BoardFormatter {
	   @Override
	    public void setBoardStyle(MancalaBoard board) {
	        board.setTitle("Let's play some Mancala!");
	        board.getMancaA().setBackground(Color.GRAY);
	        board.getMancaB().setBackground(Color.GRAY);



	    }
	   
	   
	    public Color getColorA()
	    {
	    	return Color.BLACK;
	    }
	    
	    public Color getColorB()
	    {
	    	return Color.BLACK;
	    }
	    
	    public RectangularShape getShape()
	    {
	    	return new Ellipse2D.Double(18, 0, 45, 45);
	    }
	    
	    
	}
