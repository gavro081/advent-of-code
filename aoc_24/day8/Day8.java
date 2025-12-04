package aoc_24.day8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day8 {
    public static class Dot{
        int x;
        int y;
        Dot(){}
        Dot(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    class Part1 {
        static boolean checkRange(int x, int y, ArrayList<ArrayList<Character>> grid) {
            int rows = grid.size();
            int cols = grid.getFirst().size();
            if (x < 0 || x >= rows) return false;
            return (y >= 0 && y < cols);
        }

        static int solve(ArrayList<ArrayList<Character>> grid) {
            HashMap<Character, ArrayList<Dot>> table = new HashMap<>();
            for (int i = 0; i < grid.size(); i++) {
                for (int j = 0; j < grid.getFirst().size(); j++) {
                    if (grid.get(i).get(j) != '.') {
                        ArrayList<Dot> ls = (table.containsKey(grid.get(i).get(j))) ?
                                table.get(grid.get(i).get(j)) :
                                new ArrayList<>();
                        ls.add(new Dot(i, j));
                        table.put(grid.get(i).get(j), ls);
                    }
                }
            }
            int ctr = 0;
            for (Character ch : table.keySet()) {
                ArrayList<Dot> ls = table.get(ch);
                for (int i = 0; i < ls.size(); i++) {
                    int x1 = ls.get(i).x;
                    int y1 = ls.get(i).y;
                    for (int j = i + 1; j < ls.size(); j++) {
                        int x2 = ls.get(j).x;
                        int y2 = ls.get(j).y;
                        int xoffset = x2 - x1;
                        int yoffset = y2 - y1;
                        if (checkRange(x1 - xoffset, y1 - yoffset, grid) &&
                                grid.get(x1 - xoffset).get(y1 - yoffset) != '#') {
                            grid.get(x1 - xoffset).set(y1 - yoffset, '#');
                            ctr++;
                        }
                        if (checkRange(x2 + xoffset, y2 + yoffset, grid) &&
                                grid.get(x2 + xoffset).get(y2 + yoffset) != '#') {
                            grid.get(x2 + xoffset).set(y2 + yoffset, '#');
                            ctr++;
                        }
                    }
                }
            }
            return ctr;
        }
    }

    class Part2{
        static boolean checkRange(int x, int y, ArrayList<ArrayList<Character>> grid){
            int rows = grid.size();
            int cols = grid.getFirst().size();
            if (x < 0 || x >= rows) return false;
            return (y >= 0 && y < cols);
        }
        static int solve(ArrayList<ArrayList<Character>> grid){
            HashMap<Character, ArrayList<Dot>> table = new HashMap<>();
            for (int i = 0; i < grid.size(); i++) {
                for (int j = 0; j < grid.getFirst().size(); j++) {
                    if (grid.get(i).get(j) != '.') {
                        ArrayList<Dot> ls = (table.containsKey(grid.get(i).get(j))) ?
                                table.get(grid.get(i).get(j)) :
                                new ArrayList<>();
                        ls.add(new Dot(i,j));
                        table.put(grid.get(i).get(j), ls);
                    }
                }
            }
            int ctr = 0;
            for (Character ch : table.keySet()){
                ArrayList<Dot> ls = table.get(ch);
                for (int i = 0; i < ls.size(); i++) {
                    if (grid.get(ls.get(i).x).get(ls.get(i).y) != '#') {
                        grid.get(ls.get(i).x).set(ls.get(i).y, '#');
                        ctr++;
                    }
                    for (int j = i+1; j < ls.size(); j++) {
                        int x1 = ls.get(i).x;
                        int y1 = ls.get(i).y;
                        int x2 = ls.get(j).x;
                        int y2 = ls.get(j).y;
                        if (grid.get(x2).get(y2) != '#'){
                            grid.get(x2).set(y2, '#');ctr++;
                        }
                        int xoffset = x2 - x1;
                        int yoffset = y2 - y1;
                        while (checkRange(x1 - xoffset,y1 - yoffset,grid)){
                            if (grid.get(x1 - xoffset).get(y1 - yoffset) != '#'){
                                grid.get(x1 - xoffset).set(y1 - yoffset, '#');
                                ctr++;
                            }
                            x1 -= xoffset; y1 -= yoffset;
                        }
                        while (checkRange(x2 + xoffset, y2 + yoffset, grid)){
                            if (grid.get(x2 + xoffset).get(y2 + yoffset) != '#'){
                                grid.get(x2 + xoffset).set(y2 + yoffset, '#');
                                ctr++;
                            }
                            x2 += xoffset; y2 += yoffset;
                        }
                    }
                }
            }
            return ctr;
        }
    }

    public static void main(String[] args) {
        File file = new File("aoc_24/day8/input.txt");
        ArrayList<ArrayList<Character>> grid = new ArrayList<>();
        try{
            Scanner in = new Scanner(file);
            String line;
            while (in.hasNextLine()){
                ArrayList<Character> row = new ArrayList<>();
                line = in.nextLine();
                for (Character ch : line.toCharArray()){
                    row.add(ch);
                }
                grid.add(row);
            }

        } catch (FileNotFoundException e) {
            System.out.println("file not found!");
        }
        // copy poso se menja orginalniot grid u solve funkcijata
        ArrayList<ArrayList<Character>> copy = new ArrayList<>();
        for (ArrayList<Character> row : grid) {
            copy.add(new ArrayList<>(row));
        }
        System.out.println(Part1.solve(grid));
        System.out.println(Part2.solve(copy));
    }
}