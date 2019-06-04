package catchBox;

import agentSearch.Action;
import agentSearch.Problem;

import java.util.LinkedList;
import java.util.List;

public class CatchProblemSearch<S extends CatchState> extends Problem<S> {
    private List<Action> availableActions;
    private Cell goalPosition;

    public CatchProblemSearch(S initialCatchState, Cell goalPosition) {
        super(initialCatchState);

        this.availableActions = new LinkedList<>();

        this.availableActions.add(new ActionUp());
        this.availableActions.add(new ActionLeft());
        this.availableActions.add(new ActionDown());
        this.availableActions.add(new ActionRight());

        this.goalPosition = goalPosition;
    }

    @Override
    public List<S> executeActions(S state) {
        List<S> successors = new LinkedList<>();

        for (Action action : this.availableActions) {
            if (action.isValid(state)) {
                S successor = (S) state.clone();
                action.execute(successor);
                successors.add(successor);
            }
        }

        return successors;
    }

    public Cell getGoalPosition() {
        return goalPosition;
    }

    public boolean isGoal(S state) {
        return state.getLineCatch() == state.getLineGoal() && state.getColumnCatch() == state.getColumnGoal();
    }
}
