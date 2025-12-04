package aoc_24.day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Day1 {
    public static void main(String[] args) {
        ArrayList<Integer> arr1 = new ArrayList<>();
        ArrayList<Integer> arr2 = new ArrayList<>();

        File file = new File("aoc_24/day1/input.txt");
        try{
            Scanner in = new Scanner(file);
            String line;
            while (in.hasNextLine()){
                line = in.nextLine();
                if (line.isBlank()) break;
                arr1.add(Integer.parseInt(line.split(" {3}")[0]));
                arr2.add(Integer.parseInt(line.split(" {3}")[1]));
            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found!");
        }

        Collections.sort(arr1);
        Collections.sort(arr2);
        // part 1
        long sum = 0;
        for (int i = 0; i < arr1.size(); i++) {
            sum += Math.abs(arr1.get(i) - arr2.get(i));
        }
        System.out.println(sum);

        // part 2
        int []res = new int[arr1.getLast() + 1];
        Arrays.fill(res, 0);
        for (int i = 0; i < arr2.size(); i++) {
            int n = arr2.get(i);
            if (!arr1.contains(n)) continue;
            res[arr2.get(i)]++;
        }
        long res2 = 0;
        for (int i = 0; i < arr1.size(); i++) {
            res2 += arr1.get(i) * res[arr1.get(i)];
        }
        System.out.println(res2);
    }
}
