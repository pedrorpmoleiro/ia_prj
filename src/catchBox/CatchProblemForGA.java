package catchBox;

import ga.Problem;

import java.util.LinkedList;

public class CatchProblemForGA implements Problem<CatchIndividual> {
    public LinkedList<Cell> cellsBoxes;
    public LinkedList<Pair> pairs;
    public Cell cellCatch;
    public Cell door;
    public int size;
    // public long probls;

    public CatchProblemForGA(LinkedList<Cell> cellsBoxes, LinkedList<Pair> pairs, Cell cellCatch, Cell door) {
        this.cellsBoxes = new LinkedList<>();
        this.pairs = new LinkedList<>();
        for (Cell cellsBox : cellsBoxes) {
            this.cellsBoxes.add(cellsBox);
        }

        for (Pair pair : pairs) {
            this.pairs.add(pair);
        }

        this.cellCatch = cellCatch;
        this.door = door;

        this.size = cellsBoxes.size();
    }

    @Override
    public CatchIndividual getNewIndividual() {
        return new CatchIndividual(this, this.size);
    }

    @Override
    public String toString() {
        return "Genetic Algorithm";
    }

    public LinkedList<Pair> getPairs() {
        return pairs;
    }
}
