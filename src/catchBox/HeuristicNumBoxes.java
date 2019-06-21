package catchBox;

import agentSearch.Heuristic;

public class HeuristicNumBoxes extends Heuristic<CatchProblemSearch, CatchState> {
    @Override
    public double compute(CatchState state) {
        return state.computeNumBoxes();
    }

    @Override
    public String toString() {
        return "Calculation of boxes";
    }
}
