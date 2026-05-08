package manjava;

public class Board {

    public static final int TOTAL_SLOTS   = 14;
    public static final int MANCALA_A     = 6;   
    public static final int MANCALA_B     = 13;  
    public static final int PITS_PER_SIDE = 6;

    private final Pit[]     pits;     
    private final Mancala[] mancalas; 

    public Board() {
        pits     = new Pit[PITS_PER_SIDE * 2];
        for (int i = 0; i < pits.length; i++) pits[i] = new Pit();
        mancalas = new Mancala[]{ new Mancala(), new Mancala() };
    }


    public void initialize(int stonesPerPit) {
        if (stonesPerPit < 1 || stonesPerPit > 4)
            throw new IllegalArgumentException("there should be 1 to 4 stones per pit");
        for (Pit p : pits) p.setStones(stonesPerPit);

        for (int player = 0; player < 2; player++) {
            int cur = mancalas[player].getStones();

            mancalas[player].setStones(0);
        }
    }



    public Pit getPit(int index) {
        validatePitIndex(index);
        return pits[pitArrayIndex(index)];
    }

    public Mancala getMancala(int player) {
        validatePlayer(player);
        return mancalas[player];
    }

    public int getStonesIn(int index) {
        validatePitIndex(index);
        return pits[pitArrayIndex(index)].getStones();
    }

    public void setStonesIn(int index, int stones) {
        validatePitIndex(index);
        pits[pitArrayIndex(index)].setStones(stones);
    }

    public void addStoneToPit(int index) {
        validatePitIndex(index);
        pits[pitArrayIndex(index)].addStones(1);
    }

    public void addStoneToMancala(int player) {
        validatePlayer(player);
        mancalas[player].addStones(1);
    }

    public boolean isMancalaEmpty(int player) {
        validatePlayer(player);
        return mancalas[player].getStones() == 0;
    }

    public boolean isSideEmpty(int player) {
        validatePlayer(player);
        int start = (player == 0) ? 0 : PITS_PER_SIDE; 
        for (int i = start; i < start + PITS_PER_SIDE; i++) {
            if (pits[i].getStones() > 0) return false;
        }
        return true;
    }

    public int getOppositePitIndex(int index) {
        validatePitIndex(index);

        int arr = pitArrayIndex(index);
        int oppArr = (PITS_PER_SIDE * 2 - 1) - arr; 
        return boardIndex(oppArr);
    }


    public int nextIndex(int index, int skipOpponentMancala) {
        int next = (index + 1) % TOTAL_SLOTS;

        if (skipOpponentMancala == 0 && next == MANCALA_A) next = (next + 1) % TOTAL_SLOTS;
        if (skipOpponentMancala == 1 && next == MANCALA_B) next = (next + 1) % TOTAL_SLOTS;
        return next;
    }

    public int ownerOf(int index) {
        if(index >= 0 && index <= 5) {
            return 0;
        }
        if(index >= 7 && index <= 12) {
            return 1;
        }
        return -1;
    }

    public boolean isMancalaIndex(int index) {
        return index == MANCALA_A || index == MANCALA_B;
    }

    @Override
    public Board clone() {
        Board copy = new Board();

        // Copy pits
        for (int i = 0; i < pits.length; i++) {
            copy.pits[i] = new Pit(pits[i].getStones());
        }

        // Copy mancalas
        for (int i = 0; i < mancalas.length; i++) {
            copy.mancalas[i] = new Mancala();
            copy.mancalas[i].addStones(mancalas[i].getStones());
        }

        return copy;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("   B: ");
        for (int i = 12; i >= 7; i--)
            sb.append(String.format("[%2d] ", getStonesIn(i)));
        sb.append("\n");
        sb.append(String.format("MB:%3d", mancalas[1].getStones()));
        sb.append("                        ");
        sb.append(String.format("MA:%3d\n", mancalas[0].getStones()));
        sb.append("   A: ");
        for (int i = 0; i <= 5; i++)
            sb.append(String.format("[%2d] ", getStonesIn(i)));
        sb.append("\n");
        return sb.toString();
    }

    private int pitArrayIndex(int boardIdx) {
        return (boardIdx < MANCALA_A) ? boardIdx : boardIdx - 1;
    }

    private int boardIndex(int arrayIdx) {
        return (arrayIdx < MANCALA_A) ? arrayIdx : arrayIdx + 1;
    }

    private void validatePitIndex(int index) {
        if (index < 0 || index >= TOTAL_SLOTS || isMancalaIndex(index))
            throw new IllegalArgumentException("Invalid pit index: " + index);
    }

    private void validatePlayer(int player) {
        if (player != 0 && player != 1)
            throw new IllegalArgumentException("Player must be 0 or 1.");
    }


}