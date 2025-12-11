package aoc_25.day11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class Helper{}

class Part1{
    static Map<String, ArrayList<String>> neighbors = new HashMap<>();
    public static long solve(ArrayList<String> inputs){
        for (String line : inputs){
            String start = line.split(":")[0];
            String []parts = line.split(": ")[1].split("\\s+");
            neighbors.put(start, new ArrayList<>(List.of(parts)));
        }

        return dfs("you", new HashSet<>());
    }

    static long dfs(String node, Set<String> seen){
        long ans = 0;
        for (String other : neighbors.get(node)){
            if (other.equals("out")) ans++;
            else if (!seen.contains(other)){
                seen.add(other);
                ans += dfs(other, seen);
                seen.remove(other);
            }
        }

        return ans;
    }
}



class Part2{
    static Map<String, ArrayList<String>> neighbors = new HashMap<>();
    static Map<String, Long> mem = new HashMap<>();

    public static long solve(ArrayList<String> inputs){
        for (String line : inputs){
            String start = line.split(":")[0];
            String []parts = line.split(": ")[1].split("\\s+");
            neighbors.put(start, new ArrayList<>(List.of(parts)));
        }

        return dfs("svr", false, false);
    }

    static long dfs(String node, boolean dac, boolean fft){
        if ("dac".equals(node)) dac = true;
        if ("fft".equals(node)) fft = true;

        if ("out".equals(node))
            return (dac && fft) ? 1 : 0;
        String key = String.format("%s%s%s", node, dac, fft);
        if (mem.containsKey(key)) return mem.get(key);
        long ans = 0;
        if (neighbors.containsKey(node)) {
            for (String other : neighbors.get(node)) {
                ans += dfs(other, dac, fft);
            }
        }
        mem.put(key, ans);
        return ans;
    }
}

public class Day11 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("aoc_25/day11/input.txt");
//        File file = new File("aoc_25/day11/test-input.txt");

        Scanner sc = new Scanner(file);

        ArrayList<String> inputs = new ArrayList<>();
        while (sc.hasNextLine()){
            inputs.add(sc.nextLine());
        }

        System.out.println("part1: " + Part1.solve(inputs));
        System.out.println("part2: " + Part2.solve(inputs));
    }
}

