import java.awt.Point;
import java.awt.event.*;

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
