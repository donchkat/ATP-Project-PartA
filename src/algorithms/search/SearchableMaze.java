package algorithms.search;

import Errors.LowBoundInput;
import Errors.NullError;
import Errors.OutOfBoundMatrixInput;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

/**
 * Adapter object from a maze to a searchable object for searching algorithms.
 */
public class SearchableMaze implements ISearchable {
    private Maze adapterMaze;
    private String[][] visitRecord;
    private double[][] costs;


    /**
     * building the adapter
     * @param adapterMaze - the instance of the maze object we will be searching.
     * @throws NullError - throws exception
     */
    public SearchableMaze (Maze adapterMaze) throws NullError {
        if (adapterMaze == null)
            throw new NullError();
        this.adapterMaze = adapterMaze;
        this.visitRecord = new String[adapterMaze.getRows()][adapterMaze.getCols()];
        this.costs = new double[adapterMaze.getRows()][adapterMaze.getCols()];
        initMatColor();
        initMatCost();
    }

    /**
     *all the nodes start white
     */
    private void initMatColor () {
        for (int i = 0; i < adapterMaze.getRows(); i++) {
            for (int j = 0; j < adapterMaze.getCols(); j++) {
                this.visitRecord[i][j] = "white";
            }
        }
    }

    /**
     *we assume that the start position of any node is infinity because we didnt found it yet
     */
    private void initMatCost () {
        for (int i = 0; i < adapterMaze.getRows(); i++) {
            for (int j = 0; j < adapterMaze.getCols(); j++) {

                costs[i][j] = Integer.MAX_VALUE;
            }
        }
    }

    /**
     * @return the start of the search
     * @throws NullError - throws exception
     */
    @Override
    public MazeState getStartState () throws NullError {
        return new MazeState(0, null, adapterMaze.getStartPosition());
    }

    /**
     * @return the goal of search
     * @throws NullError - throws exception
     */
    @Override
    public MazeState getGoalState () throws NullError{
        return new MazeState(Integer.MAX_VALUE, null, adapterMaze.getGoalPosition());
    }

    /**
     * it returns an Array List of all the possible positions we can move to.
     * @param state-current state
     * @throws LowBoundInput - throws exception
     * @throws OutOfBoundMatrixInput - throws exception
     * @throws NullError - throws exception
     */
    @Override
    public ArrayList<AState> getAllSuccessors (AState state) throws LowBoundInput, OutOfBoundMatrixInput, NullError{
        if (state == null) return null;
        if (state.equals(this.getStartState())) { this.initMatColor(); }
        ArrayList<AState> possibleMoves = new ArrayList<>();
        Position currPos = (Position) state.getValue();
        MazeState Mstate = new MazeState(state.getCost(), state.getCameFrom(), currPos);
        int r = Mstate.getCurrPosition().getRowIndex();
        int c = Mstate.getCurrPosition().getColumnIndex();

        //UP
        boolean upCell = isInsertedStateToList(possibleMoves, Mstate, r - 1, c, 10);
        //RIGHT
        boolean rightCell = isInsertedStateToList(possibleMoves, Mstate, r, c + 1, 10);

        //DOWN
        boolean downCell = isInsertedStateToList(possibleMoves, Mstate, r + 1, c, 10);
        //LEFT
        boolean leftCell = isInsertedStateToList(possibleMoves, Mstate, r, c - 1, 10);

        //DOWNRIGHT
        if (rightCell || downCell) isInsertedStateToList(possibleMoves, Mstate, r + 1, c + 1, 15);
        //DOWNLEFT
        if (downCell || leftCell) isInsertedStateToList(possibleMoves, Mstate, r + 1, c - 1, 15);
        //rightup
        if (upCell || rightCell) isInsertedStateToList(possibleMoves, Mstate, r - 1, c + 1, 15);
        //leftUp
        if (upCell || leftCell) isInsertedStateToList(possibleMoves, Mstate, r - 1, c - 1, 15);
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
    public boolean isInsertedStateToList (ArrayList<AState> list, AState state, int r, int c, double cost) throws LowBoundInput, OutOfBoundMatrixInput, NullError {
        if (!adapterMaze.checkLegalCell(r, c)) {
            if (adapterMaze.isContainZero(r, c) && visitRecord[r][c].equals("white")) {
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
    private void insertStateToList (ArrayList<AState> list, AState state, int r, int c, double cost) throws  NullError {
        Position uPosition = new Position(r, c);
        AState newState = new MazeState(state.getCost() + cost, state, uPosition);
        costs[r][c] = state.getCost();
        list.add(newState);
        visitRecord[r][c] = "gray";


    }

}
