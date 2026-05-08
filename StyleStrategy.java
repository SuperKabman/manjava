package manjava;

import java.awt.*;

import java.awt.*;
import java.awt.geom.*;

public interface StyleStrategy {
    void drawBoardBackground(Graphics2D g2, Shape boardShape);
    void drawPit(Graphics2D g2, Shape pitShape, boolean highlighted);
    void drawMancala(Graphics2D g2, Shape mancalaShape);
    void drawStone(Graphics2D g2,int x, int y);
}