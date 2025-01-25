package Memetic;

import BitFlipAI.BitFlip;
import Initialization.FileScanner;
import TournamentAI.TournamentSelection;

import java.util.ArrayList;
import java.util.Random;

/**
 * Creates and modifies the memetic material of the various solutions in the population
 */
public class Memeplex {

    /**
     * Assigns memetic material to the specified solution
     *
     * @param solution The solution to assign the memetic material to
     */
    public static void assignMemeplex(ArrayList<Integer> solution) {
        Random rand = new Random();
        int upperBound = 2;
        int random = rand.nextInt(upperBound);

        while (solution.size() < FileScanner.getTotalNum() + 3) {
            solution.add(random);
            solution.add(random);
            upperBound = 4;
            random = rand.nextInt(upperBound);
            solution.add(random);
        }
    }

    /**
     * Performs memetic inheritance by comparing the memetic material of both parents and assigning the better one to the child
     *
     * @param child   The child to inherit the memetic material
     * @param parent1 The first parent
     * @param parent2 The second parent
     */
    public static void performMemeticInheritance(ArrayList<Integer> child, ArrayList<Integer> parent1, ArrayList<Integer> parent2) {
        double parent1Profit = TournamentSelection.getProfit(parent1);
        double parent2Profit = TournamentSelection.getProfit(parent2);
        Random rand = new Random();
        if (parent1Profit > parent2Profit) {
            copyMemeplex(child, parent1);
        } else if (parent2Profit > parent1Profit) {
            copyMemeplex(child, parent2);
        } else {
            if (rand.nextDouble() < 0.5)
                copyMemeplex(child, parent1);
            else
                copyMemeplex(child, parent2);
        }

    }

    /**
     * Copies the memetic material from the parent solution to the child solution
     *
     * @param child
     * @param parent
     */
    public static void copyMemeplex(ArrayList<Integer> child, ArrayList<Integer> parent) {

        for (int i = child.size() - 3; i < child.size(); i++) {
            child.set(i, parent.get(i));
        }
    }

    /**
     * Mutates the memetic material of a given solution based on the innovation rate
     *
     * @param child The child solution to be mutated
     * @param IR    The innovation rate (this is 1 / number of memes = 1 / 3)
     */
    public static void mutateMemeplex(ArrayList<Integer> child, double IR) {
        Random rand = new Random();
        Random randIR = new Random();
        if (randIR.nextDouble() < IR) {
            for (int i = child.size() - 3; i < child.size(); i++) {
                if (i != child.size() - 1) {
                    if (rand.nextDouble() < 0.5)
                        BitFlip.BitFlip(child, i);
                } else {
                    int upperBound = 4;
                    int random = rand.nextInt(upperBound);
                    if (rand.nextDouble() < 0.5)
                        child.set(i, random);
                }
            }
        }

    }

}
