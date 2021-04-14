package algorithms.search;

import Errors.LowBoundInput;
import Errors.NullError;
import Errors.OutOfBoundMatrixInput;
import algorithms.maze3D.IMaze3DGenerator;
import algorithms.maze3D.Maze3D;
import algorithms.maze3D.MyMaze3DGenerator;
import algorithms.maze3D.SearchableMaze3D;

import java.util.ArrayList;

public class Maze3DTest {
    public static void main(String[] args) throws LowBoundInput, OutOfBoundMatrixInput, NullError {
        CheckTimeBuildAndSolve();
        System.out.println("good job!");
    }
    //TAKES A LOT OF TIME- BUT WORKS.
    private static void CheckTimeBuildAndSolve() throws LowBoundInput, OutOfBoundMatrixInput, NullError {
        IMaze3DGenerator mg = new MyMaze3DGenerator();
        for (int i = 100; i <150 ; i++) {
            for (int j = 100; j < 110; j++) {
                for (int k = 100; k < 110; k++) {
                    long startTime=System.currentTimeMillis();
                    Maze3D maze = mg.generate(i, j,k);
                    SearchableMaze3D searchableMaze = new SearchableMaze3D(maze);
                    if(startTime-System.currentTimeMillis()>=60000)
                        System.out.println("build bugbug");
                    solveProblem3D(searchableMaze, new BreadthFirstSearch());
                    if(startTime-System.currentTimeMillis()>=120000)
                        System.out.println("solve bugbug");
                }
            }
        }
    }

    //???
    private static void solveProblem3D (ISearchable domain, ISearchingAlgorithm searcher) throws NullError, LowBoundInput, OutOfBoundMatrixInput {
        //Solve a searching problem with a searcher
        Solution solution = searcher.solve(domain);

    }
}


