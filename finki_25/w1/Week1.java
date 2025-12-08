package finki_25.w1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Week1 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("finki_25/w1/input.txt");
        Scanner sc = new Scanner(file);
        int K = Integer.parseInt(sc.nextLine());
        int[] array = Arrays.stream(sc.nextLine()
                        .split(","))
                .mapToInt(Integer::parseInt)
                .filter(num -> num >= 2)
                .sorted()
                .toArray();
        int left = 0;
        int right = 0;
        int ans = 0;
        while (right < array.length){
            ans = Math.max(right - left, ans);
            if (array[left] + K < array[right]) left++;
            else right++;
        }
        ans = Math.max(right - left, ans);
        System.out.println(ans);
    }
}
