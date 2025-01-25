package HillClimbingAI;

import BitFlipAI.BitFlip;
import Initialization.FileScanner;
import TournamentAI.TournamentSelection;

import java.util.ArrayList;

/**
 * Performs the Davis's Bit algorithm with improving or non-worsening moves on solutions
 */
public class DavissBit_IE {

    /**
     * Applies Davis's Bit algorithm with improving or non-worsening moves to a specific solution
     *
     * @param solution The solution to apply the algorithm to
     */
    public static void applyDavissBit_IE(ArrayList<Integer> solution) {

        double bestProfit = TournamentSelection.getProfit(solution);
        int length = FileScanner.getTotalNum();
        ArrayList<Integer> list = generateRandomPermutation(length);

        for (int i = 0; i < length; i++) {

            solution = BitFlip.BitFlip(solution, list.get(i));

            double tempProfit = TournamentSelection.getProfit(solution);

            if (tempProfit >= bestProfit) {
                bestProfit = tempProfit;
            } else
                solution = BitFlip.BitFlip(solution, list.get(i));

        }

    }

    /**
     * Applies the Davis's Bit IE algorithm a specific number of times based on the depth of search
     *
     * @param solution The solution to apply the algorithm to
     * @param DOS      The depth of search variable to specify how many times to perform the algorithm on the inputted solution
     */
    public static void applyHeuristic(ArrayList<Integer> solution, double DOS) {

        if (DOS >= 0.0 && DOS < 0.2) {
            applyDavissBit_IE(solution);

        } else if (DOS >= 0.2 && DOS < 0.4) {
            int i = 0;
            while (i < 2) {
                applyDavissBit_IE(solution);
                i++;
            }

        } else if (DOS >= 0.4 && DOS < 0.6) {
            int i = 0;
            while (i < 3) {
                applyDavissBit_IE(solution);
                i++;
            }

        } else if (DOS >= 0.6 && DOS < 0.8) {
            int i = 0;
            while (i < 4) {
                applyDavissBit_IE(solution);
                i++;
            }

        } else if (DOS >= 0.8 && DOS < 1.0) {
            int i = 0;
            while (i < 5) {
                applyDavissBit_IE(solution);
                i++;
            }

        } else if (DOS == 1.0) {
            int i = 0;
            while (i < 6) {
                applyDavissBit_IE(solution);
                i++;
            }

        } else
            System.out.println("Invalid IOM Inputted");

    }

    /**
     * Shuffles and returns an arraylist to generate a random permutation
     *
     * @param size Size of the arraylist
     * @return An arraylist to generate a random permutation
     */
    static private ArrayList<Integer> generateRandomPermutation(int size) {

        ArrayList<Integer> list = new ArrayList<>();
        //int[] list = new int[length];
        for (int i = 0; i < size; i++) {
            list.add(i);
        }

        java.util.Collections.shuffle(list);

        return list;

    }
}
