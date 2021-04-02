package search;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch extends ASearchingAlgorithm {


    @Override
    public Solution search(ISearchable iSearchable) {
        LinkedList<AState> myQ = new LinkedList<>();
        AState start = iSearchable.getStartState();
        AState goal = iSearchable.getGoalState();
        myQ.addAll(iSearchable.getAllSuccessors(start));
        AState curr = start;
        this.numberOfVisitedNodes++;
        while (!curr.equals(goal)) {
            this.numberOfVisitedNodes++;
            curr = myQ.pollFirst();
            myQ.addAll(iSearchable.getAllSuccessors(curr));
            for (int i = 0; i < iSearchable.getAllSuccessors(curr).size(); i++)
                iSearchable.getAllSuccessors(curr).get(i).setCameFrom(curr);
        }
        return new Solution(curr);
    }

    @Override
    public String getName() {
        return "Breadth First Search";
    }

    @Override
    public int getNumberOfNodesEvaluated() {
       return this.numberOfVisitedNodes;
    }


}
