package aoc_25.day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class Helper{}

record Pair(int i, int j) {
    static boolean checkSurrounding(ArrayList<char[]> inputs, int i, int j, boolean mark){
        ArrayList<Pair> pairs = new ArrayList<>();
        for (int k = -1; k <= 1; k++) {
            for (int l = -1; l <= 1; l++) {
                if (k == 0 && l == 0) continue;
                if (inputs.get(i + k)[j + l] == '@') pairs.add(new Pair(i + k, j + l));
            }
        }
        if (pairs.size() < 4){
            if (mark) inputs.get(i)[j] = 'x';
            return true;
        }
        return false;
    }
}

class Part1{
    public static long solve(ArrayList<char[]> inputs){
        long ans = 0;
        for (int i = 0; i < inputs.size(); i++) {
            for (int j = 0; j < inputs.get(i).length; j++) {
                if (inputs.get(i)[j] != '@') continue;
                if (Pair.checkSurrounding(inputs, i, j, false)) ans++;
            }
        }
        return ans;
    }
}

class Part2{
    public static long solve(ArrayList<char[]> inputs){
        long ans = 0;
        while (true) {
            boolean changed = false;
            for (int i = 1; i < inputs.size() - 1; i++) {
                for (int j = 1; j < inputs.get(i).length - 1; j++) {
                    if (inputs.get(i)[j] != '@') continue;
                    if (Pair.checkSurrounding(inputs, i, j, true)) {
                        changed = true;
                        ans++;
                    }
                }
            }
            if (!changed) break;
        }
        return ans;
    }
}

public class Day4 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("aoc_25/day4/input.txt");
//        File file = new File("aoc/d4/test-input.txt");
        Scanner sc = new Scanner(file);
        ArrayList<char[]> inputs = new ArrayList<>();
        while (sc.hasNextLine()){
            inputs.add(("0" + sc.nextLine() + "0").toCharArray());
        }
        inputs.addFirst("0".repeat(inputs.getFirst().length).toCharArray());
        inputs.addLast("0".repeat(inputs.getFirst().length).toCharArray());
        System.out.println("part 1: " + Part1.solve(inputs));
        System.out.println("part 2: " + Part2.solve(inputs));
    }
}
