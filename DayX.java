import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class Helper{}

class Part1{
    public static long solve(){
        return 0;
    }
}

class Part2{
    public static long solve(){
        return 0;
    }
}

public class DayX {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("aoc_25/dx/input.txt");
//        File file = new File("aoc/dx/test-input.txt");

        Scanner sc = new Scanner(file);
        // 1
        String input = sc.nextLine();

        // 2
        ArrayList<String> inputs = new ArrayList<>();
        while (sc.hasNextLine()){
            inputs.add(sc.nextLine());
        }

        System.out.println("part1: " + Part1.solve());
        System.out.println("part1: " + Part2.solve());
    }
}
