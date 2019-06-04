package ga.geneticOperators;

import ga.GeneticAlgorithm;
import ga.IntVectorIndividual;
import ga.Problem;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Recombination2<I extends IntVectorIndividual, P extends Problem<I>> extends Recombination<I, P> {
    public Recombination2(double probability) {
        super(probability);
    }

    @Override
    public void recombine(I ind1, I ind2) {
        //TODO
        throw new NotImplementedException();
    }

    @Override
    public String toString(){
        //TODO
        throw new NotImplementedException();
    }    
}