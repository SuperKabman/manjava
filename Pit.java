package manjava;

public class Pit {
    private int stones;

    public Pit() {
        this.stones = 0;
    }

    public Pit(int initialStones) {
        if (initialStones < 0) throw new IllegalArgumentException("stone count can't be negative.");
        this.stones = initialStones;
    }

    public int getStones() {
        return stones;
    }


    public int addStones(int stones) {
        if (stones < 0) throw new IllegalArgumentException("can't add negative stones.");
        this.stones += stones;
        return this.stones;
    }


    public int removeAllStones() {
        int removed = this.stones;
        this.stones = 0;
        return removed;
    }

    public boolean isEmpty() {
        return stones == 0;
    }

    public int setStones(int stones) {
        if (stones < 0) throw new IllegalArgumentException("stone count can't be negative.");
        this.stones = stones;
        return this.stones;
    }

    @Override
    public String toString() {
        return "Pit[" + stones + "]";
    }
}
