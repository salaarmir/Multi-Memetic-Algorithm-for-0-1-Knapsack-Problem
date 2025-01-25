package CrossoverAI;

import java.util.ArrayList;

/**
 * Applies the crossover heuristic on solutions
 */
public class Crossover {

    /**
     * Selects between applying uniform crossover or 1-point crossover based on the memetic material of the first solution
     *
     * @param solution1 First solution to be crossed
     * @param solution2 Second solution to be crossed
     */
    public static void applyCrossover(ArrayList<Integer> solution1, ArrayList<Integer> solution2) {
        if (solution1.get(solution1.size() - 2) == 0) {
            UniformXO.applyUniformXO(solution1, solution2);
        }
        if (solution1.get(solution1.size() - 2) == 1) {
            PTX1.applyPTX1(solution1, solution2);
        }
    }
}
