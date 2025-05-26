package solver;

import model.Board;
import java.util.*;

public class CSPSolver implements Solver {
    private int size;
    private int[] queens;

    @Override
    public Board solve(int n) {
        this.size = n;
        queens = new int[size];
        Arrays.fill(queens, -1);

        if (backtrack(0)) {
            return new Board(queens);
        } else {
            return null;
        }
    }

    private boolean backtrack(int row) {
        if (row == size) return true;

        for (int col = 0; col < size; col++) {
            if (isConsistent(row, col)) {
                queens[row] = col;
                if (backtrack(row + 1)) return true;
                queens[row] = -1;
            }
        }
        return false;
    }

    private boolean isConsistent(int row, int col) {
        for (int i = 0; i < row; i++) {
            if (queens[i] == col || Math.abs(queens[i] - col) == Math.abs(i - row)) {
                return false;
            }
        }
        return true;
    }
}
