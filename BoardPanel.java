package manjava;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class BoardPanel extends JPanel {
    private RoundRectangle2D board;

    public BoardPanel() {
        // setBackground(Color.WHITE);

        board = new RoundRectangle2D.Double(2, 2, 825, 325, 50, 50);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(3));
        g2.draw(board);
    }
}
