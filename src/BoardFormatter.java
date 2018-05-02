import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;

// CS 151 - Project Group SSR
// BoardFormatter.java

public interface BoardFormatter
{
    public void setBoardStyle(MancalaBoard board);
    
    public Color getColorA();
    
    public Color getColorB();
    
    public RectangularShape getShape();
    
    
}
