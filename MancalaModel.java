// CS 151 - Project Group SSR
// MancalaModel.java


import java.util.ArrayList;
import javax.swing.event.*;



/**	The ManacalaModel represents the [M]odel of the MVC pattern.
*
* @author	Rui Yang, Sandro Sallenbach, and Stefan Do
* @version	CS151 Dr. Kim
*/
public class MancalaModel {


    private int playerUndotime;
    private MancalaPlayer playerA, playerB;

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
    int playerTurn = 2;            // stores which players turn it is. While it's player A's turn, the pits of player B are deactivated. 0=PlayerA, 1=PlayerB, 2=Game not initialized
    boolean initializationDone = false;
    boolean lastInOwnMancala = false;
    boolean didUndo = false;
    boolean changePlayerFlag = false;
    int endOfGame = 0;

    
    /**
     * a getter method for manA
     * @return  a MancalaPt type
     */
    public MancalaPit getManA() {
        return manA;
    }

    
    /**
     * a setter method for manA
     * @param manA   pass a MancalaPt type and sets it to manA
     */
    public void setManA(MancalaPit manA) {
        this.manA = manA;
    }

    
    /**
     * a getter method for manB
     * @return  a MancalaPt type
     */
    public MancalaPit getManB() {
        return manB;
    }

    
    /**
     * a setter method for manA
     * @param manB   pass a MancalaPt type and sets it to manB
     */
    public void setManB(MancalaPit manB) {
        this.manB = manB;
    }

    
    /**
     * a getter method to get the player's undo time
     * @return   a int type
     */
    public int getPlayerUndotime() {
        return playerUndotime;
    }

    
    /**
     * a setter method for manA
     * @param playerUndotime   pass int type and sets it to its relative player's undo time
     */
    public void setPlayerUndotime(int playerUndotime) {
        this.playerUndotime = playerUndotime;
    }

    
    /**
     * a  method to increase the player's undo time
     *
     */
    public void increasePlayerUndotime() {
        if (this.playerUndotime == 3) {
            this.playerUndotime = 0;
        } else
            this.playerUndotime++;

    }


    /**
     * This method adds the pitComponents to the arrayLists of data and previousState
     * @param pitComponent
     */
    public void addHole(PitComponent pitComponent)
    {
        data.add(pitComponent);
        previousState.add(new PitComponent(pitComponent.dataModel, pitComponent.id));
    }


