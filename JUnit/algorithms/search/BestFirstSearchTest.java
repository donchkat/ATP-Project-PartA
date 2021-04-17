package algorithms.search;

//import static org.junit.jupiter.api.Assertions.*;

import Errors.LowBoundInput;
import Errors.NullError;
import Errors.OutOfBoundMatrixInput;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import java.util.ArrayList;

class BestFirstSearchTest {


    /**
     * checks the time and the ability(how well they create the maze) of the generators
     * @param args 
     * @throws LowBoundInput
     * @throws NullError
     * @throws OutOfBoundMatrixInput
     *
     */
    public static void main (String[] args) throws LowBoundInput, NullError, OutOfBoundMatrixInput {
    CheckTotalCost();
    CheckTotalTime();
    System.out.println("good job!");
    }



    //
    private static void CheckTotalTime() throws LowBoundInput, NullError, OutOfBoundMatrixInput {
        for(int i=1000;i<1050;i++) {
           long startTime=System.currentTimeMillis();
            IMazeGenerator mg1 = new MyMazeGenerator();
            Maze maze1 = mg1.generate(i, i);
            SearchableMaze searchableMaze1 = new SearchableMaze(maze1);
            double z = getCost(searchableMaze1, new BestFirstSearch());
            if (startTime-System.currentTimeMillis()>=60000)
                System.out.println("bugbug");

        }
    }

    private static double getCost (ISearchable domain, ISearchingAlgorithm searcher) throws NullError, LowBoundInput, OutOfBoundMatrixInput {
        Solution solution = searcher.solve(domain);
        ArrayList<AState> solutionPath = solution.getSolutionPath();
        return solutionPath.get(solutionPath.size()-1).getCost();
    }
private static void CheckTotalCost() throws NullError, LowBoundInput, OutOfBoundMatrixInput {
            for(int i=0;i<100;i++) {
                IMazeGenerator mg1 = new MyMazeGenerator();
                for (int j = 2; j < 35; j++) {
                    for (int k = 2; k <35 ; k++) {
                        Maze maze1 = mg1.generate(j, k);
                        SearchableMaze searchableMaze1 = new SearchableMaze(maze1);
                        double x = getCost(searchableMaze1, new BreadthFirstSearch());
                        double y = getCost(searchableMaze1, new DepthFirstSearch());
                        double z = getCost(searchableMaze1, new BestFirstSearch());
                        if (x < z || y < z)
                            System.out.println("bugbug");
                    }
                }
            }
}



}