package search;

import java.util.ArrayList;

public interface ISearchable {


public MazeState getStartState();
public MazeState getGoalState();
public ArrayList<MazeState> getAllSuccessors();

}
