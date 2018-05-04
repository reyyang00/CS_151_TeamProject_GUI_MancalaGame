// CS 151 - Project Group SSR
// MancalaPlayer.java

/**
 * This class represent player in the a mancala game
 * @author Rui Yang, Sandro Sallenbach, and Stefan Do
 * @version CS151 Dr. Kim
 *
 */

public class MancalaPlayer
{
    private int undoTimeLeft;

    /**
     * A constructor that initialize 3 undo time when the object is created
     *
     */
    public MancalaPlayer() {
        this.undoTimeLeft = 3;
    }

    /**
     * A method to decrease the undo time
     *
     */
    public void decreaseUndoTimeLeft(){
        this.undoTimeLeft--;
    }

    /**
     * A getter for the undo time
     * @return int, the # of undo's left
     *
     */
    public int getUndoTimeLeft() {
        return undoTimeLeft;
    }

    
    /**
     * A setter for the undo time
     *@param undoTimeLeft, to set the new # of undo's left
     */
    public void setUndoTimeLeft(int undoTimeLeft) {
        this.undoTimeLeft = undoTimeLeft;
    }
}