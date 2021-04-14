package algorithms.search;

import Errors.LowBoundInput;
import Errors.NullError;
import Errors.OutOfBoundMatrixInput;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import java.util.ArrayList;

/**
 * testing the timing, is the maze solvable.
 */
public class BuildMazeTest {
    public static void main(String[] args) throws Exception {
        testTime();
        testSolveAbility();
        System.out.println("good job");
    }

    private static void testTime() throws Exception {
        //TAKES A LOT OF TIME, BUT WORKS!
        for (int i = 950; i < 1010; i++) {
            for (int j = 950; j < 1010; j++) {
                long startTime = System.currentTimeMillis();
                IMazeGenerator mg1 = new MyMazeGenerator();
                Maze maze1 = mg1.generate(i, j);
                SearchableMaze searchableMaze1 = new SearchableMaze(maze1); //NOT USING IT - WHY?
                if(startTime-System.currentTimeMillis()>=60000)
                    System.out.println("bugbug");
            }
        }
    }
    private static void testSolveAbility() throws Exception {
        for (int i = 100; i < 200; i++) {
            for (int j = 100; j < 200; j++) {
                long startTime = System.currentTimeMillis();
                IMazeGenerator mg1 = new MyMazeGenerator();
                Maze maze1 = mg1.generate(i, j);
                SearchableMaze searchableMaze1 = new SearchableMaze(maze1);
                ArrayList<AState> x = mySolveProblem(searchableMaze1, new BreadthFirstSearch());
                ArrayList<AState> y = mySolveProblem(searchableMaze1, new DepthFirstSearch());
                ArrayList<AState> z = mySolveProblem(searchableMaze1, new BestFirstSearch());
                if (x.size()==0||y.size()==0||z.size()==0)
                    System.out.println("not solvable");
                if(!x.get(0).equals(y.get(0))||!y.get(0).equals(z.get(0)))
                    System.out.println("bugbug start");
                if(!x.get(x.size()-1).equals(y.get(y.size()-1))||!y.get(y.size()-1).equals(z.get(z.size()-1)))
                    System.out.println("bugbug end");
                if(startTime-System.currentTimeMillis()>=60000) //-WHY 60000?
                    throw new Exception("run out of time");

            }
        }

    }
    private static ArrayList<AState> mySolveProblem (ISearchable domain, ISearchingAlgorithm searcher) throws NullError, LowBoundInput, OutOfBoundMatrixInput {
        Solution solution = searcher.solve(domain);
        ArrayList<AState> solutionPath = solution.getSolutionPath();
        return solutionPath;



    }

}
