package algorithms.maze3D;

import Errors.NullError;
import algorithms.search.AState;

/**
 * Class that represents a 3D maze state - a Position that is wrapped as a State object
 */
public class Maze3DState extends AState {

    /**
     * create new Maze3D state
     * @param cost
     * @param cameFrom
     * @param value
     * @throws NullError
     */
    public Maze3DState (double cost, AState cameFrom, Position3D value) throws NullError {
        super();
        if(value == null)
            throw new NullError();
        this.cameFrom = cameFrom;
        this.cost = cost;
        this.value=value;
    }

    /**
     * @return 3D Position of curr state
     */
    public Position3D getPosition3D(){
        return (Position3D) this.value;
    }


}
