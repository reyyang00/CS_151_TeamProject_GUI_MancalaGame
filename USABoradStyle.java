
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class USABoradStyle implements BoardFormatter {
	   @Override
	    public void setBoardStyle(MancalaBoard board) {
	        board.setTitle("USA!");
	        board.getMancaA().setBackground(Color.RED);
	        board.getMancaB().setBackground(Color.BLUE);

	    }
	}

