package aoc_24.day4;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Day4 {
    static String reverse(String s){
        String reversed = "";
        for (int i = s.length() - 1; i >= 0 ; i--) {
            reversed += s.charAt(i);
        }
        return reversed;
    }
    class Part1{
        static int countOccurances(List<String> list){
            int ctr = 0;
            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < list.get(i).length(); j++) {
                    if (list.get(i).charAt(j) == 'X'){
                        if (list.get(i).startsWith("XMAS", j)) ctr++;
                        if (j - 3 >= 0 && list.get(i).startsWith("XMAS", j-3)) ctr++;
                    }
                }
            }
            return ctr;
        }
        static int diagonals(List<String> list){
            ArrayList<String>diagonals = new ArrayList<>();
            int tempctr = 0;
            int j, i = 0;
            int k = 3;
            while (i + 3 < list.size()) {
                String tmp = "";
                while (k < list.getFirst().length() && k - 3 >= 0) {
                    i = tempctr;
                    j = k;
                    while (j >= k - 3 && i < list.size()) {
                        tmp += list.get(i).charAt(j);
                        j--;
                        i++;
                    }
                    tmp += ".";
                    k++;
                }
                k = 3;
                tempctr++;
                i = tempctr;
                diagonals.add(tmp);
            }

            return countOccurances(diagonals);
        }


        static int solve(ArrayList<String> list){
            int ctr = 0;
            ArrayList<String> reversed = new ArrayList<>();
            for (String s : list){
                reversed.add(reverse(s));
            }
            ctr += countOccurances(list);
            ctr += countOccurances(reversed);

            ctr += diagonals(list);
            ctr += diagonals(list.reversed()); // tuka
            ctr += diagonals(reversed);
            ctr += diagonals(reversed.reversed());
            ArrayList<String> column = new ArrayList<>();
            ArrayList<String> columnreversed = new ArrayList<>();
            for (int j = 0; j < list.get(0).length(); j++){
                String tmp = "";
                for (int i = 0; i < list.size(); i++) {
                    tmp += list.get(i).charAt(j);
                }
                column.add(tmp);
                columnreversed.add(reverse(tmp));
            }

            ctr += countOccurances(column);
//        ctr += countOccurances(reverse(column.reversed()));
            ctr += countOccurances(columnreversed);
            return ctr;
        }
    }

    class Part2{
        static class Point{
            int x;
            int y;
            Point(){}
            Point(int a, int b){
                x = a;
                y = b;
            }
        }
        static int solve(ArrayList<String> list) {
            int n = list.size();
            int m = list.getFirst().length();
            int ctr = 0;
            char[][] matrix = new char[n][m];
            ArrayList<Point> l = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    matrix[i][j] = list.get(i).charAt(j);
                    if (matrix[i][j] == 'A') l.add(new Point(i, j));
                }
            }

            boolean p;
            for (int i = 0; i < l.size(); i++) {
                Point curr = l.get(i);
                int x = curr.x - 1;
                int y = curr.y - 1;
                if (curr.x == 0 || curr.y == 0 || curr.x == n - 1 || curr.y == m - 1) continue;
                p = false;
                String s = "";

                while (y <= curr.y + 1 && y < m && x < n) {
                    s += matrix[x][y];
                    y++;
                    x++;
                }
                if (s.equals("MAS") || reverse(s).equals("MAS")) p = true;
                s = "";

                x = curr.x + 1;
                y = curr.y - 1;
                while (y <= curr.y + 1 && y < m && x >= 0) {
                    s += matrix[x][y];
                    y++;
                    x--;
                }

                if (p && (s.equals("MAS") || reverse(s).equals("MAS"))) ctr++;
            }
            return ctr;
        }
    }

    public static void main(String[] args) {
        File input = new File("aoc_24/day4/input.txt");
        ArrayList<String> list = new ArrayList<>();

        try {
            Scanner in = new Scanner(input);
            while (in.hasNextLine()){
                String line = in.nextLine();
                list.add(line);
            }

        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        }
        System.out.println(Day4.Part1.solve(list));
        System.out.println(Day4.Part2.solve(list));
    }
}