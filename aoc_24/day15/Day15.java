package aoc_24.day15;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day15 {
    static void print_grid(ArrayList<ArrayList<Character>> grid, int ctr){
        System.out.println();
        System.out.println("move: " + ++ctr);
        for (int k = 0; k < grid.size(); k++) { // tuka
            for (int j = 0; j < grid.getFirst().size(); j++) {
                System.out.print(grid.get(k).get(j));
            }
            System.out.println();
        }
    }
    class Part1{
        static long solve(ArrayList<String> g, ArrayList<Character> moves){
            ArrayList<ArrayList<Character>> grid = new ArrayList<>();
            for (int i = 0; i < g.size(); i++) {
                ArrayList<Character> tmp = new ArrayList<>();
                for (int j = 0; j < g.getFirst().length(); j++) {
                    tmp.add(g.get(i).charAt(j));
                }
                grid.add(new ArrayList<>(tmp));
                tmp.clear();
            }

            int currx = -1, curry = -1;
            for (int i = 0; i < g.size(); i++) {
                for (int j = 0; j < g.getFirst().length(); j++) {
                    if (g.get(i).charAt(j) == '@') {
                        currx = j; curry = i;
                    }
                }
            }

            // simulate all moves
            for (int i = 0; i < moves.size(); i++) {

                if (currx > grid.size() || curry > grid.size() ) {
                    System.out.println(1);
                }
                switch (moves.get(i)){
                    case '<':
                        if (trymove(currx - 1, curry, grid, -1, 0)){
                            grid.get(curry).set(currx, '.');
                            currx--;
                        }
                        break;
                    case '>':
                        if (trymove(currx + 1, curry, grid, 1, 0)){
                            grid.get(curry).set(currx, '.');
                            currx++;
                        }
                        break;
                    case '^':
                        if (trymove(currx, curry - 1, grid, 0, -1)){
                            grid.get(curry).set(currx, '.');
                            curry--;
                        }
                        break;
                    case 'v':
                        if (trymove(currx, curry + 1, grid, 0, 1)){
                            grid.get(curry).set(currx, '.');
                            curry++;
                        }
                        break;
                }
            }
            long ans = 0;
            for (int i = 0; i < grid.size(); i++) {
                for (int j = 0; j < grid.getFirst().size(); j++) {
                    if (grid.get(i).get(j) == 'O') ans += (long) 100 * i + j;
                }
            }

            return ans;
        }

        static boolean trymove(int x, int y, ArrayList<ArrayList<Character>> grid, int movex, int movey){
            char ch = grid.get(y).get(x);
            if (ch == '#') return false; // out of bounds
            if (ch != 'O') {
                grid.get(y).set(x, '@');
                //prefrlen e coekot treba use da se izbrise prosloto mesto
            }
            else {
                int tmpx = x, tmpy = y;

                while (grid.get(tmpy).get(tmpx) == 'O'){
                    tmpx += movex;
                    tmpy += movey;
                    if (grid.get(tmpy).get(tmpx) == '#') return false; // nema mesto kaj da se pomestat
                }
                while (grid.get(tmpy).get(tmpx) != '@'){
                    grid.get(tmpy).set(tmpx, grid.get(tmpy-movey).get(tmpx-movex));
                    tmpx -= movex;
                    tmpy -= movey;
                }
            }
            return true;
        }
    }

    class Part2{
        // NE E DOVRSENO NE RABOTI CELOSNO
        static long solve_p2(ArrayList<String> g, ArrayList<Character> moves) {
            ArrayList<ArrayList<Character>> grid = new ArrayList<>();
            for (int i = 0; i < g.size(); i++) {
                ArrayList<Character> tmp = new ArrayList<>();
                for (int j = 0; j < g.getFirst().length(); j++) {
                    tmp.add(g.get(i).charAt(j));
                }
                grid.add(new ArrayList<>(tmp));
                tmp.clear();
            }
            // make new grid
            for (int i = 0; i < grid.size(); i++) {
                for (int j = 0; j < grid.getFirst().size(); j += 2) {
                    if (grid.get(i).get(j) == '#') grid.get(i).add(j + 1, '#');
                    if (grid.get(i).get(j) == 'O') {
                        grid.get(i).set(j, '[');
                        grid.get(i).add(j + 1, ']');
                    }
                    if (grid.get(i).get(j) == '.') grid.get(i).add(j + 1, '.');
                    if (grid.get(i).get(j) == '@') grid.get(i).add(j + 1, '.');
                }
            }
            int currx = 0, curry = 0;
            for (int i = 0; i < grid.size(); i++) {
                for (int j = 0; j < grid.get(i).size(); j++) {
                    if (grid.get(i).get(j) == '@') {
                        currx = j;
                        curry = i;
                    }
                }
            }

            // simulate all moves
//        print_grid(grid, 0);
            int ctr = 0; // tuka
            for (int i = 0; i < moves.size(); i++) {
                switch (moves.get(i)) {
                    case '<':
                        if (trymove(currx - 1, curry, grid, -1, 0, ctr)) {
                            grid.get(curry).set(currx, '.');
                            currx--;
                        }
                        break;
                    case '>':
                        if (trymove(currx + 1, curry, grid, 1, 0, ctr)) {
                            grid.get(curry).set(currx, '.');
                            currx++;
                        }
                        break;
                    case '^':
                        if (trymove(currx, curry - 1, grid, 0, -1, ctr)) {
                            grid.get(curry).set(currx, '.');
                            curry--;
                        }
                        break;
                    case 'v':
                        if (trymove(currx, curry + 1, grid, 0, 1, ctr)) {
                            grid.get(curry).set(currx, '.');
                            curry++;
                        }
                        break;
                }
                print_grid(grid, ctr);
                ctr++;
            }
            long ans = 0;
            for (int i = 0; i < grid.size(); i++) {
                for (int j = 0; j < grid.getFirst().size(); j++) {
                    if (grid.get(i).get(j) == '[') ans += (long) 100 * j + i;
                }
            }

            return ans;
        }

        static boolean trymove(int x, int y, ArrayList<ArrayList<Character>> grid, int movex, int movey, int ctr1){
            char ch = grid.get(y).get(x);
            if (ctr1 == 194)
                System.out.println("1");
            if (ch == '#') return false; // out of bounds
            if (ch != '[' && ch != ']') {
                grid.get(y).set(x, '@');
                //prefrlen e coekot treba use da se izbrise prosloto mesto
            }
            else {
                int tmpx = x, tmpy = y;
                if (movey == 0){
                    int ctr = 2;
                    if (grid.get(tmpy).get(tmpx + 2 * movex) == '[' ||
                            grid.get(tmpy).get(tmpx + 2 * movex) == ']') {
                        ctr = 4;
                    }
//                if (tmpx + 4 * movex < 0 || grid.get(tmpy).get(tmpx + 4 * movex) == '#') return false;
                    tmpx += ctr * movex;
                    if (grid.get(tmpy).get(tmpx) != '.' || grid.get(tmpy).get(tmpx+1) != '.') return false;
                    for (int i = 0; i < ctr + 1; i++) {
                        grid.get(tmpy).set(tmpx, grid.get(tmpy).get(tmpx-movex));
                        tmpx -= movex;
                    }
                }
                else {
                    boolean is_left = (grid.get(tmpy).get(tmpx) == '[');

                    int closed = is_left ? tmpx + 1 : tmpx - 1; // kaj e zatvorenata zagrada
                    if (grid.get(tmpy + movey).get(tmpx) == '#' || grid.get(tmpy + movey).get(closed) == '#')
                        return false;
                    // slucaj koa samo edna precka ima nad nego
                    if ((grid.get(tmpy + movey).get(closed) == '.' && grid.get(tmpy + movey).get(closed) != '.') ||
                            (grid.get(tmpy + movey).get(tmpx) != '.' && grid.get(tmpy + movey).get(closed) == '.'))
                        return false;
                    if (grid.get(tmpy + movey).get(tmpx) == '.' && grid.get(tmpy + movey).get(closed) == '.'){
                        grid.get(tmpy + movey).set(tmpx, ch);
                        grid.get(tmpy + movey).set(closed, ch == ']' ? '[' : ']');
                        grid.get(tmpy).set(tmpx, '@');
                        grid.get(tmpy).set(closed, '.');
                        return true;
                    }
                    // slucaj koa ima dve
                    if (grid.get(tmpy + movey).get(tmpx) == grid.get(tmpy).get(tmpx)){
                        if (grid.get(tmpy + 2 * movey).get(tmpx) != '.' ||
                                grid.get(tmpy + 2 * movey).get(closed) != '.')
                            return false;
                        tmpy += movey * 2;
                        for (int i = 0; i < 2; i++) {
                            grid.get(tmpy).set(tmpx, grid.get(tmpy - movey).get(tmpx));
                            grid.get(tmpy).set(closed, grid.get(tmpy - movey).get(tmpx) == '[' ? ']' : '[');
                            tmpy -= movey;
                        }
                        grid.get(tmpy).set(closed, '.');
                        grid.get(tmpy).set(tmpx, '@');
                        return true;
                    }
                    // slucaj koa ima tri


                    // NE RABOTI
                    /*
                     * ...@....
                     * ..[]....
                     * .[]...[]
                     *
                     * ovoj case me ebe
                     * */
                    int checker = is_left ? tmpx - 1 : tmpx - 2;
                    for (int i = 0; i < 4; i++) {
                        if (grid.get(tmpy + 2 * movey).get(checker + i) != '.') return false;
                    }
                    for (int i = 0; i < 4; i++) {
                        char c = grid.get(tmpy + 3 * movey).get(checker + i);
                        if (c == '#') continue;
                        grid.get(tmpy + 3 * movey).set(checker + i, c);
                    }
                    grid.get(tmpy + movey).set(checker, '.');
                    grid.get(tmpy + movey).set(checker + 1, '[');
                    grid.get(tmpy + movey).set(checker + 2, ']');
                    grid.get(tmpy + movey).set(checker + 3, '.');
                    grid.get(tmpy).set(tmpx, '@');
                    grid.get(tmpy).set(closed, '.');
                }
            }
            return true;
        }
    }
    public static void main(String[] args) {
        File file = new File("aoc_24/day15/input.txt");
        ArrayList<String> grid = new ArrayList<>();
        ArrayList<String> move_input = new ArrayList<>();
        ArrayList<Character> moves = new ArrayList<>();
        try{
            Scanner in = new Scanner(file);
            String line;
            while (in.hasNextLine()){
                line = in.nextLine();
                if (line.isBlank()) break;
                grid.add(line);
            }
            while (in.hasNextLine()){
                line = in.nextLine();
                move_input.add(line);
            }
            for (String s : move_input){
                char []arr = s.toCharArray();
                for (char c : arr){
                    moves.add(c);
                }
            }
            System.out.println(Day15.Part1.solve(grid, moves));
            // part2 ne rabote :(

        } catch (FileNotFoundException e) {
            System.out.println("file not found!");
        }
    }
}