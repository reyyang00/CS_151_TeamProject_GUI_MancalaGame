// CS 151 - Project Group SSR
// MancalaModel.java


import java.util.ArrayList;
import javax.swing.event.*;


/**
 * The ManacalaModel represents the [M]odel of the MVC pattern.
 */
public class MancalaModel {


    private int playerUndotime;

    ArrayList<PitComponent> data = new ArrayList<>();
    ArrayList<PitComponent> previousState = new ArrayList<>(12);

    ArrayList[] status = new ArrayList[3];
    MancalaPit manA = new MancalaPit();
    MancalaPit previousManA = new MancalaPit();
    MancalaPit manB = new MancalaPit();
    MancalaPit previousManB = new MancalaPit();

    ArrayList listeners;
    int numberStones;
    int boardStyle;
    int playerTurn = 2;			// stores which players turn it is. While it's player A's turn, the pits of player B are deactivated. 0=PlayerA, 1=PlayerB, 2=Game not initialized
    boolean initializationDone = false;
    boolean playerGoesAgain = false;


    public MancalaPit getManA() {
        return manA;
    }

    public void setManA(MancalaPit manA) {
        this.manA = manA;
    }

    public MancalaPit getManB() {
        return manB;
    }

    public void setManB(MancalaPit manB) {
        this.manB = manB;
    }

    public int getPlayerUndotime() {
        return playerUndotime;
    }

    public void setPlayerUndotime(int playerUndotime) {
        this.playerUndotime = playerUndotime;
    }

    public void increasePlayerUndotime() {
        if (this.playerUndotime == 3) {
            this.playerUndotime = 0;
        } else
            this.playerUndotime++;

    }



    public void addHole(PitComponent pitComponent) {
        data.add(pitComponent);
        previousState.add(new PitComponent(pitComponent.dataModel, pitComponent.id));
    }


    /**
     * Constructs a MancalaModel object
     */
    public MancalaModel() {
        listeners = new ArrayList();
        playerUndotime = 0;

    }

    /**
     * Returns the current state of the MancalaModel data.
     *
     * @return the data in an ArrayList
     */
    public ArrayList getData() {
        return (ArrayList) data.clone();
    }


    /**
     * Attach a listener to the Model
     *
     * @param c the listener
     */
    public void attach(ChangeListener c) {
        listeners.add(c);
    }


    
    public void updateStatus()
    {

    	for(int i = 0; i < previousState.size(); i++)
    	{
        	previousState.get(i).hole.setNbStones(data.get(i).hole.getNbStones());
    	}
    	
    	previousManA.mancala.numberOfStones = manA.mancala.numberOfStones;
    	previousManB.mancala.numberOfStones = manB.mancala.numberOfStones;



    }
    
    
    
    /**
     * Change the data in the model at a particular location
     *
     * @param location the index of the field to change
     * @param value    the new value
     */
    public void update(int location, int value)
    {
    	updateStatus();
    	
    	
	 	   if(location < 6)				// a pit of Player B was selected
	 	   {			   
	 		   
		 	   int stop = location-value;
		 	   for(int i = location-1; i >= stop && i >=0; i--) //
		 	   {
		 			   
		 		   data.get(i).hole.addStone();
		 		   
		 		   value--;
		 	   }
		 	   if(value > 0)							////&& playerTurn == 1   we dont fill the oponents mancala!
		 	   {
		 		   manB.mancala.addStone();
		 		   value--;
		 		   
		 		   if(value == 0)
		 		   {
		 			   playerGoesAgain = true;
			 		   playerTurn = changePlayerOrder(playerTurn);

		 		   }
		 	   }
		 	   if(value > 0)
		 	   {
		 		   if(value < 7)
		 		   {
			 		   for(int i = 6; i < 6+value; i++)
			 		   {
			 				   
			 			   data.get(i).hole.addStone();
			 		   }
		 		   }
		 		   else
		 		   {
		 			   data.get(6).hole.addStone();					// if there is so many stones, it will overflow the bottom row and go around
		 			   update(6, value-1);
		 		   }
		 	   }
	 		   playerTurn = changePlayerOrder(playerTurn);

	
	 	   
	 	   }
	 	   else					// a pit of Player A was selected
	 	   {
	 		   
	
	 		   int counter = value;
	 		   for(int i = location+1; i <= location+value && i < 12; i++)
	 		   {
		 			   
	 			   data.get(i).hole.addStone();
	 			   counter--;
	 		   }
	 		   if(counter > 0)
	 		   {
		 			   
	 			   manA.mancala.addStone();
	 			   counter--;
	 			   
	 			   if(counter == 0)
	 			   {   playerTurn = changePlayerOrder(playerTurn);
	 			   		playerGoesAgain = true;

	 			   }

		
	 		   }
	 		   if(counter > 0)
	 		   {
	 			   if(counter < 7)
	 			   {
	 			   		for(int i = 5; i > 5-counter && i >=0; i--) //
	 			   		{
		 				   
	 			   			data.get(i).hole.addStone();
	 			   		}
	 			   }
		 		   else
		 		   {
		 			   data.get(5).hole.addStone();					// if there is so many stones, it will overflow the bottom row and go around
		 			   update(5, counter-1);
		 		   }
	 			  

	 		   }
	 		   playerTurn = changePlayerOrder(playerTurn);

	
			    		   
	 	   }
	
	
	 	   
	 	   	   
	// 	   for(int i = 0; i < listeners.size(); i++)
	// 	   {
	 		   ChangeListener l = (ChangeListener) listeners.get(0);
	 		   l.stateChanged(new ChangeEvent(this));
	          
	// 	   } 
		
    }

    
    /**
	checks whether the last stone was dropped in an empty pit of own side
    */
   private int checkDropInEmpty(int current)
   {
   	if(current == 1)
   		return 0;
   	else
   		return 1;
   		
   }
    
    
    
    /**
     changes from player A to player B and vice versa. used in the update-method
     */
    private int changePlayerOrder(int current)
    {
    	if(current == 1)
    		return 0;
    	else
    		return 1;
    		
    }
    
    
    /**
     * XXXX
     */
    public void undoFuntion(int playerMove)
    {
    	for(int i = 0; i < previousState.size(); i++)
    	{
        	data.get(i).hole.setNbStones(previousState.get(i).hole.getNbStones());
    	}
    	
    	manA.mancala.numberOfStones = previousManA.mancala.numberOfStones;
    	manB.mancala.numberOfStones = previousManB.mancala.numberOfStones;

    	if(playerGoesAgain == false)
    	{   playerTurn = changePlayerOrder(playerTurn);
    	}
    	
		playerGoesAgain = false;

        ChangeListener l = (ChangeListener) listeners.get(0);
        l.stateChanged(new ChangeEvent(this));
    }


    /**
     * XXXX
     */
    public void initializeStones() {
        for (int i = 0; i < 12; i++) {
            data.get(i).hole.setNbStones(numberStones);

//	        ChangeListener l = (ChangeListener) listeners.get(i);

//	        l.stateChanged(new ChangeEvent(this));
        }

        ChangeListener l = (ChangeListener) listeners.get(0);

        l.stateChanged(new ChangeEvent(this));


        // do something
    }


}












   
   