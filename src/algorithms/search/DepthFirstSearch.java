package algorithms.search;

import Errors.LowBoundInput;
import Errors.NullError;
import Errors.OutOfBoundMatrixInput;

import java.util.ArrayList;
import java.util.Stack;

/**
 *
 */
public class DepthFirstSearch extends ASearchingAlgorithm {

    public DepthFirstSearch() {
        this.name = "DepthFirstSearch";
        this.numberOfVisitedNodes=0;
    }
    @Override
    public Solution solve (ISearchable iSearchable) throws NullError, LowBoundInput, OutOfBoundMatrixInput {
        checkNull(iSearchable);
        Stack<AState> myS = new Stack<AState>();
        AState start = iSearchable.getStartState();
        AState goal = iSearchable.getGoalState();
        myS.add(start);
        AState curr = start;
        while (!myS.isEmpty()) {
            curr = myS.pop();
            this.numberOfVisitedNodes++;
            if (curr.equals(goal))
                break;
            ArrayList<AState> adjList = iSearchable.getAllSuccessors(curr);
            for (int i = 0; i < adjList.size(); i++) {
                if (!adjList.get(i).equals(curr.getCameFrom()))
                    myS.push(adjList.get(i));
            }
        }
        return new Solution(curr);
    }

}
