package Initialization;

import PopulationAI.Population;
import ReplacementAI.Replacement;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Creates and writes to an output file to display the information generated from running the algorithm
 */
public class FileWrite {

    /**
     * Creates and returns a new file to store the output created by running the algorithm
     *
     * @return A new file to store the output created by running the algorithm
     */
    public static File createFile() {

        File newFile = null;
        try {
            newFile = new File("Resources/output.txt");
            if (newFile.createNewFile()) {
                System.out.println("File created: " + newFile.getName());
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return newFile;
    }

    /**
     * Deletes the old version of the file if the algorithm is run another time
     */
    public static void deleteFile() {

        File newFile = new File("Resources/output.txt");
        if (newFile.exists()) {
            newFile.delete();
        }
    }

    /**
     * Appends the output file to contain the data of new generations and new trials
     *
     * @param file          The file to be appended
     * @param population    The new population that has been created
     * @param trialNum      The trial number
     * @param generationNum The generation number
     * @throws IOException Throws an exception if an IO operation fails while trying to access the file
     */
    public static void writeFile(File file, Population population, int trialNum, int generationNum) throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter(("Resources/" + file.getName()), true));
        if (generationNum == 1)
            writer.write("\nTrial #" + trialNum + "\n\n");
        writer.write("\nGeneration " + generationNum + "\n\n");
        writer.write("Best Objective Value : \n");
        writer.write(" " + Replacement.returnLargestProfit(Population.getPopulation().getPopArray()) + "\n\n");
        writer.write("Best Objective Solution : \n");
        ArrayList<Integer> bestSolution = Replacement.returnBestSolution(Population.getPopulation().getPopArray());
        StringBuilder sb = new StringBuilder();
        int j = 0;
        for (Integer solution : bestSolution) {
            if (j < FileScanner.getTotalNum()) {
                sb.append(solution != null ? solution.toString() : "");
                j++;
            }
        }
        writer.write(" " + sb.toString() + "\n\n");

        writer.write("Worst Objective Value : \n");
        writer.write(" " + Replacement.returnSmallestProfit(population.getPopArray()) + "\n\n");
        writer.write("Worst Objective Solution : \n");
        ArrayList<Integer> worstSolution = Replacement.returnWorstSolution(population.getPopArray());
        StringBuilder sb2 = new StringBuilder();
        j = 0;
        for (Integer solution : worstSolution) {
            if (j < FileScanner.getTotalNum()) {
                sb2.append(solution != null ? solution.toString() : "");
                j++;
            }
        }
        writer.write(" " + sb2.toString() + "\n\n");

        writer.close();

    }
}
