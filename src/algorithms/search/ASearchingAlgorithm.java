package algorithms.search;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {
    protected int numberOfVisitedNodes;

    public abstract Solution search (ISearchable iSearchable);
}
