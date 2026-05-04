import java.awt.*;

public class ConcreteStyleStrategy2 implements StyleStrategy {

    public void drawBoard(Graphics g, Board board) {
        g.setColor(Color.DARK_GRAY);
        g.fillRoundRect(50, 50, 600, 200, 30, 30);
    }

    public void drawPit(Graphics g, int x, int y, int stones) {
        g.setColor(Color.CYAN);
        g.fillRoundRect(x, y, 60, 60, 20, 20);
        g.setColor(Color.BLACK);
        g.drawString("" + stones, x + 25, y + 35);
    }

    public void drawMancala(Graphics g, int x, int y, int stones) {
        g.setColor(Color.BLUE);
        g.fillRoundRect(x, y, 60, 140, 20, 20);
        g.setColor(Color.WHITE);
        g.drawString("" + stones, x + 20, y + 70);
    }

    public void drawStone(Graphics g, int x, int y) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, 5, 5);
    }
}