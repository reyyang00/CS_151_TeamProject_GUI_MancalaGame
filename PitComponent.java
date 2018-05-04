// CS 151 - Project Group SSR
// PitComponent.java

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.JComponent;


/** The PitComponent holds the StoneHole objects and delegates drawing to those objects.
 * @author Rui Yang, Sandro Sallenbach, and Stefan Do
 * @version CS151 Dr. Kim
 */
public class PitComponent extends JComponent
{
    int id;
    StoneHole hole;
    MancalaModel dataModel;
    ArrayList<PitComponent> previousStatus = new ArrayList<PitComponent>();
    final int STATUSCOUNTER = 1;
    int mo;

    
    /**
     * Constructor that references the Model MancalaModel
     * @param data, a reference to the MancalaModel
     * @param id, the index number of the pit
     */
    public PitComponent(MancalaModel data, int id) {
        this.id = id;
        dataModel = data;
        hole = new StoneHole();
        MouseListeners listeners = new MouseListeners();
        addMouseListener(listeners);
    }

    
    /**
     * MouseListener class with a method that updates the Mancala for each player.
     * Includes a mousePressed method, that activates when the mouse is pressed within one of the pit components.
     *
     */
    private class MouseListeners extends MouseAdapter {
        public void mousePressed(MouseEvent event) {
        	
            if(dataModel.initializationDone == true && hole.getNbStones() > 0)
            {
            	if(dataModel.playerTurn == 2)	// first turn of the game, 
            	{
            		if(id < 6)
            			dataModel.playerTurn = 1;
            		else
            			dataModel.playerTurn = 0;
            	}
            	
     		   if((dataModel.playerTurn == 1 && id < 6) || (dataModel.playerTurn == 0 && id > 5))
     		   {
            	
            		// update the move
 //           		System.out.println("Mancala A:" + dataModel.getManA().mancala.numberOfStones);						// JUST FOR TESTING
 //         		System.out.println("Mancala B:" + dataModel.getManB().mancala.numberOfStones);						// JUST FOR TESTING
	
	
            		int stonesToDistribute = hole.getNbStones();

		            dataModel.update(id, stonesToDistribute);
		
		            repaint();
     		   }
            }
        }


    }

    /**
     * Delegates drawing to the StoneHole objects
     */
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        hole.draw(g2);
    }


}
