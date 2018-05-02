import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Concrete strategy with another style for the Mancala Board
 * @author Rui Yang, Sandro Sallenbach, and Stefan Do
 * @version CS151 Dr. Kim
 */
public class SJSUBoradStyle implements BoardFormatter {
    @Override
    public void setBoardStyle(MancalaBoard board) {
        board.setTitle("Go Spartans!");
        board.getMancaA().setBackground(Color.YELLOW);
        board.getMancaB().setBackground(Color.BLUE);
        
        

        
 //       board.playerFont = new Font("Helvetica", Font.BOLD, 20);
        
 //       Font playerFont = new Font("Bahnschrift", Font.PLAIN, 20);


//        BufferedImage myPicture = null;
//        try {
//            myPicture = ImageIO.read(new File("src/resource/sjsu.gif"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
//        board.getSouthPanel().add(picLabel);

    }
    
    /**
     * Gets the color blue for MancalaA
     */
    public Color getColorA()
    {
    	return Color.BLUE;
    }
    
    /**
     * Gets the color gray for MancalaB
     */
    public Color getColorB()
    {
    	return Color.GRAY;
    }
    
    /**
     * Get a reference of the shape for the Mancala Board
     */
    public RectangularShape getShape()
    {
    	return new Rectangle2D.Double(18, 0, 45, 45);
    }
    
}