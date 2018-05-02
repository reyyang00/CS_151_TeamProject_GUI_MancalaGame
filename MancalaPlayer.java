/**
 * This class contains functions that the player uses in the game
 * @author Rui Yang, Sandro Sallenbach, and Stefan Do
 * @version CS151 Dr. Kim
 */
public class MancalaPlayer {
    private int undoTime;
    
    /**
     * Initializes MancalaPlayer
     * @param undoTime	Number of times player can use undo function
     */
    public MancalaPlayer(int undoTime) {
        this.undoTime = undoTime;
    }

    /**
     * Gets the number of times of undo
     * @return	A reference to the player's undo time
     */
    public int getUndoTime() {
        return undoTime;
    }

    /**
     * Sets the number of times player can use the undo function
     * @param undoTime	Number of times player can use undo function
     */
    public void setUndoTime(int undoTime) {
        this.undoTime = undoTime;
    }
    
    /**
     * This method increments the number of times of the undo function
     */
    public void increaseUndoTime(){
        this.undoTime++;
    }
}