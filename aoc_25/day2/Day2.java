package aoc_25.day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Helper{}

record IdPair(Long first, Long second){
}

class Part1{
    static long solve(String input){
        ArrayList<IdPair> pairs = new ArrayList<>();
        Arrays.stream(input.split(","))
                .map(s -> s.split("-"))
                .forEach(p -> pairs.add(
                        new IdPair(
                                Long.parseLong(p[0]),
                                Long.parseLong(p[1])
                        )));
        long ans = 0;
        for (var pair : pairs){
            for (long i = pair.first(); i <= pair.second(); i++) {
                String s = String.valueOf(i);
                int sz = s.length();
                if (s.substring(0, sz/2).equals(s.substring(sz/2))) ans+=i;
            }
        }
        return ans;
    }
}
class Part2{

    private static long tryNumber(String word, Pattern pattern){
        Matcher m = pattern.matcher(word);
        if (m.matches()){
            return Long.parseLong(word);
        }
        return 0;
    }

    static long solve(String input){
        ArrayList<IdPair> pairs = new ArrayList<>();
        Arrays.stream(input.split(","))
                .map(s -> s.split("-"))
                .forEach(p -> pairs.add(
                        new IdPair(
                                Long.parseLong(p[0]),
                                Long.parseLong(p[1])
                        )));
        // poglupo mi bese prvicnoto resenie ova e vtora verzija
        long ans = 0;
        Pattern pattern = Pattern.compile("^(\\d+)\\1+$");
        for (var pair : pairs){
            for (long i = pair.first(); i <= pair.second(); i++) {
                String s = String.valueOf(i);
                ans += tryNumber(s, pattern);
            }
        }
        return ans;
    }

}

public class Day2 {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("aoc_25/day2/input.txt");
//        File file = new File("aoc/d2/test-input.txt");
        Scanner sc = new Scanner(file);
        String input = sc.nextLine();

        System.out.println("part1: " + Part1.solve(input));
        System.out.println("part2: " + Part2.solve(input));
    }
}
