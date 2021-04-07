package algorithms.maze3D;

import algorithms.mazeGenerators.Maze;
import algorithms.search.AState;
import algorithms.search.ISearchable;
import algorithms.search.MazeState;

import java.util.ArrayList;

public class SearchableMaze3D implements ISearchable {
    private Maze3D adapterMaze;
    private String [][][] visitRecord;
    private double [][][] costs;

    public SearchableMaze3D (Maze3D adapterMaze) {
        this.adapterMaze = adapterMaze;
        this.visitRecord=new String[adapterMaze.depth][adapterMaze.rows][adapterMaze.cols];
        this.costs=new double[adapterMaze.depth][adapterMaze.rows][adapterMaze.cols];
        initMatColor();
        initMatCost();
    }
    private  void initMatColor(){
        for (int i = 0; i < adapterMaze.depth; i++) {
            for (int j = 0; j < adapterMaze.rows; j++) {
                for (int k = 0; k < adapterMaze.cols; k++) {
                    this.visitRecord[i][j][k]="white";
                }
            }
        }
    }
    private  void initMatCost(){
        for (int i = 0; i < adapterMaze.depth; i++) {
            for (int j = 0; j < adapterMaze.rows; j++) {
                for (int k = 0; k < adapterMaze.cols; k++) {
                    costs[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }
    }

    @Override
    public Maze3DState getStartState () {
        return new Maze3DState( 0, null, adapterMaze.getStartPosition3D() );
    }

    @Override
    public Maze3DState getGoalState () {
        return new Maze3DState( Integer.MAX_VALUE, null, adapterMaze.getGoalPosition3D());
    }

    @Override
    public ArrayList<AState> getAllSuccessors(AState state) {
        return null;
    }




}
