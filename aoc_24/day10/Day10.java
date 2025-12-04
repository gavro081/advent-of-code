package aoc_24.day10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day10 {
    public static class Point{
        int x;
        int y;
        Point(){}
        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    class Part1{
        public static int solve(ArrayList<String> ls){
            ArrayList<Point> start = new ArrayList<>();
            int rows = ls.size();
            int cols = ls.getFirst().length();
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (ls.get(i).charAt(j) == '0') start.add(new Point(i,j));
                }
            }
            int ans = 0;
            for (int i = 0; i < start.size(); i++) {
                Point curr = start.get(i);
                boolean [][]dp = new boolean[rows][cols];
                ans += solve_helper(curr, ls, dp, 0);
            }

            return ans;
        }

        public static int solve_helper(Point a, ArrayList<String> ls,boolean[][]dp, int ctr){
            // out of bounds handle
            if (a.x >= ls.size() || a.x < 0 || a.y >= ls.getFirst().length() || a.y < 0) return 0;
            // not the right number
            if (Character.getNumericValue(ls.get(a.x).charAt(a.y)) != ctr) return 0;
            if (ctr == 9){
                if (dp[a.x][a.y]) return 0;
                else {
                    dp[a.x][a.y] = true;
                    return 1;
                }
            }
            return solve_helper(new Point(a.x+1, a.y), ls, dp, ctr+1) +
                    solve_helper(new Point(a.x-1, a.y), ls, dp, ctr+1) +
                    solve_helper(new Point(a.x, a.y+1), ls, dp, ctr+1) +
                    solve_helper(new Point(a.x, a.y-1), ls, dp, ctr+1);
        }
    }

    class Part2{
        public static int solve(ArrayList<String> ls){
            ArrayList<Point> start = new ArrayList<>();
            int rows = ls.size();
            int cols = ls.getFirst().length();
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (ls.get(i).charAt(j) == '0') start.add(new Point(i,j));
                }
            }
            int ans = 0;
            for (int i = 0; i < start.size(); i++) {
                Point curr = start.get(i);
                boolean [][]dp = new boolean[rows][cols];
                ans += solve_helper(curr, ls, dp, 0);
            }

            return ans;
        }

        public static int solve_helper(Point a, ArrayList<String> ls,boolean[][]dp, int ctr){
            // out of bounds handle
            if (a.x >= ls.size() || a.x < 0 || a.y >= ls.getFirst().length() || a.y < 0) return 0;
            // not the right number
            if (Character.getNumericValue(ls.get(a.x).charAt(a.y)) != ctr) return 0;
            if (ctr == 9){
                return 1;
            }
            return solve_helper(new Point(a.x+1, a.y), ls, dp, ctr+1) +
                    solve_helper(new Point(a.x-1, a.y), ls, dp, ctr+1) +
                    solve_helper(new Point(a.x, a.y+1), ls, dp, ctr+1) +
                    solve_helper(new Point(a.x, a.y-1), ls, dp, ctr+1);
        }
    }

    public static void main(String[] args) {
        File file = new File("aoc_24/day10/input.txt");
        ArrayList<String> ls = new ArrayList<>();
        try{
            Scanner in = new Scanner(file);
            String line;
            while (in.hasNextLine()){
                line = in.nextLine();
                ls.add(line);
            }
            System.out.println(Part1.solve(ls));
            System.out.println(Part2.solve(ls));
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        }
    }
}