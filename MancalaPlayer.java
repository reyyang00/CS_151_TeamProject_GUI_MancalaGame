public class MancalaPlayer {
    private int undoTimeLeft;

    public MancalaPlayer() {
        this.undoTimeLeft = 3;
    }

    public void decreaseUndoTimeLeft(){
        this.undoTimeLeft--;
    }

    public int getUndoTimeLeft() {
        return undoTimeLeft;
    }

    public void setUndoTimeLeft(int undoTimeLeft) {
        this.undoTimeLeft = undoTimeLeft;
    }
}
