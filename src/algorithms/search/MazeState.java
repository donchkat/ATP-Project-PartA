package algorithms.search;

import Errors.NullError;
import algorithms.mazeGenerators.Position;

/**
 *Astate that contains a Position as value
 */
public class MazeState extends AState{

    /**
     * @return the data of the state
     */
    public Position getCurrPosition () {
        return (Position)this.value;
    }

    /**
     * @param cost - the cost we gained so far.
     * @param cameFrom - it's father
     * @param currPosition - the current position we are at.
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
