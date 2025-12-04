package aoc_24.day3;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day3 {
    class Part1 {
        static long solve(String line) {
            long prod = 0;
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) != 'm') continue;
                if (i + 7 >= line.length()) continue;
                if (!line.startsWith("mul", i) || line.charAt(i + 3) != '(') continue;
                int j = i + 4;
                boolean comma = false;
                String num1, num2;
                boolean flag = false;
                while (line.charAt(j) != ')') {
                    if (!(line.charAt(j) >= '0' && line.charAt(j) <= '9') && (line.charAt(j) != ')' && line.charAt(j) != ',')) {
                        flag = true;
                        break;
                    }
                    j++;
                }
                if (flag) continue;
                String para = line.substring(i + 4, j);
                prod += Integer.parseInt(para.split(",")[0]) * Integer.parseInt(para.split(",")[1]);
            }
            return prod;
        }
    }
    public static class Help{
        long num;
        boolean enabled;

        Help(long num, boolean enabled){
            this.num = num;
            this.enabled = enabled;
        }

        Help(Help o){
            this.num = o.num;
            this.enabled = o.enabled;
        }
    }
    class Part2{

        static Help solve(String line, boolean enabled){
            long prod = 0;
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == 'd' && line.startsWith("do()", i)) enabled = true;
                else if (line.charAt(i) == 'd' && line.startsWith("don't()", i)) {
                    enabled = false;
                    continue;
                }
                else if (line.charAt(i) != 'm' || i + 7 >= line.length() || !line.startsWith("mul", i)
                        || line.charAt(i+3) != '(' || !enabled)
                    continue;
                int j = i+4;
                boolean flag = false;
                while (line.charAt(j) != ')'){
                    if (!(line.charAt(j) >= '0' && line.charAt(j) <= '9') && (line.charAt(j) != ')' && line.charAt(j) != ',')) {
                        flag = true;
                        break;
                    }
                    j++;
                }
                if (flag) continue;
                String para = line.substring(i+4, j);
                String[] parasplit = para.split(",");
                prod += Integer.parseInt(parasplit[0]) * Integer.parseInt(parasplit[1]);
            }
            return new Help(prod, enabled);
        }
    }

    public static void main(String[] args) {
        File input = new File("aoc_24/day3/input.txt");
        long sol = 0;
        long sol2 = 0;
        boolean enabled = true;
        try {
            Scanner in = new Scanner(input);
            while (in.hasNextLine()){
                String line = in.nextLine();
                sol += Part1.solve(line);
                Help h = new Help(Part2.solve(line, enabled));
                enabled = h.enabled;
                sol2 += h.num;
            }

        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        }
        System.out.println(sol);
        System.out.println(sol2);
    }
}