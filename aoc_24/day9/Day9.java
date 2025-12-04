package aoc_24.day9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day9 {
    class Part1 {
        public static class Help{
            int block;
            int freespace;

            Help(int b, int f){
                this.block = b;
                this.freespace = f;
            }
        }

        public static long solve(String line) {
            int n = line.length() / 2 + 1;
            Help[] initial = new Help[n];
            int ctr = 0;
            long totalfree = 0;
            for (int i = 0; i < line.length() - 1; i += 2) {
                initial[ctr] = new Help(line.charAt(i) - '0', line.charAt(i + 1) - '0');
                totalfree += initial[ctr++].block;
            }
            initial[ctr] = new Help(line.charAt(line.length() - 1) - '0', 0);
            totalfree += initial[ctr].block;

            ArrayList<Integer> moved = new ArrayList<>();

            for (int i = 0; i < initial[0].block; i++) {
                moved.add(0);
                totalfree--;
            }

            int i = 0;
            int j = ctr;

            while (totalfree != 0) {
                while (totalfree != 0 && initial[i].freespace == 0) {
                    i++;
                    for (int k = 0; k < initial[i].block; k++) {
                        moved.add(i);
                        totalfree--;
                    }
                }
                if (totalfree == 0) break;
                while (initial[j].block == 0) j--;
                moved.add(j);
                initial[j].block--;
                initial[i].freespace--;
                totalfree--;
            }
            long totalans = 0;
            for (int k = 0; k < moved.size(); k++) {
                totalans += moved.get(k) * k;
            }
            return totalans;
        }
    }

    class Part2{
        static class Help{
            int block;
            int freespace;
            int id;
            ArrayList<Integer> space;

            Help(int b, int f, int id){
                this.block = b;
                this.freespace = f;
                this.id = id;
                space = new ArrayList<>();
            }
        }

        public static long solve(String line){
            int n = line.length() / 2 + 1;
            Help []initial = new Help[n];
            int ctr = 0;
            int totalfree = 0;
            for (int i = 0; i < line.length() - 1; i+=2) {
                initial[ctr] = new Help(line.charAt(i) - '0', line.charAt(i+1) - '0', ctr);
                totalfree += initial[ctr].block + initial[ctr++].freespace;
            }
            initial[ctr] = new Help(line.charAt(line.length()-1) - '0', 0, ctr);
            totalfree += initial[ctr].block + initial[ctr].freespace;



            int j = ctr;
            int i;

            while (j > 0) {
                i = 0;
                while (i < j) {
                    if (initial[i].freespace >= initial[j].block) {
                        // Found space, insert
                        for (int k = 0; k < initial[j].block; k++) {
                            initial[i].space.add(j);
                        }
                        initial[i].freespace -= initial[j].block;
                        // Essentially skipping in checksum, but the position is still there
                        initial[j].id = 0;
                        break;
                    }
                    i++;
                }
                j--;
            }

            long ans = 0;

            int offset = 0;
            for (Help h : initial){
                for (int k = 0; k < h.block; k++) {
                    ans += (long)offset * h.id;
                    offset++;
                }
                for (int s : h.space){
                    if (s >= 0){
                        ans += (long)offset * s;
                    }
                    offset++;
                }
                offset += h.freespace;
            }

            return ans;
        }
    }
    public static void main(String[] args) {
        File file = new File("aoc_24/day9/input.txt");
        try{
            Scanner in = new Scanner(file);
            String line = in.nextLine();
            System.out.println(Part1.solve(line));
            System.out.println(Part2.solve(line));
        } catch (FileNotFoundException e) {
            System.out.println("file not found!");
        }
    }
}