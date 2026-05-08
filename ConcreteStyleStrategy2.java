package manjava;

import java.awt.*;
import java.awt.geom.*;

public class ConcreteStyleStrategy2 implements StyleStrategy {

    public void drawBoardBackground(
            Graphics2D g2,
            Shape boardShape) {
        GradientPaint modern = new GradientPaint(0,0,new Color(35, 35, 40), 800, 300, new Color(15, 15, 20));
        g2.setPaint(modern);
        g2.fill(boardShape);
        g2.setColor(new Color(90, 170, 255));
        g2.setStroke(new BasicStroke(4));
        g2.draw(boardShape);
    }

    public void drawPit(Graphics2D g2, Shape pitShape, boolean highlighted) {
        if(highlighted) {
            g2.setColor(new Color(120, 220, 255));
        } else {
            g2.setColor(new Color(70, 90, 120));
        }
        g2.fill(pitShape);
        g2.setColor(new Color(180, 220, 255));
        g2.setStroke(new BasicStroke(3));
        g2.draw(pitShape);
    }

    public void drawMancala(Graphics2D g2, Shape mancalaShape) {
        g2.setColor(new Color(50, 65, 90));
        g2.fill(mancalaShape);
        g2.setColor(new Color(170, 220, 255));
        g2.setStroke(new BasicStroke(4));
        g2.draw(mancalaShape);
    }

    public void drawStone(Graphics2D g2, int x, int y) {
        g2.setColor(new Color(220, 240, 255));
        g2.fillOval(x, y, 12, 12);
        g2.setColor(new Color(120, 180, 255));
        g2.drawOval(x, y, 12, 12);
    }
}