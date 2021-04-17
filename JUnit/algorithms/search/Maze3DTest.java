package algorithms.search;

import Errors.LowBoundInput;
import Errors.NullError;
import Errors.OutOfBoundMatrixInput;
import algorithms.maze3D.IMaze3DGenerator;
import algorithms.maze3D.Maze3D;
import algorithms.maze3D.MyMaze3DGenerator;
import algorithms.maze3D.SearchableMaze3D;


public class Maze3DTest {
    public static void main (String[] args) throws LowBoundInput, OutOfBoundMatrixInput, NullError {
        CheckTimeBuildAndSolve();
        try {
            IMaze3DGenerator mg1 = new MyMaze3DGenerator();
            Maze3D maze1 = mg1.generate(-1, -1, -1);
        } catch (LowBoundInput e) {
            e.Handle();
        }
        System.out.println("good job!");
    }

    //TAKES A LOT OF TIME- BUT WORKS.
    private static void CheckTimeBuildAndSolve () throws LowBoundInput, OutOfBoundMatrixInput, NullError {
        IMaze3DGenerator mg = new MyMaze3DGenerator();
        for (int i = 100; i < 150; i++) {
            for (int j = 100; j < 110; j++) {
                for (int k = 100; k < 110; k++) {
                    long startTime = System.currentTimeMillis();
                    Maze3D maze = mg.generate(i, j, k);
                    SearchableMaze3D searchableMaze = new SearchableMaze3D(maze);
                    if (startTime - System.currentTimeMillis() >= 60000)
                        System.out.println("build bugbug");
                    Solution sol = solveProblem3D(searchableMaze, new BreadthFirstSearch());
                    if(sol == null)
                        System.out.println("no solution bugbug");
                    if (startTime - System.currentTimeMillis() >= 120000)
                        System.out.println("solve bugbug");
                }
            }
        }
    }

    /*
    solves the maze
     */
    private static Solution solveProblem3D (ISearchable domain, ISearchingAlgorithm searcher) throws NullError, LowBoundInput, OutOfBoundMatrixInput {
        //Solve a searching problem with a searcher
        return searcher.solve(domain);

    }
}


