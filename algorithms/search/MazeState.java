package algorithms.search;

import algorithms.mazeGenerators.Position;

public class MazeState extends AState{
    private Position currPosition;

    public Position getCurrPosition () {
        return currPosition;
    }

    public MazeState (double cost, AState cameFrom, Position currPosition) {
        super(cost,cameFrom, currPosition );
        this.currPosition = currPosition;
    }
}
