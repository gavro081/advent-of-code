package aoc_25.day6;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.*;
import java.util.function.LongBinaryOperator;

class Helper{}

class Part1{
    public static long solve(ArrayList<String> inputs){
        long ans = 0;
        ArrayList<List<String>> nums = new ArrayList<>();
        for (int i = 0; i < inputs.size(); i++){
            nums.add(i, Arrays.stream(inputs
                    .get(i)
                    .split("\\s++"))
                    .filter(s -> !s.isEmpty() && !s.isBlank())
                    .toList());
        }


        for (int i = 0; i < nums.getFirst().size(); i++) {
            char sign = nums.getLast().get(i).charAt(0);
            long tmp = sign == '+' ? 0 : 1;
            for (int j = 0; j < nums.size() - 1; j++) {
                if (sign == '+') tmp += Long.parseLong(nums.get(j).get(i));
                else if (sign == '*') tmp *= Long.parseLong(nums.get(j).get(i));
            }
            ans += tmp;
        }

        return ans;
    }
}

class Part2{
    public static long solve(ArrayList<String> inputs){
        long ans = 0;

        List<Character> signs = Arrays.stream(inputs.getLast()
                                        .split("\\s++"))
                                .map(String::toCharArray)
                                .map(c -> c[0])
                                .toList();

        int index = 0;
        ArrayList<StringBuilder> col = new ArrayList<>();

        for (int j = 0; j < inputs.getFirst().length() + 1; j++) {
            char sign = signs.get(index);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < inputs.size() - 1; i++) {
                if (inputs.get(i).length() <= j) continue;
                sb.append(inputs.get(i).charAt(j));
            }

            if (sb.toString().trim().isEmpty()){
                ans += col.stream()
                        .map(StringBuilder::toString)
                        .mapToLong(s -> Long.parseLong(s.trim()))
                        .reduce(sign == '+' ? 0 : 1,
                                (a,b) -> sign == '+' ?
                                        a + b :
                                        a * b
                        );
                col = new ArrayList<>();
                index++;
            } else col.add(sb);
        }

        return ans;
    }
}

public class Day6 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("aoc_25/day6/input.txt");
//        File file = new File("aoc_25/day6/test-input.txt");

        Scanner sc = new Scanner(file);
        // 1
//        String input = sc.nextLine();

        // 2
        ArrayList<String> inputs = new ArrayList<>();
        while (sc.hasNextLine()){
            inputs.add(sc.nextLine());
        }

        System.out.println("part1: " + Part1.solve(inputs));
        System.out.println("part2: " + Part2.solve(inputs));
    }
}

