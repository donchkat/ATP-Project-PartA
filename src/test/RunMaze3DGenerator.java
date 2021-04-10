package test;

import Errors.LowBoundInput;
import algorithms.maze3D.IMaze3DGenerator;
import algorithms.maze3D.Maze3D;
import algorithms.maze3D.MyMaze3DGenerator;
import algorithms.maze3D.Position3D;


public class RunMaze3DGenerator {
    public static void main (String[] args) throws LowBoundInput {
        testMazeGenerator3D(new MyMaze3DGenerator());
    }

    private static void testMazeGenerator3D (IMaze3DGenerator mazeGenerator) throws LowBoundInput {
        System.out.println(String.format("Maze generation time(ms): %s", mazeGenerator.measureAlgorithmTimeMillis(100, 100/*rows*/, 100/*columns*/)));
// generate another maze
        Maze3D maze = mazeGenerator.generate(2, 5/*rows*/, 5/*columns*/);
// prints the maze
        maze.print();
// get the maze entrance
        Position3D startPosition = maze.getStartPosition3D();
// print the start position
        System.out.println(String.format("Start Position: %s", startPosition)); // format "{row,column}"
// prints the maze exit position
        System.out.println(String.format("Goal Position: %s", maze.getGoalPosition3D()));
    }
}

