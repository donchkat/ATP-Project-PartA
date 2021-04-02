package search;

public interface ISearchingAlgorithm {
     Solution search(ISearchable iSearchable);
     String getName();
     int getNumberOfNodesEvaluated();
}
