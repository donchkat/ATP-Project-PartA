package searchK;

public interface ISearchingAlgorithm {

    AState search(ISearchable s);
    int getNumberOfVisitedNodes();
}
