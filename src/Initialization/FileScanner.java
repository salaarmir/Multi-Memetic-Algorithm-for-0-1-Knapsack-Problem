package Initialization;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Scans the problem instance file to collect and store the information about the problem
 */
public class FileScanner {

    private static FileScanner file = new FileScanner();
    private static int totalNum;
    private static int maxWeight;

    private static double[] profits;
    private static double[] weights;

    private final String FILENAME = "Resources/hiddenInstances/hidden5_23_10000.txt";

    /**
     * Scans the problem instance file to collect and store all the information about the problem
     *
     * @throws FileNotFoundException Error thrown if the problem instance file is not found
     */
    public void scanFile() throws FileNotFoundException {

        File testInstance = new File(FILENAME);
        Scanner scanner = new Scanner(testInstance);

        int[] numweight = new int[2];
        int k = 0;

        while (k < 2) {
            numweight[k] = scanner.nextInt();
            k++;
        }

        totalNum = numweight[0];
        maxWeight = numweight[1];

        profits = new double[totalNum];
        weights = new double[totalNum];

        int i = 0;

        while (scanner.hasNextLine()) {

            profits[i] = scanner.nextDouble();
            weights[i] = scanner.nextDouble();
            i++;

        }


    }

    /**
     * Returns the singleton FileScanner object
     *
     * @return the singleton FileScanner object
     */
    public static FileScanner getFileScanner() {
        if (file == null)
            file = new FileScanner();

        return file;
    }

    /**
     * Returns the total number of items in the problem instance
     *
     * @return the total number of items in the problem instance
     */
    public static int getTotalNum() {
        return totalNum;
    }

    /**
     * Returns the max weight that can be stored in the knapsack
     *
     * @return The max weight that can be stored in the knapsack
     */
    public static int getMaxWeight() {
        return maxWeight;
    }

    /**
     * Returns the array of profits of each of the different items in the problem instance
     *
     * @return The array of profits of each of the different items in the problem instance
     */
    public double[] getProfits() {
        return profits;
    }

    /**
     * Returns the array of weights of each of the different items in the problem instance
     *
     * @return The array of weights of each of the different items in the problem instance
     */
    public double[] getWeights() {
        return weights;
    }


}
