package search;

public class AState {
    private String state;
    private double cost;
    private AState cameFrom;

    public void setCameFrom(AState cameFrom) {
        this.cameFrom = cameFrom;
    }

    public AState getCameFrom() {
        return cameFrom;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AState aState = (AState) o;
        return aState.state == this.state;
    }

    public AState (String state, double cost, AState cameFrom) {
        this.state = state;
        this.cameFrom = cameFrom;
        this.cost = cost;
    }
}
