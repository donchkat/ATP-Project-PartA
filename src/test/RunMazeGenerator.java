package test;


import Errors.LowBoundInput;
import Errors.NullError;
import algorithms.mazeGenerators.*;

/**
 * testing maze generators
 */
public class RunMazeGenerator {
    public static void main (String[] args) throws LowBoundInput, NullError {
        testMazeGenerator(new EmptyMazeGenerator());
        testMazeGenerator(new SimpleMazeGenerator());
        testMazeGenerator(new MyMazeGenerator());
    }

    private static void testMazeGenerator (IMazeGenerator mazeGenerator) throws LowBoundInput, NullError {
        System.out.println(String.format("Maze generation time(ms): %s", mazeGenerator.measureAlgorithmTimeMillis(1000/*rows*/, 1000/*columns*/)));
// generate another maze
        Maze maze = mazeGenerator.generate(6/*rows*/, 36/*columns*/);
// prints the maze
        maze.print();
// get the maze entrance
        Position startPosition = maze.getStartPosition();
// print the start position
        System.out.println(String.format("Start Position: %s", startPosition)); // format "{row,column}"
// prints the maze exit position
        System.out.println(String.format("Goal Position: %s", maze.getGoalPosition()));
    }


}
