package aoc_25.day7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class Helper{}

class Part1{
    public static long solve(ArrayList<char[]> inputs){
        long ans = 0;
        Set<Integer> occupiedCols = new TreeSet<>(List.of(inputs.size() / 2 - 1));
        for (int i = 1; i < inputs.size(); i++) {
            ArrayList<Integer> toAdd = new ArrayList<>();
            ArrayList<Integer> toRemove = new ArrayList<>();
            for (int col : occupiedCols) {
                if (inputs.get(i)[col] == '^'){
                    if (col - 1 >= 0){
                        toAdd.add(col - 1);
                    }
                    if (col + 1 < inputs.size()){
                        toAdd.add(col + 1);
                    }
                    toRemove.add(col);
                    ans++;
                }
            }

            toRemove.forEach(occupiedCols::remove);
            occupiedCols.addAll(toAdd);
        }
        return ans;
    }
}

class Part2{
    public static long[][]mem;
    private static long recursiveHelper(int row, int col, ArrayList<char[]> grid){
        if (col < 0 || col >= grid.size()) return 0;
        if (row >= grid.size() - 1) return 1;
        if (mem[row][col] != -1) return mem[row][col];
        mem[row][col] = grid.get(row)[col] != '^' ?
                recursiveHelper(row+1,col,grid) :
                recursiveHelper(row+1,col-1,grid) + recursiveHelper(row+1,col+1,grid);
        return mem[row][col];
    }

    public static long solve(ArrayList<char[]> inputs){
        mem = new long[inputs.size()][inputs.getFirst().length];
        for (int i = 0; i < inputs.size(); i++) {
            Arrays.fill(mem[i], -1);
        }
        return recursiveHelper(0,inputs.size() / 2 - 1, inputs);
    }
}

public class Day7 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("aoc_25/day7/input.txt");
//        File file = new File("aoc_25/day7/test-input.txt");

        Scanner sc = new Scanner(file);
        // 1
//        String input = sc.nextLine();

        // 2
        ArrayList<char[]> inputs = new ArrayList<>();
        while (sc.hasNextLine()){
            inputs.add(sc.nextLine().toCharArray());
        }

        System.out.println("part1: " + Part1.solve(inputs));
        System.out.println("part2: " + Part2.solve(inputs));
    }
}

