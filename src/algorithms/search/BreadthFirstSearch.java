package algorithms.search;

import Errors.LowBoundInput;
import Errors.NullError;
import Errors.OutOfBoundMatrixInput;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 */
public class BreadthFirstSearch extends ASearchingAlgorithm {


    public BreadthFirstSearch() {
        this.name = "BreadthFirstSearch";
        this.numberOfVisitedNodes=0;
    }

    @Override
    public Solution solve (ISearchable iSearchable) throws NullError, LowBoundInput, OutOfBoundMatrixInput {
        checkNull(iSearchable);
        LinkedList<AState> myQ = new LinkedList<>();
        AState start = iSearchable.getStartState();
        AState goal = iSearchable.getGoalState();
        myQ.add(start);
        AState curr = start;
        while (!myQ.isEmpty()) {
            curr = myQ.pollFirst();
            this.numberOfVisitedNodes++;
            if (curr.equals(goal)) {
                break;
            }
            ArrayList<AState> arr = iSearchable.getAllSuccessors(curr);
            for (int i = 0; i < arr.size(); i++) {
                if (!arr.get(i).equals(curr.getCameFrom()))
                    myQ.add(arr.get(i));
            }
        }
        return new Solution(curr);

    }
}
