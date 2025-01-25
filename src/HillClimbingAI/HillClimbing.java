package HillClimbingAI;

import java.util.ArrayList;
import java.util.Random;

/**
 * Applies the hill climbing heuristic to solutions
 */
public class HillClimbing {

    /**
     * Selects between which of the hill climbing algorithms to perform on the inputted solution based on its memetic material
     *
     * @param solution The solution to perform the hill climbing algorithm on
     * @param DOS      Depth of search variable to specify how many times to perform the algorithm on the solution
     */
    public static void applyHillClimb(ArrayList<Integer> solution, double DOS) {

        Random rand = new Random();
        int upperBound = 4;
        int random = rand.nextInt(upperBound);
        if (solution.get(solution.size() - 1) == 0) {
            DavissBit_IE.applyHeuristic(solution, DOS);
        } else if (solution.get(solution.size() - 1) == 1) {
            DavissBit_IO.applyHeuristic(solution, DOS);
        } else if (solution.get(solution.size() - 1) == 2) {
            SteepestDescent_IE.applyHeuristic(solution, DOS);
        } else {
            SteepestDescent_OI.applyHeuristic(solution, DOS);
        }
    }
}
