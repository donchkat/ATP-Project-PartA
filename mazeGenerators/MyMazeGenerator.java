package mazeGenerators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;

/**
 *
 */
public class MyMazeGenerator extends AMazeGenerator {
    /**
     * @param rows - number of rows in the maze
     * @param cols - number of columns in the maze
     * @return a maze that was generated with the DFS algorithm
     */
    @Override
    public Maze generate (int rows, int cols) {
        Maze newMaze = new Maze(rows, cols);
        newMaze.matrix = onesMatrix(rows, cols);
        Stack pathStack = new Stack();
        Position currPos = new Position(0, 0);

        newMaze.matrix[currPos.getRowIndex()][currPos.getColumnIndex()] = 0; //marking as visited
        pathStack.push(currPos.copy());
        while (!pathStack.isEmpty()) {
            if (checkNeighbors(newMaze, currPos)) {
                randomNeighbor(newMaze.matrix, currPos);
                pathStack.push(currPos.copy());
            } else if (!pathStack.isEmpty())
                currPos = (Position) pathStack.pop();

        }
        Position toGoal=new Position(newMaze.rows-2, newMaze.cols-2);
        if(isInGoalInvironment(toGoal,newMaze))
            zeroPathToGoal(toGoal,newMaze);
        return newMaze;
    }

    private void zeroPathToGoal(Position currPos, Maze myMaze) {
        myMaze.matrix[myMaze.rows-1][myMaze.cols-1]=0;
        Random rnd=new Random();
        int num=rnd.nextInt(10);
        if(num%2!=0)
            myMaze.matrix[myMaze.rows-1][myMaze.cols-2]=0;
        else
            myMaze.matrix[myMaze.rows-2][myMaze.cols-1]=0;
    }

    /**
     * Check if we are close to the goal postion and if we are, we break the walls to this position
     * @param myMaze - the maze we generate
     * @param currPos - the current position
     * @return true we are a step or two from the goal(which is mat[numofrows,numofcols] else false)
     */
    private boolean isInGoalInvironment(Position currPos, Maze myMaze) {
        if(myMaze.getGoalPosition().getColumnIndex()-1<=currPos.getColumnIndex()||
                myMaze.getGoalPosition().getRowIndex()-1<= currPos.getColumnIndex()){
            return true;
        }
        return false;
    }




    /**
     * first check if some neighbors are out of range and don't go there.
     * Check only the INranged cells if they contain 1's.
     * @param myMaze - the maze we generate
     * @param currPos - the current position
     * @return true if at least one neighbor(between the INranged cells) contains 1 (unvisited)
     */
    private boolean checkNeighbors (Maze myMaze, Position currPos) {
        int r = currPos.getRowIndex();
        int c = currPos.getColumnIndex();

        //checking for each neighbor if it's INrange
        boolean leftN = isOutOfRange(myMaze, new Position(r, c-2));
        boolean rightN = isOutOfRange(myMaze, new Position(r, c+2));
        boolean upN = isOutOfRange(myMaze, new Position(r-2, c));
        boolean downN = isOutOfRange(myMaze, new Position(r+2, c));

        //only the INrange neighbors are checked for their value
        if(leftN){
            leftN = false;
        }
        else{
            leftN = (myMaze.matrix[r][c - 2] == 1);
        }

        if(rightN){
            rightN = false;
        }
        else{
            rightN = (myMaze.matrix[r][c + 2] == 1);
        }
        if(upN){
            upN = false;
        }
        else {
            upN = (myMaze.matrix[r - 2][c] == 1);
        }
        if(downN){
            downN = false;
        }
        else{
            downN = (myMaze.matrix[r + 2][c] == 1);
        }
        return leftN || rightN || upN || downN;
    }

    /**
     * this function chooses to which possible neighbor to go.
     * it breaks the wall between them and puts 0 inside.
     * @param matrix  - the 2D array we generate our maze on
     * @param currPos - the current position in the maze.
     */
    private void randomNeighbor (int[][] matrix, Position currPos) {
        //random directions
        Integer[] ranDirs = randomDirections(4);
        int r = currPos.getRowIndex();
        int c = currPos.getColumnIndex();
        //check each direction
        for (Integer ranDir : ranDirs) {
            switch (ranDir) {

                case 1: //UP
                    //index out of range
                    if (r - 2 < 0)
                        continue;
                    if (matrix[r - 2][c] != 0) {
                        matrix[r - 2][c] = 0;
                        matrix[r - 1][c] = 0;
                        currPos.setRowIndex(r - 2);
                        currPos.setColumnIndex(c);
                        return ;
                    }
                    break;
                case 2: //RIGHT
                    if (c + 2 > matrix[0].length - 1)
                        continue;
                    if (matrix[r][c + 2] != 0) {
                        matrix[r][c + 2] = 0;
                        matrix[r][c + 1] = 0;
                        currPos.setRowIndex(r);
                        currPos.setColumnIndex(c + 2);
                        return ;
                    }
                    break;
                case 3: //LEFT
                    if (c - 2 < 0)
                        continue;
                    if (matrix[r][c - 2] != 0) {
                        matrix[r][c - 2] = 0;
                        matrix[r][c - 1] = 0;
                        currPos.setRowIndex(r);
                        currPos.setColumnIndex(c - 2);
                        return ;
                    }
                    break;
                case 4: //DOWN
                    if (r + 2 > matrix.length - 1)
                        continue;
                    if (matrix[r + 2][c] != 0) {
                        matrix[r + 2][c] = 0;
                        matrix[r + 1][c] = 0;
                        currPos.setRowIndex(r + 2);
                        currPos.setColumnIndex(c);
                        return ;
                    }
                    break;
            }
        }
    }


    /**
     * creates an array of numbers in not organized way
     * @param numOfDirs - quantity of the directions
     * @return array of numbers 0-X representing X different directions in random order
     */
    private Integer[] randomDirections (int numOfDirs) {
        ArrayList<Integer> directions = new ArrayList<>();
        for (int i = 0; i < numOfDirs; i++) {
            directions.add(i + 1);
        }
        Collections.shuffle(directions);

        return directions.toArray(new Integer[numOfDirs]);
    }

    /**
     * @param myMaze - the maze we generate
     * @param optionalPos - the neighbor position we are checking
     * @return true if this neighbor is not in range.
     */
    private boolean isOutOfRange (Maze myMaze, Position optionalPos) {
        int r = optionalPos.getRowIndex();
        int c = optionalPos.getColumnIndex();
        int mazeHeight = myMaze.rows-1;
        int mazeWidth = myMaze.cols-1;
        return r<0 ||r > mazeHeight || c <0 || c > mazeWidth;

    }

    /**
     * fills the maze with 1's.
     * @param rows - number of rows
     * @param cols - number of columns
     * @return matrix full of 1's
     */
    private int[][] onesMatrix (int rows, int cols) {
        Maze newEmptyMaze = new Maze(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                newEmptyMaze.matrix[i][j] = 1;
            }
        }
        return newEmptyMaze.matrix;
    }

}



