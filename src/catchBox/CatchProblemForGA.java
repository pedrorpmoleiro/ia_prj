package catchBox;

import ga.Problem;

import java.util.LinkedList;

public class CatchProblemForGA implements Problem<CatchIndividual> {
    public LinkedList<Cell> cellsBoxes;
    public LinkedList<Pair> pairs;
    public Cell cellCatch;
    public Cell door;
    public int size;
    public long probls;

    public CatchProblemForGA(LinkedList<Cell> cellsBoxes, LinkedList<Pair> pairs, Cell cellCatch, Cell door) {
        this.cellsBoxes = cellsBoxes;
        this.pairs = pairs;
        this.cellCatch = cellCatch;
        this.door = door;

    }

    @Override
    public CatchIndividual getNewIndividual() {
        //TODO
        return new CatchIndividual(this,size); //falta implementar o resto
    }

    @Override
    public String toString() {
        //TODO
        throw new UnsupportedOperationException("Not Implemented Yet");
    }
}
