package aoc_24.day7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day7 {
    class Part1 {
        static long helper(ArrayList<Integer> nums, long sum, int i, String goal) {
            if (i == nums.size()) return sum;
            long h1 = helper(nums, sum + nums.get(i), i + 1, goal);
            long h2 = helper(nums, sum * nums.get(i), i + 1, goal);
            String s1 = "" + h1;
            String s2 = "" + h2;
            return (s1.equals(goal) || s2.equals(goal)) ? Long.parseLong(goal) : -1;
        }

        static boolean isValid(String goal, String line) {
            line = line.substring(line.indexOf(':') + 2);
            String[] tmp = line.split(" ");
            ArrayList<Integer> numbers = new ArrayList<>();
            for (String t : tmp) {
                numbers.add(Integer.parseInt(t));
            }
            return helper(numbers, numbers.getFirst(), 1, goal) == Long.parseLong(goal);
        }
    }

    class Part2{
        static long helper(ArrayList<Integer> nums, long sum, int i ,long goal){
            if (i == nums.size()) return sum;
            if (sum > goal) return -1;
            long h1 = helper(nums, sum + nums.get(i), i + 1, goal);
            long h2 = helper(nums, sum * nums.get(i), i + 1, goal);
            long h3 = helper(nums, Long.parseLong("" + sum + nums.get(i)), i + 1, goal);
            return (h1 == goal || h2 == goal || h3 == goal) ? goal : -1;
        }

        static boolean isValid(long goal, String line){
            line = line.substring(line.indexOf(':') + 2);
            String []tmp = line.split(" ");
            ArrayList<Integer> numbers = new ArrayList<>();
            for (String t : tmp){
                numbers.add(Integer.parseInt(t));
            }
            return helper(numbers, numbers.getFirst(), 1, goal) == goal;
        }
    }
    public static void main(String[] args) {
        File file = new File("aoc_24/day7/input.txt");
        long sum = 0;
        long sum2 = 0;
        try{
            Scanner in = new Scanner(file);
            String line;
            while (in.hasNextLine()){
                line = in.nextLine();
                String goal = (line.split(":")[0]);
                if (Part1.isValid(goal, line)) sum+=Long.parseLong(goal);
                if (Part2.isValid(Long.parseLong(goal), line)) sum2 += Long.parseLong(goal);
            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        }
        System.out.println(sum);
        System.out.println(sum2);
    }
}