package finki_25.w4;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Week4 {
    static boolean checkBounds(int i, int j, int rows, int cols){
        return (i >= 0 && j >= 0 && i < rows && j < cols);
    }

    static long solve(char[][] grid){
        int startRow = 0;
        int startCol = 0;
        assert grid[startRow][startCol] == 'S';

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startRow, startCol, 0}); // i, j, score
        grid[startRow][startCol] = '#'; // marker for seen cells

        while (!queue.isEmpty()){
            int []curr = queue.poll();
            int i = curr[0];
            int j = curr[1];
            int score = curr[2];

            int[][]MOVES = new int[][]{
                    {-1,0},  // l
                    {1,0},   // r
                    {0,-1},  // d
                    {0,1}    // u
            };

            for (int[]move : MOVES){
                int new_i = i + move[0];
                int new_j = j + move[1];
                if (checkBounds(new_i, new_j, grid.length, grid[0].length)){
                    char sign = grid[new_i][new_j];

                    if (sign == 'E') return score + 1;

                    if (sign == '.'){
                        grid[new_i][new_j] = '#'; // seen
                        queue.offer(new int[]{new_i, new_j, score + 1});
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        File file = new File("finki_25/w4/input.txt");
        Scanner sc = new Scanner(file);
        String []parts = sc.nextLine().split(" ");
        int rows = Integer.parseInt(parts[0]);
        int cols = Integer.parseInt(parts[1]);
        char [][]grid = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            grid[i] = sc.nextLine().toCharArray();
        }

        System.out.println(solve(grid));
    }
}
