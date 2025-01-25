package CrossoverAI;

import BitFlipAI.BitFlip;

import java.util.ArrayList;
import java.util.Random;

/**
 * Performs the 1-point crossover on solutions
 */
public class PTX1 {

    /**
     * Applies 1-point crossover to 2 solutions
     *
     * @param solution1 First solution to be crossed
     * @param solution2 Second solution to be crossed
     */
    public static void applyPTX1(ArrayList<Integer> solution1, ArrayList<Integer> solution2) {

        Random rand = new Random();
        int upperBound = solution1.size();
        int random = rand.nextInt(upperBound);

        for (int i = 0; i < random; i++) {
            exchangeBits(solution1, solution2, i);

        }

    }

    /**
     * Exchanges the bits of 2 solutions at a specific bit index
     *
     * @param solution1 First solution to swap the bit of
     * @param solution2 Second solution to swap the bit of
     * @param bitIndex  Bit index at which to switch the 2 bits
     */
    public static void exchangeBits(ArrayList<Integer> solution1, ArrayList<Integer> solution2, int bitIndex) {

        if (solution1.get(bitIndex) != solution2.get(bitIndex)) {
            if (bitIndex != solution1.size() - 1) {
                BitFlip.BitFlip(solution1, bitIndex);
                BitFlip.BitFlip(solution2, bitIndex);
            }
        } else {
            int tempVariable = solution1.get(bitIndex);
            solution1.set(bitIndex, solution2.get(bitIndex));
            solution2.set(bitIndex, tempVariable);
        }


    }
}
