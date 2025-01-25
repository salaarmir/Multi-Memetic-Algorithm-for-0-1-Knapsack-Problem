package Initialization;

import BitFlipAI.BitFlip;
import CrossoverAI.Crossover;
import HillClimbingAI.HillClimbing;
import Memetic.Memeplex;
import PopulationAI.Population;
import ReplacementAI.Replacement;
import TournamentAI.TournamentSelection;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Main class to run the multi-memetic algorithm
 */
public class Main {

    static Population population = Population.getPopulation();

    static final int TOURSIZE = 3;

    static final double IOM = 0.2;

    static final double DOS = 1;

    static final double IR = 1.0 / 3.0;

    static final double numberOf = 100;

    static final double GENERATIONS = 150;

    static final double TRIALS = 5;

    /**
     * Main function to run the algorithm on
     *
     * @param args Command-line arguments are not used for this program
     * @throws IOException Throws an IO exception error if theres an error while trying to access the output file
     */
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        FileWrite.deleteFile();
        for (int i = 1; i <= TRIALS; i++) {

            // Create initial solution using greedy heuristic #1
            Population.getPopulation().createInitialSolution();

            // Create initial population by randomly bitflipping the initial solution
            ArrayList<ArrayList<Integer>> initialPopulation = Population.createInitialPopulation(Population.getInitSolution());

            // If initial population is an odd number randomly duplicate one of the solutons in the population to allow for population/2 iterations in the main loop
            if (initialPopulation.size() % 2 != 0) {
                Random rand = new Random();
                int upperBound = initialPopulation.size();
                int randomSolution = rand.nextInt(upperBound);
                initialPopulation.add(initialPopulation.get(randomSolution));
            }

            // Run main loop for a specified number of generations
            int generationNum = 1;
            File file = FileWrite.createFile();
            while (generationNum <= GENERATIONS) {
                if (generationNum <= numberOf) {
                    FileWrite.writeFile(file, Population.getPopulation(), i, generationNum);

                }
                runMainLoop();
                generationNum++;
            }


        }
    }

    /**
     * The main loop of the multi-memetic algorithm
     */
    public static void runMainLoop() {

        // Initialise offpsring array that will take over the parent array
        ArrayList<ArrayList<Integer>> offSprings = new ArrayList<>();

        // For loop to create children
        for (int i = 0; i < (population.getPopArray().size()) / 2; i++) {

            // Tournament selection to choose parents
            ArrayList<Integer> parent1 = TournamentSelection.selectParents(TOURSIZE);
            ArrayList<Integer> parent2 = TournamentSelection.selectParents(TOURSIZE);

            // Make sure both parents are not equal
            while (parent2 == parent1) {
                parent2 = TournamentSelection.selectParents(TOURSIZE);
            }

            // Copy parent solutions into children
            ArrayList<Integer> child1 = Population.copySolution(parent1);//population.getPopArray().get(parent1);
            ArrayList<Integer> child2 = Population.copySolution(parent2);

            // Crossover children, taking into account the memeplexes
            Crossover.applyCrossover(child1, child2);

            // Perform memetic inheritance on both children
            Memeplex.performMemeticInheritance(child1, parent1, parent2);
            Memeplex.performMemeticInheritance(child2, parent1, parent2);

            // Mutate the memeplexes of the children
            Memeplex.mutateMemeplex(child1, IR);
            Memeplex.mutateMemeplex(child2, IR);

            // Apply hill climbing before bit mutation depending on memeplex, depth of search times
            if ((child1.get(child1.size() - 3) == 0)) {
                HillClimbing.applyHillClimb(child1, DOS);
            }
            if ((child2.get(child2.size() - 3) == 0)) {
                HillClimbing.applyHillClimb(child2, DOS);
            }

            // Perform bit mutation on the children solutions based on intensity of mutation
            BitFlip.bitMutation(child1, IOM);
            BitFlip.bitMutation(child2, IOM);

            // Apply hill climbing after bit mutation depending on memeplex, depth of search times
            if ((child1.get(child1.size() - 3) == 1)) {
                HillClimbing.applyHillClimb(child1, DOS);
            }
            if ((child2.get(child2.size() - 3) == 1)) {
                HillClimbing.applyHillClimb(child2, DOS);
            }

            // Add children solutions to the offspring array
            offSprings.add(child1);
            offSprings.add(child2);

        }

        // Perform transgenerational replacement with elitism to replace old parent array with new children array
        Replacement.transgenerationalReplacementEI(population.getPopArray(), offSprings);

    }

    /**
     * Prints a specified arraylist of solutions
     *
     * @param array The arraylist of solutions to be printed
     */
    public static void printArrayList(ArrayList<ArrayList<Integer>> array) {
        for (int i = 0; i < array.size(); i++) {
            ArrayList<Integer> solution = array.get(i);
            for (int j = 0; j < FileScanner.getTotalNum(); j++) {
                System.out.print("" + solution.get(j));
            }
            System.out.println(" ");
        }
        System.out.println(" ");
    }

    /**
     * Prints a specified solution (arraylist of integers)
     *
     * @param solution The solution to be printed
     */
    public static void printArray(ArrayList<Integer> solution) {
        for (int i = 0; i < FileScanner.getTotalNum(); i++) {
            System.out.print("" + solution.get(i));
        }
        System.out.println(" ");
    }

}

