# Multi-Memetic Algorithm for the 0-1 Knapsack Problem

This repository contains the code for a multi-memetic algorithm designed to solve the 0-1 Knapsack problem. Memetic algorithms combine the global search power of evolutionary algorithms with local optimisation strategies, making them well-suited for complex combinatorial problems like the knapsack problem.

## Project Overview

The 0-1 Knapsack problem is a classic optimisation problem where the goal is to maximize the total value of items placed in a knapsack without exceeding its weight capacity. This project implements a multi-memetic approach, combining genetic operators (e.g., crossover, mutation) with local search techniques to efficiently explore the solution space.

## Features

* **Genetic Operators**: Crossover and mutation strategies for diverse exploration.
* **Local Search**: Hill climbing methods for rapid local optimisation.
* **Flexible Problem Instances**: Easily switch between different test instances.

## Code Structure

```
📁 Multi-Memetic-Algorithm-for-0-1-Knapsack-Problem
├── README.md                     # Project documentation
├── 📁 Resources
│   ├── initialTestInstances      # Initial test cases for early experimentation
│   └── hiddenInstances           # More challenging test instances for final evaluation
├── 📁 src
│   ├── BitFlipAI                 # Mutation strategies
│   ├── CrossoverAI               # Crossover mechanisms
│   ├── HillClimbingAI            # Local search methods
│   ├── Initialization            # Input file reading and main entry point
│   ├── Memetic                   # Main memetic algorithm logic
│   ├── PopulationAI              # Population management
│   └── TournamentAI              # Selection strategies
└── README.txt                    # Original project instructions
```

## Getting Started

1. Ensure you have Java OpenJDK 11 installed.
2. Clone this repository.
3. Update the `FILENAME` variable in FileScanner.java to specify the input problem instance.
4. Run the `Main` class to execute the algorithm.

## Usage

To run the algorithm:

```bash
javac Main.java
java Main
```

Output will be saved to `output.txt` in the Resources directory, containing the best and worst solutions for each generation.

## Future Work

* **Improved Crossover**: Consider multi-point or adaptive crossover for better genetic diversity.

* **Enhanced Local Search**: Explore more sophisticated methods like Simulated Annealing or Tabu Search.

* **Adaptive Mutation**: Dynamically adjust mutation rates based on population diversity.

* **Parallelisation**: Use multi-threading for faster performance on larger instances.

## Important:

In order to use a specific problem instance, navigate to the FileScanner class. Here the FILENAME string needs
to be modified with a specific file name and path. The different problem instances can be found in the Resources folder.
The program will throw and print an exception in the console if the file is not found.

## License

This project is released under the MIT License.


