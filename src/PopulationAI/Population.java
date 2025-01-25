package PopulationAI;

import BitFlipAI.BitFlip;
import Initialization.FileScanner;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Creates the initial solution and population to be used for the algorithm
 */
public class Population {

    private static Population population = new Population();
    static ArrayList<ArrayList<Integer>> popArray = new ArrayList<>();
    private static FileScanner file = FileScanner.getFileScanner();
    private static ArrayList<Integer> initSolution;
    private static int totalProfit = 0;
    private static int totalWeight = 0;
    double[] cloneProfits;

    /**
     * Creates the initial solution to be added to the initial population using the greedy heuristic #1 that maximises profits
     *
     * @throws FileNotFoundException Throws an exception if the problem instance file can't be found
     */
    public void createInitialSolution() throws FileNotFoundException {
        file.scanFile();
        cloneProfits = file.getProfits().clone();

        double profit = 0;
        int weightFull = 0;
        initSolution = new ArrayList<>();
        for (int j = 0; j < file.getProfits().length; j++) {
            initSolution.add(0);
        }

        while (totalWeight <= file.getMaxWeight()) {
            if (weightFull == 0) {

                profit = biggestProfit();
                for (int i = 0; i < file.getProfits().length; i++) {
                    if (file.getProfits()[i] == profit) {

                        totalWeight += file.getWeights()[i];
                        totalProfit += profit;

                        if (totalWeight > file.getMaxWeight()) {
                            totalWeight -= file.getWeights()[i];
                            totalProfit -= profit;
                            weightFull = 1;
                            break;
                        } else
                            initSolution.set(i, 1);


                    }
                }
            } else
                break;


        }

        popArray.add(initSolution);

    }

    /**
     * Creates and returns the initial population by taking in a solution and randomly bit flipping it to create other solutions
     *
     * @param solution The initial solution to be bit flipped to create the other solutions
     * @return Returns the initial population
     */
    public static ArrayList<ArrayList<Integer>> createInitialPopulation(ArrayList<Integer> solution) {
        for (int i = 0; i < file.getTotalNum() - 1; i++) {
            ArrayList<Integer> newSolution = getPopulation().copySolution(solution);

            while (!getPopulation().checkSolution(BitFlip.randomBitFlip(newSolution))) {
                newSolution = BitFlip.randomBitFlip(newSolution);
            }
            popArray.add(newSolution);

        }

        return popArray;

    }

    /**
     * Checks whether a specified solution's weight is below the maximum weight allowed for the problem instance
     *
     * @param solution The solution that's weight needs to be checked
     * @return True if the solution's weight is less than or equal to the maximum weight, otherwise false
     */
    boolean checkSolution(ArrayList<Integer> solution) {
        int weight = 0;
        for (int i = 0; i < file.getTotalNum(); i++) {
            if (solution.get(i) == 1) {
                weight += file.getWeights()[i];
            }
        }

        return weight <= file.getMaxWeight();
    }

    /**
     * Copies and returns a solution
     *
     * @param solution The solution to be copied
     * @return A copy of the specified solution
     */
    public static ArrayList<Integer> copySolution(ArrayList<Integer> solution) {

        ArrayList<Integer> newSolution = new ArrayList<>();

        for (int i = 0; i < solution.size(); i++) {
            newSolution.add(solution.get(i));
        }

        return newSolution;
    }

    /**
     * Returns the biggest profit from the array of profits of the problem instance and sets this value to 0 to be able to find the next largest profit in the array
     *
     * @return The biggest profit from the array of profits of the problem instance
     */
    double biggestProfit() {

        double current = cloneProfits[0];
        for (int i = 0; i < file.getProfits().length; i++) {
            if (cloneProfits[i] > current) {
                current = cloneProfits[i];
            }

        }
        for (int j = 0; j < file.getProfits().length; j++) {
            if (cloneProfits[j] == current) {
                cloneProfits[j] = 0;
            }

        }

        return current;
    }

    /**
     * Returns the singleton population object
     *
     * @return The singleton population object
     */
    public static Population getPopulation() {
        if (population == null)
            population = new Population();

        return population;
    }

    /**
     * Returns the initial solution
     *
     * @return The initial solution
     */
    public static ArrayList<Integer> getInitSolution() {
        return initSolution;
    }

    /**
     * Returns the population array
     *
     * @return The population array
     */
    public ArrayList<ArrayList<Integer>> getPopArray() {
        return popArray;
    }


}
