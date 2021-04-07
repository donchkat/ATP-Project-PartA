package algorithms.search;

import java.util.ArrayList;

public class Solution {
    private ArrayList<AState> solutionPath;

    public Solution (AState aState) {
        solutionPath = new ArrayList<>();
        while (aState != null) {
            this.solutionPath.add(aState);
            aState = aState.getCameFrom();
        }

    }

    public ArrayList<AState> getSolutionPath () {
        return solutionPath;
    }
}
