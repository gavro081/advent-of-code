package aoc_25.day12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class Helper{}

class Part1{
    public static long solve(ArrayList<String> inputs){
        long ans = 0;
        return ans;
    }
}

class Part2{
    public static long solve(ArrayList<String> inputs){
        long ans = 0;
        return ans;
    }
}

public class Day12 {
    public static void main(String[] args) throws FileNotFoundException {
//        File file = new File("aoc_25/day12/input.txt");
        File file = new File("aoc_25/day12/test-input.txt");

        Scanner sc = new Scanner(file);

        ArrayList<String> inputs = new ArrayList<>();
        while (sc.hasNextLine()){
            inputs.add(sc.nextLine());
        }

        System.out.println("part1: " + Part1.solve(inputs));
        System.out.println("part2: " + Part2.solve(inputs));
    }
}

