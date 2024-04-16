import java.util.Scanner;
//假设用区间[start,end) 表示一次内存初始化操作范围，end 减去start 表示初始化的内存长度。
//
//给定一组内存的初始化操作，请计算最终初始化内存的内存空间的总长度。
//
//同一块内存允许重复初始化
//
//
//
//输入描述：
//
//首行一个整数n，表示一组内存初始化操作的次数，取值范围为[1,10000]
//
//接下来n行，每行表示一次内存初始化，格式为 "start end",取值范围 0<=start<end<=10^9
//
//输出描述：
//
//一个整数，表示最终初始化的内存空间的总长度。
//
//输入：
//3
//2 4
//3 7
//4 6
//输出：5
//原因是[2.4) 和[3,7) 合并为[2,7),[2,7)和[4,6)合并为[2,7),总长度为7-2=5.
class Route {
    public String destIP;
    public int maskLen;

    public Route(String destIP, int maskLen) {
        this.destIP = destIP;
        this.maskLen = maskLen;
    }

    public boolean matches(String targetIP) {
        String[] destParts = destIP.split("\\.");
        String[] targetParts = targetIP.split("\\.");
        for (int i = 0; i < 4; i++) {
            int destByte = Integer.parseInt(destParts[i]);
            int targetByte = Integer.parseInt(targetParts[i]);
            int mask = (1 << (8 - (i == 3 ? maskLen : 8))) - 1;
            if ((destByte & mask) != (targetByte & mask)) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        return destIP + "/" + maskLen;
    }
}

public class dsdsc {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String targetIP = scanner.nextLine();
        int numRoutes = scanner.nextInt();
        scanner.nextLine();
        Route[] routes = new Route[numRoutes];
        for (int i = 0; i < numRoutes; i++) {
            String[] parts = scanner.nextLine().split("/");
            String destIP = parts[0];
            int maskLen = Integer.parseInt(parts[1]);
            routes[i] = new Route(destIP, maskLen);
        }
        scanner.close();
        Route longestMatch = null;
        for (Route route : routes) {
            if (route.matches(targetIP)) {
                if (longestMatch == null || route.maskLen > longestMatch.maskLen) {
                    longestMatch = route;
                }
            }
        }
        System.out.println(longestMatch == null ? "empty" : longestMatch.toString());
    }
}
