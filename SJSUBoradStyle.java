import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SJSUBoradStyle implements BoardFormatter {
    @Override
    public void setBoardStyle(MancalaBoard board) {
        board.setTitle("Go Spartans!");
        board.getMancaA().setBackground(Color.YELLOW);
        board.getMancaB().setBackground(Color.BLUE);


//        BufferedImage myPicture = null;
//        try {
//            myPicture = ImageIO.read(new File("src/resource/sjsu.gif"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
//        board.getSouthPanel().add(picLabel);

    }
}