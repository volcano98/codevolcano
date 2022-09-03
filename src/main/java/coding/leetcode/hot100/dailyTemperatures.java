package coding.leetcode.hot100;


import java.util.*;

import static java.lang.Math.sqrt;

class dailyTemperatures {

    private dailyTemperatures.Solution Solution;

    //给定一个整数数组 temperatures ，表示每天的温度，
    // 返回一个数组 answer ，其中 answer[i] 是指对于第 i 天，下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用 0 来代替
    //输入: temperatures = [73,74,75,71,69,72,76,73]
    //输出: [1,1,4,2,1,1,0,0]
    public int[] dailyTemperatures(int[] temperatures) {
        //可以确定最后一天的位置，前一天的结果可以由后面一天推出来。

        int[] res = new int[temperatures.length];
        int max = temperatures[temperatures.length - 1];
        res[temperatures.length - 1] = 0;
        for (int i = temperatures.length - 2; i >= 0; i--) {
            if (temperatures[i] >= max) {
                res[i] = 0;
                max = temperatures[i];
                continue;
            }
            for (int j = i + 1; j < temperatures.length; j = j + res[j]) {
                if (temperatures[i] < temperatures[j]) {
                    res[i] = j - i;
                    break;
                }
            }

        }
        return res;
    }

    public int leastInterval(char[] tasks, int n) {
        //贪心，
        if (n < 1) {
            return tasks.length;
        }
        //                                                      1      1
        //找到task里面出现次数最多的任务，若为A，3次，间隔为n>=1,则有 A------A-----A
        // 则最短时间就是   （A的次数-1）*n+与A次数相同的元素
        HashMap<Character, Integer> map = new HashMap<>();
        int count = 0;
        for (char task : tasks) {
            //abcabcabcde1d

            map.put(task, map.getOrDefault(task, 0) + 1);
            count = Math.max(map.get(task), count);
        }
        int sum = 0;
        for (Character character : map.keySet()) {
            if (map.get(character) == count) {
                sum++;
            }
            System.out.println(map.get(character));
        }
        return (count - 1) * n + sum;

    }


    //给你一个变量对数组 equations 和一个实数值数组 values 作为已知条件，其中 equations[i] = [Ai, Bi] 和 values[i] 共同表示等式 Ai / Bi = values[i] 。每个 Ai 或 Bi 是一个表示单个变量的字符串。
    //
    //另有一些以数组 queries 表示的问题，其中 queries[j] = [Cj, Dj] 表示第 j 个问题，请你根据已知条件找出 Cj / Dj = ? 的结果作为答案。
    //
    //返回 所有问题的答案 。如果存在某个无法确定的答案，则用 -1.0 替代这个答案。如果问题中出现了给定的已知条件中没有出现的字符串，也需要用 -1.0 替代这个答案。
    //
    //注意：输入总是有效的。你可以假设除法运算中不会出现除数为 0 的情况，且不存在任何矛盾的结果
    //
    //来源：力扣（LeetCode）
    //链接：https://leetcode.cn/problems/evaluate-division
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public int[][] reconstructQueue(int[][] people) {
        //第一个元素是身高，第二个元素是前面比他多高的元素。2维遍历优先确定第一个元素。
        Arrays.sort(people, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0]);
        //排序之后第一个元素是身高最高而且前面没有比他更高的个数最少的在前面。
        LinkedList<int[]> list = new LinkedList();
        for (int[] person : people) {
            list.add(person[1], person);
        }
        return list.toArray(new int[people.length][]);
    }
    //输入：equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
    //输出：[6.00000,0.50000,-1.00000,1.00000,-1.00000]
    //解释：
    //条件：a / b = 2.0, b / c = 3.0
    //问题：a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
    //结果：[6.0, 0.5, -1.0, 1.0, -1.0 ]

    //equations key ,value： values；从queries查key的集合。
    class Solution {
        public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
            double[] result = new double[queries.size()];
            Map<String, Map<String, Double>> graph = buildGraph(equations, values);

            for (int i = 0; i < queries.size(); i++) {
                String start = queries.get(i).get(0);
                String end = queries.get(i).get(1);

                if (!graph.containsKey(start) || !graph.containsKey(end)) {
                    result[i] = -1;
                } else {
                    Set<String> visited = new HashSet<>();
                    result[i] = dfs(graph, start, end, visited);
                }
            }

            return result;
        }

        private Map<String, Map<String, Double>> buildGraph(List<List<String>> equations, double[] values) {
            Map<String, Map<String, Double>> graph = new HashMap<>();

            for (int i = 0; i < equations.size(); i++) {
                String v1 = equations.get(i).get(0);
                String v2 = equations.get(i).get(1);
                graph.putIfAbsent(v1, new HashMap<String, Double>());
                graph.get(v1).put(v2, values[i]);

                graph.putIfAbsent(v2, new HashMap<String, Double>());
                graph.get(v2).put(v1, 1.0 / values[i]);
            }

            return graph;
        }

        private double dfs(Map<String, Map<String, Double>> graph, String start, String end, Set<String> visited) {
            visited.add(start);
            Map<String, Double> next = graph.get(start);
            for (Map.Entry<String, Double> entry : next.entrySet()) {
                if (entry.getKey().equals(end)) {
                    return entry.getValue();
                }
                if (!visited.contains(entry.getKey())) {
                    double nextValue = dfs(graph, entry.getKey(), end, visited);
                    if (nextValue > 0) {
                        return entry.getValue() * nextValue;
                    }
                }
            }

            return -1;
        }

        public int dd(int N) {
            // write your code in Java 11 (Java SE 11)
            //
            System.out.println(String.valueOf(N));
            return 1;
        }


    }

    public static void main(String[] args) {
        Solution s = new dailyTemperatures().Solution;
        s.dd(20);

    }

    private int MaxTotal = 1000000000;



    boolean isZS(int n) {
        double sqrtN = (double) sqrt((double) n);
        if (n == 1 || n == 2)
            return true;
        for (int i = 2; i <= sqrtN; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

}

