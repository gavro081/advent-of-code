package aoc_25.day8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

class Helper{
    public static ArrayList<Pair> getAllPairs(ArrayList<Point> points) {
        ArrayList<Pair> allPairs = new ArrayList<>();
        for (int i = 0; i < points.size(); i++) {
            Point a = points.get(i);
            for (int j = i+1; j < points.size(); j++) {
                Point b = points.get(j);
                allPairs.add(new Pair(a, b));
            }
        }
        allPairs.sort(Comparator.comparingDouble(p -> p.dist));

        return allPairs;
    }

    public static ArrayList<Point> getAllPoints(ArrayList<String> inputs) {
        ArrayList<Point> points = new ArrayList<>();
        for (String line : inputs){
            List<Integer> list = Arrays.stream(line.split(",")).map(Integer::parseInt).toList();
            points.add(new Point(list));
        }
        return points;
    }
}

class Point{
    int x;
    int y;
    int z;

    public Point(List<Integer> list) {
        this.x = list.get(0);
        this.y = list.get(1);
        this.z = list.get(2);
    }

    public String toString(){
        return String.format("(%s, %s, %s)", x,y,z);
    }
}

class Pair{
    Point a;
    Point b;
    double dist;

    public Pair(Point a, Point b) {
        this.a = a;
        this.b = b;
        double term1 = Math.pow(a.x - b.x, 2);
        double term2 = Math.pow(a.y - b.y, 2);
        double term3 = Math.pow(a.z - b.z, 2);
        dist = term1 + term2 + term3;
    }
}

class Part1{
    public static long solve(ArrayList<String> inputs){
        ArrayList<Point> points = Helper.getAllPoints(inputs);
        List<Pair> allPairs = Helper.getAllPairs(points);

        Map<Point, Integer> parentColor = new HashMap<>();
        for (int i = 0; i < points.size(); i++) {
            parentColor.put(points.get(i), i);
        }
        int N = 1000;

        for (int i = 0; i < N; i++){
            Pair p = allPairs.get(i);
            int colorA = parentColor.get(p.a);
            int colorB = parentColor.get(p.b);

            if (colorA == colorB) continue;

            // not optimized :)
            for (Map.Entry<Point, Integer> entry : parentColor.entrySet()) {
                if (entry.getValue() == colorB) {
                    entry.setValue(colorA);
                }
            }
        }

        return parentColor.values().stream()
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()))
                .values().stream()
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .reduce(1L, (a, b) -> a * b);
    }
}

class Part2{
    public static long solve(ArrayList<String> inputs){
        ArrayList<Point> points = Helper.getAllPoints(inputs);
        List<Pair> allPairs = Helper.getAllPairs(points);

        Map<Point, Integer> parentColor = new HashMap<>();
        for (int i = 0; i < points.size(); i++) {
            parentColor.put(points.get(i), i);
        }

        int clustersRemaining = points.size();

        for (Pair p : allPairs){
            int colorA = parentColor.get(p.a);
            int colorB = parentColor.get(p.b);

            if (colorA == colorB) continue;

            // not optimized :)
            for (Map.Entry<Point, Integer> entry : parentColor.entrySet()) {
                if (entry.getValue() == colorB) {
                    entry.setValue(colorA);
                }
            }

            clustersRemaining--;

            if (clustersRemaining == 1){
                return (long) p.a.x * p.b.x;
            }
        }
        return -1;
    }
}

public class Day8 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("aoc_25/day8/input.txt");
//        File file = new File("aoc_25/day8/test-input.txt");

        Scanner sc = new Scanner(file);
        ArrayList<String> inputs = new ArrayList<>();
        while (sc.hasNextLine()){
            inputs.add(sc.nextLine());
        }

        System.out.println("part1: " + Part1.solve(inputs));
        System.out.println("part2: " + Part2.solve(inputs));
    }
}

