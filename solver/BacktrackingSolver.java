
package solver;

import model.Board;

public class BacktrackingSolver implements Solver {
    private int size;
    private int[] queens;

    @Override
    public Board solve(int n) {
        this.size = n;
        this.queens = new int[n];
        return placeQueen(0) ? new Board(queens) : null;
    }

    private boolean placeQueen(int row) {
        if (row == size) return true;
        for (int col = 0; col < size; col++) {
            if (isSafe(row, col)) {
                queens[row] = col;
                if (placeQueen(row + 1)) return true;
            }
        }
        return false;
    }

    private boolean isSafe(int row, int col) {
        for (int i = 0; i < row; i++) {
            if (queens[i] == col || Math.abs(i - row) == Math.abs(queens[i] - col)) return false;
        }
        return true;
    }
}
