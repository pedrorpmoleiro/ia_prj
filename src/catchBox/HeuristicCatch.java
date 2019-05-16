package catchBox;

import agentSearch.Heuristic;

public class HeuristicCatch extends Heuristic<CatchProblemSearch, CatchState> {

    @Override
    public double compute(CatchState state) {
        return state.getNumBox();

    }

    @Override
    public String toString() {
       return "quantity of boxes";
    }
}