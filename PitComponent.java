import java.awt.*;
//import java.awt.Point;
//import java.awt.event.MouseAdapter;
import java.awt.event.*;

import javax.swing.JComponent;

public class PitComponent extends JComponent
{
	 int id;
	 StoneHole hole;
	 MancalaModel dataModel;

	 public PitComponent(MancalaModel data, int id)
	 {
		 this.id = id;
		 dataModel = data;
		 hole = new StoneHole();
		 MouseListeners listeners = new MouseListeners();
		 addMouseListener(listeners);
	 }
	 private class MouseListeners extends MouseAdapter 
	 {
		 public void mousePressed(MouseEvent event)
		 {
			 dataModel.update(id, hole.getNbStones());
			 hole.setNbStones(0);

			 repaint();

		 }


	 }
	 
	 public void paintComponent(Graphics g)
	 {
		 Graphics2D g2 = (Graphics2D) g;
		 hole.draw(g2);
	 }
	 

}
