// CS 151 - Project Group SSR
// MancalaBoardContext.java


/**
 * This a context class used for the strategy pattern to execute setting the look
 * of how window looks.
 *
 * @author Rui Yang, Sandro sallenbach, Stefan Do
 * @version since 5/1/2018 CS151 Dr.kim
 */

public class MancalaBoradContext
{
    private BoardFormatter strategy;

    /**
     * This is a constructor of the MancalaBoradContext class. It is used for the strategy pattern.
     * @param strategy takes in the BoardFormatter type and set it to strategy variable
     */
    public MancalaBoradContext(BoardFormatter strategy)
    {
        this.strategy = strategy;
    }

    
    /**
     * This class sets the board style to the board. It is used for the strategy pattern.
     * @param board takes in the MancalaBoard type
     */
    public void excuteSettingBoradStyle(MancalaBoard board)
    {
        strategy.setBoardStyle(board);

    }

}