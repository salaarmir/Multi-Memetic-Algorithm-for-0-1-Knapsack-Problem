package ReplacementAI;

import TournamentAI.TournamentSelection;

import java.util.ArrayList;

/**
 * Uses transgenerational replacement with elitism to replace the initial population with the new and improved population after the algorithm is run
 */
public class Replacement {

    /**
     * Performs transgenerational replacement on an array using the values in a new array
     *
     * @param initialArray The initial array to be replaced
     * @param newArray     The new array that will replace the initial one
     */
    public static void transgenerationalReplacement(ArrayList<ArrayList<Integer>> initialArray, ArrayList<ArrayList<Integer>> newArray) {
        for (int i = 0; i < initialArray.size(); i++) {
            initialArray.set(i, newArray.get(i));
        }
    }

    /**
     * Performs transgenerational replacement with elitism by comparing the best fitness values in the initial array and new array
     *
     * @param initialArray The initial array to be replaced
     * @param newArray     The new array that will replace the initial one
     */
    public static void transgenerationalReplacementEI(ArrayList<ArrayList<Integer>> initialArray, ArrayList<ArrayList<Integer>> newArray) {

        double initProfit = returnLargestProfit(initialArray);
        double newProfit = returnLargestProfit(newArray);

        if (initProfit > newProfit) {
            ArrayList<Integer> bestSolution = returnBestSolution(initialArray);

            copyToArray(bestSolution, newArray);
            transgenerationalReplacement(initialArray, newArray);
        } else
            transgenerationalReplacement(initialArray, newArray);

    }

    /**
     * Returns the largest profit that is contained in a specific array
     *
     * @param array The array of which to find the largest profit
     * @return The largest profit that is contained in a specific array
     */
    public static double returnLargestProfit(ArrayList<ArrayList<Integer>> array) {

        double largestProfit = 0;

        for (int i = 0; i < array.size(); i++) {
            ArrayList<Integer> tempSolution = array.get(i);
            double currentProfit = TournamentSelection.getProfit(tempSolution);
            if (currentProfit > largestProfit) {
                largestProfit = currentProfit;
            }
        }

        return largestProfit;

    }

    /**
     * Returns the solution with the largest profit (best fitness value) in a specified array
     *
     * @param array The array of which to return the best solution
     * @return The solution with the largest profit (best fitness value) in a specified array
     */
    public static ArrayList<Integer> returnBestSolution(ArrayList<ArrayList<Integer>> array) {
        ArrayList<Integer> bestSolution = new ArrayList<>();

        for (int i = 0; i < array.size(); i++) {
            ArrayList<Integer> tempSolution = array.get(i);
            double tempProfit = TournamentSelection.getProfit(tempSolution);
            if (tempProfit == returnLargestProfit(array))
                bestSolution = tempSolution;
        }

        return bestSolution;
    }

    /**
     * Returns the smallest profit that is contained in a specific array
     *
     * @param array The array of which to find the smallest profit
     * @return The smallest profit that is contained in a specific array
     */
    public static double returnSmallestProfit(ArrayList<ArrayList<Integer>> array) {
        double smallestProfit = returnLargestProfit(array);
        for (int i = 0; i < array.size(); i++) {
            ArrayList<Integer> tempSolution = array.get(i);
            double tempProfit = TournamentSelection.getProfit(tempSolution);
            if (tempProfit < smallestProfit) {
                smallestProfit = tempProfit;
            }
        }

        return smallestProfit;

    }

    /**
     * Returns the solution with the smallest profit (best fitness value) in a specified array
     *
     * @param array The array of which to return the worst solution
     * @return The solution with the smallest profit (best fitness value) in a specified array
     */
    public static ArrayList<Integer> returnWorstSolution(ArrayList<ArrayList<Integer>> array) {
        ArrayList<Integer> worstSolution = new ArrayList<>();

        for (int i = 0; i < array.size(); i++) {
            ArrayList<Integer> tempSolution = array.get(i);
            double tempProfit = TournamentSelection.getProfit(tempSolution);
            if (tempProfit == returnSmallestProfit(array))
                worstSolution = tempSolution;
        }

        return worstSolution;
    }

    /**
     * Replaces the worst solution in a specified array with the passed in solution
     *
     * @param solution The solution which should replace the worst solution in the array
     * @param array    The array of which the worst solution should be replaced
     */
    public static void copyToArray(ArrayList<Integer> solution, ArrayList<ArrayList<Integer>> array) {
        for (int i = 0; i < array.size(); i++) {
            ArrayList<Integer> tempSolution = array.get(i);
            if (tempSolution == returnWorstSolution(array)) {
                array.set(i, solution);
            }
        }

    }


}
