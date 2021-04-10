package algorithms.search;

import Errors.LowBoundInput;
import Errors.NullError;
import Errors.OutOfBoundMatrixInput;

/**
 *represent any searching algorithm functionality
 */
public interface ISearchingAlgorithm {

    Solution solve (ISearchable iSearchable) throws NullError, LowBoundInput, OutOfBoundMatrixInput;

    String getName ();

    int getNumberOfNodesEvaluated ();
}
