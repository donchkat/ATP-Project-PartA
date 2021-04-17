package algorithms.maze3D;

import Errors.LowBoundInput;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;

/**
 * 3D maze generator (DFS algorithm)
 */
public class MyMaze3DGenerator extends AMaze3DGenerator {
    /**
     * generates a 3D maze with the DFS algorithm
     *
     * @param depth - the number of layers in the maze
     * @param rows  - the number of rows in the maze
     * @param cols  - the number of columns in the maze
     * @return 3D maze
     */
    @Override
    public Maze3D generate (int depth, int rows, int cols) throws LowBoundInput {
        Maze3D newMaze = new Maze3D(depth, rows, cols);
        newMaze.setMatrix3D(onesMatrix(depth, rows, cols));
        Stack<Position3D> pathStack = new Stack<>();
        Position3D currPos = new Position3D(0, 0, 0);

        newMaze.setCellInMatrix3D(currPos.getDepthIndex(),currPos.getRowIndex() ,currPos.getColumnIndex() ,0 ); //marking as visited
        pathStack.push(currPos.copy());
        while (!pathStack.isEmpty()) {
            if (checkNeighbors(newMaze, currPos)) {
                randomNeighbor(newMaze.getMatrix3D(), currPos);
                pathStack.push(currPos.copy());
            }
            else if (!pathStack.isEmpty())
                currPos =pathStack.pop();
        }
        Position3D toGoal = new Position3D(newMaze.getDepth() - 2, newMaze.getRows() - 2, newMaze.getCols() - 2);
        if (isInGoalEnvironment(toGoal, newMaze))
            zeroPathToGoal( newMaze);
        return newMaze;
    }

    /**
     *
     *select randomly path from the closest position to the end position and build it on the matrix
     * @param myMaze  - the 3D maze we generate
     */
    private void zeroPathToGoal ( Maze3D myMaze) {
        myMaze.setCellInMatrix3D(myMaze.getDepth() - 1,myMaze.getRows() - 1,myMaze.getCols() - 1, 0 );
        myMaze.setCellInMatrix3D(myMaze.getDepth() - 1, myMaze.getRows() - 2, myMaze.getCols() - 2, 0 );
        Random rnd = new Random();
        int num = rnd.nextInt(10);
        if (num % 2 != 0)
            myMaze.setCellInMatrix3D(myMaze.getDepth() - 1,myMaze.getRows() - 1, myMaze.getCols() - 2, 0 );
        else
            myMaze.setCellInMatrix3D(myMaze.getDepth() - 1, myMaze.getRows() - 2, myMaze.getCols() - 1, 0);
    }

    /**
     * Check if we are close to the goal position.
     *
     * @param myMaze  - the maze we generate
     * @param currPos - the current position
     * @return True - we are a step or two from the goal (myMaze[d][r][c]) ,else False)
     */
    private boolean isInGoalEnvironment (Position3D currPos, Maze3D myMaze) {
        int goalDepthIndex = myMaze.getGoalPosition3D().getDepthIndex() - 1;
        int goalRowIndex = myMaze.getGoalPosition3D().getRowIndex() - 1;
        int goalColIndex = myMaze.getGoalPosition3D().getColumnIndex() - 1;
        return goalDepthIndex <= currPos.getDepthIndex() || goalRowIndex <= currPos.getRowIndex() ||
                goalColIndex <= currPos.getColumnIndex();
    }

    /**
     * first check if some neighbors are out of range and if yes, don't go there.
     * Check only the in ranged cells if they contain 1's.
     *
     * @param myMaze  - the maze we generate
     * @param currPos - the current position
     * @return true if at least one neighbor(between the in ranged cells) contains 1 (unvisited)
     */
    private boolean checkNeighbors (Maze3D myMaze, Position3D currPos) {
        int d = currPos.getDepthIndex();
        int r = currPos.getRowIndex();
        int c = currPos.getColumnIndex();
        //checking for each neighbor if it's in range
        boolean leftN = isOutOfRange(myMaze, new Position3D(d, r, c - 2));
        boolean rightN = isOutOfRange(myMaze, new Position3D(d, r, c + 2));
        boolean upN = isOutOfRange(myMaze, new Position3D(d, r - 2, c));
        boolean downN = isOutOfRange(myMaze, new Position3D(d, r + 2, c));
        boolean insideN = isOutOfRange(myMaze, new Position3D(d + 2, r, c));
        boolean outsideN = isOutOfRange(myMaze, new Position3D(d - 2, r, c));
        //only the in range neighbors are checked for their value
        if (leftN) {
            leftN = false;
        } else {
            leftN = (myMaze.getMatrix3D()[d][r][c - 2] == 1);
        }
        if (rightN) {
            rightN = false;
        } else {
            rightN = (myMaze.getMatrix3D()[d][r][c + 2] == 1);
        }
        if (upN) {
            upN = false;
        } else {
            upN = (myMaze.getMatrix3D()[d][r - 2][c] == 1);
        }
        if (downN) {
            downN = false;
        } else {
            downN = (myMaze.getMatrix3D()[d][r + 2][c] == 1);
        }
        if (insideN) {
            insideN = false;
        } else {
            insideN = (myMaze.getMatrix3D()[d+2][r][c] == 1);
        }
        if (outsideN) {
            outsideN = false;
        } else {
            outsideN = (myMaze.getMatrix3D()[d-2][r][c] == 1);
        }
        return leftN || rightN || upN || downN || insideN || outsideN;
    }
    /**
     * the function "breaks" wall between current cell and neighbor and moves to the neighbor
     * @param matrix-matix of the maze
     * @param r1-cell behind the wall row index
     * @param d1-cell behind the wall depth index
     * @param c1-cell behind the wall column index
     * @param r2-wall row index
     * @param d2-wall depth index
     * @param c2-wall column index
     * @param currPos-current cell
     * @throws LowBoundInput
     */
    private void randomNeighborHelper(int[][][] matrix,int d1,int d2 ,int r1, int c1, int r2, int c2, Position3D currPos) throws LowBoundInput {
        matrix[d1][r1][c1] = 0;
        matrix[d2][r2][c2] = 0;
        currPos.setDepth(d1);
        currPos.setRowIndex(r1);
        currPos.setColumnIndex(c1);
    }

