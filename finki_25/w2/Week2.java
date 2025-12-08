package finki_25.w2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Week2 {


    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("finki_25/w2/input.txt");
        Scanner sc = new Scanner(file);
        int []ints = Arrays
                .stream(sc.nextLine().split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
        int [] dp = new int[ints.length];
        dp[0] = ints[0];
        int ans = 0;
        for (int i = 1; i < ints.length; i++) {
            dp[i] = Math.max(dp[i-1] + ints[i], ints[i]);
            ans = Math.max(dp[i], ans);
        }
        System.out.println(ans);
    }

}
