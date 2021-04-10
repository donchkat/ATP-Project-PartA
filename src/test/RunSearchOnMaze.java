package test;

import Errors.LowBoundInput;
import Errors.NullError;
import Errors.OutOfBoundMatrixInput;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.search.*;

import java.util.ArrayList;

public class RunSearchOnMaze {
    public static void main (String[] args) throws LowBoundInput, NullError, OutOfBoundMatrixInput {
        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(10, 10);
        maze.print();
        SearchableMaze searchableMaze = new SearchableMaze(maze);

        //solveProblem(searchableMaze, new BreadthFirstSearch());
        // solveProblem(searchableMaze, new DepthFirstSearch());
        solveProblem(searchableMaze, new BestFirstSearch());
        /**
         for(int i=0;i<1000;i++){
         IMazeGenerator mg1 = new MyMazeGenerator();
         Maze maze1 = mg1.generate(30, 30);
         //  maze.Print();

         SearchableMaze searchableMaze1 = new SearchableMaze(maze1);

         int x=mysolveProblem(searchableMaze1, new BreadthFirstSearch());
         int y=mysolveProblem(searchableMaze1, new DepthFirstSearch());
         int z=mysolveProblem(searchableMaze1, new BestFirstSearch());
         if(x>z||y>z)
         System.out.println("wronggggggggggggggggggggggggg");
         System.out.println(Math.min(Math.min(x,y),z));
         **/
    }


    private static void solveProblem (ISearchable domain, ISearchingAlgorithm searcher) throws NullError, LowBoundInput, OutOfBoundMatrixInput {
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

    private static int mysolveProblem (ISearchable domain, ISearchingAlgorithm searcher) throws NullError, LowBoundInput, OutOfBoundMatrixInput {
//Solve a searching problem with a searcher
        Solution solution = searcher.solve(domain);
        //  System.out.println(String.format("'%s' algorithm - nodes evaluated: %s", searcher.getName(), searcher.getNumberOfNodesEvaluated()));
//Printing Solution Path
        //System.out.println("Solution path:");
        ArrayList<AState> solutionPath = solution.getSolutionPath();
        return solutionPath.size();
        /***    for (int i = 0; i < solutionPath.size(); i++) {
         System.out.println(String.format("%s %s",i,solutionPath.get(i)));
         }
         ***/


    }
}