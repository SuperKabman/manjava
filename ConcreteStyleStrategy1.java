package manjava;

import java.awt.*;
import java.awt.geom.*;

public class ConcreteStyleStrategy1 implements StyleStrategy {

    public void drawBoardBackground(Graphics2D g2, Shape boardShape) {
        GradientPaint wood = new GradientPaint(0,0, new Color(156, 102, 31),800,300,new Color(110, 70, 20));
        g2.setPaint(wood);
        g2.fill(boardShape);
        g2.setColor(new Color(60, 35, 10));
        g2.setStroke(new BasicStroke(5));
        g2.draw(boardShape);
    }

    public void drawPit(Graphics2D g2, Shape pitShape, boolean highlighted) {
        if(highlighted) {
            g2.setColor(new Color(255, 220, 120));
        } else {
            g2.setColor(new Color(205, 170, 125));
        }
        g2.fill(pitShape);
        g2.setColor(new Color(80, 45, 20));
        g2.setStroke(new BasicStroke(3));
        g2.draw(pitShape);
    }

    public void drawMancala(Graphics2D g2, Shape mancalaShape) {
        g2.setColor(new Color(185, 145, 90));
        g2.fill(mancalaShape);
        g2.setColor(new Color(70, 40, 15));
        g2.setStroke(new BasicStroke(4));
        g2.draw(mancalaShape);
    }

    public void drawStone(Graphics2D g2, int x, int y) {
        g2.setColor(new Color(40, 40, 40));
        g2.fillOval(x, y, 12, 12);
        g2.setColor(Color.WHITE);
        g2.drawOval(x, y, 12, 12);
    }
}