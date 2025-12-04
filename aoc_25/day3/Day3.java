package aoc_25.day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class Helper{}

class Part1{
    public static long solve(ArrayList<String> inputs){
        long ans = 0;
        for (String line : inputs){
            int max = 0;
            int max_idx = -1;
            for (int i = 0; i < line.length() - 1; i++){
                char c = line.charAt(i);
                if ((c - '0') > max){
                    max = c - '0';
                    max_idx = i;
                    if (max == 9) break;
                }
            }
            int sec_max = 0;
            for (int i = max_idx + 1; i < line.length(); i++) {
                char c = line.charAt(i);
                if ((c - '0') > sec_max){
                    sec_max = c - '0';
                    if (sec_max == 9) break;
                }
            }
            ans += Integer.parseInt("" + max + sec_max);
        }
        return ans;
    }
}

class Part2{
    public static long solve(ArrayList<String> inputs){
        long ans = 0;
        for (String line : inputs){
            int left = 12;
            StringBuilder curr = new StringBuilder();
            int max_idx = -1;
            for (int i = 0; i < 12; i++) {
                long max = 0;
                for (int j = max_idx + 1; j < line.length() - left + 1; j++){
                    char c = line.charAt(j);
                    if ((c - '0') > max){
                        max = c - '0';
                        max_idx = j;
                        if (max == '9') break;
                    }
                }
                left--;
                curr.append(max);
            }
            ans += Long.parseLong(curr.toString());
        }
        return ans;
    }
}

public class Day3 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("aoc_25/day3/input.txt");
//        File file = new File("aoc/d3/test-input.txt");
        Scanner sc = new Scanner(file);
        ArrayList<String> inputs = new ArrayList<>();
        while (sc.hasNextLine()){
            inputs.add(sc.nextLine());
        }
        System.out.println("part 1: " + Part1.solve(inputs));
        System.out.println("part 2: " + Part2.solve(inputs));
    }
}
