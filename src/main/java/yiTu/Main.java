package yiTu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author : can
 * create at:  2023/4/26  19:11
 * @description:
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        Book[] books=new Book[n];
        for (int i = 0; i < n; i++) {
            String name=sc.next();
            int price=sc.nextInt();
            books[i]=new Book(name,price);
        }
        Arrays.sort(books);
        for (Book book : books) {
            System.out.println(book.name);
        }
    }
}
class Book implements  Comparable<Book>{
    String name;
    int price;
    public Book(String name,int price){
        this.name=name;
        this.price=price;
    }
    @Override
    public int compareTo(Book o) {
        return this.price-o.price;
    }
}


