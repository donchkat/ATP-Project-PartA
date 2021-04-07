package algorithms.search;

import java.util.ArrayList;
import java.util.Stack;

public class DepthFirstSearch extends ASearchingAlgorithm {

    @Override
    public Solution search (ISearchable iSearchable) {
        Stack<AState> myS = new Stack<AState>();
        AState start = iSearchable.getStartState();
        AState goal = iSearchable.getGoalState();
        myS.add(start);
        AState curr = start;
        while (!myS.isEmpty()) {
            curr = myS.pop();
            this.numberOfVisitedNodes++;
            //  if (curr.getState() == "gray") //visited
            //    continue;
            if (curr.equals(goal))
                break;
            curr.setState("gray");

            ArrayList<AState> adjList = iSearchable.getAllSuccessors(curr);
            // for (int i = adj.size() - 1; i >= 0; i--) - that's what they used in the algorithm
            for (int i = 0; i < adjList.size(); i++) {
                //AState neighbor = adjList.get(i);
                //  if (adjList.get(i).getState() == "white")
                myS.push(adjList.get(i));
            }

        }
        return new Solution(curr);
    }

    @Override
    public String getName () {
        return "Depth First Search";
    }

    @Override
    public int getNumberOfNodesEvaluated () {
        return this.numberOfVisitedNodes;
    }

}
