package algorithms.search;


//changed here to abstract class
public abstract class AState {
    protected String state;
    protected double cost;
    protected AState cameFrom;
    protected Object value;


    public void setCameFrom (AState cameFrom) {
        this.cameFrom = cameFrom;
    }

    public String getState () {
        return state;
    }

    public void setState (String state) {
        this.state = state;
    }

    public double getCost () {
        return cost;
    }

    public void setCost (double cost) {
        this.cost = cost;
    }

    public Object getValue () {
        return value;
    }

    public void setValue (Object value) {
        this.value = value;
    }

    public AState getCameFrom () {
        return cameFrom;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null) return false;
        AState aState = (AState) o;
        return this.value.equals(aState.value);
    }


    //CHANGE THE FORMAT OF THIS TO: {X,Y}
    @Override
    public String toString () {
        return "AState{" +
                ", cost=" + cost +
                ", value=" + value +
                '}';
    }
}