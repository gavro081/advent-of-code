package aoc_24.day12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class Day12 {
    static class Part1 {
        boolean[][] checked;
        int temp_ctr = 0;

        long calc_ctr(ArrayList<String> list, int i, int j, char ch) {
            int ctr = 4;
            if (list.get(i).charAt(j - 1) == ch) ctr--;
            if (list.get(i - 1).charAt(j) == ch) ctr--;
            if (list.get(i + 1).charAt(j) == ch) ctr--;
            if (list.get(i).charAt(j + 1) == ch) ctr--;
            return ctr;
        }

        long calc_area(ArrayList<String> list, int i, int j, char ch) {
            if (list.get(i).charAt(j) != ch) return 0;
            if (checked[i][j]) return 0;
            checked[i][j] = true;
            temp_ctr++;
            return calc_ctr(list, i, j, ch) + calc_area(list, i - 1, j, ch) + calc_area(list, i, j + 1, ch)
                    + calc_area(list, i + 1, j, ch) + calc_area(list, i, j - 1, ch);
        }

        long solve(ArrayList<String> list) {
            HashMap<Character, Integer> p = new HashMap<>(); // plostina
            HashMap<Character, Integer> l = new HashMap<>(); // perimetar
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < list.getFirst().length(); i++) {
                sb.append("1");
            }
            list.addFirst(sb.toString());
            list.add(sb.toString());
            // nejkam da pravam out of bounds handling
            long ans = 0;
            this.checked = new boolean[list.size()][list.getFirst().length()];
            for (int i = 1; i < list.size() - 1; i++) {
                for (int j = 1; j < list.getFirst().length() - 1; j++) {
                    if (checked[i][j]) continue;
                    char ch = list.get(i).charAt(j);
                    long area = calc_area(list, i, j, ch);
                    ans += temp_ctr * area;
                    temp_ctr = 0;
                }
            }
            return ans;
        }
    }
    public static void main(String[] args) {
        File file = new File("aoc_24/day12/input.txt");
        try{
            Scanner in = new Scanner(file);
            ArrayList<String> list = new ArrayList<>();
            String line;
            while (in.hasNextLine()){
                line = "1" + in.nextLine() + "1";
                list.add(line);
            }
            // part 1
            Part1 p1 = new Part1();
            System.out.println(p1.solve(list));
            // part 2 nemam :(
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        }
    }
}