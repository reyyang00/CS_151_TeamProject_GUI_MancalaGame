// CS 151 - Project Group SSR
// MancalaBoard.java

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.*;
import java.util.ArrayList;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;


/**
 * This is a Jframe window which can be seen and it contains multiple JPanels on it,
 *
 * @author Rui Yang, Sandro sallenbach, Stefan Do
 * @version since 5/1/2018 CS151 Dr.kim
 */


/**
 * A class that implements the [V]iew and [C]ontroller part of the MVC pattern.
 */
public class MancalaBoard extends JFrame implements ChangeListener
{
    private BoardFormatter strategy;
    private MancalaBoradContext newContext;
    private MancalaBoard selfPointer; // a reference pointing to Mancala Frame, use to endGame function.
    private ArrayList a;
    private MancalaModel dataModel;
    private ArrayList<PitComponent> status;
    
    Font playerFont = new Font("Bahnschrift", Font.PLAIN, 20);
    Font mancalaFont = new Font("Bahnschrift", Font.PLAIN, 17);

    JPanel mancaA, mancaB;

    private int buttonCounter = 0;

    private static final int PANEL_WIDTH = 200;
    private static final int PANEL_HEIGHT = 100;
    final int FIELD_WIDTH = 20;
    private JTextField outputField = new JTextField();

    private JPanel southPanel = new JPanel(new BorderLayout());

    
    /**
     * a getter method for mancaB
     * @return  a JPanel type
     */
    public JPanel getMancaB() {
        return mancaB;
    }

    /**
     * a setter method for mancaB
     * @param mancaB  pass a JPanel type and sets it to mancaB
     */
    public void setMancaB(JPanel mancaB) {
        this.mancaB = mancaB;
    }

    /**
     * a getter method for southPanel
     * @return  a JPanel type
     */
    public JPanel getSouthPanel() {
        return southPanel;
    }

    /**
     * a setter method for southPanel
     * @param southPanel pass a JPanel type and sets it to southPanel
     */
    public void setSouthPanel(JPanel southPanel) {
        this.southPanel = southPanel;
    }

    /**
     * a getter method for mancaA
     * @return  a JPanel type
     */
    public JPanel getMancaA() {
        return mancaA;
    }

    /**
     * a setter method for mancaA
     * @param mancaA  pass a JPanel type and sets it to mancaB
     */
    public void setMancaA(JPanel mancaA) {
        this.mancaA = mancaA;
    }

    
    /**
     * a reference used to point to board itself, so that can be closed easier.
     *
     * @param selfPointer the data that is displayed on the MancalaBoard
     */
    public void setSelfPointer(MancalaBoard selfPointer) {
        this.selfPointer = selfPointer;
    }


     /**
     *  Constructs a MancalaBoard object
     *  Functions as View and Controller at the same time
     *  @param data the data that is displayed on the MancalaBoard
     */
    public MancalaBoard(MancalaModel data)
        {

            this.dataModel = data;
            this.status = new ArrayList<PitComponent>(12);
            this.setLayout(new BorderLayout());
            this.setResizable(false);

            outputField.setText("How many stones per field?");
            outputField.setHorizontalAlignment(JTextField.CENTER);
            outputField.setEditable(false);

            /*South panel includes "<< PLAYER A" */
            JTextField playerA = new JTextField(FIELD_WIDTH);
            playerA.setHorizontalAlignment(JTextField.CENTER);
            playerA.setBorder(new LineBorder(Color.BLACK, 0));
            playerA.setFont(playerFont);
            playerA.setText("PLAYER A >>");
            playerA.setEditable(false);
            playerA.setBackground(Color.white);


            southPanel.add(playerA, BorderLayout.NORTH);
            southPanel.add(outputField, BorderLayout.CENTER);
            activateButtons();

            /*North panel includes "<< PLAYER A" */
            JTextField playerB = new JTextField(FIELD_WIDTH);
            playerB.setHorizontalAlignment(JTextField.CENTER);
            playerB.setBorder(new LineBorder(Color.BLACK, 0));
            playerB.setFont(playerFont);
            playerB.setText("<< PLAYER B");
            playerB.setEditable(false);
            playerB.setBackground(Color.white);


            JPanel eastPanel = new JPanel(new BorderLayout());
            JPanel westPanel = new JPanel(new BorderLayout());
            JPanel centerPanel = new JPanel(new BorderLayout());

            centerPanel.setBorder(new LineBorder(Color.BLACK, 4));

            /*East panel*/
            JTextArea east = new JTextArea(2, 2);
            east.setBorder(new LineBorder(Color.BLACK, 0));
            east.setFont(mancalaFont);
            east.setText("\n  M\n  A\n  N\n  C\n  A\n  L\n  A\n\n  A");
            eastPanel.add(east, BorderLayout.EAST);
            east.setEditable(false);


            /*West panel*/
            JTextArea west = new JTextArea(2, 2);
            west.setBorder(new LineBorder(Color.BLACK, 0));
            west.setFont(mancalaFont);
            west.setText("\n  M\n  A\n  N\n  C\n  A\n  L\n  A\n\n  B");
            westPanel.add(west, BorderLayout.WEST);
            west.setEditable(false);


            /*Center panel*/
            JPanel holes = new JPanel(new GridLayout(4, 6));
            holes.setPreferredSize(new Dimension(500, 220));


            holes.setBackground(Color.WHITE);

            for (int i = 6; i > 0; i--) {

                final JTextField b = new JTextField();
                b.setHorizontalAlignment(JTextField.CENTER);
                b.setBorder(new LineBorder(Color.BLACK, 0));
                b.setFont(mancalaFont);
                b.setText("B" + i);
                holes.add(b);
                b.setEditable(false);
                b.setBackground(Color.white);

            }

            for (int i = 0; i < 12; i++) {
                PitComponent pit = new PitComponent(dataModel, i);
                dataModel.addHole(pit);
                holes.add(pit);
            }

            for (int i = 1; i < 7; i++) {
                JTextField b = new JTextField();
                b.setHorizontalAlignment(JTextField.CENTER);
                b.setBorder(new LineBorder(Color.BLACK, 0));
                b.setFont(mancalaFont);
                b.setText("A" + i);
                holes.add(b);
                b.setEditable(false);
                b.setBackground(Color.white);

            }

            mancaA = new JPanel(new BorderLayout());
            mancaA.setPreferredSize(new Dimension(80, 220));
            mancaA.setBackground(Color.white);
            MancalaPit pitA = new MancalaPit();
            dataModel.manA = pitA;
            mancaA.add(pitA, BorderLayout.CENTER);
            centerPanel.add(mancaA, BorderLayout.EAST);


            mancaB = new JPanel(new BorderLayout());
            mancaB.setPreferredSize(new Dimension(80, 220));
            mancaB.setBackground(Color.white);
            MancalaPit pitB = new MancalaPit();
            dataModel.manB = pitB;
            mancaB.add(pitB, BorderLayout.CENTER);
            centerPanel.add(mancaB, BorderLayout.WEST);

            centerPanel.add(holes, BorderLayout.CENTER);


            this.add(playerB, BorderLayout.NORTH);
            this.add(westPanel, BorderLayout.WEST);
            this.add(eastPanel, BorderLayout.EAST);

            this.add(centerPanel, BorderLayout.CENTER);
            this.add(southPanel, BorderLayout.SOUTH);


            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.pack();
            this.show();
        }


