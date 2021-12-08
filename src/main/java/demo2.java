import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author : can
 * create at:  2021/11/28  20:05
 * @description: demo2
 */
public class demo2 {


    public static void main(String[] args) {
//      String a=new String("d");
//      String b=new String("d");
//        String c="d";
//        String d="d";
//        System.out.println(a.equals(b));
//        System.out.println(a==b);
//        System.out.println(d==c);
//        Integer ad=2222;
//        Integer as=2222;
//        System.out.println(ad.equals(as));
//        ArrayList v=new ArrayList();

        Queue a=new LinkedList();
        a.add(null);
        a.poll();
        System.out.println(a.size());
    }
}
