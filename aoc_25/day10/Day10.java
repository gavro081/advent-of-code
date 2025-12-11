package aoc_25.day10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class Helper{}

record State(
   String state,
   int count
) {}

class Part1{
    public static long solve(ArrayList<String> inputs){
        long ans = 0;
        ArrayList<String> targets = new ArrayList<>();
        Map<Integer, ArrayList<int[]>> movesMap = new HashMap<>();
        int count = 0;
        for (String input : inputs){
            String []parts = input.split("\\s+");
            String target = parts[0].substring(1, parts[0].length() - 1);
            targets.add(target);
            ArrayList<int[]> moves_curr = new ArrayList<>();
            for (int i = 1; i < parts.length - 1; i++) {
                int[] array = Arrays.stream(parts[i].substring(1, parts[i].length() - 1).split(","))
                        .mapToInt(Integer::parseInt)
//                        .map(i_ -> i_ - 1)
                        .toArray();
                moves_curr.add(array);
            }
            movesMap.put(count++, moves_curr);
        }

        for (int i = 0; i < targets.size(); i++) {
            ans += bfs(movesMap.get(i), targets.get(i));

        }
        return ans;
    }

    private static int bfs(ArrayList<int[]> moves, String target){
        String startState = new StringBuilder().repeat('.', target.length()).toString();

        Queue<State> queue = new LinkedList<>();
        Set<String> seen = new HashSet<>();

        queue.add(new State(startState, 0));
        seen.add(startState);
        while (!queue.isEmpty()){
            State current = queue.remove();
            for (int[] move : moves) {
                char[] chars = current.state().toCharArray();
                for (int idx : move){
                    chars[idx] = chars[idx] == '#' ? '.' : '#';
                }
                String newStateStr = new String(chars);
                if (newStateStr.equals(target)){
                    return current.count() + 1;
                }
                if (!seen.contains(newStateStr)) {
                    queue.add(new State(newStateStr, current.count() + 1));
                }
            }
        }
        return 0;
    }
}

class Part2{
    public static long solve(ArrayList<String> inputs){
        long ans = 0;
        return ans;
    }
}

public class Day10 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("aoc_25/day10/input.txt");
//        File file = new File("aoc_25/day10/test-input.txt");

        Scanner sc = new Scanner(file);

        ArrayList<String> inputs = new ArrayList<>();
        while (sc.hasNextLine()){
            inputs.add(sc.nextLine());
        }

        System.out.println("part1: " + Part1.solve(inputs));
//        System.out.println("part2: " + Part2.solve(inputs));
    }
}
