package algorithms.search;

import  java.util.Comparator;

/**
 * a class compares 2 states
 */
public class stateComparator implements Comparator<AState>{

    @Override
    public int compare(AState x, AState y) {
        if (x.getCost()> y.getCost()) {
            return 1;
        }
        if (x.getCost()< y.getCost()) {
            return -1;
        }
        return 0;
    }
}
