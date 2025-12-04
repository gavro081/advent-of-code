package aoc_24.day14;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class Day14 {
    static class Point{
        int x;
        int y;

        Point(){}
        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    static int GRID_WIDTH = 101;
    static int GRID_HEIGHT = 103;

    static public void process_input(String line, ArrayList<Point> s, ArrayList<Point> v){
        String []parts = line.split(" ");
        int comma = parts[0].indexOf(',');
        int equals = parts[0].indexOf('=');
        s.add(new Point(Integer.parseInt(parts[0].substring(equals + 1, comma)), Integer.parseInt(parts[0].substring(comma + 1))));
        comma = parts[1].indexOf(',');
        equals = parts[1].indexOf('=');
        v.add(new Point(Integer.parseInt(parts[1].substring(equals + 1, comma)), Integer.parseInt(parts[1].substring(comma + 1))));
    }

    class Part1 {
        static public long solve(ArrayList<Point> s, ArrayList<Point> v) {
            for (int i = 0; i < s.size(); i++) {
                Point p = part1_help(s.get(i).x, s.get(i).y, v.get(i), 100);
                s.set(i, p);
            }
            int half_width = GRID_WIDTH / 2;
            int half_height = GRID_HEIGHT / 2;
            // kvadranti: [0 - hw][0 - hh], (hw-kraj][0-hh], [0-hw](hh-kraj], (hw-kraj](hh-kraj]
            int q1, q2, q3, q4;
            q1 = q2 = q3 = q4 = 0;
            for (int i = 0; i < s.size(); i++) {
                int px = s.get(i).x;
                int py = s.get(i).y;
                if (px == half_width || py == half_height) continue;
                boolean p1 = px < half_width;
                boolean p2 = py < half_height;
                if (p1 && p2) q1++;
                else if (!p1 && p2) q2++;
                else if (p1 && !p2) q3++;
                else if (!p1 && !p2) q4++;
            }
            return (long) Math.max(1, q1) * Math.max(1, q2) * Math.max(1, q3) * Math.max(1, q4);
        }

        static public Point part1_help(int x, int y, Point v, int t) {
            // prvo resavav iterativno, vidov deka moze direktno, izbrisav iterativnoto, i za vtor del treba iterativno :|
            x = x + t * v.x;
            x = ((x % GRID_WIDTH) + GRID_WIDTH) % GRID_WIDTH;
            y = y + t * v.y;
            y = ((y % GRID_HEIGHT) + GRID_HEIGHT) % GRID_HEIGHT;
            return new Point(x, y);
        }
    }
    class Part2 {
        static public Point part2_help(int x, int y, Point v, int t, boolean[][] checked) {
            // prvo resavav iterativno, vidov deka moze direktno, izbrisav iterativnoto, i za vtor del treba iterativno :|
            x = x + t * v.x;
            x = ((x % GRID_WIDTH) + GRID_WIDTH) % GRID_WIDTH;
            y = y + t * v.y;
            y = ((y % GRID_HEIGHT) + GRID_HEIGHT) % GRID_HEIGHT;
            checked[y][x] = true;
            return new Point(x, y);
        }

        static public void solve(ArrayList<Point> s, ArrayList<Point> v) {
            /* objasnuvanje
             * niso od ova nemase ni da mi tekne da probam sam ama citav na reddit resenija i resiv da probam
             * da simuliram nekako poise primeri i da vidam kaj ke se pojavi elkata
             * prvicno sakav da gi printam samo tie kaj so na sekoja pozicija ima tocno eden robot deka na nekoj
             * toa mu rabotelo ali mene ne mi rabotese pa se prefrliv na druga opcija t.e.
             * printaj gi samo tie so imaat poise od 30 roboti u eden red (deka na dnoto na elkata ili vo ramkite
             * treba poise roboti da se skoncentrirani)
             * i pocna da printa pomalce sliki i megu tie imase neklk elki
             * zemav 2 elki gi staiv u txt file edna do dr za da najdam nekoj pattern
             * i iskoci deka kaj site elki u 32ot red e gornata ramka kaj so ima mislam 31 robot eden do drug
             * ama poso ke se ubijam ako napraam use eden off by one error samo staiv 25 roboti da ima i
             * da se printaat samo takvite gridovi i what do you know pocnaa da se printaat samo elki
             *
             */
            int time = 1;
            boolean b;
            while (time < 100000) {
                b = false;
                boolean[][] checked = new boolean[GRID_HEIGHT + 1][GRID_WIDTH + 1];
                for (int i = 0; i < s.size(); i++) {
                    Point p = part2_help(s.get(i).x, s.get(i).y, v.get(i), 1, checked);
                    s.set(i, p);
                }
//            int max = 0;
//            int ctr = 0;
//            for (int i = 0; i < GRID_HEIGHT + 1; i++) {
//                for (int j = 0; j < GRID_WIDTH + 1; j++) {
//                    if (checked[i][j]) ctr++;
//                }
//                max = Math.max(max, ctr);
//                ctr = 0;
//            }

//            if (max < 30) b = true;

                int fixedi = 31; // hardcoded af
                for (int i = 25; i < 50; i++) {
                    if (!checked[fixedi][i]) {
                        b = true;
                        break;
                    }
                }
                if (b) {
                    time++;
                    continue;
                }

                System.out.println(time);
                break;
            }
        }
    }

    public static void main(String[] args) {
        File file = new File("aoc_24/day14/input.txt");
        ArrayList<Point> s = new ArrayList<>(); // starting points
        ArrayList<Point> v = new ArrayList<>(); // velocity
        try{
            Scanner in = new Scanner(file);
            while (in.hasNextLine()) {
                String line = in.nextLine();
                process_input(line, s, v);
            }
            System.out.println(Day14.Part1.solve(s,v));
            Day14.Part2.solve(s,v);

        } catch (FileNotFoundException e) {
            System.out.println("file not found!");
        }
    }
}
