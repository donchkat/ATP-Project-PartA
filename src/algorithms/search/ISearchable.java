package algorithms.search;

import Errors.LowBoundInput;
import Errors.NullError;
import Errors.OutOfBoundMatrixInput;

import java.util.ArrayList;

/**
 * represent any searching problem functionality
 */
public interface ISearchable {


    AState getStartState () throws NullError;

    AState getGoalState () throws NullError;

    ArrayList<AState> getAllSuccessors (AState state) throws LowBoundInput, OutOfBoundMatrixInput, NullError;

}
