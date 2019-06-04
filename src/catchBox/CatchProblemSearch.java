package catchBox;

import agentSearch.Action;
import agentSearch.Problem;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

public class CatchProblemSearch<S extends CatchState> extends Problem<S> {
    private List<Action> availableActions;
    private Cell goalPosition;

    public CatchProblemSearch(S initialCatchState, Cell goalPosition) {
        super(initialCatchState);

        //TODO
        throw new NotImplementedException();
    }

    @Override
    public List<S> executeActions(S state) {
        //TODO
        throw new NotImplementedException();
    }

    public Cell getGoalPosition() {
        return goalPosition;
    }

    public boolean isGoal(S state) {
        //TODO
        throw new NotImplementedException();
    }
}
