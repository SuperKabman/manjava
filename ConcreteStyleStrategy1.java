import java.awt.*;

public class ConcreteStyleStrategy1 implements StyleStrategy {

    public void drawBoard(Graphics g, Board board) {
        g.setColor(Color.ORANGE);
        g.fillRect(50, 50, 600, 200);
    }

    public void drawPit(Graphics g, int x, int y, int stones) {
        g.setColor(Color.YELLOW);
        g.fillOval(x, y, 60, 60);
        g.setColor(Color.BLACK);
        g.drawString("" + stones, x + 25, y + 35);
    }

    public void drawMancala(Graphics g, int x, int y, int stones) {
        g.setColor(Color.RED);
        g.fillRect(x, y, 60, 140);
        g.setColor(Color.WHITE);
        g.drawString("" + stones, x + 20, y + 70);
    }

    public void drawStone(Graphics g, int x, int y) {
        g.setColor(Color.BLACK);
        g.fillOval(x, y, 5, 5);
    }
}