package ga.geneticOperators;

import ga.Individual;
import ga.Population;
import ga.Problem;

import java.util.ArrayList;
import java.util.HashSet;

import static ga.GeneticAlgorithm.random;

public abstract class Recombination <I extends Individual, P extends Problem<I>> extends GeneticOperator {
    ArrayList<Integer> emptySpotsBox1 = new ArrayList<>();
    ArrayList<Integer> emptySpotsBox2 = new ArrayList<>();
    

    public Recombination(double probability) {
        super(probability);
    }

    public void run(Population<I, P> population) {
        int populationSize = population.getSize();
        for (int i = 0; i < populationSize; i += 2) {
            if (random.nextDouble() < getProbability()) {
                recombine(population.getIndividual(i), population.getIndividual(i + 1));
            }
        }
    }

    public abstract void recombine(I ind1, I ind2);

    static void inheritBoxes(int[] parent1, int[] parent2, Integer[] child1, Integer[] child2, HashSet<Integer> boxesInChild1, HashSet<Integer> boxesInChild2, int randomPoint1) {
        for (int i = 0; i < randomPoint1; i++) {
            child1[i] = parent1[i];
            child2[i] = parent2[i];
            boxesInChild1.add(parent1[i]);
            boxesInChild2.add(parent2[i]);

        }
    }

    static void getBoxesoppositeParent(int[] parent1, int[] parent2, Integer[] child1, Integer[] child2, HashSet<Integer> boxesInChild1, HashSet<Integer> boxesInChild2, int randomPoint1, int randomPoint2) {
        for (int i = randomPoint1; i <randomPoint2; i++) {
            if (!boxesInChild1.contains(parent2[i])) {
                boxesInChild1.add(parent2[i]);
                child1[i] = parent2[i];
            }
            if (!boxesInChild2.contains(parent1[i])) {
                boxesInChild2.add(parent1[i]);
                child2[i] = parent1[i];
            }
        }
    }

    static void findAllBoxes(int[] parent1, int[] parent2, HashSet<Integer> boxesInChild1, HashSet<Integer> boxesInChild2, ArrayList<Integer> boxesNotInChild1, ArrayList<Integer> boxesNotInChild2, int totalBoxes) {
        for (int i = 0; i < totalBoxes; i++) {
            if (!boxesInChild1.contains(parent2[i])) {
                boxesNotInChild1.add(parent2[i]);
            }
            if (!boxesInChild2.contains(parent1[i])) {
                boxesNotInChild2.add(parent1[i]);
            }
        }
    }
    static void findEmptySpots(Integer[] child1, Integer[] child2, int totalBoxes, ArrayList<Integer> emptySpotsBox1, ArrayList<Integer> emptySpotsBox2) {
        for (int i = 0; i < totalBoxes; i++) {
            if (child1[i] == null) {
                emptySpotsBox1.add(i);
            }
            if (child2[i] == null) {
                emptySpotsBox2.add(i);
            }

        }
    }
    static void fillEmptySpots(Integer[] child1, Integer[] child2,ArrayList<Integer> emptySpotsBox1, ArrayList<Integer> emptySpotsBox2, ArrayList<Integer> boxesNotInChild1, ArrayList<Integer> boxesNotInChild2) {
        for (Integer box : boxesNotInChild1) {
            child1[emptySpotsBox1.remove(0)] = box;

        }
        for (Integer box : boxesNotInChild2) {
            child2[emptySpotsBox2.remove(0)] = box;

        }
    }
}