package mazeGenerators;

import mazeGenerators.Maze;

public abstract class AMazeGenerator implements IMazeGenerator {
    //protected Maze myMaze; -NOT SURE WE NEED THIS
    //do we need a constructor????

    /**
     * @param rows
     * @param cols
     * @return the time it took to build the maze
     */
    @Override
    public long measureAlgorithmTimeMillis(int rows, int cols) {
        long startTime = System.currentTimeMillis();
        this.generate(rows, cols);
        long finishTime = System.currentTimeMillis();
        long finalTime = finishTime - startTime;
        return finalTime;
    }
}
