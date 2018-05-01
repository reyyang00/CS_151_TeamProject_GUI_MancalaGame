public class MancalaPlayer {

    private int totalUndoLeft;
    private Boolean isPlayerA, getIsPlayerB; // use for check which player is moving

    public MancalaPlayer(int totalUndoLeft) {
        this.totalUndoLeft = 3;
    }

    public int getTotalUndoLeft() {
        return totalUndoLeft;
    }

    public void setTotalUndoLeft(int totalUndoLeft) {
        this.totalUndoLeft = totalUndoLeft;
    }

    public void increaseUndoTime(){
        this.totalUndoLeft++;
    }

    public void decreaseUndotime(){
        this.totalUndoLeft--;
    }

}
