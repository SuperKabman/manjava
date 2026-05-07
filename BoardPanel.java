package manjava;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

public class BoardPanel extends JPanel {

    private Board boardModel;
    private MancalaController controller;
    
    private Shape[] slots = new Shape[Board.TOTAL_SLOTS];
    private RoundRectangle2D boardShape;

    public BoardPanel() {
        setPreferredSize(new Dimension(825, 325));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int clickedIntex = getClickedSlot(e.getPoint());

                if (clickedIntex != -1 
                    && clickedIntex != Board.MANCALA_A
                    && clickedIntex != Board.MANCALA_B) {
                    
                    controller.handlePitSelection(clickedIntex);
                }
            }
        });
    }

    void setBoard(Board boardModel) {
        this.boardModel = boardModel;
        repaint();
    }

    public void setController(MancalaController controller) {
        this.controller = controller;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        createShapes();

        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(3));
        g2.draw(boardShape);

        for (int i = 0; i < slots.length; i++) {
            if (slots[i] == null) continue;

            if (i == Board.MANCALA_A || i == Board.MANCALA_B) {
                drawMancala(g2, i);
            } else {
                drawPit(g2, i);
            }
        }
    }

    private void createShapes() {
        int pitSize = 75;
        int gap = 25;
        int x = 145;
        int y = 65;
        
        boardShape = new RoundRectangle2D.Double(2, 2, 825, 325, 50, 50);

        slots[Board.MANCALA_B] = new RoundRectangle2D.Double(25, 55, 85, 215, 45, 45);
        slots[Board.MANCALA_A] = new RoundRectangle2D.Double(715, 55, 85, 215, 45, 45);

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 12; j++) {
                slots[i] = new Ellipse2D.Double(x + (pitSize + gap), y, pitSize, pitSize);
            }
            y += 120;
        }
    }

    private void drawPit(Graphics2D g2, int index) {
        Shape pit = slots[index];
        Rectangle bounds = pit.getBounds();

        g2.setColor(Color.BLACK);
        g2.draw(pit);

        String label = getPitLabel(index);
        g2.drawString(label, bounds.x + 25, bounds.y - 8);

        int stones = 0;
        if (boardModel != null) {
            stones = boardModel.getStonesIn(index);
        }

        drawStones(g2, bounds, stones);
    }

    private void drawMancala(Graphics2D g2, int index) {
        Shape mancala = slots[index];
        Rectangle bounds = mancala.getBounds();

        g2.setColor(new Color(210, 210, 210));
        g2.fill(mancala);

        g2.setColor(Color.BLACK);
        g2.draw(mancala);

        String label = index == Board.MANCALA_A ? "A" : "B";
        g2.drawString(label, bounds.x + bounds.width / 2 - 4, bounds.y - 8);

        int stones = 0;
        if (boardModel != null) {
            stones = index == Board.MANCALA_A
                    ? boardModel.getMancala(0).getStones()
                    : boardModel.getMancala(1).getStones();
        }

        drawStones(g2, bounds, stones);
    }

    private void drawStones(Graphics2D g2, Rectangle area, int stones) {
        g2.setColor(Color.BLACK);

        for (int i = 0; i < stones; i++) {
            int x = area.x + 15 + (i % 4) * 13;
            int y = area.y + 20 + (i / 4) * 13;
            g2.fillOval(x, y, 10, 10);
        }

        g2.setColor(Color.BLACK);
        g2.drawString(String.valueOf(stones), area.x + area.width / 2 - 4, area.y + area.height / 2);
    }

    private int getClickedSlot(Point p) {
        for (int i = 0; i < slots.length; i++) {
            if (slots[i] != null && slots[i].contains(p)) {
                return i;
            }
        }
        return -1;
    }

    private String getPitLabel(int index) {
        if (index >= 0 && index <= 5) {
            return "A" + (index + 1);
        }
        return "B" + (index - 6);
    }
}
