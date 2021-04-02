import mazeGenerators.Maze;
import mazeGenerators.MyMazeGenerator;

public class main {
    public static void main(String[] args) {
        MyMazeGenerator myMazeGenerator=new MyMazeGenerator();
        Maze m=myMazeGenerator.generate(5,30);
        m.Print();
    }
}

