// CS 151 - Project Group SSR
// MancalaModel.java


import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;


/**
 * The ManacalaModel represents the [M]odel of the MVC pattern.
 */
public class MancalaModel {


    ArrayList<PitComponent> data = new ArrayList<>();
    ArrayList<PitComponent> preStatus = new ArrayList<>(12);
    ArrayList<MancalaPit> preManStates = new ArrayList<>(2);

    MancalaPlayer playerA, playerB;


    ArrayList[] status = new ArrayList[3];
    MancalaPit manA;
    MancalaPit manB;
    ArrayList listeners;
    int numberStones;
    int boardStyle;
    int playerTurn = 2;            // stores which players turn it is. While it's player A's turn, the pits of player B are deactivated. 0=PlayerA, 1=PlayerB
    private int move = 0;    // use for record the play status
    boolean initializationDone = false;
    private int currentMoved =0;  //use for check if player is move, 0 not move, 1 move. if it is moved call undo function then current
                                    //  play's undo time --
                                    // really useful for undo fuction.


    public MancalaPit getManA() {
        return manA;
    }

    public void setManA(MancalaPit manA) {
        this.manA = manA;
    }

    public MancalaPit getManB() {
        return manB;
    }

    public void setManB(MancalaPit manB) {
        this.manB = manB;
    }


    public int getMove() {
        return move;
    }

    public void setMove(int move) {
        this.move = move;
    }

    public void addHole(PitComponent pitComponent) {
        data.add(pitComponent);
        preStatus.add(new PitComponent(pitComponent.dataModel, pitComponent.id));
    }

    public void initializeStatus() {
        status[0] = preStatus;

    }

    public void updateStatus(int move) {
        for (int i = 0; i < preStatus.size(); i++) {
            preStatus.get(i).hole.setNbStones(data.get(i).hole.getNbStones());
        }

        preManStates.get(0).mancala.setNumberOfStones(manA.mancala.getNumberOfStones());
        preManStates.get(1).mancala.setNumberOfStones(manB.mancala.getNumberOfStones());

    }


    /**
     * Constructs a MancalaModel object
     */
    public MancalaModel() {
        listeners = new ArrayList();
        initializeStatus();

        initializePlayerAB();
//        initializeManAB();
    }

    /**
     * Returns the current state of the MancalaModel data.
     *
     * @return the data in an ArrayList
     */
    public ArrayList getData() {
        return (ArrayList) data.clone();
    }


    /**
     * Attach a listener to the Model
     *
     * @param c the listener
     */
    public void attach(ChangeListener c) {
        listeners.add(c);
    }


    /**
     * Change the data in the model at a particular location
     *
     * @param location the index of the field to change
     * @param value    the new value
     */
    public void update(int location, int value) {
        if (location < 6)                // a pit of Player B was selected
        {

            int stop = location - value;
            for (int i = location - 1; i >= stop && i >= 0; i--) //
            {

                data.get(i).hole.addStone();

                value--;
            }
            if (value > 0)                            ////&& playerTurn == 1   we dont fill the oponents mancala!
            {
                manB.mancala.addStone();
                value--;

                if (value == 0) {
                    playerTurn = changePlayerOrder(playerTurn);

                }
            }
            if (value > 0) {
                if (value < 7) {
                    for (int i = 6; i < 6 + value; i++) {

                        data.get(i).hole.addStone();
                    }
                } else {
                    data.get(6).hole.addStone();                    // if there is so many stones, it will overflow the bottom row and go around
                    update(6, value - 1);
                }
            }
            playerTurn = changePlayerOrder(playerTurn);


        } else                    // a pit of Player A was selected
        {


            int counter = value;
            for (int i = location + 1; i <= location + value && i < 12; i++) {

                data.get(i).hole.addStone();
                counter--;
            }
            if (counter > 0) {

                manA.mancala.addStone();
                counter--;

                if (counter == 0) {
                    playerTurn = changePlayerOrder(playerTurn);
                }


            }
            if (counter > 0) {
                if (counter < 7) {
                    for (int i = 5; i > 5 - counter && i >= 0; i--) //
                    {

                        data.get(i).hole.addStone();
                    }
                } else {
                    data.get(5).hole.addStone();                    // if there is so many stones, it will overflow the bottom row and go around
                    update(5, counter - 1);
                }


            }
            playerTurn = changePlayerOrder(playerTurn);


        }


        // 	   for(int i = 0; i < listeners.size(); i++)
        // 	   {
        ChangeListener l = (ChangeListener) listeners.get(0);
        l.stateChanged(new ChangeEvent(this));

        // 	   }

    }


    /**
     * checks whether the last stone was dropped in an empty pit of own side
     */
    private int checkDropInEmpty(int current) {
        if (current == 1)
            return 0;
        else
            return 1;

    }


    /**
     * changes from player A to player B and vice versa. used in the update-method
     */
    private int changePlayerOrder(int current) {
        if (current == 1)
            return 0;
        else
            return 1;

    }


    /**
     * XXXX
     */
    public void undoFuntion() {
        System.out.println("Curent move:"+currentMoved);
        if(playerA.getTotalUndoLeft()>0||playerB.getTotalUndoLeft()>0) {
            if (currentMoved==1) {
                if (move < 1) {
                    initializeStones();
                    manA.mancala.setNumberOfStones(0);
                    manB.mancala.setNumberOfStones(0);


                } else {

                    for (int i = 0; i < 12; i++) {
                        data.get(i).hole.setNbStones(preStatus.get(i).hole.getNbStones());
                    }

                    manA.mancala.setNumberOfStones(preManStates.get(0).mancala.getNumberOfStones());
                    manB.mancala.setNumberOfStones(preManStates.get(1).mancala.getNumberOfStones());


                }
                if (playerTurn == 0) {// playA is moving
                    playerA.decreaseUndotime();
                } else {
                    playerB.decreaseUndotime();
                }
            }
        }
        setCurrentMoved(0);
        System.out.println("playerA undotime left:"+playerA.getTotalUndoLeft());
        System.out.println("playerB undotime left:"+playerB.getTotalUndoLeft());
        System.out.println("Curent move:"+currentMoved);
        ChangeListener l = (ChangeListener) listeners.get(0);
        l.stateChanged(new ChangeEvent(this));

    }


    /**
     * XXXX
     */
    public void initializeStones() {
        for (int i = 0; i < 12; i++) {
            data.get(i).hole.setNbStones(numberStones);

//	        ChangeListener l = (ChangeListener) listeners.get(i);

//	        l.stateChanged(new ChangeEvent(this));
        }

        ChangeListener l = (ChangeListener) listeners.get(0);

        l.stateChanged(new ChangeEvent(this));


        // do something
    }

    public void initializeManAB() {
        for (int i = 0; i < 2; i++) {
            preManStates.add(new MancalaPit());
        }
        preManStates.get(0).mancala.setNumberOfStones(manA.mancala.getNumberOfStones());
        preManStates.get(1).mancala.setNumberOfStones(manA.mancala.getNumberOfStones());

    }

    public void initializePlayerAB() {
        playerA = new MancalaPlayer(3);
        playerB = new MancalaPlayer(3);
    }

    public int getCurrentMoved() {
        return currentMoved;
    }

    public void setCurrentMoved(int currentMoved) {
        this.currentMoved = currentMoved;
    }
}




