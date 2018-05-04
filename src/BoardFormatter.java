import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;

/**
 * This a interface class used for the strategy pattern.
 *
 * @author Rui Yang, Sandro sallenbach, Stefan Do
 * @version since 5/1/2018 CS151 Dr.kim
 */


public interface BoardFormatter
{

    /**this method set the look of the window of MancalaBoard
     * @param board takes in a MancalaBorad type and set its look
     */

    public void setBoardStyle(MancalaBoard board);


    /**this method helps to set the look of window
     */
    public Color getColorA();

    /**this method helps to set the look of window
     */
    public Color getColorB();

    /**this method helps to set the look of window
     */
    public RectangularShape getShape();
    
    
}
