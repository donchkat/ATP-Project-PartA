package algorithms.search;

import Errors.LowBoundInput;
import Errors.NullError;
import Errors.OutOfBoundMatrixInput;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {
    protected int numberOfVisitedNodes;
    protected String name;

    /**
     * checks if the argument is not null
     * @param o
     * @throws NullError
     */
    protected void checkNull(Object o) throws NullError {
        if(o==null)
            throw new NullError();
    }
    public abstract Solution solve (ISearchable iSearchable) throws NullError, LowBoundInput, OutOfBoundMatrixInput;

    @Override
    public int getNumberOfNodesEvaluated () {
        return numberOfVisitedNodes;
    }

    @Override
    public String getName () {
        return name;
    }
}
