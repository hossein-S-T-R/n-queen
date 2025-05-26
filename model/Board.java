
package model;

public class Board {
    private final int[] queens;

    public Board(int[] queens) {
        this.queens = queens.clone();
    }

    public int[] getQueens() {
        return queens;
    }

    public int getSize() {
        return queens.length;
    }
}
