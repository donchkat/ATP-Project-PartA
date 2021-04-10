package algorithms.search;

import Errors.LowBoundInput;
import Errors.NullError;
import Errors.OutOfBoundMatrixInput;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {
    protected int numberOfVisitedNodes;
    protected void checkNull(Object o) throws NullError {
        if(o==null)
            throw new NullError();
    }
    public abstract Solution solve (ISearchable iSearchable) throws NullError, LowBoundInput, OutOfBoundMatrixInput;
}
