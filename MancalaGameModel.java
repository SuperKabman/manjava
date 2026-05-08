package manjava;

import java.util.*;

public class MancalaGameModel {

    private Board board;
    private int currentPlayer;
    private int undoCount;
    private Board previousState;
    private boolean undoUsed;
    private boolean gameOver;
    public MancalaGameModel() {
        board = new Board();
        currentPlayer = 0;
        undoCount = 0;
        undoUsed = false;
        gameOver = false;
    }

    public void initializeGame(int stonesPerPit) {
        board.initialize(stonesPerPit);
        currentPlayer = 0;
        undoCount = 0;
        undoUsed = false;
        gameOver = false;
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
        return board.getStonesIn(pitIndex) > 0;
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
            if(index == Board.MANCALA_A) {
                board.addStoneToMancala(0);
            } else if(index == Board.MANCALA_B) {
                board.addStoneToMancala(1);
            } else {
                board.addStoneToPit(index);
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
            gameOver = true;
        }
    }

    public void handleCapture(int index) {
        if(board.isMancalaIndex(index)) {
            return;
        }
        if(board.ownerOf(index) != currentPlayer) {
            return;
        }
        if(board.getStonesIn(index) != 1) {
            return;
        }
        int opposite = board.getOppositePitIndex(index);
        int captured = board.getPit(opposite).removeAllStones();
        if(captured > 0) {
            board.getPit(index).removeAllStones();
            board.getMancala(currentPlayer).addStones(captured + 1);
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
    
    public boolean isGameOver() {
        return gameOver;
    }
}