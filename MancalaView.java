package manjava;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;


public class MancalaView extends JFrame {
    private static final int ICON_WIDTH = 700;
    private static final int ICON_HEIGHT = 400;
    
    public MancalaView() {
        setTitle("Mancala Game View");
        setSize(900, 600);

        Icon boardIcon = new Icon() {
            // implement interface methods
            public int getIconWidth() {return ICON_WIDTH;}
            public int getIconHeight() {return ICON_HEIGHT;}
            public void paintIcon(Component c, Graphics g, int x, int y) {
                Graphics2D g2 = (Graphics2D) g;

                g2.setPaint(Color.BLACK);
                g2.setStroke(new BasicStroke(2.0f));

                RoundRectangle2D.Double board = new RoundRectangle2D.Double(10, 10, 240, 160, 10, 10);

                g2.draw(board);
            }
        };

        add(new JLabel(boardIcon));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    
}
