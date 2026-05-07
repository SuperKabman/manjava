package manjava;

import javax.swing.*;
import java.awt.*;
// import java.awt.geom.*;


public class MancalaView extends JFrame {
    private static final int WINDOW_WIDTH = 970;
    private static final int WINDOW_HEIGHT = 600;

    private JButton startButton;
    private JButton undoButton;
    private JButton styleButton;

    private JLabel messageLabel;

    // private String styleStrategy;
    
    public MancalaView() {
        /*
            Main View Setup
        */
        setTitle("Mancala Game View");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setMinimumSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        setMaximumSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));

        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Top Panel
        JPanel topPanel = drawTopPanel();

        // Board Area Panel
        BoardPanel board = new BoardPanel();

        //Left Spacer for Board Panel
        JPanel leftSpacer = new JPanel();
        leftSpacer.setPreferredSize(new Dimension(71, 0));

        // Bottom Text Panel
        JPanel messagePanel = new JPanel();
        messagePanel.setPreferredSize(new Dimension(getWidth(), 100));
        messagePanel.setLayout(new GridBagLayout());
        
        messageLabel = new JLabel("Press Start to begin the game!", SwingConstants.CENTER);
        messageLabel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        messageLabel.setBorder(
            BorderFactory.createEmptyBorder(0, 0, 25, 0));
        
        messagePanel.add(messageLabel);

        // adding to main panel
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(board, BorderLayout.CENTER);
        mainPanel.add(messagePanel, BorderLayout.SOUTH);
        mainPanel.add(leftSpacer, BorderLayout.WEST);

        // adding main panel to frame
        add(mainPanel);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    JPanel drawTopPanel() {
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());

        // Top right corner buttons
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(8, 0, 0, 3));

        styleButton = new JButton("Change Style");
        styleButton.setAlignmentX(Component.RIGHT_ALIGNMENT);

        undoButton = new JButton("Undo");
        undoButton.setAlignmentX(Component.RIGHT_ALIGNMENT);

        buttonsPanel.add(styleButton);
        buttonsPanel.add(undoButton);

        // Left side spacer of equal length to right panel
        JPanel topLeftSpacer = new JPanel();
        topLeftSpacer.setPreferredSize(buttonsPanel.getPreferredSize());

        // Title panel
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setBorder(
            BorderFactory.createEmptyBorder(45, 0, 25, 0)); 
            // spacing from top and bottom

        startButton = new JButton("Start");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel gameTitle = new JLabel("Mancala Game", SwingConstants.CENTER);
        gameTitle.setFont(new Font("Times New Roman", Font.PLAIN, 28));
        gameTitle.setBorder(
            BorderFactory.createEmptyBorder(0, 0, 5, 0));
            // spacing between title and start button
        gameTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        titlePanel.add(gameTitle);
        titlePanel.add(startButton);

        topPanel.add(topLeftSpacer, BorderLayout.WEST);
        topPanel.add(titlePanel, BorderLayout.CENTER);
        topPanel.add(buttonsPanel, BorderLayout.EAST);

        return topPanel;
    }

    void refresh(/* Board b */) {

    }

    void render(/* Board b */) {
        repaint();
    }

    void setController(/* MancalaController m */) {

    }

    void setStyle(/*StyleStrategy s*/) {

    }
}
