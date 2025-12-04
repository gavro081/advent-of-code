package aoc_24.day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Day5 {
    class Part1 {
        static boolean check(String line, HashMap<Integer, ArrayList<Integer>> before, HashMap<Integer, ArrayList<Integer>> after) {
            ArrayList<Integer> numbers = new ArrayList<>();

            for (String num : line.split(",")) {
                numbers.add(Integer.parseInt(num));
            }

            for (int i = 0; i < numbers.size(); i++) {
                int curr = numbers.get(i);
                ArrayList<Integer> list = after.get(curr);
                if (list == null) {
                    return i == numbers.size() - 1;
                }
                for (int j = i + 1; j < numbers.size(); j++) {
                    if (!list.contains(numbers.get(j))) return false;
                }

            }
            return true;
        }
    }

        class Part2 {
            static ArrayList<Integer> tolist(String line) {
                ArrayList<Integer> numbers = new ArrayList<>();

                for (String num : line.split(",")) {
                    numbers.add(Integer.parseInt(num));
                }
                return numbers;
            }

            static boolean check(String line, HashMap<Integer, ArrayList<Integer>> after) {
                ArrayList<Integer> numbers = tolist(line);

                for (int i = 0; i < numbers.size(); i++) {
                    int curr = numbers.get(i);
                    ArrayList<Integer> list = after.get(curr);
                    if (list == null) {
                        return i == numbers.size() - 1;
                    }
                    for (int j = i + 1; j < numbers.size(); j++) {
                        if (!list.contains(numbers.get(j))) return false;
                    }

                }
                return true;
            }

            static ArrayList<Integer> reorder(String line, HashMap<Integer, ArrayList<Integer>> after) {
                ArrayList<Integer> numbers = tolist(line);
                for (int i = 0; i < numbers.size(); i++) {
                    int curr = numbers.get(i);
                    ArrayList<Integer> list = after.get(curr);
                    if (list == null) {
                        if (i == numbers.size() - 1) return numbers;
                        else {
                            int tmp = numbers.get(i);
                            numbers.set(i, numbers.getLast());
                            numbers.set(numbers.size() - 1, tmp);
                            i--;
                        }
                    } else for (int j = i + 1; j < numbers.size(); j++) {
                        if (!list.contains(numbers.get(j))) {
                            int tmp = numbers.get(j);
                            numbers.set(j, numbers.get(i));
                            numbers.set(i, tmp);
                            i--;
                            break;
                        }
                    }
                }
                return numbers;
            }
        }

        public static void main(String[] args) {
            File file = new File("aoc_24/day5/input.txt");
            HashMap<Integer, ArrayList<Integer>> before = new HashMap<>();
            HashMap<Integer, ArrayList<Integer>> after = new HashMap<>();
            int ctr = 0;
            try {
                Scanner in = new Scanner(file);
                while (in.hasNextLine()) {
                    String line = in.nextLine();
                    if (line.isEmpty()) break;
                    ;
                    int n1 = Integer.parseInt(line.split("\\|")[0]);
                    int n2 = Integer.parseInt(line.split("\\|")[1]);
                    // 12 | 35 -> 12 doaga pred 35, 35 doaga posle 12

//                 part 1
//                if (before.get(n2) == null){
//                    ArrayList<Integer> al = new ArrayList<>();
//                    al.add(n1);
//                    before.put(n2, al);
//                }
//                else {
//                    before.get(n2).add(n1);
//                }
//                if (after.get(n1) == null){
//                    ArrayList<Integer> al = new ArrayList<>();
//                    al.add(n2);
//                    after.put(n1, al);
//                }
//                else {
//                    after.get(n1).add(n2);
//                }
//            }
//            while (in.hasNextLine()){
//                String line = in.nextLine();
//                if (check(line, before, after)) {
//                    ArrayList<Integer> numbers = new ArrayList<>();
//
//                    for (String num : line.split(",")){
//                        numbers.add(Integer.parseInt(num));
//                    }
//
//                    ctr += numbers.get(numbers.size() / 2);
//                }
//            }

//                part 2
                    if (after.get(n1) == null) {
                        ArrayList<Integer> al = new ArrayList<>();
                        al.add(n2);
                        after.put(n1, al);
                    } else {
                        after.get(n1).add(n2);
                    }
                }
                while (in.hasNextLine()) {
                    String line = in.nextLine();
                    if (!Part2.check(line, after)) {

                        ArrayList<Integer> numbers = Part2.reorder(line, after);

                        ctr += numbers.get(numbers.size() / 2);
                    }
                }

            } catch (FileNotFoundException e) {
                System.out.println("file doesnt exist");
            }
            System.out.println(ctr);
        }
}