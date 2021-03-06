package catchBox;

import agentSearch.Action;
import agentSearch.State;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CatchState extends State implements Cloneable {
    protected int[][] matrix;
    private int lineCatch;
    private int columnCatch;
    private int lineGoal;
    private int columnGoal;
    private int countBoxes;
    private int steps;

    private boolean lastWasDoor;
    private Cell lastCell;

    public CatchState(int[][] matrix) {
        this.matrix = new int[matrix.length][matrix.length];
        this.countBoxes = 0;
        this.steps = 0;

        this.lastWasDoor = false;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                this.matrix[i][j] = matrix[i][j];
                if (this.matrix[i][j] == Properties.CATCH) {
                    this.lineCatch = i;
                    this.columnCatch = j;
                }
                if (this.matrix[i][j] == Properties.BOX) {
                    this.countBoxes++; //numero de caixas
                }
            }
        }
    }

    public int getLineCatch() {
        return lineCatch;
    }

    public int getLineGoal() {
        return lineGoal;
    }

    public int getColumnGoal() {
        return columnGoal;
    }

    public int getColumnCatch() {
        return columnCatch;
    }

    public void executeAction(Action action) {
        action.execute(this);
        fireUpdatedEnvironment();

        this.action = action;
    }

    public boolean canMoveUp() {
        if (lineCatch <= 0) {
            return false;
        }
        if (matrix[lineCatch - 1][columnCatch] == Properties.WALL) {
            return false;
        }
        if (matrix[lineCatch - 1][columnCatch] == Properties.DOOR && countBoxes > 0) {
            return false;
        }
        return true;

    }

    public boolean canMoveRight() {
        if (columnCatch >= matrix.length - 1) {
            return false;
        }
        if (matrix[lineCatch][columnCatch + 1] == Properties.WALL) {
            return false;
        }
        return true;
    }

    public boolean canMoveDown() {
        if (lineCatch >= matrix.length - 1) {
            return false;
        }
        if (matrix[lineCatch + 1][columnCatch] == Properties.WALL) {
            return false;
        }
        if (matrix[lineCatch + 1][columnCatch] == Properties.DOOR && countBoxes > 0) {
            return false;
        }
        return true;
    }

    public boolean canMoveLeft() {
        if (columnCatch <= 0) {
            return false;
        }
        if (matrix[lineCatch][columnCatch - 1] == Properties.WALL) {
            return false;
        }
        return true;


    }

    public void moveUp() {
        matrix[lineCatch--][columnCatch] = Properties.EMPTY;

        auxMove();
    }

    public void moveRight() {
        matrix[lineCatch][columnCatch++] = Properties.EMPTY;

        auxMove();
    }

    public void moveDown() {
        matrix[lineCatch++][columnCatch] = Properties.EMPTY;

        auxMove();
    }

    public void moveLeft() {
        matrix[lineCatch][columnCatch--] = Properties.EMPTY;

        auxMove();
    }

    private void auxMove() {
        if (matrix[lineCatch][columnCatch] == Properties.BOX) {
            countBoxes--;
        }

        if (matrix[lineCatch][columnCatch] == Properties.DOOR) {
            this.lastWasDoor = true;
            this.lastCell = new Cell(lineCatch, columnCatch);

        } else if (this.lastWasDoor) {
            matrix[this.lastCell.getLine()][this.lastCell.getColumn()] = Properties.DOOR;
            this.lastWasDoor = false;
        }

        matrix[lineCatch][columnCatch] = Properties.CATCH;
        steps++;
    }

    public int getNumBox() {
        return countBoxes;
    }

    public double computeDistance(Cell goalPosition) {
        return Math.abs(goalPosition.getLine() - lineCatch) + Math.abs(goalPosition.getColumn() - columnCatch);
    }

    public double computeNumBoxes() {
        double h = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {

                if (this.matrix[i][j] == Properties.BOX) {
                    h++;
                }
            }
        }
        return h;
    }

    public void setCellCatch(int line, int column) {
        this.columnCatch = column;
        this.lineCatch = line;
    }

    public int[][] getMatrix() {
        return matrix;
    }


    public void setGoal(int line, int column) {
        this.lineGoal = line;
        this.columnGoal = column;
    }

    public int getSteps() {
        return steps;
    }

    public int getSize() {
        return matrix.length;
    }

    public Color getCellColor(int line, int column) {
        switch (matrix[line][column]) {
            case Properties.BOX:
                return Properties.COLORBOX;
            case Properties.CATCH:
                return Properties.COLORCATCH;
            case Properties.DOOR:
                return Properties.COLORDOOR;
            case Properties.WALL:
                return Properties.COLORWALL;
            default:
                return Properties.COLOREMPTY;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof CatchState)) {
            return false;
        }

        CatchState o = (CatchState) other;
        if (matrix.length != o.matrix.length) {
            return false;
        }

        return Arrays.deepEquals(matrix, o.matrix);
    }

    @Override
    public int hashCode() {
        return 97 * 7 + Arrays.deepHashCode(this.matrix);
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append(matrix.length);
        for (int i = 0; i < matrix.length; i++) {
            buffer.append('\n');
            for (int j = 0; j < matrix.length; j++) {
                buffer.append(matrix[i][j]);
                buffer.append(' ');
            }
        }
        return buffer.toString();
    }

    @Override
    public CatchState clone() {
        CatchState clone = new CatchState(matrix);
        clone.setCellCatch(lineCatch, columnCatch);
        clone.setGoal(this.lineGoal, this.columnGoal);

        return clone;
    }

    //Listeners
    private final ArrayList<EnvironmentListener> listeners = new ArrayList<>();

    public synchronized void addEnvironmentListener(EnvironmentListener l) {
        if (!listeners.contains(l)) {
            listeners.add(l);
        }
    }

    public synchronized void removeEnvironmentListener(EnvironmentListener l) {
        listeners.remove(l);
    }

    public void fireUpdatedEnvironment() {
        for (EnvironmentListener listener : listeners) {
            listener.environmentUpdated();
        }
    }

}
