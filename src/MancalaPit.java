import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

public class MancalaPit extends JComponent
{
    MancalaHole mancala;



    public MancalaPit()
    {
        mancala = new MancalaHole();
        repaint();

    }


    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        mancala.draw(g2);
    }



}