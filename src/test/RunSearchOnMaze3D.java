package test;

import algorithms.maze3D.IMazeGenerator3D;
import algorithms.maze3D.Maze3D;
import algorithms.maze3D.MyMaze3DGenerator;
import algorithms.maze3D.SearchableMaze3D;
import algorithms.search.*;
import java.util.ArrayList;

public class RunSearchOnMaze3D {
    public static void main (String[] args) {
        IMazeGenerator3D mg = new MyMaze3DGenerator();
        Maze3D maze = mg.generate(100, 100, 100);
        //maze.print();
        SearchableMaze3D searchableMaze = new SearchableMaze3D(maze);
        //solveProblem3D(searchableMaze, new BreadthFirstSearch());
        //solveProblem3D(searchableMaze, new DepthFirstSearch());
        solveProblem3D(searchableMaze, new BestFirstSearch());

    }

    private static void solveProblem3D (ISearchable domain, ISearchingAlgorithm searcher) {
        //Solve a searching problem with a searcher
        Solution solution = searcher.search(domain);
        System.out.println(String.format("'%s' algorithm - nodes evaluated: %s", searcher.getName(), searcher.getNumberOfNodesEvaluated()));
        //Printing Solution Path
        System.out.println("Solution path:");
        ArrayList<AState> solutionPath = solution.getSolutionPath();
        for (int i = 0; i < solutionPath.size(); i++) {
            System.out.println(String.format("%s %s", i, solutionPath.get(i)));
        }
    }
}
