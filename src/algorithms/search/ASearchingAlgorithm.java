package algorithms.search;

import Errors.LowBoundInput;
import Errors.NullError;
import Errors.OutOfBoundMatrixInput;

/**
 * the abstract class of any searching algorithm
 */
public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {
    protected int numberOfVisitedNodes;
    protected String name;

    /**
     * checks if the argument is not null
     * @param o - the argument
     * @throws NullError - throws exception
     */
    protected void checkNull(Object o) throws NullError {
        if(o==null)
            throw new NullError();
    }
    /**
     * @param iSearchable - a search problem translated to a graph searching problem
     * @return Solution-list of states
     * @throws NullError - throws exception
     * @throws LowBoundInput - throws exception
     * @throws OutOfBoundMatrixInput - throws exception
     */
    public abstract Solution solve (ISearchable iSearchable) throws NullError, LowBoundInput, OutOfBoundMatrixInput;

    /**
     * @return the number of nodes that were removed out of the data struct of the algorithm
     */
    @Override
    public int getNumberOfNodesEvaluated () {
        return numberOfVisitedNodes;
    }

    /**
     * @return the specific name of the algorithm
     */
    @Override
    public String getName () {
        return name;
    }
}
