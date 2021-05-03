package algorithms.search;

import java.io.Serializable;

/**
 * represent the state in any searching algorithm- like node in the graph
 */
public abstract class AState implements Serializable {
    protected double cost;
    protected AState cameFrom;
    protected Object value;




    /**
     * @return the cost of the edge to the node+the cost of the edges
     * to this node
     */

    public double getCost() {
        return cost;
    }


    /**
     * @return the value that the node contains
     */
    public Object getValue() {
        return value;
    }


    /**
     * @return the parent of the node
     */
    public AState getCameFrom() {
        return cameFrom;
    }

    /**
     * @param o- another node
     * @return true if the two nodes if they are nodes contain the same data
     */
    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null) return false;
        AState aState = (AState) o;
        return this.value.equals(aState.value);
    }

    /**
     * @return the data as string
     */
    @Override
    public String toString () {
        return value.toString();
    }
}