    /**
     * this function chooses to which possible neighbor to go.
     * it breaks the wall between them and puts 0 inside.
     *
     * @param matrix  - the 3D array we generate our maze on
     * @param currPos - the current position in the maze.
     */
    private void randomNeighbor (int[][][] matrix, Position3D currPos) throws LowBoundInput {
        //random directions
        Integer[] ranDirs = randomDirections(6);
        int d = currPos.getDepthIndex();
        int r = currPos.getRowIndex();
        int c = currPos.getColumnIndex();
        //check each direction
        for (Integer ranDir : ranDirs) {
            switch (ranDir) {

                case 1: //UP
                    //index out of range
                    if (r - 2 < 0)
                        continue;
                    if (matrix[d][r - 2][c] != 0) {
                        randomNeighborHelper(matrix,d,d ,r-2,c,r-1,c,currPos);
                        return;
                    }
                    break;
                case 2: //RIGHT
                    if (c + 2 > matrix[0][0].length - 1)
                        continue;
                    if (matrix[d][r][c + 2] != 0) {
                        randomNeighborHelper(matrix,d,d ,r,c+2,r,c+1,currPos);
                        return;
                    }
                    break;
                case 3: //LEFT
                    if (c - 2 < 0)
                        continue;
                    if (matrix[d][r][c - 2] != 0) {
                        randomNeighborHelper(matrix,d,d ,r,c-2,r,c-1,currPos);
                        return;
                    }
                    break;
                case 4: //DOWN
                    if (r + 2 > matrix[0].length - 1)
                        continue;
                    if (matrix[d][r + 2][c] != 0) {
                        randomNeighborHelper(matrix,d,d ,r+2,c,r+1,c,currPos);
                        return;
                    }
                    break;
                case 5: //INSIDE
                    if (d + 2 > matrix.length - 1)
                        continue;

                    if (matrix[d + 2][r][c] != 0) {
                        randomNeighborHelper(matrix,d+2,d+1 ,r,c,r,c,currPos);
                        return;
                    }
                    break;
                case 6: //OUTSIDE
                    if (d - 2 < 0)
                        continue;
                    if (matrix[d - 2][r][c] != 0) {
                        randomNeighborHelper(matrix,d-2,d-1 ,r,c,r,c,currPos);
                        return;
                    }
                    break;
            }
        }
    }


    /**
     * creates an array of numbers in not organized way
     *
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
     * @param myMaze      - the maze we generate
     * @param optionalPos - the neighbor position we are checking
     * @return true if this neighbor is not in range.
     */
    private boolean isOutOfRange (Maze3D myMaze, Position3D optionalPos) {
        return myMaze.checkLegalCell(optionalPos.getDepthIndex(),optionalPos.getRowIndex(),optionalPos.getColumnIndex());
    }

    /**
     * fills the maze with 1's.
     *
     * @param depth - the depth of the maze
     * @param rows  - number of rows
     * @param cols  - number of columns
     * @return matrix full of 1's
     */
    private int[][][] onesMatrix (int depth, int rows, int cols) throws LowBoundInput {
        Maze3D newEmptyMaze = new Maze3D(depth, rows, cols);
        for (int i = 0; i < depth; i++) {
            for (int j = 0; j < rows; j++) {
                for (int k = 0; k < cols; k++) {
                    newEmptyMaze.setCellInMatrix3D(i,j,k,1);
                }
            }
        }
        return newEmptyMaze.getMatrix3D();
    }
}
