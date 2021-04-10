package algorithms.search;

import Errors.LowBoundInput;
import Errors.OutOfBoundMatrixInput;

import java.util.ArrayList;

public interface ISearchable {


public AState getStartState();
public AState getGoalState();
public ArrayList<AState> getAllSuccessors(AState state) throws LowBoundInput, OutOfBoundMatrixInput;

}
