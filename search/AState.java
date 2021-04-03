package search;

import mazeGenerators.Position;

public class AState {
    private String state;
    private double cost;
    private AState cameFrom;
    protected Object value;

    public AState(String state, double cost, AState cameFrom, Object value) {
        this.state = state;
        this.cost = cost;
        this.cameFrom = cameFrom;
        this.value = value;
    }

    public void setCameFrom(AState cameFrom) {
        this.cameFrom = cameFrom;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public AState getCameFrom() {
        return cameFrom;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AState aState = (AState) o;
        return this.value.equals(((AState) o).value);
    }

    public AState (double cost, AState cameFrom,Object value) {
        this.state = "white";
        this.cameFrom = cameFrom;
        this.cost = cost;
        this.value=value;
    }
}
