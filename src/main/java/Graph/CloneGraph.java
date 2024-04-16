package src.main.java.Graph;

import java.util.*;

// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

// 层次遍历。递归dfs
class Solution {
    public static Map<Node,Node> map =new HashMap<>();
    public static Node cloneGraph(Node node) {
        if(node ==null){
            return null;
        }
        if(map.containsKey(node)){
            return map.get(node);
        }
        Node now = new Node(node.val);
        map.put(node,now);
        for (Node neighbor : node.neighbors) {
            now.neighbors.add(cloneGraph(neighbor));
        }
        return now;
    }

    public static void main(String[] args) {

        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        node1.neighbors.add(node2);
        node1.neighbors.add(node4);
        node2.neighbors.add(node1);
        node2.neighbors.add(node3);
        node3.neighbors.add(node2);
        node3.neighbors.add(node4);
        node4.neighbors.add(node1);
        node4.neighbors.add(node3);
        Node newNode = cloneGraph(node1);
        for (Node neighbor : node1.neighbors) {
            System.out.print(neighbor.val+" ");
        }
    }

}
