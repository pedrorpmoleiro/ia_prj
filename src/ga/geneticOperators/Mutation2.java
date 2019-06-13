package ga.geneticOperators;

import ga.GeneticAlgorithm;
import ga.IntVectorIndividual;
import ga.Problem;

public class Mutation2<I extends IntVectorIndividual, P extends Problem<I>> extends Mutation<I, P> {

    public Mutation2(double probability) {
        super(probability);
    }

    @Override
    public void mutate(I ind) {
        int gene1 = GeneticAlgorithm.random.nextInt(ind.getNumGenes()); // 0 a numGenes
        int gene2;

        do {
            gene2 = GeneticAlgorithm.random.nextInt(ind.getNumGenes());
        } while (gene1 == gene2);

        int aux = ind.getGene(gene2);
        ind.setGene(gene2, ind.getGene(gene1));
        ind.setGene(gene1, aux);
    }

    @Override
    public String toString() {
        return "Exchange";
    }
}