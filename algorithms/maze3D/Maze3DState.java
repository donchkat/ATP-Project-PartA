package algorithms.maze3D;

import algorithms.search.AState;

public class Maze3DState extends AState {
//AState supposed to be abstract but its not

    public Maze3DState (double cost, AState cameFrom, Position3D value) {
        super();
        this.state = "white";
        this.cameFrom = cameFrom;
        this.cost = cost;
        this.value=value;
    }
    public Position3D getPosition3D(){
        return (Position3D) this.value;
    }


}
