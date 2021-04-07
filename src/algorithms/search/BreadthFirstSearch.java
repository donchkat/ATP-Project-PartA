package algorithms.search;

import java.util.ArrayList;
import java.util.LinkedList;

public class BreadthFirstSearch extends ASearchingAlgorithm {


    @Override
    public Solution solve (ISearchable iSearchable) {
        LinkedList<AState> myQ = new LinkedList<>();
        AState start = iSearchable.getStartState();
        AState goal = iSearchable.getGoalState();
        myQ.add(start);
        AState curr = start;
        while (!myQ.isEmpty()) {
            curr = myQ.pollFirst();
            curr.setState("gray");
            this.numberOfVisitedNodes++;
            if (curr.equals(goal)) {
                break;
            }
            ArrayList<AState> arr = iSearchable.getAllSuccessors(curr);

            // ????WHY TO MAKE ALL THE NEIGHBORS GRAY???
            for (int i = 0; i < arr.size(); i++) {
                //if (arr.get(i).getState() == "white")
                //  arr.get(i).setState("gray");
                if (!arr.get(i).equals(curr.getCameFrom()))
                    myQ.add(arr.get(i));


            }
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
