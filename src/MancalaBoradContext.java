/**
 * This a contest class used for the strategy pattern to exacute setting the look
 * of how window looks.
 *
 * @author Rui Yang, Sandro sallenbach, Stefan Do
 * @version since 5/1/2018 CS151 Dr.kim
 */
public class MancalaBoradContext {
    private BoardFormatter strategy;// a reference of strategy pattern


    /**
     * This a interface class used for the strategy pattern.
     * @param strategy takes in the BoardFormatter type and set it to strategy variable
     */
    public MancalaBoradContext(BoardFormatter strategy) {
        this.strategy = strategy;
    }



    /**
     * This a interface class used for the strategy pattern.
     * @param board takes in the MancalaBoard type
     */
    public void excuteSettingBoradStyle(MancalaBoard board){
        strategy.setBoardStyle(board);

    }

}