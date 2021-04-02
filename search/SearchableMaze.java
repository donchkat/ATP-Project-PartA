package search;

import mazeGenerators.Maze;
import mazeGenerators.Position;

import java.util.ArrayList;

public class SearchableMaze implements ISearchable {
    private Maze adapterMaze;

    @Override
    public MazeState getStartState () {
        return new MazeState("S", 0, null, adapterMaze.getStartPosition() );
    }

    @Override
    public MazeState getGoalState () {
        return new MazeState("E", Integer.MAX_VALUE, null, adapterMaze.getGoalPosition());
    }

    @Override
    public ArrayList<AState> getAllSuccessors (AState state) {
        ArrayList<AState> possibleMoves = new ArrayList<AState>();
        MazeState Mstate = (MazeState) state;
        int r = Mstate.getCurrPosition().getRowIndex();
        int c = Mstate.getCurrPosition().getColumnIndex();

        //UP
        boolean upCell = insertStateToList(possibleMoves, Mstate, r-1, c, 10);
        //DOWN
        boolean downCell = insertStateToList(possibleMoves, Mstate, r+1, c, 10);
        //LEFT
        boolean leftCell = insertStateToList(possibleMoves,Mstate, r, c-1, 10 );
        //RIGHT
        boolean rightCell = insertStateToList(possibleMoves, Mstate, r, c+1, 10);

        //rightup
        if(upCell || rightCell)
            insertStateToList(possibleMoves, Mstate,r-1, c+1, 15);
        //leftUp
        if(upCell || leftCell)
            insertStateToList(possibleMoves, Mstate, r-1, c-1, 15);
        //DOWNRIGHT
        if(rightCell || downCell)
            insertStateToList(possibleMoves, Mstate, r+1, c+1, 15);
        //DOWNLEFT
        if(downCell || leftCell)
            insertStateToList(possibleMoves, Mstate, r+1, c-1, 15);
        return possibleMoves;
    }


    public boolean insertStateToList(ArrayList<AState> list, AState state, int r, int c, double cost){
        if(!adapterMaze.checkLegalCell(r,c)){
            if(adapterMaze.isContainZero(r,c)){
                Position uPosition = new Position(r,c);
                list.add(new AState(uPosition.toString(),cost,state));
                return true;
            }
        }
        return false;
    }
}
