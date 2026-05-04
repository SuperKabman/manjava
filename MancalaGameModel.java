import java.util.*;

public class MancalaGameModel {

    private Board board;
    private int currentPlayer;
    private int undoCount;
    private Board previousState;
    private boolean undoUsed;

    public MancalaGameModel() {
        board = new Board();
        currentPlayer = 0;
        undoCount = 0;
        undoUsed = false;
    }

    public void initializeGame(int stonesPerPit) {
        board.initialize(stonesPerPit);
        currentPlayer = 0;
        undoCount = 0;
        undoUsed = false;
    }

    public boolean isValidMove(int pitIndex) {
        if(pitIndex < 0 || pitIndex > 11) {
            return false;
        }
        if(currentPlayer == 0 && pitIndex > 5) {
            return false;
        }
        if(currentPlayer == 1 && pitIndex < 6) {
            return false;
        }
        return board.getStonesInPit(pitIndex) > 0;
    }

    public void makeMove(int pitIndex) {
        if(!isValidMove(pitIndex)) {
            return;
        }
        saveState();
        int stones = board.getPit(pitIndex).removeAllStones();
        int index = pitIndex;
        while(stones > 0) {
            index = (index + 1) % 14;
            if(currentPlayer == 0 && index == 13) {
                continue;
            }
            if(currentPlayer == 1 && index == 6) {
                continue;
            }
            if(index == 6) {
                board.addStoneToMancala(0);
            } else if(index == 13) {
                board.addStoneToMancala(1);
            } else {
                int pitIdx;
                if(index < 6) {
                    pitIdx = index;
                } else {
                    pitIdx = index - 1;
                }
                board.addStoneToPit(pitIdx);
            }
            stones--;
        }
        handleCapture(index);
        if(!endsInMancala(index)) {
            switchPlayer();
        }
        undoUsed = false;
        if(checkGameOver()) {
            collectRemainingStones();
        }
    }

    public void handleCapture(int index) {
        if(index == 6 || index == 13) {
            return;
        }
        int pitIdx;
        if(index < 6) {
            pitIdx = index;
        } else {
            pitIdx = index - 1;
        }
        if(currentPlayer == 0 && pitIdx >= 0 && pitIdx <= 5) {
            if(board.getStonesInPit(pitIdx) == 1) {
                int opposite = 11 - pitIdx;
                int captured = board.getPit(opposite).removeAllStones();
                if(captured > 0) {
                    board.getPit(pitIdx).removeAllStones();
                    board.getMancala(0).addStones(captured + 1);
                }
            }
        }
        if(currentPlayer == 1 && pitIdx >= 6 && pitIdx <= 11) {
            if(board.getStonesInPit(pitIdx) == 1) {
                int opposite = 11 - pitIdx;
                int captured = board.getPit(opposite).removeAllStones();
                if(captured > 0) {
                    board.getPit(pitIdx).removeAllStones();
                    board.getMancala(1).addStones(captured + 1);
                }
            }
        }
    }

    public boolean endsInMancala(int index) {
        if(currentPlayer == 0 && index == 6) {
            return true;
        }
        if(currentPlayer == 1 && index == 13) {
            return true;
        }
        return false;
    }

    public boolean checkGameOver() {
        if(board.isSideEmpty(0) || board.isSideEmpty(1)) {
            return true;
        }
        return false;
    }

    public void collectRemainingStones() {
        for(int i = 0; i < 6; i++) {
            board.getMancala(0).addStones(board.getPit(i).removeAllStones());
        }
        for(int i = 6; i < 12; i++) {
            board.getMancala(1).addStones(board.getPit(i).removeAllStones());
        }
    }

    public int getWinner() {
        int p0 = board.getMancala(0).getStones();
        int p1 = board.getMancala(1).getStones();
        if(p0 > p1) {
            return 0;
        }
        if(p1 > p0) {
            return 1;
        }
        return -1;
    }

    public void switchPlayer() {
        currentPlayer = 1 - currentPlayer;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public Board getBoard() {
        return board;
    }

    public void saveState() {
        previousState = board.clone();
    }

    public void undo() {
        if(canUndo()) {
            board = previousState.clone();
            undoCount++;
            undoUsed = true;
        }
    }

    public boolean canUndo() {
        if(previousState != null && !undoUsed && undoCount < 3) {
            return true;
        }
        return false;
    }
}