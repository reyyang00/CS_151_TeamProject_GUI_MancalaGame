import java.awt.*;
//import java.awt.Point;
//import java.awt.event.MouseAdapter;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JComponent;


/** The PitComponent holds the stones.
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
     */
    public PitComponent(MancalaModel data, int id) {
        this.id = id;
        dataModel = data;
        hole = new StoneHole();
        MouseListeners listeners = new MouseListeners();
        addMouseListener(listeners);
    }

    /**
     * MouseListener class with a method that updates the Mancala for each player
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
                   System.out.println("Mancala A:" + dataModel.getManA().mancala.numberOfStones);						// JUST FOR TESTING
                   System.out.println("Mancala B:" + dataModel.getManB().mancala.numberOfStones);						// JUST FOR TESTING


                   int stonesToDistribute = hole.getNbStones();

                   dataModel.update(id, stonesToDistribute, true);

                   repaint();
     		   }
            }
        }


    }
//    public void copy(){
//
//        dataModel.getData().get()
//    }


    /**
     * Draws the Mancala pits for each player
     */

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        hole.draw(g2);
    }


}
