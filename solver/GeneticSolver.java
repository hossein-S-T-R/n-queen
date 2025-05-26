
package solver;

import model.Board;
import java.util.*;

public class GeneticSolver implements Solver {
    private static final int POP_SIZE = 100;
    private static final int MAX_GEN = 1000;

    @Override
    public Board solve(int n) {
        List<int[]> population = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < POP_SIZE; i++) {
            int[] individual = new int[n];
            for (int j = 0; j < n; j++) individual[j] = j;
            for (int j = 0; j < n; j++) {
                int swap = rand.nextInt(n);
                int temp = individual[j];
                individual[j] = individual[swap];
                individual[swap] = temp;
            }
            population.add(individual);
        }

        for (int gen = 0; gen < MAX_GEN; gen++) {
            population.sort(Comparator.comparingInt(this::fitness));
            if (fitness(population.get(0)) == 0) return new Board(population.get(0));

            List<int[]> newPop = new ArrayList<>();
            for (int i = 0; i < POP_SIZE / 2; i++) {
                int[] parent1 = population.get(i);
                int[] parent2 = population.get(POP_SIZE - 1 - i);
                int[] child = crossover(parent1, parent2);
                mutate(child);
                newPop.add(child);
            }
            population = newPop;
        }
        return null;
    }

    private int fitness(int[] individual) {
        int conflicts = 0;
        for (int i = 0; i < individual.length; i++) {
            for (int j = i + 1; j < individual.length; j++) {
                if (Math.abs(i - j) == Math.abs(individual[i] - individual[j])) conflicts++;
            }
        }
        return conflicts;
    }

    private int[] crossover(int[] p1, int[] p2) {
        int[] child = Arrays.copyOf(p1, p1.length);
        for (int i = 0; i < p1.length; i++) {
            if (Math.random() < 0.5) child[i] = p2[i];
        }
        return child;
    }

    private void mutate(int[] individual) {
        if (Math.random() < 0.2) {
            int i = (int)(Math.random() * individual.length);
            int j = (int)(Math.random() * individual.length);
            int temp = individual[i];
            individual[i] = individual[j];
            individual[j] = temp;
        }
    }
}
