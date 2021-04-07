package algorithms.search;

import algorithms.mazeGenerators.Position;

public class MazeState extends AState{

    public Position getCurrPosition () {
        return (Position)this.value;
    }

    public MazeState (double cost, AState cameFrom, Position currPosition) {
        super();
        this.state = "white";
        this.cameFrom = cameFrom;
        this.cost = cost;
        this.value=currPosition;
    }
}
