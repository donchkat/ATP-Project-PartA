package test;

import Errors.LowBoundInput;
import Errors.NullError;
import Errors.OutOfBoundMatrixInput;
import algorithms.maze3D.IMaze3DGenerator;
import algorithms.maze3D.Maze3D;
import algorithms.maze3D.MyMaze3DGenerator;
import algorithms.maze3D.SearchableMaze3D;
import algorithms.search.*;

import java.util.ArrayList;

/**
 * testing the searching algorithms on 3D maze
 */
public class RunSearchOnMaze3D {
    public static void main (String[] args) throws NullError, LowBoundInput, OutOfBoundMatrixInput {
        IMaze3DGenerator mg = new MyMaze3DGenerator();

        for (int i = 0; i < 100; i++) {
            Maze3D maze = mg.generate(3, 5, 5);
            maze.print();
            SearchableMaze3D searchableMaze = new SearchableMaze3D(maze);
            solveProblem3D(searchableMaze, new BreadthFirstSearch());
            solveProblem3D(searchableMaze, new DepthFirstSearch());
            solveProblem3D(searchableMaze, new BestFirstSearch());

        }

    }

    private static void solveProblem3D (ISearchable domain, ISearchingAlgorithm searcher) throws NullError, LowBoundInput, OutOfBoundMatrixInput {
        //Solve a searching problem with a searcher
        Solution solution = searcher.solve(domain);
        System.out.println(String.format("'%s' algorithm - nodes evaluated: %s", searcher.getName(), searcher.getNumberOfNodesEvaluated()));
        //Printing Solution Path
        System.out.println("Solution path:");
        ArrayList<AState> solutionPath = solution.getSolutionPath();
        for (int i = 0; i < solutionPath.size(); i++) {
            System.out.println(String.format("%s %s", i, solutionPath.get(i)));
        }
    }
}
