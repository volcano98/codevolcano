package mihayo;

import java.util.Scanner;

class Main1 {
    static int[] wef = {0, 0, 1, -1};
    static int[] sd = {1, -1, 0, 0};
    static char[][] ma;
    static boolean[][] vis;
    static int a, c;
    public static int cds() {
        int fd = 0;
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < c; j++) {
                if (!vis[i][j]) {
                    fd++;
                    dfs(i, j);
                }
            }
        }
        return fd;
    }
    public static void dfs(int ds, int cx) {
        vis[ds][cx] = true;
        for (int i = 0; i < 4; i++) {
            int nx = ds + sd[i];
            int ny = cx + wef[i];
            if (nx >= 0 && nx < a && ny >= 0 && ny < c && !vis[nx][ny] && ma[nx][ny] == ma[ds][cx]) {
                dfs(nx, ny);
            }
        }
    }
    public static void main(String[] args) {
        Scanner sa = new Scanner(System.in);
        a = sa.nextInt();
        c = sa.nextInt();
        ma = new char[a][c];
        vis = new boolean[a][c];
        sa.nextLine();
        for (int i = 0; i < a; i++) {
            ma[i] = sa.nextLine().toCharArray();
        }
        int count1 = cds();
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < c; j++) {
                if (ma[i][j] == 'G') {
                    ma[i][j] = 'B';
                }
            }
        }
        vis = new boolean[a][c];
        int count2 = cds();
        System.out.println(count1 - count2);
    }






}
