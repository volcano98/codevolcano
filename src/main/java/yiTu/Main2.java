package yiTu;

import java.util.Scanner;

/**
 * @author : can
 * create at:  2023/4/26  19:19
 * @description:
 */
public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        if (func(num)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    static boolean func(int n) {
        String num = Integer.toBinaryString(n);
        int len = num.length();
        int[] pre = new int[len];
        pre[0] = Character.getNumericValue(num.charAt(0));
        for (int i = 0; i < len; i++) {
            pre[i] = pre[i - 1] * Character.getNumericValue(num.charAt(i));
        }
        int suf = 1;
        for (int i = len - 1; i > 0; i--) {
            suf *= Character.getNumericValue(num.charAt(i));
            if (pre[i - 1] == suf) {
                return true;
            }
        }
        return false;
    }

}
