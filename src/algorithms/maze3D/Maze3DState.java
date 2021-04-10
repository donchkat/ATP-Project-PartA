package algorithms.maze3D;

import Errors.NullError;
import algorithms.search.AState;

public class Maze3DState extends AState {

    public Maze3DState (double cost, AState cameFrom, Position3D value) throws NullError {
        super();
        if(value == null)
            throw new NullError();
        this.cameFrom = cameFrom;
        this.cost = cost;
        this.value=value;
    }
    public Position3D getPosition3D(){
        return (Position3D) this.value;
    }


}
