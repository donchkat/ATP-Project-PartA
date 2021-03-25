public interface IMazeGenerator {
    public Maze generate(int cols, int rows);
    public long measureAlgorithmTimeMillis(int cols, int rows);

}
