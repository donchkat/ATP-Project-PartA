package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

public class SearchableMaze implements ISearchable {
    private Maze adapterMaze;
    private String[][] visitRecord;
    private double[][] costs;//ignore
    //not sure its ok to add this here.because the searching algorithms will depend on this field.
    //so I wish each searchable object would have this.

    public SearchableMaze (Maze adapterMaze) {
        this.adapterMaze = adapterMaze;
        this.visitRecord = new String[adapterMaze.getRows()][adapterMaze.getCols()];
        this.costs = new double[adapterMaze.getRows()][adapterMaze.getCols()];
        //ignore this part
        initMatColor();
        initMatCost();
    }

    private void initMatColor () {
        for (int i = 0; i < adapterMaze.getRows(); i++) {
            for (int j = 0; j < adapterMaze.getCols(); j++) {
                this.visitRecord[i][j] = "white";
            }
        }
    }

    private void initMatCost () {
        for (int i = 0; i < adapterMaze.getRows(); i++) {
            for (int j = 0; j < adapterMaze.getCols(); j++) {

                costs[i][j] = Integer.MAX_VALUE;
            }
        }
    }

    @Override
    public MazeState getStartState () {
        return new MazeState(0, null, adapterMaze.getStartPosition());
    }

    @Override
    public MazeState getGoalState () {
        return new MazeState(Integer.MAX_VALUE, null, adapterMaze.getGoalPosition());
    }

    @Override
    public ArrayList<AState> getAllSuccessors (AState state) {
        if (state == null)
            return null;
        if (state.equals(this.getStartState())) {
            this.initMatColor();
        }
        ArrayList<AState> possibleMoves = new ArrayList<AState>();
        Position currPos = (Position) state.getValue();
        MazeState Mstate = new MazeState(state.getCost(), state.getCameFrom(), currPos);
        Mstate.setState("gray");
        int r = Mstate.getCurrPosition().getRowIndex();
        int c = Mstate.getCurrPosition().getColumnIndex();

        //UP
        boolean upCell = isInsertedStateToList(possibleMoves, Mstate, r - 1, c, 10);
        //DOWN
        boolean downCell = isInsertedStateToList(possibleMoves, Mstate, r + 1, c, 10);
        //LEFT
        boolean leftCell = isInsertedStateToList(possibleMoves, Mstate, r, c - 1, 10);
        //RIGHT
        boolean rightCell = isInsertedStateToList(possibleMoves, Mstate, r, c + 1, 10);

        //DOWNRIGHT
        if (rightCell || downCell)
            isInsertedStateToList(possibleMoves, Mstate, r + 1, c + 1, 15);
        //DOWNLEFT
        if (downCell || leftCell)
            isInsertedStateToList(possibleMoves, Mstate, r + 1, c - 1, 15);
        //rightup
        if (upCell || rightCell)
            isInsertedStateToList(possibleMoves, Mstate, r - 1, c + 1, 15);
        //leftUp
        if (upCell || leftCell)
            isInsertedStateToList(possibleMoves, Mstate, r - 1, c - 1, 15);
        return possibleMoves;
    }

    /**
     * the function gets parameters of possible cell, and checks if it's an unvisited legal cell.
     * only if true - it will continue the process.(call for insertStateToList)
     *
     * @param list-the open list of the algorithm
     * @param state-   the previous state
     * @param r-       index of row of possible next state
     * @param c-index  of column of possible next state
     * @param cost-    weight of "edge" between "vertices"
     * @return true if new state is inserted, else false
     */
    public boolean isInsertedStateToList (ArrayList<AState> list, AState state, int r, int c, double cost) {
        if (!adapterMaze.checkLegalCell(r, c)) {
            if (adapterMaze.isContainZero(r, c) && visitRecord[r][c] == "white") {
                insertStateToList(list, state, r, c, cost);
                return true;
            }
        }
        return false;
    }

    /**
     * gets parameters of an unvisited legal cell and inserts it into a list.
     *
     * @param list-the open list of the algorithm
     * @param state-   the previous state
     * @param r-       index of row of possible next state
     * @param c-index  of column of possible next state
     * @param cost-    weight of "edge" between "vertices"
     */
    private void insertStateToList (ArrayList<AState> list, AState state, int r, int c, double cost) {
        Position uPosition = new Position(r, c);
        //double minimum=Math.min(state.getCost(),costs[r][c])+cost;
        AState newState = new MazeState(state.getCost() + cost, state, uPosition);
        costs[r][c] = state.getCost();
        list.add(newState);
        visitRecord[r][c] = "gray";


    }

}