        /**
        * Called when the data in the model is changed.
        * @param e the event representing the change
        */
        public void stateChanged (ChangeEvent arg0)
        {

            for (int i = 0; i < 12; i++)
            {
                dataModel.data.get(i).repaint();
            }

            dataModel.manA.repaint();
            dataModel.manB.repaint();
            
            
            if(dataModel.endOfGame > 0)
            {
            	outputField.setBackground(Color.GREEN);	
                if (dataModel.endOfGame == 1)
                    outputField.setText("PLAYER A WINS");
                if (dataModel.endOfGame == 2)
                    outputField.setText("PLAYER B WINS");
                if (dataModel.endOfGame == 3)
                    outputField.setText("It's a tie");
                
            }
            
            

        }

        
        /**
         * a helper method to help us to use strategy pattern base on the way we design our game
         *
         * @param updatedStyle a BoardFormatter type use to the reference of the strategy
         */
        public void style(BoardFormatter updatedStyle)
        {
        	newContext = new MancalaBoradContext(updatedStyle);
        	newContext.excuteSettingBoradStyle(selfPointer);
        	
        	for(int i = 0; i < 12; i++)
        	{
        		dataModel.data.get(i).hole.setSyle(updatedStyle.getColorA(), updatedStyle.getColorB(), updatedStyle.getShape());
        	}
        	
        	stateChanged(new ChangeEvent(this));
        }
        
        
        

        /**
         * activate all the buttons on the window.
         * also includes an anonymous actionListener.
         */
        public void activateButtons ()
        {
            JButton firstButton = new JButton("3");
            JButton secondButton = new JButton("4");
            JPanel buttonPanel = new JPanel();

            firstButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String newStringA;
                    String newStringB;

                    if (buttonCounter == 0) {
                        dataModel.numberStones = 3;
                        dataModel.initializeStones();
                        newStringA = "Style A";
                        newStringB = "Style B";
                        outputField.setText("Which Style? A = USA theme, B = SJSU theme");

                        buttonCounter++;
                    } else if (buttonCounter == 1) {
                        dataModel.boardStyle = 1;
                        newStringA = "End Game";
                        newStringB = "Undo";
                        
                        style(new USABoradStyle());
   
                     
                        dataModel.initializationDone = true;			// signal that user is done with initialization (chose # stones and style)

                        outputField.setText("Click 'Undo' to undo your turn");

                        buttonCounter++;
                    } else {

                        selfPointer.dispose();

                        newStringA = "End Game";
                        newStringB = "Undo";
                    }


                    try {
                        firstButton.setText(newStringA);
                        secondButton.setText(newStringB);


                        ///   remove the buttons
                    } catch (Exception exc) {

                    }
                }
            });

            secondButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String newStringB, newStringA;
                    if (buttonCounter == 0) {
                        dataModel.numberStones = 4;
                        dataModel.initializeStones();
                        newStringB = "Style B";
                        newStringA = "Style A";
                        outputField.setText("Which Style? A = USA theme, B = SJSU theme");
                        buttonCounter++;
                    } else if (buttonCounter == 1) {

                        dataModel.boardStyle = 2;
                        newStringB = "Undo";
                        newStringA = "End Game";
                        style(new SJSUBoradStyle());

                        
                        dataModel.initializationDone = true;			// signal that user is done with initialization (chose # stones and style)

                        outputField.setText("Click 'Undo' to undo your turn");

                        buttonCounter++;


                    } else {
                        dataModel.undoFuntion();
                        System.out.println("undo clicked");
                        newStringB = "Undo";
                        newStringA = "End Game";

                    }

                    try {
                        secondButton.setText(newStringB);
                        firstButton.setText(newStringA);

                    } catch (Exception exc) {

                    }
                }
            });

            buttonPanel.add(firstButton);
            buttonPanel.add(secondButton);

            southPanel.add(buttonPanel, BorderLayout.SOUTH);


        }

    }






	
	