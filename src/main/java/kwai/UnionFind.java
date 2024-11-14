package kwai;


//根据上文描述，我们就可以定义一下「并查集」结构所支持的操作接口：
//
//合并 union(x, y)：将集合
//𝑥
//x 和集合
//𝑦
//y 合并成一个集合。
//查找 find(x)：查找元素
//𝑥
//x 属于哪个集合。
//查找 is_connected(x, y)：查询元素
//𝑥
//x 和
//𝑦
//y 是否在同一个集合中。
public class UnionFind {
    private int[] nums;

    UnionFind(int x) {
        this.nums = new int[x];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = i;
        }
    }

    boolean isConnect(int x, int y) {
        return nums[x] == nums[y];
    }

    void union(int x, int y) {
        int i = find(x);
        int j = find(y);
        if (i != j) {
            nums[i] = j;
        }
    }

    int find(int i) {
        while (nums[i] != i) {
            nums[i] = nums[nums[i]];
            i = nums[i];
        }
        return i;
    }

    public boolean equationsPossible(String[] equations) {
        for (int i = 0; i < equations.length; i++) {
            int x = equations[i].charAt(0) - 'a';
            int y = equations[i].charAt(3) - 'a';
            if (equations[i].charAt(1) == '=') {
                union(x, y);
            }
        }
        for (int i = 0; i < equations.length; i++) {
            int x = equations[i].charAt(0) - 'a';
            int y = equations[i].charAt(3) - 'a';
            if (equations[i].charAt(1) == '!') {
                int father = find(x);
                int mother = find(y);
                if (father == mother) {
                    return false;
                }
            }
        }
        return true;
    }

    public int findCircleNum(int[][] isConnected) {
        if (isConnected == null) return 0;
        this.nums = new int[isConnected.length];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = i;
        }
        for (int i = 0; i < isConnected.length; i++) {
            for (int j = 0; j < isConnected[i].length; j++) {
                if(isConnected[i][j]==1){
                    union(i,j);
                }
            }
        }
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i]==i){
                count++;
            }
        }
        return count;
    }
    public static void main(String[] args) {
        UnionFind unionFind = new UnionFind(26);
        boolean res = unionFind.equationsPossible(new String[]{"a==b", "b!=a"});
        System.out.println(res);
    }
}