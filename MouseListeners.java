import java.awt.Point;
import java.awt.event.*;
/**
 * MouseListener class required for actions requiring player to press the mouse 
 * @author Rui Yang, Sandro Sallenbach, and Stefan Do
 * @version CS151 Dr. Kim 
 *
 */
public class  MouseListeners extends MouseAdapter
{
	
	public void mousePressed(MouseEvent event)
	{
		Point mousePoint = event.getPoint();
//		for (SceneShape s : shapes)
		{
//			if (s.contains(mousePoint))
//				s.setSelected(!s.isSelected());
		}
		//repaint();
	}
	
	
}
