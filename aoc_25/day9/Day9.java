package aoc_25.day9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class Helper{}

class Point{
    long x;
    long y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Part1{
    public static long solve(ArrayList<String> inputs){
        long ans = 0;
        ArrayList<Point> points = new ArrayList<>();
        for (String input : inputs) {
            String[] parts = input.split(",");
            points.add(new Point(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
        }

        for (int i = 0; i < points.size() - 1; i++) {
            for (int j = i + 1; j < points.size(); j++) {
                Point p1 = points.get(i);
                Point p2 = points.get(j);
                long area = Math.abs(p1.x - p2.x + 1) * Math.abs(p1.y - p2.y + 1);
                ans = Math.max(ans, area);
            }
        }
        return ans;
    }
}

class Part2{
    public static long solve(ArrayList<String> inputs) {
        long ans = 0;
        List<Point> points = new ArrayList<>();

        for (String s : inputs) {
            String[] parts = s.split(",");
            points.add(new Point(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
        }

        for (int i = 0; i < points.size() - 1; i++) {
            for (int j = i + 1; j < points.size(); j++) {
                Point p1 = points.get(i);
                Point p2 = points.get(j);
                long area = (Math.abs(p1.x - p2.x) + 1) * (Math.abs(p1.y - p2.y) + 1);

                if (ans > area) continue;

                long minX = Math.min(p1.x, p2.x);
                long maxX = Math.max(p1.x, p2.x);
                long minY = Math.min(p1.y, p2.y);
                long maxY = Math.max(p1.y, p2.y);

                boolean ok = true;

                for (int k = 0; k < points.size(); k++) {
                    int l = (k + 1) % points.size();
                    Point e1 = points.get(k);
                    Point e2 = points.get(l);

                    if (!(
                            (e1.x <= minX && e2.x <= minX) ||
                            (e1.x >= maxX && e2.x >= maxX) ||
                            (e1.y <= minY && e2.y <= minY) ||
                            (e1.y >= maxY && e2.y >= maxY)
                    )) {
                        ok = false;
                        break;
                    }
                }
                if (ok) ans = area;
            }

        }
        return ans;
    }
}

public class Day9 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("aoc_25/day9/input.txt");
//        File file = new File("aoc_25/day9/test-input.txt");

        Scanner sc = new Scanner(file);

        ArrayList<String> inputs = new ArrayList<>();
        while (sc.hasNextLine()){
            inputs.add(sc.nextLine());
        }

        System.out.println("part1: " + Part1.solve(inputs));
        System.out.println("part2: " + Part2.solve(inputs));
    }
}

