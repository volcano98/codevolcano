package bitOperation;

import java.util.ArrayList;
import java.util.List;

public class demo {
    public int hammingDistance(int x, int y) {
        int d = x ^ y;
        String res = Integer.toBinaryString(d);
        int j = 0;
        for (char c : res.toCharArray()) {
            if (c == '1') {
                j++;
            }
        }
        return j;
    }





    public static void main(String[] args) {

    }

}
