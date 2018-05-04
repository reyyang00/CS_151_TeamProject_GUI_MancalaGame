/**
 * This class represent player in the a mancala game
 * @author Rui Yang, Sandro Sallenbach, and Stefan Do
 * @version CS151 Dr. Kim
 *
 */

public class MancalaPlayer {
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
     * a getter for the undo time
     *
     */

    public int getUndoTimeLeft() {
        return undoTimeLeft;
    }


    /**
     * A setter for the undo time
     *
     */
    public void setUndoTimeLeft(int undoTimeLeft) {
        this.undoTimeLeft = undoTimeLeft;
    }
}
