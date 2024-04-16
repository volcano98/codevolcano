package du;

import java.util.Scanner;
//路由表最长匹配是IP（v4）路由器的最基本功能之一：当路由器收到一个IP数据包，会将数据包的目的IP和本地路由表进行匹配：
//
//格式：目的IP地址为'dstIP'，路由表中每条路由为“entryIP/掩码长度m”,如 '10.166.50.0/23' 。注：'10.166.50.0'的二进制表示如下：
//
//00001010 10100110 00110010 000000
//
//- ‘0.0.0.0/0’是默认路由，与任何目的IP地址都是匹配的，m值为0。
//- 所有匹配的路由中，m最大的即为'最长匹配'。
//
//现给出目的ip地址和本地路由表，请输出最长匹配的路由；如果有多条，则按给出的先后顺序，输出最先的，没有匹配的，输出字符串“empty”
//
//输入描述：
//
//第一行是目的IP地址，点分十进制表示的字符串。
//
//第二行一个整数n，表示路由表中的路由数量，取值范围为[1,10000]。
//
//接下来n行表示n条路由，其中掩吗长度m的取值范围为[0,32],m值为0 的情况仅存在于路由 0.0.0.0/0 。
//
//
//
//输出描述：
//
//最长匹配的路由，格式和输入相同，如果没有则输出字符串‘empty’。
//输入：
//192.168.0.3
//6
//10.166.50.0/23
//192.0.0.0/8
//10.255.255.255/32
//192.168.0.1/24
//1277.0.0.0/8
//192.168.0.0/24
//输出：192.168.0.0/24。

//输入：最长匹配的路由，格式输入相同，如果没有则输出字符串empty

//10.110.32.77
//2
//127.0.0.1/8
//0.0.0.0/0
//输出：0.0.0.0/0
public class function {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String dstIP = sc.nextLine();
        int n = sc.nextInt();
        sc.nextLine();

        String res = "empty";
        int len = -1;

        for (int i = 0; i < n; i++) {
            String[] entry = sc.nextLine().split("/");

            String entryIP = entry[0];

            int prelen = Integer.parseInt(entry[1]);

            if (func(dstIP, entryIP, prelen) && prelen > len) {
                res = entry[0] + "/" + entry[1];
                len = prelen;
            }
        }
        System.out.println(res);
    }

    private static boolean func(String dstIP, String entryIP, int prefixLength) {
        String[] a = dstIP.split("\\.");
        String[] b = entryIP.split("\\.");

        for (int i = 0; i < 4; i++) {
            int temp1 = Integer.parseInt(a[i]);
            int temp2 = Integer.parseInt(b[i]);

            int count = (prefixLength >= 8 * i + 8) ? 255 : (prefixLength % 8 == 0 ? 0 : 256 - (1 << (8 - prefixLength % 8)));

            if ((temp1 & count) != (temp2 & count)) {
                return false;
            }
        }
        return true;
    }
}
