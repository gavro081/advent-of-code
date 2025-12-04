package aoc_24.day6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

// preprepreprepsporo ama oh well
public class Day6 {
    class Part1 {
        static int solve(char[][] table, int currx, int curry) {
            int rows = table.length;
            int cols = table[0].length;
            boolean[][] checked = new boolean[rows][cols];
            for (int i = 0; i < rows; i++) {
                Arrays.fill(checked[i], false);
            }
            checked[currx][curry] = true;
            int dir = 0;
            // dir : 0 gore 1 desno 2 dole 3 levo
            // % 3
            int prevx = -1, prevy = -1;
            while (true) {
                if (table[currx][curry] == '#') {
                    dir++;
                    dir %= 4;
                    currx = prevx;
                    curry = prevy;
                }
                if (!checked[currx][curry]) {
                    checked[currx][curry] = true;
                }
                if (currx == 0 || currx == rows - 1 || curry == cols - 1 || curry == 0) {

                    break;
                }
                prevx = currx;
                prevy = curry;
                switch (dir) {
                    case 0:
                        currx--;
                        break;
                    case 1:
                        curry++;
                        break;
                    case 2:
                        currx++;
                        break;
                    case 3:
                        curry--;
                        break;
                }
            }
            int ctr = 0;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (checked[i][j]) ctr++;
                }
            }
            return ctr;
        }
    }

    class Part2{
        static boolean isLoop(char [][]table, int currx, int curry, int newx, int newy){
            if (table[newx][newy] == '^') return false;
            char tmp = table[newx][newy];
            table[newx][newy] = '#';
            int rows = table.length;
            int cols = table[0].length;
            boolean [][][]checked = new boolean[rows][cols][4];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    for (int k = 0; k < 4; k++) {
                        checked[i][j][k] = false;
                    }
                }
            }
            checked[currx][curry][0] = true;
            int dir = 0;
            int prevx = -1, prevy = -1;
            while (true){
                if (table[currx][curry] == '#'){
//                if (!checked[currx][curry][dir + 1 % 4])
                    if (checked[currx][curry][dir]) {
                        table[newx][newy] = tmp;
                        return true;
                    }
                    if (!checked[currx][curry][dir]) checked[currx][curry][dir] = true;
                    dir++;
                    dir %= 4;
                    currx = prevx;
                    curry = prevy;
                }
                if (currx == 0 || currx == rows - 1 || curry == cols -1 || curry == 0){
                    table[newx][newy] = tmp;
                    return false;
                }
                prevx = currx; prevy = curry;
                switch (dir){
                    case 0: currx--; break;
                    case 1: curry++; break;
                    case 2: currx++; break;
                    case 3: curry--; break;
                }
            }
        }
        static int solve(char [][]table, int currx, int curry){
            int rows = table.length;
            int cols = table[0].length;
            boolean [][][]checked = new boolean[rows][cols][4];
            boolean [][]dp = new boolean[rows][cols];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    for (int k = 0; k < 4; k++) {
                        checked[i][j][k] = false;
                    }
                }
            }
            int startx = currx;
            int starty = curry;
            int ctr = 0;
            checked[currx][curry][0] = true;
            int dir = 0;
            int prevx = -1, prevy = -1;
            while (true){
                if (table[currx][curry] == '#'){
                    dir++;
                    dir %= 4;
                    currx = prevx;
                    curry = prevy;
                }
                if (!checked[currx][curry][dir]){
                    checked[currx][curry][dir] = true;
                    switch (dir){
                        case 0:
                            if (currx - 1 >= 0 && !dp[currx-1][curry] && isLoop(table, startx, starty,currx-1,curry))
                                dp[currx - 1][curry] = true;
                            break;
                        case 1:
                            if (curry + 1 < cols && !dp[currx][curry + 1] && isLoop(table, startx, starty, currx, curry + 1))
                                dp[currx][curry + 1] = true;
                            break;
                        case 2:
                            if (currx + 1 < rows && !dp[currx+1][curry] && isLoop(table, startx, starty, currx + 1, curry))
                                dp[currx + 1][curry] = true;
                            break;
                        case 3:
                            if (curry - 1 >= 0 && !dp[currx][curry-1] && isLoop(table, startx, starty, currx, curry - 1))
                                dp[currx][curry - 1] = true;
                            break;
                    }
                }
                if (currx == 0 || currx == rows - 1 || curry == cols -1 || curry == 0){
                    break;
                }
                prevx = currx; prevy = curry;
                switch (dir){
                    case 0: currx--; break;
                    case 1: curry++; break;
                    case 2: currx++; break;
                    case 3: curry--; break;
                }
            }

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (dp[i][j]) ctr++;
                }
            }
            return ctr;
        }
    }

    public static void main(String[] args) {
        File file = new File("aoc_24/day6/input.txt");
        ArrayList<String> l = new ArrayList<>();
        try{
            Scanner in = new Scanner(file);
            while (in.hasNextLine()){
                String line = in.nextLine();
                l.add(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found!");
        }

        int currx = -1, curry = -1;
        char [][]table = new char[l.size()][l.getFirst().length()];
        for (int i = 0; i < l.size(); i++) {
            for (int j = 0; j < l.getFirst().length(); j++) {
                table[i][j] = l.get(i).charAt(j);
                if (table[i][j] == '^'){
                    currx = i; curry = j;
                }
            }
        }

        System.out.println(Part1.solve(table, currx, curry));
        System.out.println(Part2.solve(table, currx, curry));
    }
}