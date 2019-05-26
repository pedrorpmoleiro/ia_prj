package ga.geneticOperators;

import ga.GeneticAlgorithm;
import ga.IntVectorIndividual;
import ga.Problem;

public class Recombination2<I extends IntVectorIndividual, P extends Problem<I>> extends Recombination<I, P> {
    public Recombination2(double probability) {
        super(probability);
    }

    @Override
    public void recombine(I ind1, I ind2) {
        int cut1 = GeneticAlgorithm.random.nextInt(ind1.getNumGenes());
        int cut2 = GeneticAlgorithm.random.nextInt(ind2.getNumGenes());

        if (cut1 > cut2) {
            int aux = cut1;
            cut1 = cut2;
            cut2 = aux;
        }

        for (int i = cut1; i < cut2; i++) {
            ind1.swapGenes(ind2, i);
        }
    }

    @Override
    public String toString(){
        return "Two Cut";
    }    
}