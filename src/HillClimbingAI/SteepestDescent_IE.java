package HillClimbingAI;

import BitFlipAI.BitFlip;
import TournamentAI.TournamentSelection;

import java.util.ArrayList;

/**
 * Performs the Steepest Descent algorithm with improving or non-worsening moves on solutions
 */
public class SteepestDescent_IE {

    /**
     * Applies the Steepest Descent algorithm with improving or non-worsening moves to a specific solution
     *
     * @param solution The solution to apply the algorithm to
     */
    public static void applySteepestDescent_IE(ArrayList<Integer> solution) {
        double bestProfit = TournamentSelection.getProfit(solution);
        boolean improved = false;
        int bestIndex = -1;

        for (int i = 0; i < solution.size(); i++) {

            BitFlip.BitFlip(solution, i);
            double tempProfit = TournamentSelection.getProfit(solution);

            if (tempProfit >= bestProfit) {
                bestProfit = tempProfit;
                bestIndex = i;
                improved = true;
            }
            BitFlip.BitFlip(solution, i);
        }

        if (improved)
            BitFlip.BitFlip(solution, bestIndex);

    }

    /**
     * Applies the Steepest Descent IE algorithm a specific number of times based on the depth of search
     *
     * @param solution The solution to apply the algorithm to
     * @param DOS      The depth of search variable to specify how many times to perform the algorithm on the inputted solution
     */
    public static void applyHeuristic(ArrayList<Integer> solution, double DOS) {

        if (DOS >= 0.0 && DOS < 0.2) {
            applySteepestDescent_IE(solution);

        } else if (DOS >= 0.2 && DOS < 0.4) {
            int i = 0;
            while (i < 2) {
                applySteepestDescent_IE(solution);
                i++;
            }

        } else if (DOS >= 0.4 && DOS < 0.6) {
            int i = 0;
            while (i < 3) {
                applySteepestDescent_IE(solution);
                i++;
            }

        } else if (DOS >= 0.6 && DOS < 0.8) {
            int i = 0;
            while (i < 4) {
                applySteepestDescent_IE(solution);
                i++;
            }

        } else if (DOS >= 0.8 && DOS < 1.0) {
            int i = 0;
            while (i < 5) {
                applySteepestDescent_IE(solution);
                i++;
            }

        } else if (DOS == 1.0) {
            int i = 0;
            while (i < 6) {
                applySteepestDescent_IE(solution);
                i++;
            }

        } else
            System.out.println("Invalid IOM Inputted");


    }
}
