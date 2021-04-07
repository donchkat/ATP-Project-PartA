package algorithms.search;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class BestFirstSearch extends ASearchingAlgorithm {

    @Override
    public Solution search (ISearchable iSearchable) {
        Comparator<AState> comparator = new stateComparator();
        PriorityQueue<AState> queue = new PriorityQueue<AState>(1, comparator);
        //PriorityQueue<AState> myQ = new PriorityQueue<>();
        AState start = iSearchable.getStartState();
        AState goal = iSearchable.getGoalState();
        queue.add(start);
        AState curr = start;
        while (!queue.isEmpty()) {

            curr = queue.remove();
            /***
            System.out.println("Solution path:");
            System.out.println(curr);
            System.out.println("Solution path:");
***/
           // curr.setState("gray");
            this.numberOfVisitedNodes++;
            if (curr.equals(goal)) {
                break;
            }
            ArrayList<AState> arr = iSearchable.getAllSuccessors(curr);
            for (int i = 0; i < arr.size(); i++) {
                //if (arr.get(i).getState() == "white")
                  //  arr.get(i).setState("gray");
               if(!arr.get(i).equals(curr.getCameFrom()))
                 queue.add(arr.get(i));
            }

        }
        return new Solution(curr);

    }

    @Override
    public String getName () {
        return "Best First Search";
    }

    @Override
    public int getNumberOfNodesEvaluated () {
        return this.numberOfVisitedNodes;
    }

}
