// CS 151 - Project Group SSR
// BoardFormatter.java

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;

/**
 * This is an interface class used for the strategy pattern.
 *
 * @author Rui Yang, Sandro sallenbach, Stefan Do
 * @version since 5/1/2018 CS151 Dr.kim
 */


public interface BoardFormatter
{
	
    /**
     * this method set the look of the window of MancalaBoard
     * @param board takes in a MancalaBorad type and set its look
     */
    public void setBoardStyle(MancalaBoard board);
    
    /**
     * this method defines a specific color for the look of the board
     * @ return a Color
     */
    public Color getColorA();
    
    /**
     * this method defines a specific color for the look of the board
     * @ return a Color
     */
    public Color getColorB();
    
    
    /**
     * this method defines what shape the boards' pits will be
     * @ return a RectangularShape
     */
    public RectangularShape getShape();
    
    
}
