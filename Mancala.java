package manjava;

public class Mancala {
    private int stones;
    public Mancala() {
        this.stones = 0;
    }
    public int getStones() {
        return stones;
    }
    public void setStones(int stones) {
        this.stones = stones;
    }
    public void addStones(int stones) {
        if (stones < 0) throw new IllegalArgumentException("can't add negative stones.");
        this.stones += stones;
    }

    @Override
    public String toString() {
        return "Mancala[" + stones + "]";
    }
}