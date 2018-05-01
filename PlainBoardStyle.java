
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class PlainBoardStyle implements BoardFormatter {
	   @Override
	    public void setBoardStyle(MancalaBoard board) {
	        board.setTitle("Let's play some Mancala!");
	        board.getMancaA().setBackground(Color.GRAY);
	        board.getMancaB().setBackground(Color.GRAY);


//	        BufferedImage myPicture = null;
//	        try {
//	            myPicture = ImageIO.read(new File("src/resource/sjsu.gif"));
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        }
//	        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
//	        board.getSouthPanel().add(picLabel);

	    }
	}
