public class MancalaPlayer {
    private int undoTime;

    public MancalaPlayer(int undoTime) {
        this.undoTime = undoTime;
    }

    public int getUndoTime() {
        return undoTime;
    }

    public void setUndoTime(int undoTime) {
        this.undoTime = undoTime;
    }

    public void increaseUndoTime(){
        this.undoTime++;
    }
}
