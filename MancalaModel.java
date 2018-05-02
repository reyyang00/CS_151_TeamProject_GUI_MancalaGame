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
    boolean didUndo = false;


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
    public void update(int location, int value)				// value is the number of stones to be distributed
    {
    	updateStatus();
    	
    	
        data.get(location).hole.setNbStones(0);

	 	   if(location < 6)				// a pit of Player B was selected
	 	   {			   
	 		   
		 	   int stop = location-value;
		 	   for(int i = location-1; i >= stop && i >=0; i--) //
		 	   {
		 			if(value == 1)
		 			{
		 				data.get(i).hole.addStone();
		 				checkDropInEmpty(i);
		 			}
		 			else
		 			{
		 				data.get(i).hole.addStone();
		 			}
		 			
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
			 		   didUndo = false;

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
	 		   didUndo = false;

	
	 	   
	 	   }
	 	   else					// a pit of Player A was selected
	 	   {
	 		   int counter = value;
	 		   for(int i = location+1; i <= location+value && i < 12; i++)
	 		   {
		 			
		 			if(counter == 1)
		 			{
		 				data.get(i).hole.addStone();
		 				checkDropInEmpty(i);
		 			}
		 			else
		 			{
		 				data.get(i).hole.addStone();
		 			}
		 			
	 			   
	 			   counter--;
	 		   }
	 		   if(counter > 0)
	 		   {
		 			   
	 			   manA.mancala.addStone();
	 			   counter--;
	 			   
	 			   if(counter == 0)
	 			   {   playerTurn = changePlayerOrder(playerTurn);
	 			   		playerGoesAgain = true;
				 		didUndo = false;


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
	 		   didUndo = false;

	
			    		   
	 	   }
	
	
	 	   
	 	   
 		   
	 	   int endOfGame = checkEndOfGameCondition();
	 	   
	 	   if(endOfGame > 0)
	 	   {
	 		   playerTurn = 3;		// end of game. disable all buttons
	 		   
		 	   if(endOfGame == 1)
		 		   System.out.println("PLAYER A WINS");
		 	   if(endOfGame == 2)
		 		   System.out.println("PLAYER B WINS");
		 	   if(endOfGame == 3)
		 		   System.out.println("Its a tie");
	 	   }
 	
 	
	 	   	   
	// 	   for(int i = 0; i < listeners.size(); i++)
	// 	   {
	 		   ChangeListener l = (ChangeListener) listeners.get(0);
	 		   l.stateChanged(new ChangeEvent(this));
	          
	// 	   } 
	 		   


		
    }

    
    
    /**
	checks whether end of game condition is met
   */
   private int checkEndOfGameCondition()
   {

	   
	   boolean allZero = true;
	   for(int i =0; i < 6 && allZero == true; i++)
	   {
		   if(data.get(i).hole.getNbStones() > 0)
		   {	allZero = false;}
	   }
	   
	   if(allZero)
	   {				//Player B's side is all zero
		   System.out.println("CLEAN UP SIDE OF 6");
		   cleanUpSide(6);
		   
	   }
	   
	   allZero = true;
	   for(int i = 6; i < 12 && allZero == true; i++)
	   {
		   if(data.get(i).hole.getNbStones() > 0)
		   {	allZero = false;}
	   }
	   
	   if(allZero)
	   {				//Player A's side is all zero
		   
		   System.out.println("CLEAN UP SIDE OF 0");

		   cleanUpSide(0);
	   }
	   
	   
	   
	   
	   
	   int stonesNeededForWin = numberStones*6;
	   
	   if(manA.mancala.numberOfStones > stonesNeededForWin)
		   return 1;			// Player A wins
	   
	   if(manB.mancala.numberOfStones > stonesNeededForWin)
		   return 2;			// Player B wins
	   
	   
	   if(manA.mancala.numberOfStones == stonesNeededForWin && manB.mancala.numberOfStones == stonesNeededForWin)
	   {
		   return 3;
	   }
	   
	   
	   return 0;
   		
   }
   
   
   /**
	one side of pits is all empty, clean up the other side
   */
  private void cleanUpSide(int startPit)
  {
	  int counter = 0;
	   for(int i = startPit; i < startPit+6; i++)
	   {
		   counter += data.get(i).hole.getNbStones();		// count total of stones
		   
		   data.get(i).hole.setNbStones(0);					// empty the pit
	   }
	   
	   if(startPit == 0)								// add cleaned up stones to mancala
	   {
		   manB.mancala.numberOfStones += counter;
	   }
	   else
	   {
		   manA.mancala.numberOfStones += counter;
	   }
	  

	   
  }
  
  
    
    /**
	checks whether the last stone was dropped in an empty pit of own side
    */
   private void checkDropInEmpty(int current)
   {
	   if(data.get(current).hole.getNbStones() == 1)			// was empty before, now has 1 --> we steal own an opposite pit
	   {
		   int counter = 1;
		   
		   data.get(current).hole.setNbStones(0);
		   
		   if(current > 5)			// empty pit was an player A's side
		   {
			   counter += data.get(current - 6).hole.getNbStones();
			   manA.mancala.numberOfStones += counter;
			   data.get(current-6).hole.setNbStones(0);

		  
		   }
		   if(current < 6)			// empty pit was an player B's side
		   {
			   counter += data.get(current + 6).hole.getNbStones();
			   manB.mancala.numberOfStones += counter;
			   data.get(current+6).hole.setNbStones(0);

		   }
		   
		   
		   
		   
	   }
	   
   		
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
    	
    	if(didUndo == false)
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
    	
    	didUndo = true;
    	
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












   
   