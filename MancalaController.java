public class MancalaController {

    private MancalaGameModel model;
    private MancalaView view;

    public MancalaController(MancalaGameModel model, MancalaView view) {
        this.model = model;
        this.view = view;
        this.view.setController(this);
    }

    public void startGame(int stonesPerPit) {
        model.initializeGame(stonesPerPit);
        updateView();
    }

    public void handlePitSelection(int pitIndex) {
        if(!model.isValidMove(pitIndex)) {
            view.displayMessage("Invalid move!");
            return;
        }
        model.makeMove(pitIndex);
        updateView();
        if(model.checkGameOver()) {
            int winner = model.getWinner();
            if(winner == -1) {
                view.displayMessage("It's a tie!");
            } else {
                view.displayMessage("Player " + winner + " wins!");
            }
        }
    }

    public void handleUndo() {
        if(model.canUndo()) {
            model.undo();
            updateView();
        } else {
            view.displayMessage("Undo not available.");
        }
    }

    public void updateView() {
        view.refresh(model.getBoard());
    }

    public void setStyle(StyleStrategy style) {
        view.setStyle(style);
        updateView();
    }
}