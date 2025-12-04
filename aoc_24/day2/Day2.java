package aoc_24.day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Day2 {
//    The levels are either all increasing or all decreasing.
//    Any two adjacent levels differ by at least one and at most three.
    class Part1 {
        static boolean checkLine(String s) {
            String[] nums = s.split(" ");
            if (nums[0].equals(nums[1])) return false;
            boolean is_ascending = (Integer.parseInt(nums[1]) > Integer.parseInt(nums[0]));
            for (int i = 0; i < nums.length - 1; i++) {
                if (is_ascending && (Integer.parseInt(nums[i + 1]) - Integer.parseInt(nums[i]) > 3 ||
                        Integer.parseInt(nums[i + 1]) <= Integer.parseInt(nums[i]))) return false;
                else if (!is_ascending && (Integer.parseInt(nums[i]) - Integer.parseInt(nums[i + 1]) > 3 ||
                        Integer.parseInt(nums[i + 1]) >= Integer.parseInt(nums[i]))) return false;

            }
            return true;
        }
    }

    class Part2{
        static boolean checkLine(String s, int index, boolean p) {
            if (!p && checkLine(s, 0, true)) return true;
            String[] nums = s.split(" ");
            ArrayList<Integer> list = new ArrayList<>();
            for (String num : nums) {
                list.add(Integer.parseInt(num));
            }
            if (p) list.remove(index);
            if (list.get(0).equals(list.get(1))) return false;
            boolean is_ascending = (list.get(0) < list.get(1));
            for (int i = 0; i < list.size() - 1; i++) {
                if (is_ascending && ((list.get(i + 1)) - list.get(i) > 3 ||
                        list.get(i + 1) <= list.get(i))) {
                    return !p && (checkLine(s, i, true) || checkLine(s, i + 1, true));
                } else if (!is_ascending && (list.get(i) - list.get(i + 1) > 3 ||
                        list.get(i + 1) >= list.get(i))) {
                    return !p && (checkLine(s, i, true) || checkLine(s, i + 1, true));
                }

            }
            return true;
        }
    }
    public static void main(String[] args) {
        File input = new File("aoc_24/day2/input.txt");
        int ctr1 = 0;
        int ctr2 = 0;
        try {
            Scanner in = new Scanner(input);
            while (in.hasNextLine()) {
                String s = in.nextLine();
                if (Part1.checkLine(s)) ctr1++;
                if (Part2.checkLine(s, -1, false)) ctr2++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found!");
        }
        System.out.println(ctr1);
        System.out.println(ctr2);
    }
}