    /**
     * Constructs a MancalaModel object
     */
    public MancalaModel()
    {
        listeners = new ArrayList();
        playerUndotime = 0;
        initializePlayerAB();

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
    public void attach(ChangeListener c)
    {
        listeners.add(c);
    }


    /**
     * This method copies the current state of the entire board (all pits and mancalas)
     * The copy will be used in case of an "undo"
     *
     */
    public void updateStatus()
    {

        for (int i = 0; i < previousState.size(); i++) {
            previousState.get(i).hole.setNbStones(data.get(i).hole.getNbStones());
        }

        previousManA.mancala.numberOfStones = manA.mancala.numberOfStones;
        previousManB.mancala.numberOfStones = manB.mancala.numberOfStones;


    }

    
    
    /**
     * This method helps the update-method to distribute the stones on player B's side (incl. all 6 pits and mancala B)
     *
     * @param location, the current location, where we have to start filling up
     * @param value, the number of stones to distribute
     * @param flag, indicates whether we are on the side of the player that started this turn (if so, we have to consider the mancala
     * 					and also a possible drop into an empty pit)
     */
    private int distributeStonesB(int location, int value, boolean flag)
    {
        int stop = location - value;
        
        for (int i = location - 1; i >= stop && i >= 0; i--) //
        {
            if (value == 1 && flag)					// if only one stone left and we are on own side (flag)
            {
                data.get(i).hole.addStone();		// add a stone to the pit
                checkDropInEmpty(i);				// check if stone was dropped in empty pit
            }
            else
            {
                data.get(i).hole.addStone();
            }

            value--;
        }
           
        if (value > 0 && flag)                            ////&& playerTurn == 1   we dont fill the oponents mancala!
        {
            manB.mancala.addStone();
            value--;

            if (value == 0)
            {
            	lastInOwnMancala = true;
                changePlayerFlag = false;	// we go again
                
            }
        }
        
          return value;  
    }
    
    
    
    /**
     * This method helps the update-method to distribute the stones on player A's side (incl. all 6 pits and mancala A)
     *
     * @param location, the current location, where we have to start filling up
     * @param value, the number of stones to distribute
     * @param flag, indicates whether we are on the side of the player that started this turn (if so, we have to consider the mancala
     * 					and also a possible drop into an empty pit)
     */
    private int distributeStonesA(int location, int value, boolean flag)
    {

    	int c = value;
        for (int i = location; i < location + c && i < 12; i++)
        {
        	
            if (value == 1 && flag)				// if only one stone left and we are on own side (flag)
            {
                data.get(i).hole.addStone();		// add a stone to the pit
                checkDropInEmpty(i);				// check if stone was dropped in empty pit
            } else {
                data.get(i).hole.addStone();
            }
        	
            value--;
        }

        	
        if (value > 0 && flag)
        {

            manA.mancala.addStone();
            value--;

            if (value == 0)
            {
                changePlayerFlag = false;	// we go again
                lastInOwnMancala = true;
            }


        } 
        
        return value;
   
    }
    
    

    /**
     * Change the data in the model at a particular location
     *
     * @param location the index of the field to change
     * @param value    the number of stones to be distributed
     *      */
    public void update(int location, int value)                // value is the number of stones to be distributed
    {
        updateStatus();

        didUndo = false;
        changePlayerFlag = true;		// we are planning on changing to opponent
        lastInOwnMancala = false;
        
        data.get(location).hole.setNbStones(0);

        if (location < 6)                // a pit of Player B was selected
        {
        	playerA.setUndoTimeLeft(3);

        	value = distributeStonesB(location, value, true);
        	        	
        	
        	while(value > 0)
        	{
                if (value > 0)
                {
                	value = distributeStonesA(6, value, false);

                }
                
                if (value > 0)
                {
                    value = distributeStonesB(6, value, true);

                }
                
        	}

        }
        //=============================================================================================================================================
        //=============================================================================================================================================
        else                 		   // a pit of Player A was selected
        {
        	
          	playerB.setUndoTimeLeft(3);

            value = distributeStonesA(location+1, value, true);
              
        	while(value > 0)
        	{
                if (value > 0)
                {
                	value = distributeStonesB(6, value, false);

                }
                
                if (value > 0)
                {
                	value = distributeStonesA(6, value, true);

                }
                
        	}
                      
                               
        }
       
        //=============================================================================================================================================
        //=============================================================================================================================================
 
        
        
        if(changePlayerFlag)
          playerTurn = changePlayerOrder(playerTurn);    

        endOfGame = checkEndOfGameCondition();

        if (endOfGame > 0) {
            playerTurn = 3;        // end of game. disable all buttons

            if (endOfGame == 1)
                System.out.println("PLAYER A WINS");
            if (endOfGame == 2)
                System.out.println("PLAYER B WINS");
            if (endOfGame == 3)
                System.out.println("Its a tie");
        }


   	   for(int i = 0; i < listeners.size(); i++)
   	   {
   		   ChangeListener l = (ChangeListener) listeners.get(i);
   		   l.stateChanged(new ChangeEvent(this));

   	   }


    }


    /**
     * checks whether end of game condition is met.
     * @return integer, 1= player A wins, 2 = player B wins, 3= tie, 0= end of game conditions are not met
     */
    private int checkEndOfGameCondition() {


        boolean allZero = true;
        for (int i = 0; i < 6 && allZero == true; i++) {
            if (data.get(i).hole.getNbStones() > 0) {
                allZero = false;
            }
        }

        if (allZero) {                //Player B's side is all zero
            System.out.println("CLEAN UP SIDE OF 6");
            cleanUpSide(6);

        }

        allZero = true;
        for (int i = 6; i < 12 && allZero == true; i++) {
            if (data.get(i).hole.getNbStones() > 0) {
                allZero = false;
            }
        }

        if (allZero) {                //Player A's side is all zero

            System.out.println("CLEAN UP SIDE OF 0");

            cleanUpSide(0);
        }


        int stonesNeededForWin = numberStones * 6;

        if (manA.mancala.numberOfStones > stonesNeededForWin)
            return 1;            // Player A wins

        if (manB.mancala.numberOfStones > stonesNeededForWin)
            return 2;            // Player B wins


        if (manA.mancala.numberOfStones == stonesNeededForWin && manB.mancala.numberOfStones == stonesNeededForWin)
        {
            return 3;			// its a tie
        }


        return 0;

    }


    /**
     * If one side of pits is all empty, the game ends. Clean up the other side and add all stones to the opposite mancala
     * @param startPit, either 6 or 0, depending if player A or player B caused the empty side of pits
     */
    private void cleanUpSide(int startPit)
    {
        int counter = 0;
        for (int i = startPit; i < startPit + 6; i++) {
            counter += data.get(i).hole.getNbStones();        // count total of stones

            data.get(i).hole.setNbStones(0);                    // empty the pit
        }

        if (startPit == 0)                                // add cleaned up stones to mancala
        {
            manB.mancala.numberOfStones += counter;
        } else {
            manA.mancala.numberOfStones += counter;
        }


    }


    /**
     * Method checks whether the last stone was dropped in an empty pit of own side
     * @param current, index of the current pit
     */
    private void checkDropInEmpty(int current) {
        if (data.get(current).hole.getNbStones() == 1)            // was empty before, now has 1 --> we steal own an opposite pit
        {
            int counter = 1;

            data.get(current).hole.setNbStones(0);

            if (current > 5)            // empty pit was an player A's side
            {
                counter += data.get(current - 6).hole.getNbStones();
                manA.mancala.numberOfStones += counter;
                data.get(current - 6).hole.setNbStones(0);


            }
            if (current < 6)            // empty pit was an player B's side
            {
                counter += data.get(current + 6).hole.getNbStones();
                manB.mancala.numberOfStones += counter;
                data.get(current + 6).hole.setNbStones(0);

            }


        }


    }


    /**
     * Method changes from player A to player B or vice versa. Used in the update-method.
     * @param current, the current player
     * @return int, the new current player
     */
    private int changePlayerOrder(int current)
    {
        if (current == 1)
            return 0;
        else
            return 1;

    }


    /**
     *  This method provides the user with the undo functionality. If run, the method loads the most previous state of the game board.
     */
    public void undoFuntion()
    {
    	if(endOfGame == 0)
    	{
	    	if(lastInOwnMancala == true)
	    	{
	            playerTurn = changePlayerOrder(playerTurn);
	
	    	}
	    	
	        if (playerTurn == 0 && playerB.getUndoTimeLeft() > 0 )		// Player B presses undo
	        {
	            if (didUndo == false)
	            {
	                for (int i = 0; i < previousState.size(); i++)
	                {
	                    data.get(i).hole.setNbStones(previousState.get(i).hole.getNbStones());
	                }
	
	                manA.mancala.numberOfStones = previousManA.mancala.numberOfStones;
	                manB.mancala.numberOfStones = previousManB.mancala.numberOfStones;
	

	                playerTurn = changePlayerOrder(playerTurn);

	                
	            	for(int i = 0; i < listeners.size(); i++)
	               	{
	            		ChangeListener l = (ChangeListener) listeners.get(i);
	               		l.stateChanged(new ChangeEvent(this));
	               	}
	                    	                
	                playerB.decreaseUndoTimeLeft();
	
	            }
	            
	            didUndo = true;
	
	            
	        }
	        else if (playerTurn == 1 && playerA.getUndoTimeLeft() > 0)
	        {
	            if (didUndo == false)
	            {
	                for (int i = 0; i < previousState.size(); i++)
	                {
	                    data.get(i).hole.setNbStones(previousState.get(i).hole.getNbStones());
	                }
	
	                manA.mancala.numberOfStones = previousManA.mancala.numberOfStones;
	                manB.mancala.numberOfStones = previousManB.mancala.numberOfStones;
	

	                playerTurn = changePlayerOrder(playerTurn);

	                for(int i = 0; i < listeners.size(); i++)
	                {
	                	ChangeListener l = (ChangeListener) listeners.get(i);
	                	l.stateChanged(new ChangeEvent(this));
	                }
	                	   	                
	                playerA.decreaseUndoTimeLeft();
	
	            }
	
	            didUndo = true;
	
	        }
	        
	        
	        System.out.println("playerA undo time left:" + playerA.getUndoTimeLeft());
	        System.out.println("playerB undo time left:" + playerB.getUndoTimeLeft());
    	}
    }


    /**
     * This method is used during the set-up of the game. It initialized each pit with either 3 or 4 stones, according to the 'numberStones' variable.
     */
    public void initializeStones() {
        for (int i = 0; i < 12; i++)
        {
            data.get(i).hole.setNbStones(numberStones);

        }

        
    	for(int i = 0; i < listeners.size(); i++)
       	{
    		ChangeListener l = (ChangeListener) listeners.get(i);
    		l.stateChanged(new ChangeEvent(this));
       	}
        
    }

    
    /**
     * This method initialized player A and B
     */
    public void initializePlayerAB()
    {
        playerA = new MancalaPlayer();
        playerB = new MancalaPlayer();
    }

}










   
   