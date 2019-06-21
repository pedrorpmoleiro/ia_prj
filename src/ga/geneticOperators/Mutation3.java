package ga.geneticOperators;

import ga.GeneticAlgorithm;
import ga.IntVectorIndividual;
import ga.Problem;

public class Mutation3<I extends IntVectorIndividual, P extends Problem<I>> extends Mutation<I, P> {

    public Mutation3(double probability) {
        super(probability);
    }

    @Override
    public void mutate(I ind) {
        int start = GeneticAlgorithm.random.nextInt(ind.getNumGenes());
        int end = GeneticAlgorithm.random.nextInt(ind.getNumGenes());

        if (end < start) {
            int aux = end;
            end = start;
            start = aux;
        }

        int i = start;
        while (i <= end) {
            int random = GeneticAlgorithm.random.nextInt(ind.getNumGenes());

            if (random == i || random + i >= ind.getNumGenes()) {
                continue;
            }

            int aux = ind.getGene(i);
            ind.setGene(i, ind.getGene(i + random));
            ind.setGene(i + random, aux);

            i++;
        }
    }

    @Override
    public String toString() {
        return "Scramble";
    }
}