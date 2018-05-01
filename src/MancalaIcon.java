
import java.awt.*;
//import java.awt.Graphics;
//import java.awt.Graphics2D;

import javax.swing.Icon;

public class MancalaIcon implements Icon
{
    int test;

    private int width;
    private int height;
    private MancalaHole mancala;



    public MancalaIcon(MancalaHole mancala, int width, int height)
    {
        this.mancala = mancala;
        this.width = width;
        this.height = height;



    }

    public int getIconWidth()
    {
        return width;
    }

    public int getIconHeight()
    {
        return height;
    }

    public void paintIcon(Component c, Graphics g, int x, int y)
    {
        Graphics2D g2 = (Graphics2D) g;
        mancala.draw(g2);
    }

}

