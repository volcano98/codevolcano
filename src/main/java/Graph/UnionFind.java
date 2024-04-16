package src.main.java.Graph;



public class UnionFind {
    int[] parent;
    int count;
    public UnionFind(int val) {
        parent = new int[val];
        count = val;
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
    }

    public void union(int i, int j) {
        int p1 = find(i);
        int p2 = find(j);
        if (p1 == p2) return;
        count--;
        parent[p1] = p2;

    }
    void union(int i, int j,int v) {
        int p1 = find(i);
        int p2 = find(j);
        if (p1 == p2) return;
        count--;
        parent[p1] = p2;
    }

    public int getCount() {
        return this.count;
    }

    public int find(int p) {
        if (parent[p] != p) {
            int temp=parent[p];
            parent[p] = find(parent[p]);
        }
        return parent[p];
    }

}

