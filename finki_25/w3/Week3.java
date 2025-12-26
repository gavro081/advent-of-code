package finki_25.w3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Week3 {
    static class UnionFind {
        private final Map<Integer, Integer> parent = new HashMap<>();
        private final Map<Integer, Integer> size = new HashMap<>();

        public void add(int x){
            parent.putIfAbsent(x, x);
            size.putIfAbsent(x, 1);
        }

        public int find(int x){
            if (parent.get(x) != x){
                parent.put(x, find(parent.get(x)));
            }
            return parent.get(x);
        }

        public void union(int a, int b){
            add(a);
            add(b);

            int rootA = find(a);
            int rootB = find(b);

            if (rootA != rootB){
                int sizeA = size.get(rootA);
                int sizeB = size.get(rootB);
                if (sizeA < sizeB){
                    parent.put(rootA, rootB);
                    size.put(rootB, sizeA + sizeB);
                } else {
                    parent.put(rootB, rootA);
                    size.put(rootA, sizeA + sizeB);
                }
            }
        }

        public int largestGroupSize(){
            int max = 0;
            for (int s : size.values()){
                max = Math.max(max, s);
            }
            return max;
        }
    }

    public static int largestRumorGroup(String input){
        UnionFind uf = new UnionFind();
        String []pairs = input.split(",");

        for (String pair : pairs){
            String []users = pair.split("-");
            int a = Integer.parseInt(users[0].trim());
            int b = Integer.parseInt(users[1].trim());
            uf.union(a,b);
        }

        return uf.largestGroupSize();
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("finki_25/w3/input.txt");
        Scanner sc = new Scanner(file);
        System.out.println(largestRumorGroup(sc.nextLine()));
    }
}
