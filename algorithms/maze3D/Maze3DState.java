package algorithms.maze3D;

import algorithms.search.AState;

public class Maze3DState extends AState {
//AState supposed to be abstract but its not

    public Maze3DState (double cost, AState cameFrom, Object value) {
        super(cost, cameFrom, value);
    }
}
