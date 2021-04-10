package algorithms.search;

import Errors.LowBoundInput;
import Errors.NullError;
import Errors.OutOfBoundMatrixInput;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BestFirstSearch extends ASearchingAlgorithm {

    public BestFirstSearch () {
        this.name = "BestFirstSearch";
        this.numberOfVisitedNodes=0;
    }

    @Override
    public Solution solve (ISearchable iSearchable) throws NullError, LowBoundInput, OutOfBoundMatrixInput {
        checkNull(iSearchable);
        Comparator<AState> comparator = new stateComparator();
        PriorityQueue<AState> queue = new PriorityQueue<AState>(1, comparator);
        AState start = iSearchable.getStartState();
        AState goal = iSearchable.getGoalState();
        queue.add(start);
        AState curr = start;
        while (!queue.isEmpty()) {
            curr = queue.remove();
            this.numberOfVisitedNodes++;
            if (curr.equals(goal)) {
                break;
            }
            ArrayList<AState> arr = iSearchable.getAllSuccessors(curr);
            for (int i = 0; i < arr.size(); i++) {
                if (!arr.get(i).equals(curr.getCameFrom()))
                    queue.add(arr.get(i));
            }
        }
        return new Solution(curr);

    }
}
