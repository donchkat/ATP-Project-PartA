package algorithms.search;

public abstract class AState {
    //protected String state;
    protected double cost;
    protected AState cameFrom;
    protected Object value;


/**
    public String getState () {
        return state;
    }

    public void setState (String state) {
        this.state = state;
    }
*/
    public double getCost () {
        return cost;
    }


    public Object getValue () {
        return value;
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

    @Override
    public String toString () {
        return value.toString();
    }
}
