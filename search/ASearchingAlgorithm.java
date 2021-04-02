package search;

public abstract class ASearchingAlgorithm  implements ISearchingAlgorithm{
    public abstract MazeState search(ISearchable iSearchable);
    public abstract int getNumberVisitedNodes();
}
