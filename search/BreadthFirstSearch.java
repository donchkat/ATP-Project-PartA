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
        myQ.add(start);
        AState curr=start;
        while (!myQ.isEmpty()) {
            curr = myQ.pollFirst();
            this.numberOfVisitedNodes++;
            if (curr.equals(goal))
                  break;
            for (int i = 0; i < iSearchable.getAllSuccessors(curr).size(); i++) {
                if (iSearchable.getAllSuccessors(curr).get(i).getState() == "gray")
                    myQ.add(iSearchable.getAllSuccessors(curr).get(i));
            }
            curr.setState("black");
        }
        return new Solution(curr);

    }

        @Override
        public String getName () {
            return "Breadth First Search";
        }

        @Override
        public int getNumberOfNodesEvaluated () {
            return this.numberOfVisitedNodes;
        }



}
