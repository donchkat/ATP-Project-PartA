package algorithms.search;

import Errors.NullError;
import algorithms.mazeGenerators.Position;

/**
 *
 */
public class MazeState extends AState{

    /**
     * @return
     */
    public Position getCurrPosition () {
        return (Position)this.value;
    }

    /**
     * @param cost
     * @param cameFrom
     * @param currPosition
     */
    public MazeState (double cost, AState cameFrom, Position currPosition) throws NullError {
        super();
        if(currPosition == null)
            throw new NullError();
        this.cameFrom = cameFrom;
        this.cost = cost;
        this.value=currPosition;
    }
}
