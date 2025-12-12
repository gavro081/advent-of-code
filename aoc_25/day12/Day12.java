package aoc_25.day12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class Helper{}

record Region(long area, ArrayList<Long> giftCounts) {}

class Part1{
    public static long solve(ArrayList<String> inputs){
        ArrayList<Long> areas = new ArrayList<>();
        ArrayList<Region> targets = new ArrayList<>();
        long hashtagCount = 0;
        int index = 0;
        for (String line : inputs.stream().skip(1).toList()){
            if (line.equals(String.format("%d:", index + 1))) {
                areas.add(index++, hashtagCount);
                hashtagCount = 0;
            }
            else if (!line.contains(":")) {
                hashtagCount += line.chars().mapToLong(c -> c == '#' ? 1 : 0).sum();
            }
            else {
                if (index != -1) {
                    areas.add(index, hashtagCount);
                    index = -1;
                }
                long height = Long.parseLong(line.split("x")[0]);
                long width = Long.parseLong(line.split("x")[1].split(":")[0]);
                long[] array = Arrays.stream(line.split(": ")[1].split("\\s+"))
                        .mapToLong(Long::parseLong)
                        .toArray();
                ArrayList<Long> al = new ArrayList<>();
                for (long n : array) al.add(n);
                targets.add(new Region(height * width, al));
            }
        }
        long ans = 0;
        for (Region ad: targets){
            ArrayList<Long> counts = ad.giftCounts();
            long sum = 0;
            for (int i = 0; i < counts.size(); i++) {
                sum += counts.get(i) * areas.get(i);
            }
            if (ad.area() >= sum) ans++;
        }

        return ans;
    }
}


public class Day12 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("aoc_25/day12/input.txt");
//        File file = new File("aoc_25/day12/test-input.txt");

        Scanner sc = new Scanner(file);

        ArrayList<String> inputs = new ArrayList<>();
        while (sc.hasNextLine()){
            inputs.add(sc.nextLine());
        }

        System.out.println("part1: " + Part1.solve(inputs));
    }
}

