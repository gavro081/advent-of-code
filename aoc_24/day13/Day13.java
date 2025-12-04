package aoc_24.day13;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day13 {
    static int PRICE_A = 3;
    static int PRICE_B = 1;

    static public class Button {
        long x;
        long y;

        Button(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }

    static public void process_input(String a, ArrayList<Button> btn_a) {
        String ch = a.startsWith("B") ? "\\+" : "=";
        String[] parts = a.split(ch);
        int comma = a.indexOf(',') - parts[0].length();
        btn_a.add(new Button(Integer.parseInt(parts[1].substring(0, comma - 1)),
                Integer.parseInt(parts[2])));
    }

    static long mod(long a, long b) {
        return a % b;
    }
    class Part1{
        static public long solve(ArrayList<Button> a, ArrayList<Button> b, ArrayList<Button> p) {
            // klasikata prvo resenie so brute force najneefikasno na svet
            long ans = 0;
            for (int i = 0; i < a.size(); i++) {
                long mult_b = (p.get(i).x / b.get(i).x);
                if (mult_b > 100) mult_b = 100;
                while (true) {
                    if (mod(p.get(i).x - mult_b * b.get(i).x, a.get(i).x) == 0 && mod(p.get(i).y - mult_b * b.get(i).y, a.get(i).y) == 0) {
                        long d = (p.get(i).x - mult_b * b.get(i).x) / a.get(i).x;
                        if (d * a.get(i).y + mult_b * b.get(i).y == p.get(i).y) {
                            ans += (long) PRICE_A * d + (long) PRICE_B * mult_b;
                            break;
                        }
                    }
                    mult_b--;
                    if (mult_b < 0) break;
                }
            }
            return ans;
        }
    }

    class Part2 {
        static public long solve(ArrayList<Button> a, ArrayList<Button> b, ArrayList<Button> p) {
        /* matematicki moze u O(1) (poedinecno) ili O(n) za site da najdes dali postoi Y, i posle dali postoi i X
        taka sto ke se ispolni ravenkata (zemajki u previd deka ima samo eden tocen par
        nemase nikad u 100 zivoti da mi tekne taka da resam ama vidov na reddit nekoj so pisal i posle probav sam
        anyway, 6to odd algebra:
        px = ax * X + bx * Y
        py = ay * X + by * Y
        X = (px - bx * Y) / ax = (py - by * Y) / ay
        ax (py - by * Y) = ay (px - bx * Y)
        ax * py - ax * by * Y - ay * px + ay * bx * Y
        Y(ay * bx - ax * by) = ay * px - ax * py
        Y = (ay * px - ax * py) / (ay * bx - ax * by)  <--- site veke gi imame...O(1) lol
        ako Y e cel broj, treba da se proveri dali X bi bil cel broj
        X = (px - bx * Y) / ax = (py - by * Y) / ay ---> se zema edno od ovie dve, se menja za Y i se resava za X
        ako X i Y se celi broevi voila imame resenie
        */
            long ans = 0;
            p.replaceAll(button -> new Button(button.x + 10000000000000L, button.y + 10000000000000L));
            for (int i = 0; i < a.size(); i++) {
                if (mod(a.get(i).y * p.get(i).x - a.get(i).x * p.get(i).y,
                        a.get(i).y * b.get(i).x - a.get(i).x * b.get(i).y) == 0) {

                    long Y = (a.get(i).y * p.get(i).x - a.get(i).x * p.get(i).y) / (
                            a.get(i).y * b.get(i).x - a.get(i).x * b.get(i).y);

                    if (mod(p.get(i).x - b.get(i).x * Y, a.get(i).x) == 0) {
                        long X = (p.get(i).x - b.get(i).x * Y) / a.get(i).x;
                        ans += Y * PRICE_B + X * PRICE_A;
                    }
                }

            }

            return ans;
        }
    }
    public static void main(String[] args) {
        File file = new File("aoc_24/day13/input.txt");
        ArrayList<Day13.Button> first = new ArrayList<>();
        ArrayList<Day13.Button> second = new ArrayList<>();
        ArrayList<Day13.Button> prizes = new ArrayList<>();
        try{
            Scanner in = new Scanner(file);
            while (in.hasNextLine()){
                String btn_a = in.nextLine();
                String btn_b = in.nextLine();
                String prize = in.nextLine();
                Day13.process_input(btn_a, first);
                Day13.process_input(btn_b, second);
                Day13.process_input(prize, prizes);
                if (!in.hasNextLine()) break;
                in.nextLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found!");
        }
        System.out.println(Day13.Part1.solve(first, second, prizes));
        System.out.println(Day13.Part2.solve(first, second, prizes));
    }
}