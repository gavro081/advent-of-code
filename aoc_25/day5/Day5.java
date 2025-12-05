package aoc_25.day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class Helper{}

class Range{
    long min;
    long max;

    public Range(long min, long max) {
        this.min = min;
        this.max = max;
    }
}

class Part1{
    public static long solve(ArrayList<String> inputs){
        ArrayList<Range> ranges = new ArrayList<>();
        long ans = 0;
        for (String line : inputs){
            if (line.contains("-")){
                String []nums = line.split("-");
                ranges.add(new Range(Long.parseLong(nums[0]), Long.parseLong(nums[1])));
            }
            else if (!line.trim().isEmpty()){
                for (Range r : ranges){
                    long num = Long.parseLong(line);
                    if (num >= r.min && num <= r.max) {
                        ans++;
                        break;
                    }
                }
            }
        }

        return ans;
    }
}

class Part2{
    public static long solve(ArrayList<String> inputs){
        long ans = 0;
        List<Range> rangesInit = new ArrayList<>();
        for (String line : inputs){
            if (!line.contains("-")) break;
            String []nums = line.split("-");
            rangesInit.add(new Range(Long.parseLong(nums[0]), Long.parseLong(nums[1])));
        }

        rangesInit.sort(Comparator.comparing(r -> r.min));

        List<Range> merged = new ArrayList<>();
        Range curr = rangesInit.getFirst();
        for (int i = 1; i < rangesInit.size(); i++) {
            Range next = rangesInit.get(i);
            if (next.min <= curr.max + 1) {
                curr.max = Math.max(next.max, curr.max);
            }
            else {
                merged.add(curr);
                curr = next;
            }
        }
        merged.add(curr);
        for (Range r : merged){
            ans+= r.max - r.min + 1;
        }
        return ans;
    }
}

public class Day5 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("aoc_25/day5/input.txt");
//        File file = new File("aoc_25/day5/test-input.txt");

        Scanner sc = new Scanner(file);
        ArrayList<String> inputs = new ArrayList<>();
        while (sc.hasNextLine()){
            inputs.add(sc.nextLine());
        }

        System.out.println("part1: " + Part1.solve(inputs));
        System.out.println("part1: " + Part2.solve(inputs));
    }
}

