package catchBox;

import ga.Problem;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.LinkedList;

public class CatchProblemForGA implements Problem<CatchIndividual> {
    public LinkedList<Cell> cellsBoxes;
    public LinkedList<Pair> pairs;
    public Cell cellCatch;
    public Cell door;
    public int size;
    public long probls;

    public CatchProblemForGA(
            LinkedList<Cell> cellsBoxes,
            LinkedList<Pair> pairs,
            Cell cellCatch,
            Cell door) {
        //TODO
        throw new NotImplementedException();
    }

    @Override
    public CatchIndividual getNewIndividual() {
        //TODO
        throw new NotImplementedException();
    }

    @Override
    public String toString() {
        //TODO
        throw new NotImplementedException();
    }
}
