package BitFlipAI;

import Initialization.FileScanner;
import TournamentAI.TournamentSelection;

import java.util.ArrayList;
import java.util.Random;

/**
 * Applies bit flips on solutions
 */
public class BitFlip {

    /**
     * Performs a bitflip on a randomly selected bit from an inputted solution
     *
     * @param solution The solution to perform a bitflip on
     * @return The solution after one of the bits has been randomly flipped
     */
    public static ArrayList<Integer> randomBitFlip(ArrayList<Integer> solution) {

        Random rand = new Random();
        int upperBound = FileScanner.getTotalNum();
        int randomNumber = rand.nextInt(upperBound);

        if (solution.get(randomNumber) == 1)
            solution.set(randomNumber, 0);
        else
            solution.set(randomNumber, 1);

        if (TournamentSelection.getWeight(solution) > FileScanner.getMaxWeight()) {
            if (solution.get(randomNumber) == 1)
                solution.set(randomNumber, 0);
            else if (solution.get(randomNumber) == 0)
                solution.set(randomNumber, 1);
        }

        return solution;
    }

    /**
     * Flips the bit at the inputted bit index in the inputted solution
     *
     * @param solution The solution to conduct the bitflip on
     * @param index    The bit index to be flipped
     * @return The new solution after the specified bit has been flipped
     */
    public static ArrayList<Integer> BitFlip(ArrayList<Integer> solution, int index) {

        if (solution.get(index) == 1) {
            solution.set(index, 0);
        } else if (solution.get(index) == 0)
            solution.set(index, 1);

        if (TournamentSelection.getWeight(solution) > FileScanner.getMaxWeight()) {
            if (solution.get(index) == 1)
                solution.set(index, 0);
            else if (solution.get(index) == 0)
                solution.set(index, 1);
        }

        return solution;
    }

    /**
     * Performs the randomBitFlip function on an inputted solution a certain number of times
     *
     * @param solution The solution to perform the random bitflip on
     * @param IOM      Intensity of mutation to specify how many times to invoke the randomBitFlip function
     * @return The new solution after the random btflips have been done
     */
    public static ArrayList<Integer> bitMutation(ArrayList<Integer> solution, double IOM) {

        if (IOM >= 0.0 && IOM < 0.2) {
            randomBitFlip(solution);

        } else if (IOM >= 0.2 && IOM < 0.4) {
            int i = 0;
            while (i < 2) {
                randomBitFlip(solution);
                i++;
            }

        } else if (IOM >= 0.4 && IOM < 0.6) {
            int i = 0;
            while (i < 3) {
                randomBitFlip(solution);
                i++;
            }

        } else if (IOM >= 0.6 && IOM < 0.8) {
            int i = 0;
            while (i < 4) {
                randomBitFlip(solution);
                i++;
            }

        } else if (IOM >= 0.8 && IOM < 1.0) {
            int i = 0;
            while (i < 5) {
                randomBitFlip(solution);
                i++;
            }

        } else if (IOM == 1.0) {
            int i = 0;
            while (i < 6) {
                randomBitFlip(solution);
                i++;
            }

        } else
            System.out.println("Invalid IOM Inputted");

        return solution;
    }

}
