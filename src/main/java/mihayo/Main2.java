package mihayo;

/**
 * @author : can
 * create at:  2023/3/19  20:32
 * @description:
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main2 {
   static List<Character> list=new ArrayList<>();
    public static void main(String[] args) {
        list.add('m');
        list.add('h');
        list.add('y');
        Scanner sc = new Scanner(System.in);
        int q = sc.nextInt();
        sc.nextLine();
        while (q-- > 0) {
            String s = sc.nextLine();
            String t = sc.nextLine();
            if (canTransform(s, t)) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }
        sc.close();
    }

    private static boolean canTransform(String s, String t) {
        int sIndex = 0;
        int tIndex = 0;
        int countS=s.length();
        int countT=t.length();
        while (sIndex < s.length() && tIndex < t.length()) {
            char temps=s.charAt(sIndex);
            char tempt=t.charAt(tIndex);
            if (list.contains(temps) && list.contains(tempt)) {
                sIndex++;
                tIndex++;
            } else if (list.contains(tempt)) {
                tIndex++;
            } else if (list.contains(temps)) {
                sIndex++;
            } else if (temps == tempt) {
                countS--;
                countT--;
                sIndex++;
                tIndex++;
            } else {
                return false;
            }
        }
        if(countS%3!=0||countT%3!=0){
            return false;
        }
        while (tIndex < t.length()) {
            if ((!list.contains(t.charAt(tIndex)))) {
                return false;
            }

            tIndex++;
        }
        while (sIndex < s.length()) {
            if ((!list.contains(s.charAt(sIndex)))) {
                return false;
            }
            sIndex++;
        }
        return true;
    }
}