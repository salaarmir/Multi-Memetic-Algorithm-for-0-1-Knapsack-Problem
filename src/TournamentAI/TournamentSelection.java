package TournamentAI;

import Initialization.FileScanner;
import Memetic.Memeplex;
import PopulationAI.Population;

import java.util.ArrayList;
import java.util.Random;

/**
 * Selects the parents with the best fitness in the population to be passed on to the offspring
 */
public class TournamentSelection {

    static FileScanner file = FileScanner.getFileScanner();

    /**
     * Selects and returns the tour group from which the parents will be selected
     *
     * @param solutions The population array from which to select the tour group
     * @param tourSize  The tour size
     * @return The tour group
     */
    static ArrayList<ArrayList<Integer>> selectTour(ArrayList<ArrayList<Integer>> solutions, int tourSize) {
        ArrayList<ArrayList<Integer>> selectedPopulation = new ArrayList<>();

        while (selectedPopulation.size() < tourSize) {
            ArrayList<Integer> randomSolution = randomSolution((solutions));
            selectedPopulation.add(randomSolution);
        }

        return selectedPopulation;
    }

    /**
     * Select and return the parents from the tour group with the best fitness
     *
     * @param tourSize The tour size
     * @return The parents from the tour group with the best fitness
     */
    public static ArrayList<Integer> selectParents(int tourSize) {

        ArrayList<Integer> parent = new ArrayList<>();
        ArrayList<ArrayList<Integer>> tour1 = selectTour(Population.getPopulation().getPopArray(), tourSize);

        for (int i = 0; i < tour1.size(); i++) {
            ArrayList<Integer> solution = tour1.get(i);
            double profit = getProfit(solution);
            if (profit == findLargestProfit(tour1)) {
                parent = solution;
            }
        }

        Memeplex.assignMemeplex(parent);
        return parent;
    }

    /**
     * Selects and returns a random solution from a specified array of solutions
     *
     * @param solutions The array of solutions from which to return a random solution
     * @return A random solution from a specified array of solutions
     */
    static ArrayList<Integer> randomSolution(ArrayList<ArrayList<Integer>> solutions) {
        Random rand = new Random();
        int upperBound = solutions.size();
        int randomNumber = rand.nextInt(upperBound);

        return solutions.get(randomNumber);
    }

    /**
     * Returns the profit value of a specified solution
     *
     * @param solution The specified solution to return the profit of
     * @return The profit value
     */
    public static Double getProfit(ArrayList<Integer> solution) {
        double profit = 0;
        for (int i = 0; i < FileScanner.getTotalNum(); i++) {
            if (solution.get(i) == 1) {
                profit += file.getProfits()[i];
            }
        }
        return profit;
    }

    /**
     * Returns the weight value of a specified solution
     *
     * @param solution The specified solution to return the weight of
     * @return The weight value
     */
    public static Double getWeight(ArrayList<Integer> solution) {
        double weight = 0;
        for (int i = 0; i < FileScanner.getTotalNum(); i++) {
            if (solution.get(i) == 1) {
                weight += file.getWeights()[i];
            }
        }
        return weight;
    }

    /**
     * Returns the profit value of the solution with the largest profit in a specified array of solutions
     *
     * @param solutions The array of solutions
     * @return The profit value
     */
    static Double findLargestProfit(ArrayList<ArrayList<Integer>> solutions) {
        double largestProfit = 0;
        for (int i = 0; i < solutions.size(); i++) {
            ArrayList<Integer> solution = solutions.get(i);
            double currentProfit = getProfit(solution);
            if (currentProfit > largestProfit) {
                largestProfit = currentProfit;
            }
        }

        return largestProfit;
    }

}
