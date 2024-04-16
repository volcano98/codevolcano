package mihayo;

import java.util.*;

public class Main3 {
    static final long MOD = 1000000007;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int nsd = in.nextInt();
        int[] a = new int[nsd];
        for (int i = 0; i < nsd; i++) {
            a[i] = in.nextInt();
        }
        Arrays.sort(a);
        Map<Integer, Long> ds = new HashMap<>();
        long ans = 0;
        for (int i = 0; i < nsd; i++) {
            long now = 1;
            for (int j = 0; j < i; j++) {
                if (a[i] % a[j] == 0) {
                    now = (now + ds.get(a[j])) % MOD;
                }
            }
            ans = (ans + now - 1) % MOD;
            ds.put(a[i], now);
        }
        System.out.println(ans);
    }
}
