import java.awt.*;

public interface StyleStrategy {
    void drawBoard(Graphics g, Board board);
    void drawPit(Graphics g, int x, int y, int stones);
    void drawMancala(Graphics g, int x, int y, int stones);
    void drawStone(Graphics g, int x, int y);
}