package search;

import mazeGenerators.Position;

public class MazeState extends AState{
    private Position currPosition;

    public Position getCurrPosition () {
        return currPosition;
    }

    public MazeState (String state, double cost, AState cameFrom, Position currPosition) {
        super(state, cost, cameFrom,currPosition);
        this.currPosition = currPosition;
    }
}
