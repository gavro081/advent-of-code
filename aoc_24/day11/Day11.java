package aoc_24.day11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Day11 {
    HashMap<Long, Long>[] table;
//     vo table[i] cuvas sostojba posle i-blinks
//     key - prvicna vrednost na kamenot, value - novata negova vrednost

    long solve_r(String rock, int blink, int count){
        if (blink == count) return 1;

        Long rocknum = Long.parseLong(rock);
        rock = Long.toString(rocknum);

        if (table[blink].containsKey(rocknum)) return table[blink].get(rocknum);

        long val = 0;

        if (rocknum == 0) {
            val = solve_r("1", blink + 1, count);
            table[blink].put(rocknum, val);
            return val;
        }
        if (rock.length() % 2 == 0){
            long val1 = solve_r(rock.substring(0, rock.length() / 2), blink + 1, count);
            long val2 = solve_r(rock.substring(rock.length() / 2), blink + 1, count);

            table[blink].put(rocknum, val1 + val2);
            return table[blink].get(rocknum);
        }

        val = solve_r(Long.toString(rocknum * 2024), blink + 1, count);
        table[blink].put(rocknum, val);
        return val;
    }

    long solve(ArrayList<String> rocks, int count){
        table = new HashMap[count + 1];
        for (int i = 0; i <= count ; i++) {
            table[i] = new HashMap<>();
        }
        long ans = 0;

        for (String r : rocks){
            solve_r(r, 0, count);
            ans += table[0].get(Long.parseLong(r));
        }
        return ans;
    }
    public static void main(String[] args) {
        Day11 main = new Day11();
        File file = new File("aoc_24/day11/input.txt");
        try{
            Scanner in = new Scanner(file);
            String line = in.nextLine();
            String []parts = line.split(" ");
            ArrayList<String> rocks = new ArrayList<>(Arrays.asList(parts));
            System.out.println(main.solve(rocks, 25));
            System.out.println(main.solve(rocks, 75));
        } catch (FileNotFoundException e) {
            System.out.println("file not found!");
        }
    }
}