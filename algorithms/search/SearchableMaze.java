package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

public class SearchableMaze implements ISearchable {
    private Maze adapterMaze;
    private String [][] visitRecord; //ignore
    //not sure its ok to add this here.because the searching algorithms will depend on this field.
    //so I wish each searchable object would have this.

    public SearchableMaze(Maze adapterMaze) {
        this.adapterMaze = adapterMaze;

        //ignore this part
        for (int i = 0; i < adapterMaze.getRows(); i++) {
            for (int j = 0; j < adapterMaze.getCols(); j++) {
                visitRecord[i][j] = "white";
            }
        }
    }


    @Override
    public MazeState getStartState () {
        return new MazeState( 0, null, adapterMaze.getStartPosition() );
    }

    @Override
    public MazeState getGoalState () {
        return new MazeState( Integer.MAX_VALUE, null, adapterMaze.getGoalPosition());
    }

    @Override
    public ArrayList<AState> getAllSuccessors (AState state) {
        if(state==null)
            return  null;
        ArrayList<AState> possibleMoves = new ArrayList<AState>();
        Position tmp=(Position) state.getValue();
        MazeState Mstate = new MazeState(state.getCost(),state.getCameFrom(),tmp);
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
            if(adapterMaze.isContainZero(r,c)&&(state.getState()!="gray")){
                Position uPosition = new Position(r,c);
                AState newState=new AState(cost,state,uPosition);
                for (int i = 0; i < list.size(); i++) {
                    if(list.get(i).equals(newState))
                        return false;
                }
                   list.add(newState);
                return true;
            }
        }
        return false;
    }
}
