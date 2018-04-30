import java.util.ArrayList;

// CS 151 - Project Group SSR
// MancalaTester.java

/**
 A class for testing the Mancala Game.
 */
public class MancalaTester
{
    /**
     Creates a MancalaModel and attaches MancalaBoard
     @param args unused
     */
    public static void main(String[] args)
    {

        MancalaModel model = new MancalaModel();


        MancalaBoard gameboard = new MancalaBoard(model);
        MancalaBoradContext context = new MancalaBoradContext(new SJSUBoradStyle());
        context.excuteSettingBoradStyle(gameboard);


      //  System.out.println("hi");

        model.attach(gameboard);
        gameboard.setSelfPointer(gameboard);


//	    gameboard.stoneButtons();
//	    gameboard.styleButtons();


    }
}