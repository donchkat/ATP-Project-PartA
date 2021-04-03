package algorithms.maze3D;

public abstract class AMaze3DGenerator implements IMazeGenerator3D{
    @Override
    public long measureAlgorithmTimeMillis (int depth, int row, int column) {
        return 0;
    }
    @Override
    public abstract Maze3D generate(int depth, int row, int column);
}
