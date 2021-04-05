package algorithms.maze3D;

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
    public Maze3D generate (int depth, int rows, int cols) {
        Maze3D newMaze = new Maze3D(depth, rows, cols);
        newMaze.matrix3D = onesMatrix(depth, rows, cols);
        Stack<Position3D> pathStack = new Stack<>();
        Position3D currPos = new Position3D(0, 0, 0);

        newMaze.matrix3D[currPos.getDepthIndex()][currPos.getRowIndex()][currPos.getColumnIndex()] = 0; //marking as visited
        pathStack.push(currPos.copy());
        while (!pathStack.isEmpty()) {
            if (checkNeighbors(newMaze, currPos)) {
                randomNeighbor(newMaze.matrix3D, currPos);
                pathStack.push(currPos.copy());
            }
            else if (!pathStack.isEmpty())
                currPos =pathStack.pop();
        }
        Position3D toGoal = new Position3D(newMaze.depth - 2, newMaze.rows - 2, newMaze.cols - 2);
        if (isInGoalEnvironment(toGoal, newMaze))
            zeroPathToGoal(toGoal, newMaze);
        return newMaze;
    }

    /**
     * ??????????????????????????????
     *
     * @param currPos - current position - ITS NEVER USED - WHY??
     * @param myMaze  - the 3D maze we generate
     */
    private void zeroPathToGoal (Position3D currPos, Maze3D myMaze) {
        myMaze.matrix3D[myMaze.depth - 1][myMaze.rows - 1][myMaze.cols - 1] = 0;
        Random rnd = new Random();
        int num = rnd.nextInt(10);
        if (num % 2 != 0)
            myMaze.matrix3D[myMaze.depth - 1][myMaze.rows - 1][myMaze.cols - 2] = 0;
        else
            myMaze.matrix3D[myMaze.depth - 1][myMaze.rows - 2][myMaze.cols - 1] = 0;
    }

    /**
     * Check if we are close to the goal position and if we are, we break the walls to this position
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
     * first check if some neighbors are out of range and don't go there.
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
            leftN = (myMaze.matrix3D[d][r][c - 2] == 1);
        }
        if (rightN) {
            rightN = false;
        } else {
            rightN = (myMaze.matrix3D[d][r][c + 2] == 1);
        }
        if (upN) {
            upN = false;
        } else {
            upN = (myMaze.matrix3D[d][r - 2][c] == 1);
        }
        if (downN) {
            downN = false;
        } else {
            downN = (myMaze.matrix3D[d][r + 2][c] == 1);
        }
        if (insideN) {
            insideN = false;
        } else {
            insideN = (myMaze.matrix3D[d+2][r][c] == 1);
        }
        if (outsideN) {
            outsideN = false;
        } else {
            outsideN = (myMaze.matrix3D[d-2][r][c] == 1);
        }
        return leftN || rightN || upN || downN || insideN || outsideN;
    }

    /**
     * this function chooses to which possible neighbor to go.
     * it breaks the wall between them and puts 0 inside.
     *
     * @param matrix  - the 3D array we generate our maze on
     * @param currPos - the current position in the maze.
     */
    private void randomNeighbor (int[][][] matrix, Position3D currPos) {
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
                        matrix[d][r - 2][c] = 0;
                        matrix[d][r - 1][c] = 0;
                        currPos.setDepth(d);
                        currPos.setRowIndex(r - 2);
                        currPos.setColumnIndex(c);
                        return;
                    }
                    break;
                case 2: //RIGHT
                    if (c + 2 > matrix[0][0].length - 1)
                        continue;
                    if (matrix[d][r][c + 2] != 0) {
                        matrix[d][r][c + 2] = 0;
                        matrix[d][r][c + 1] = 0;
                        currPos.setRowIndex(d);
                        currPos.setRowIndex(r);
                        currPos.setColumnIndex(c + 2);
                        return;
                    }
                    break;
                case 3: //LEFT
                    if (c - 2 < 0)
                        continue;
                    if (matrix[d][r][c - 2] != 0) {
                        matrix[d][r][c - 2] = 0;
                        matrix[d][r][c - 1] = 0;
                        currPos.setDepth(d);
                        currPos.setRowIndex(r);
                        currPos.setColumnIndex(c - 2);
                        return;
                    }
                    break;
                case 4: //DOWN
                    if (r + 2 > matrix[0].length - 1)
                        continue;
                    if (matrix[d][r + 2][c] != 0) {
                        matrix[d][r + 2][c] = 0;
                        matrix[d][r + 1][c] = 0;
                        currPos.setDepth(d);
                        currPos.setRowIndex(r + 2);
                        currPos.setColumnIndex(c);
                        return;
                    }
                    break;
                case 5: //INSIDE
                    if (d + 2 > matrix.length - 1)
                        continue;

                    if (matrix[d + 2][r][c] != 0) {
                        matrix[d + 2][r][c] = 0;
                        matrix[d + 1][r][c] = 0;
                        currPos.setDepth(d + 2);
                        currPos.setRowIndex(r);
                        currPos.setColumnIndex(c);
                        return;
                    }
                    break;
                case 6: //OUTSIDE
                    if (d - 2 < 0)
                        continue;
                    if (matrix[d - 2][r][c] != 0) {
                        matrix[d - 2][r][c] = 0;
                        matrix[d - 1][r][c] = 0;
                        currPos.setDepth(d - 2);
                        currPos.setRowIndex(r);
                        currPos.setColumnIndex(c);
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
        int d = optionalPos.getDepthIndex();
        int r = optionalPos.getRowIndex();
        int c = optionalPos.getColumnIndex();
        int mazeDepth = myMaze.depth - 1;
        int mazeHeight = myMaze.rows - 1;
        int mazeWidth = myMaze.cols - 1;
        return r < 0 || r > mazeHeight || c < 0 || c > mazeWidth || d < 0 || d > mazeDepth;

    }

    /**
     * fills the maze with 1's.
     *
     * @param depth - the depth of the maze
     * @param rows  - number of rows
     * @param cols  - number of columns
     * @return matrix full of 1's
     */
    private int[][][] onesMatrix (int depth, int rows, int cols) {
        Maze3D newEmptyMaze = new Maze3D(depth, rows, cols);
        for (int i = 0; i < depth; i++) {
            for (int j = 0; j < rows; j++) {
                for (int k = 0; k < cols; k++) {
                    newEmptyMaze.matrix3D[i][j][k] = 1;
                }
            }
        }
        return newEmptyMaze.matrix3D;
    }
}
