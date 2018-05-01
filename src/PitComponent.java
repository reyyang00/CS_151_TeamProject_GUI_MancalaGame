import java.awt.*;
//import java.awt.Point;
//import java.awt.event.MouseAdapter;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JComponent;


//PitComponent holds the stones.

public class PitComponent extends JComponent {
    int id;
    StoneHole hole;
    MancalaModel dataModel;
    ArrayList<PitComponent> previousStatus = new ArrayList<PitComponent>();
    final int STATUSCOUNTER = 1;
    int mo;

    public PitComponent(MancalaModel data, int id) {
        this.id = id;
        dataModel = data;
        hole = new StoneHole();
        MouseListeners listeners = new MouseListeners();
        addMouseListener(listeners);
    }

    public void updateMove() {
        mo = dataModel.getMove();
        dataModel.setMove(mo + STATUSCOUNTER);
    }

    private class MouseListeners extends MouseAdapter {
        public void mousePressed(MouseEvent event) {
            // update the move
            System.out.println("Curent move before move:"+dataModel.getCurrentMoved());
            dataModel.setCurrentMoved(1);
            System.out.println("Curent move after move:"+dataModel.getCurrentMoved());

            updateMove();
            // System.out.println("The total move is:"+dataModel.getMove());
            dataModel.updateStatus(dataModel.getMove());

            //  System.out.println(dataModel.getMove());

            //this is update the data
            dataModel.update(id, hole.getNbStones());

            System.out.println("Mancala A:" + dataModel.getManA().mancala.numberOfStones);
            System.out.println("Mancala B:" + dataModel.getManB().mancala.numberOfStones);
            hole.setNbStones(0);

            repaint();

        }


    }
//    public void copy(){
//
//        dataModel.getData().get()
//    }


    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        hole.draw(g2);
    }


}