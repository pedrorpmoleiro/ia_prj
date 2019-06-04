package catchBox;

import agentSearch.Heuristic;

public class HeuristicCatch extends Heuristic<CatchProblemSearch, CatchState> {

    @Override
    public double compute(CatchState state) {
        return state.computeDistance(problem.getGoalPosition());
    }

    @Override
    public String toString() {
       return "calculation of distance";
    }
}