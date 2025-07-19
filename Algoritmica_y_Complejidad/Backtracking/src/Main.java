import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        BackTracking laberint = new BackTracking();
        int[][] maze = {{0, 0, 0, 0},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0}};
        //maze[1][2] = 1;
        maze[2][2] = 1;
        List<int[]> camino = laberint.resolverLaberinto(maze, 2, 2, 4, 4);
        for(int[] posicion : camino){
            System.out.println(Arrays.toString(posicion));
        }
    }
}
