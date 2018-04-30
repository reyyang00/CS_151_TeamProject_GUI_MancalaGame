public class MancalaBoradContext {
    private BoardFormatter strategy;

    public MancalaBoradContext(BoardFormatter strategy) {
        this.strategy = strategy;
    }

    public void excuteSettingBoradStyle(MancalaBoard board){
        strategy.setBoardStyle(board);

    }

}
