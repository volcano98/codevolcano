package hello;

import java.util.Scanner;


/**
 * @author : can
 * create at:  2024/7/11  00:47
 * @description:
 */
public class HelloGoodbye {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            System.out.print(scanner.next()+" ");
        }
    }
}
