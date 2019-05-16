package catchBox;

import agentSearch.Action;
import agentSearch.State;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class CatchState extends State implements Cloneable {
    //TODO this class might require the definition of additional methods and/or attributes

    protected int[][] matrix;


    private int lineCatch;
    private int columnCatch; //line blank será sem caixa
    private int lineGoal;
    private int columnGoal;

    public CatchState(int[][] matrix) {
      this.matrix = new int[matrix.length][matrix.length];
      //this.goalMatrix = new int [matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                this.matrix[i][j]= matrix[i][j];
                if(this.matrix[i][j]==0)
                {
                    lineCatch=i;
                    columnCatch=j;
                }
                /*
               switch (this.matrix[i][j])
               {
                   case 0:
                       this.goalMatrix[i][j] = 0;
                       break;
                   case 1:
                       this.goalMatrix[i][j] = 0;
                       break;
                   case 2:
                       this.goalMatrix[i][j] = 0;
                       break;
                   case 3:
                       this.goalMatrix[i][j] = 3;
                       break;
                   case 4:
                       this.goalMatrix[i][j] = 1;
                       break;
               }
*/

            }
        }

    }
    public int getLineCatch() {
        return lineCatch;
    }

    public int getColumnCatch() {
        return columnCatch;
    }

    public void executeAction(Action action) {
        action.execute(this);
        fireUpdatedEnvironment();


    }

    public boolean canMoveUp() {
        if (lineCatch == Properties.WALL){
            return false;
        }

        return lineCatch != 0;
    }

    public boolean canMoveRight() {
        if (columnCatch == Properties.WALL){
            return false;
        }
        return columnCatch != matrix.length - 1;
    }

    public boolean canMoveDown() {
       if (lineCatch == Properties.WALL){
           return false;

       }

        return lineCatch != matrix.length - 1;

    }

    public boolean canMoveLeft() {
        if (columnCatch == Properties.WALL){
            return false;

        }

        return columnCatch != 0;

    }

    public void moveUp() {
        matrix[lineCatch][columnCatch] = matrix[--lineCatch][columnCatch];
        matrix[lineCatch][lineCatch] = 1;
    }

    public void moveRight() {
        matrix[lineCatch][columnCatch] = matrix[lineCatch][++columnCatch];
        matrix[lineCatch][lineCatch] = 1;
    }

    public void moveDown() {
        matrix[lineCatch][columnCatch] = matrix[++lineCatch][columnCatch];
        matrix[lineCatch][lineCatch] = 1;
    }

    public void moveLeft() {
        matrix[lineCatch][columnCatch] = matrix[lineCatch][--columnCatch];
        matrix[lineCatch][lineCatch] = 1;
    }

    public int getNumBox() {
        int box =0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (this.matrix[i][j] == Properties.BOX){
                    box++;
                }
            }
        }
    return box;
    }

    public void setCellCatch(int line, int column) {

    }

    public int[][] getMatrix() {
        return matrix;
    }


    public void setGoal(int line, int column) {

        this.lineGoal = line;
        this.columnGoal = column;

    }

    public int getSteps() {
        //TODO
        throw new UnsupportedOperationException("Not Implemented Yet");
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
        //TODO
        throw new UnsupportedOperationException("Not Implemented Yet");
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
