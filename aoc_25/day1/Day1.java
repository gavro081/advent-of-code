package aoc_25.day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


class Helper{
    static int[] extracted(ArrayList<String> inputs) {
        return inputs.stream()
                .map(s -> s.contains("L") ? "-" + s.split("L")[1] : s.split("R")[1])
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}

class Part1{
    static long solve(ArrayList<String> inputs){
        int[] array = Helper.extracted(inputs);
        long curr = 50;
        int ans = 0;
        for (int n : array){
            n %= 100;
            curr += n;
            curr = curr < 0 ? 100 + (curr % 100) : curr % 100;
            if (curr == 0) ans++;
        }
        if (curr == 0) ans++;
        return ans;
    }
}

class Part2{
    static long solve(ArrayList<String> inputs){
        int[] array = Helper.extracted(inputs);

        long curr = 50;
        int ans = 0;

        for (int n : array){
            long target = curr + n;
            if (n > 0){
                ans += (Math.floorDiv(target, 100) - Math.floorDiv(curr, 100));
            } else {
                ans += (Math.floorDiv(curr - 1, 100) - Math.floorDiv(target - 1, 100));
            }
            curr = target;
        }
        return ans;
    }


}

public class Day1 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("aoc_25/day1/input.txt");
        Scanner sc = new Scanner(file);

        ArrayList<String> inputs = new ArrayList<>();

        while (sc.hasNextLine()){
            inputs.add(sc.nextLine());
        }

        System.out.println("part 1: " + Part1.solve(inputs));
        System.out.println("part 2: " + Part2.solve(inputs));
    }
}

