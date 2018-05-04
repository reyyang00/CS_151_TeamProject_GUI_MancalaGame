import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
/**Concrete strategy containing a SJSU-themed style for the Mancala board
 * @author Rui Yang, Sandro Sallenbach, and Stefan Do
 *
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
     * Gets the color blue to style the MancalaBoard
     */
    public Color getColorA()
    {
    	return Color.BLUE;
    }

    /**
     * Gets the color blue to style the MancalaBoard
     */
    public Color getColorB()
    {
    	return Color.GRAY;
    }

    /**
     * Gets a reference of the shape for the style of the MancalaBoard
     */
    
    public RectangularShape getShape()
    {
    	return new Rectangle2D.Double(18, 0, 45, 45);
    }
    
}