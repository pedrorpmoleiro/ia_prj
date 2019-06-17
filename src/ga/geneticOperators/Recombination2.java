package ga.geneticOperators;

import ga.GeneticAlgorithm;
import ga.IntVectorIndividual;
import ga.Problem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

//onePointCrossover
public class Recombination2<I extends IntVectorIndividual, P extends Problem<I>> extends Recombination<I, P> {
    public Recombination2(double probability) {

        super(probability);
    }

    @Override
    public void recombine(I ind1, I ind2) {
        Random r = GeneticAlgorithm.random;
        int[] parent1, parent2;

        parent1 = new int[ind1.getNumGenes()];
        parent2 = new int[ind2.getNumGenes()];

        for (int i = 0; i < ind1.getNumGenes(); i++) {
            parent1[i] = ind1.getGene(i);
        }
        for (int i = 0; i < ind2.getNumGenes(); i++) {
            parent2[i] = ind2.getGene(i);
        }

        Integer[] child1 = new Integer[parent1.length];
        Integer[] child2 = new Integer[parent2.length];

        HashSet<Integer> boxesInChild1 = new HashSet<>();
        HashSet<Integer> boxesInChild2 = new HashSet<>();

        ArrayList<Integer> boxesNotInChild1 = new ArrayList<>();
        ArrayList<Integer> boxesNotInChild2 = new ArrayList<>();

        int totalBoxes = parent1.length;

        int randomPoint = r.nextInt(totalBoxes);

        for (int i = 0; i < randomPoint; i++) {
            child1[i] = parent1[i];
            child2[i] = parent2[i];
            boxesInChild1.add(parent1[i]);
            boxesInChild2.add(parent2[i]);

        }
        //get the boxes of the opposite parent if the child does not already contain them
        for (int i = randomPoint; i < totalBoxes; i++) {
            if (!boxesInChild1.contains(parent2[i])) {
                boxesInChild1.add(parent2[i]);
                child1[i] = parent2[i];
            }
            if (!boxesInChild2.contains(parent1[i])) {
                boxesInChild2.add(parent1[i]);
                child2[i] = parent1[i];
            }
        }

        //Find all the boxes that are still missing from each child
        for (int i = 0; i < totalBoxes; i++) {
            if (!boxesInChild1.contains(parent2[i])) {
                boxesNotInChild1.add(parent2[i]);
            }
            if (!boxesInChild2.contains(parent1[i])) {
                boxesNotInChild2.add(parent1[i]);
            }
        }
        // Find which spots are still empty in each child
        ArrayList<Integer> emptySpotsBox1 = new ArrayList<>();
        ArrayList<Integer> emptySpotsBox2 = new ArrayList<>();

        for (int i = 0; i < totalBoxes; i++) {
            if (child1[i] == null) {
                emptySpotsBox1.add(i);
            }
            if (child2[i] == null) {
                emptySpotsBox2.add(i);
            }

        }
        // Fill in the empty spots
        for (Integer box : boxesNotInChild1) {
            child1[emptySpotsBox1.remove(0)] = box;

        }
        for (Integer box : boxesNotInChild2) {
            child2[emptySpotsBox2.remove(0)] = box;

        }
        for (int i = 0; i < ind1.getNumGenes(); i++) {
            ind1.setGene(i, child1[i]);
            ind2.setGene(i, child2[i]);
        }


    }

    @Override
    public String toString() {
        return "Two Cut";
    }
}