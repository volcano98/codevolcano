package src.main.java.Graph;

import java.util.*;

public class SolutionByUnionFind {
    public int makeConnected(int n, int[][] connections) {
        UnionFind unionFind = new UnionFind(n);
        boolean[] arr = new boolean[n];
        Arrays.fill(arr, false);
        //多余的线要和缺少的arr匹配。
        int count = 0;
        for (int[] connection : connections) {
            int px = unionFind.find(connection[0]);
            int py = unionFind.find(connection[1]);
            if (px == py) {
                count++;
            } else {
                unionFind.union(px, py);
            }
        }
        int needUnion = n - unionFind.getCount();
        System.out.println(count + " " + needUnion);
        if (count < needUnion) {
            return -1;
        } else {
            return needUnion;
        }
    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        // 邮箱，账户id
        Map<String, Integer> map = new HashMap<>();
        UnionFind unionFind = new UnionFind(accounts.size());
        for (int i = 0; i < accounts.size(); i++) {
            for (int j = 1; j < accounts.get(i).size(); j++) {
                if (map.containsKey(accounts.get(i).get(j))) {
                    unionFind.union(i, map.get(accounts.get(i).get(j)));
                } else {
                    map.put(accounts.get(i).get(j), i);
                }
            }
        }
        //id ，对应邮箱。
        Map<Integer, List<String>> IdEmails = new HashMap<>();
        for (Map.Entry<String, Integer> stringIntegerEntry : map.entrySet()) {
            int parentId = unionFind.find(stringIntegerEntry.getValue());
            List<String> list = IdEmails.getOrDefault(parentId, new ArrayList<>());
            list.add(stringIntegerEntry.getKey());
            IdEmails.put(parentId, list);
        }
        List<List<String>> res = new ArrayList<>();
        IdEmails.forEach((k, v) -> {
            List<String> temp = new ArrayList<>();
            Collections.sort(v);
            temp.add(accounts.get(k).get(0));
            temp.addAll(v);
            res.add(temp);
        });
        return res;
    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {

        int index = 0;
        Map<String, Integer> map = new HashMap();
        for (List<String> equation : equations) {
            String x = equation.get(0);
            String y = equation.get(1);
            if (!map.containsKey(x)) {
                map.put(x, index);
                index++;
            }
            if (!map.containsKey(y)) {
                map.put(y, index);
                index++;
            }
        }
        Union unionFind = new Union(index);
        double[] res = new double[index];
        for (int i = 0; i < equations.size(); i++) {
            int pL = map.get(equations.get(i).get(0));
            int pR = map.get(equations.get(i).get(1));
            if (pL != pR) {
                unionFind.union(pL, pR, values[i]);
            }
        }
        for (int i = 0; i < queries.size(); i++) {

            if (!map.containsKey(queries.get(i).get(0)) || !map.containsKey(queries.get(i).get(1))) {
                res[i] = -1.0d;
            } else {
                int x = map.get(queries.get(i).get(0));
                int y = map.get(queries.get(i).get(1));
                res[i] = unionFind.division(x, y);
            }
        }

        return res;
    }

    private class Union {
        int[] parents;
        double[] values;

        private Union(int val) {
            parents = new int[val];
            values = new double[val];
            for (int i = 0; i < parents.length; i++) {
                parents[i] = i;
                values[i] = 1.0d;
            }
        }

        private double division(int x, int y) {
            if (find(x) != find(y)) {
                return -1.0d;
            }
            return values[x] / values[y];
        }

        private void union(int l, int r, double v) {
            int parentL = find(l);
            int parentR = find(r);
            if (parentL != parentR) {
                parents[parentL] = parentR;
                values[parentL] = v * values[l] / values[r];
            }
        }

        private int find(int index) {
            if (parents[index] != index) {
                int temp = parents[index];
                parents[index] = find(parents[index]);
                values[index] *= values[temp];
            }
            return parents[index];
        }

    }

    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        UnionFind union = new UnionFind(s.length());
        for (List<Integer> pair : pairs) {
            int i = pair.get(0);
            int j = pair.get(1);
            union.union(i, j);
        }

        //map 放一个父亲的所有子类，具有共同祖先，把子类排序后合并就是结果。


        HashMap<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            int parent = union.find(i);
//            PriorityQueue<Integer> list = map.getOrDefault(parent, new PriorityQueue<>( (a, b) -> s.charAt(a) - s.charAt(b)));
//            list.add(i);
//            map.put(parent, list);
            map.computeIfAbsent(parent, key -> new PriorityQueue<>((a, b) -> s.charAt(a) - s.charAt(b))).add(i);



        }
        System.out.println(map.size());
        // "dcab"          3,0,  2,1  0=3 3=0, 1=2, 2=1
        //[[0,3],[1,2]]
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            int parent = union.find(i);
            PriorityQueue<Integer> list = map.get(parent);
            res.append(s.charAt(list.poll()));
            map.put(parent, list);
        }
        return res.toString();
    }


    public void solve(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            dfs(board, i, 0);
            dfs(board, i, board[0].length - 1);
        }
        for (int i = 0; i < board[0].length; i++) {
            dfs(board, 0, i);
            dfs(board, board.length - 1, i);
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O') ;
                board[i][j] = 'X';
            }
        }
    }

    void dfs(char[][] board, int i, int j) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != 'O') {
            return;
        }
        board[i][j] = '#';
        dfs(board, i - 1, j);
        dfs(board, i + 1, j);
        dfs(board, i, j - 1);
        dfs(board, i, j + 1);
    }
}



