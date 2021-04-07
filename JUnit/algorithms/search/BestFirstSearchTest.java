package algorithms.search;

//import static org.junit.jupiter.api.Assertions.*;

import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import java.util.ArrayList;

class BestFirstSearchTest {


    public static void main (String[] args) {
        long startTime = System.currentTimeMillis();
        boolean flag=true;

        for(int i=0;i<100;i++) {
             IMazeGenerator mg1 = new MyMazeGenerator();
              if(startTime>=60000)
                  flag=false;
             for (int j = 2; j < 35; j++) {
                 for (int k = 2; k <35 ; k++) {
                     Maze maze1 = mg1.generate(j, k);
                     SearchableMaze searchableMaze1 = new SearchableMaze(maze1);
                     int x = mysolveProblem(searchableMaze1, new BreadthFirstSearch());
                     int y = mysolveProblem(searchableMaze1, new DepthFirstSearch());
                     int z = mysolveProblem(searchableMaze1, new BestFirstSearch());
                     if (x > z || y > z)
                         System.out.println("bugbug");

                 }
             }
         }
        if(!flag)
            System.out.println("good job!");
        else
            System.out.println("bad time");
    }
         private static int mysolveProblem (ISearchable domain, ISearchingAlgorithm searcher) {
        Solution solution = searcher.solve(domain);
        ArrayList<AState> solutionPath = solution.getSolutionPath();
        return solutionPath.size();



    }

}