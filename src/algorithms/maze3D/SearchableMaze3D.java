package algorithms.maze3D;

import algorithms.search.AState;
import algorithms.search.ISearchable;
import java.util.ArrayList;

/**
 * An adapter that allows us to run a search algorithm on a 3D maze instance.
 */
public class SearchableMaze3D implements ISearchable {
    private Maze3D adapterMaze;
    private String[][][] visitRecord;
    private double[][][] costs;

    /**
     * constructor
     *
     * @param adapterMaze - the adapter object that allows us to use an instance of Maze3D
     *                    for the search operation.
     */
    public SearchableMaze3D (Maze3D adapterMaze) {
        this.adapterMaze = adapterMaze;
        this.visitRecord = new String[adapterMaze.depth][adapterMaze.rows][adapterMaze.cols];
        this.costs = new double[adapterMaze.depth][adapterMaze.rows][adapterMaze.cols];
        initMatColor();
        initMatCost();
    }

    /**
     * Fills the "visitRecord" matrix with "white" color for all cells.
     * Because at the start everyone are unvisited.
     */
    private void initMatColor () {
        for (int i = 0; i < adapterMaze.depth; i++) {
            for (int j = 0; j < adapterMaze.rows; j++) {
                for (int k = 0; k < adapterMaze.cols; k++) {
                    this.visitRecord[i][j][k] = "white";
                }
            }
        }
    }

    /**
     * fills the "costs" matrix with the max cost it can have at first.
     */
    private void initMatCost () {
        for (int i = 0; i < adapterMaze.depth; i++) {
            for (int j = 0; j < adapterMaze.rows; j++) {
                for (int k = 0; k < adapterMaze.cols; k++) {
                    costs[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }
    }

    /**
     * @return the initial state of the maze.
     */
    @Override
    public Maze3DState getStartState () {
        return new Maze3DState(0, null, adapterMaze.getStartPosition3D());
    }

    /**
     * @return the final state we aim to get to in the maze.
     */
    @Override
    public Maze3DState getGoalState () {
        return new Maze3DState(Integer.MAX_VALUE, null, adapterMaze.getGoalPosition3D());
    }

    /**
     * this function returns an array list of the possible neighbors the current state can go to.
     * @param state - the current state
     * @return Array list of states that are possible to move to from the current state.
     */
    @Override
    public ArrayList<AState> getAllSuccessors (AState state) {
        if (state == null)
            return null;
        if (state.equals(this.getStartState())) {
            this.initMatColor();
            this.initMatCost();
            visitRecord[0][0][0]="gray";
        }
        ArrayList<AState> possibleMoves = new ArrayList<AState>();
        Position3D currPos = (Position3D) state.getValue();
        Maze3DState Mstate = new Maze3DState(state.getCost(), state.getCameFrom(), currPos);
        Mstate.setState("gray");
        int d = Mstate.getPosition3D().getDepthIndex();
        int r = Mstate.getPosition3D().getRowIndex();
        int c = Mstate.getPosition3D().getColumnIndex();
        //UP
        isInsertedStateToList(possibleMoves, Mstate, d, r - 1, c, 10);
        //DOWN
        isInsertedStateToList(possibleMoves, Mstate, d, r + 1, c, 10);
        //LEFT
        isInsertedStateToList(possibleMoves, Mstate, d, r, c - 1, 10);
        //RIGHT
        isInsertedStateToList(possibleMoves, Mstate, d, r, c + 1, 10);
        //INSIDE
        isInsertedStateToList(possibleMoves, Mstate, d + 1, r, c, 10);
        //OUTSIDE
        isInsertedStateToList(possibleMoves, Mstate, d - 1, r, c, 10);
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
    public boolean isInsertedStateToList (ArrayList<AState> list, AState state, int d, int r, int c, double cost) {
        if (!adapterMaze.checkLegalCell(d, r, c)) {
            if (adapterMaze.isContainZero(d, r, c) && visitRecord[d][r][c].equals("white")) {
                insertStateToList(list, state, d, r, c, cost);
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
    private void insertStateToList (ArrayList<AState> list, AState state, int d, int r, int c, double cost) {
        Position3D newPos = new Position3D(d, r, c);
        //double minimum=Math.min(state.getCost(),costs[r][c])+cost;
        AState newState = new Maze3DState(state.getCost() + cost, state, newPos);
        costs[d][r][c] = state.getCost();
        list.add(newState);
        visitRecord[d][r][c] = "gray";


    }

}
