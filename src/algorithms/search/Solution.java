package algorithms.search;

import Errors.NullError;
import java.util.ArrayList;

/**
 * class of list of states from the start position of the maze to the goal position
 */
public class Solution {
    private ArrayList<AState> solutionPath;

    public Solution (AState aState) throws NullError {
        if(aState == null)
            throw new NullError();
        solutionPath = new ArrayList<>();
        ArrayList<AState> tmp=new ArrayList<>();
        while (aState != null) {
            tmp.add(aState);
            aState = aState.getCameFrom();
        }
        for (int i = tmp.size()-1; i >= 0; i--) {
              solutionPath.add(tmp.get(i));
        }

    }

    public ArrayList<AState> getSolutionPath () {
        return solutionPath;
    }
}
