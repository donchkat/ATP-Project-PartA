package search;

import java.util.ArrayList;
import java.util.LinkedList;

public class Solution {
     private ArrayList<AState> solutionPath;

    public Solution(AState aState) {
        while(aState.getCameFrom()!=null) {
            this.solutionPath.add(aState);
             aState=aState.getCameFrom();
        }

    }

    public ArrayList<AState> getSolutionPath() {
        return solutionPath;
    }
}
