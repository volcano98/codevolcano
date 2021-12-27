import java.util.*;

/**
 * @author : can
 * create at:  2021/11/28  20:05
 * @description: 记录写过的算法
 */
public class codevolcano {

    public static void main(String[] args) {


        // 唉，赶紧冲起来吧。时间不多了。
//        System.out.println(a[a.length - 1][0]);

    }


    /**
     * 128. 最长连续序列，要求数值+1连续递增。
     * 想法：放入set，因为重复不算递增了，然后遍历，判断当前元素-1存在不，目的是找到递增的第一个节点。
     * 不存在，判断+1存在不，同时记录长度，同时遍历下一个节点。
     * 这个复杂度是O(n),不要想当然认为for循环里面加个while就是O（n2），要看循环次数。
     *
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int cout = 1; //有元素最小是1。
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        for (int num : set) {//尽量用这个方式，不然你不知道用nums[i],i会不会越界。
            if (!set.contains(num - 1)) {
                int j = num;
                while (set.contains(j + 1)) {
                    j++;
                    cout = Math.max(cout, j - num + 1);//j-num+1不理解，从1到10一共11个数。
                }
            }
        }
        return cout;
    }

    /**
     * 199 二叉树的右视图,思路：右子树不空，傻逼了吧，直接bfs找最后一个元素加入res就可以了，或者dfs，根，右，左。返回。左视图同理，层序遍历，加个flag
     *
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {

        List<Integer> list = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList();
        if (root != null) {
            queue.add(root);
        }
        while (!queue.isEmpty()) {
            List<Integer> temp = new ArrayList<>();
            int cout = queue.size();
            while (cout != 0) {
                TreeNode res = queue.poll();
                cout--;
                if (res != null) {
                    temp.add(res.val);
                    if (res.left != null) {
                        queue.add(res.left);
                    }
                    if (res.right != null) {
                        queue.add(res.right);
                    }
                }
            }
            list.add(temp.get(temp.size() - 1));

        }
        return list;
    }


    /**
     * 快速排序，难点在于交换位置后，会认为原始值被覆盖了，其实没有，最后留下来的是base，注意等于号。
     *
     * @param num
     * @param left
     * @param right
     */
    public void quickSort(int[] num, int left, int right) {
        if (left >= right) {
            return;
        }
        int base = num[left];
        int l = left;
        int r = right;
        while (l < r) {
            while (num[r] >= base && l < r) {
                r--;
            }
            if (l < r) {
                num[l] = num[r];//不互相换位置是因为左边的还没有被比较过。
                l++;
            }
            while (num[l] <= base && l < r) {
                l++;
            }
            if (l < r) {
                num[r] = num[l];
                r--;
            }
        }
        num[l] = base;//这个地方是前面排序过程中覆盖掉的base；
        quickSort(num, left, l - 1);
        quickSort(num, l + 1, right);

    }

    /**
     * 写法2
     *
     * @param nums
     */
    void quickSort(int nums[]) {
        int left = 0;
        int right = nums.length - 1;
        function(nums, left, right);
    }

    void function(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int i = left;
        int j = right;
        int base = nums[left];
        while (i < j) {
            while (i < j && nums[j] >= base) {
                j--;
            }
            if (i < j) {
                nums[i] = nums[j];
            }
            while (i < j && nums[i] <= base) {
                i++;
            }
            if (i < j) {
                nums[j] = nums[i];
            }
        }
        nums[i] = base;
        function(nums, left, i - 1);
        function(nums, i + 1, right);
    }

    void QuickSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int i = left;
        int j = right;
        while (i < j) {
            while (i < j && nums[left] <= nums[j]) {
                j--;
            }
            while (i < j && nums[i] <= nums[left]) {
                i++;
            }
            swap(nums, i, j);
        }
        swap(nums, i, left);
        QuickSort(nums, left, i - 1);
        QuickSort(nums, i + 1, right);
    }

    void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * 56 合并区间,想法很简单，就是写起来有点复杂，二维数组的题写的比较少。list还能加数组。
     * 就是。排序后，找到合并的第一个区间，从第一个开始比较，int[0][1]>int[1][0]?
     * 有重合就合并，（合并的方式就是new一个新的数组{start,end}的形式。）不重合就加入结果集。
     *
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        List<int[]> ans = new ArrayList<>();
        Arrays.sort(intervals, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]);
        int start = intervals[0][0], end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= end) {//两个区间有重叠
                end = Math.max(end, intervals[i][1]);
            } else {
                //两个区间没有重叠，保存[start,end]，然后更新
                ans.add(new int[]{start, end});
                start = intervals[i][0];
                end = intervals[i][1];
            }
        }
        ans.add(new int[]{start, end});
        int[][] res = new int[ans.size()][2];
        for (int i = 0; i < res.length; i++) {
            res[i] = ans.get(i);
        }
        return res;
    }

    /**
     * 03无重复的最长子串
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        //思路：DFS，滑动窗口,left为最大不重复的左值下标，max是长度。i是右值下标
        if (s.length() == 0) {
            return -1;
        }
        HashMap<Character, Integer> map = new HashMap<>();
        char[] a = s.toCharArray();
        int left = 0, max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(a[i])) {
                left = Math.max(left, map.get(a[i]) + 1);//这里要用max比较是因为abba到a的时候，map.get(a)+1=0,不能让左下标变小了。所以取最大值。
            }
            map.put(a[i], i);
            max = Math.max(max, i - left + 1);// 右-左+1；
        }
        return max;
    }

    /**下面是剑指offer*************/
    /*****************************************************************************************************************************/
    //剑指05，替换空格。
    public String replaceSpace(String s) {
        StringBuilder stdu = new StringBuilder(s);
        if (s == null || s.length() == 0) {
            return s;
        }
        for (int i = 0; i < s.length(); i++) {
            char cout = s.charAt(i);
            if (cout == (' ')) {
                stdu.append("%20");
            } else {
                stdu.append(cout);
            }
        }
        return stdu.toString();
    }


    /**
     * 剑指34，二叉树中和为某一值的路径。从根结点到叶子节点的路径和为目标节点的全部返回。
     * base line 不好找。什么时候结束呢？root==null呗，咋想的。
     *
     * @param root
     * @param target
     * @return
     */
    LinkedList<List<Integer>> result = new LinkedList<>();
    LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> pathSum(TreeNode root, int target) {
        return function(root, target);
    }

    List<List<Integer>> function(TreeNode root, int target) {
        if (root == null) {
            return new ArrayList<>();
        }
        path.add(root.val);
        target = target - root.val;
        if (target == 0 && root.left == null && root.right == null) {
            result.add(new ArrayList<>(path));
        }
        function(root.left, target);
        function(root.right, target);
        path.removeLast();
        return result;
    }


    /**
     * 剑指12，矩阵路径，二维表格，回溯。dfs+剪枝叶,注意
     * 判断边界的时候，也不能等于length；找到结束条件，然后做选择递归
     * 递归完后，将最后一步操作改回去。
     * 思路，2层循环，找到元素，进行dfs，位置+-1判断是否越界。
     */
    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {

                if (dfs(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean dfs(char[][] board, String word, int i, int j, int k) {
        if (i >= board.length || i < 0 || j >= board[0].length || j < 0 || board[i][j] != word.charAt(k)) {
            return false;
        }
        if (word.length() - 1 == k) {
            return true;
        }
        //标记为
        board[i][j] = '\0';
        boolean res = dfs(board, word, i + 1, j, k + 1) || dfs(board, word, i - 1, j, k + 1)
                || dfs(board, word, i, j + 1, k + 1) || dfs(board, word, i, j - 1, k + 1);
        board[i][j] = word.charAt(k);
        return res;
    }

    /**
     *剑指13，机器人运动范围。感觉有点麻烦，等等再写
     */

    /**
     * 剑指26，寻找子树。dfs
     *
     * @param A
     * @param B
     * @return 思路：在A的每个节点都需要确认 是否和B一样(遍历A)，然后一样再判断子树
     * 判断子树：b==null 说明B完成匹配，A==null return false;
     * a.val!=b.val return false;
     */
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        return function1(A, B);
    }

    boolean function1(TreeNode A, TreeNode B) {
        if (A == null || B == null) {
            return false;
        }
        return (dfs1(A, B) || function1(A.left, B) || function1(A.right, B));

    }

    boolean dfs1(TreeNode A, TreeNode B) {
        if (B == null) return true;
        if (A == null) return false;

        return (A.val == B.val) && dfs1(A.left, B.left) && dfs1(A.right, B.right);
    }

    /**
     * 15 3数之和。O(n2)
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res=new ArrayList<>();
        if(nums==null||nums.length<3){return res;}
        int len=nums.length;
        Arrays.sort(nums);
        for(int i=0;i<nums.length;i++){
            if(nums[i]>0){break;}
            if(i>0&&nums[i]==nums[i-1]){
                continue;
            }
            int L=i+1;
            int R=len-1;
            while(L<R){
                int sum=nums[i]+nums[L]+nums[R];
                if(sum==0){
                    res.add(Arrays.asList(nums[i],nums[L],nums[R]));
                    while(L<R&&nums[L]==nums[L+1]){L++;}
                    while(L<R&&nums[R]==nums[R-1]){R--;}
                    L++;
                    R--;
                }
                else if(sum>0){R--;}
                else {L++;}
            }
        }
        return res;
    }


    /**
     * 剑指27，镜像二叉树。前序遍历，然后temp存一下左子树，left=(root.right),right=(temp);
     *
     * @param root
     * @return
     */
    public TreeNode mirrorTree(TreeNode root) {
        LinkedList list = new LinkedList();

        if (root == null) {
            return null;
        }
        TreeNode temp = root.right;
        root.left = mirrorTree(root.right);

        root.right = mirrorTree(temp);
        return root;
    }

    TreeNode function2(TreeNode root, TreeNode left, TreeNode right) {
        return null;
    }

    /**
     * 28 判断是不是对称二叉树，和27异曲同工。
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        return isSymmetric1(root, root);
    }

    boolean isSymmetric1(TreeNode root, TreeNode root1) {
        if (root == null && root1 == null) {
            return true;
        }
        if (root == null || root1 == null) {
            return false;
        }
        return root.val == root1.val && isSymmetric1(root.left, root1.right) && isSymmetric1(root.right, root1.left);
    }

    /**
     * 32 层序遍历
     *
     * @param root
     * @return
     */
    public int[] levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        ArrayList<Integer> list = new ArrayList<>();

        while (!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            if (temp != null) {
                list.add(temp.val);
                if (temp.left != null) {
                    queue.add(temp.left);
                }
                if (temp.right != null) {
                    queue.add(temp.right);
                }
            }

        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    /**
     * 32-|| 层序遍历，每层返回一个数组。
     * 32-||| 加了一个flag 之型打印出来。
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrdertwo(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return new ArrayList<>();
        Queue<TreeNode> list = new LinkedList<>();
        list.add(root);
        boolean flag = true;
        while (!list.isEmpty()) {
            int d = list.size();
            LinkedList<Integer> listc = new LinkedList<>();
            flag = !flag;
            while (d != 0) {

                TreeNode temp = list.poll();
                if (temp != null) {
                    if (flag) {
                        listc.addFirst(temp.val);

                    } else {
                        listc.add(temp.val);
                    }

                    if (temp.left != null) {
                        list.add(temp.left);
                    }
                    if (temp.right != null) {
                        list.add(temp.right);
                    }
                    d--;
                }
            }
            res.add(new ArrayList(listc));
        }
        return res;
    }


    /**
     * 分治思想，就是递归参数，不好想，用map把中序遍历(value,key)放一遍，然后根据value查找索引。
     * 07重建二叉树，根据前序和中序遍历二叉树。
     *
     * @param preorder
     * @param inorder
     * @return
     */
    //递归的时候，左右字树的边界不好确定。左子树好说，根结点是前序的下一位，
    //右子树，根结点是前序+左字树长度+1
    HashMap<Integer, Integer> map = new HashMap<>();
    int[] preorder;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        if (preorder == null) {
            return new TreeNode();
        }
        for (int i = 0; i < inorder.length; i++) {
            int i1 = inorder[i];
            map.put(i1, i);
        }
        return recur(0, 0, inorder.length - 1);
    }

    TreeNode recur(int root, int left, int right) {
        if (left > right) {
            return null;
        }
        TreeNode res = new TreeNode(preorder[root]);
        int cout = map.get(preorder[root]);  //拿到根结点在中序遍历的索引。
        res.left = recur(root + 1, left, cout - 1);
        res.right = recur(cout - left + root + 1, cout + 1, right);
        return res;
    }

    /**
     * 剑指38，字符串排列，不重复的返回所有的排序结果。经典回溯。
     *
     * @param s 用set去从（不同索引下相同位置）。visit标识每一位是不是重复走过了，毕竟每次for循环都是从0开始。
     * @return
     */
    Set<String> res = new HashSet<>();

    public String[] permutation(String s) {

        function3(s.toCharArray(), new StringBuilder(), new Boolean[s.length()]);
        return res.toArray(new String[0]);
    }

    void function3(char[] s, StringBuilder sb, Boolean[] visit) {
        if (s.length == sb.length()) {
            res.add(sb.toString());
            return;
        }
        for (int i = 0; i < s.length; i++) {
            if (visit[i]) {
                sb.append(s[i]);
                visit[i] = !visit[i];
                function3(s, sb, visit);
                sb.deleteCharAt(sb.length() - 1);
                visit[i] = !visit[i];

            }
        }
    }


    //二分查找，不是最优版本，细节狂魔啊，左右边界搞不清楚。

    /**
     * hot 34我这个版本不是最优解。应该2次二分，下次看
     *
     * @param d
     * @param target
     * @return
     */
    public int[] searchRange(int[] d, int target) {

        if (d.length == 0 || target > d[d.length - 1]) {
            return new int[]{-1, -1};
        }
        return function5(d, target);

    }

    int[] function5(int[] nums, int target) {
        int min = -1;
        int max = nums.length;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                int temp = mid;
                min = max = temp;
                while (nums[temp--] == target) {
                    min = mid;
                }
                while (nums[temp++] == target) {
                    max = mid;
                }
                return new int[]{min, max};
            } else if (nums[mid] > target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return new int[]{-1, -1};
    }
    //https://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247485044&idx=1&sn=e6b95782141c17abe206bfe2323a4226&scene=21

    /**
     * 剑指50,找到字符串中出现次数为1并且是第一次出现的元素。
     * 要2次遍历，第一次遍历是放入有序map，第二次遍历有序map(LinkedHashMap)，返回value==1的元素。
     *
     * @param s
     */
    public char firstUniqChar(String s) {
        Map<Character, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char cout = s.charAt(i);
            map.put(cout, map.getOrDefault(cout, 0) + 1);
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }

        return ' ';
    }

    /**
     * 剑指53，二分变种，寻找target出现多少次。
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        //todo 53二分变种
        return 0;
    }

    /**
     * 剑指 Offer 54. 二叉搜索树的第 k 大节点
     *
     * @param root
     * @param k
     * @return 我的想法是，右子树比左大，中序倒置，就是按照大小排序了，然后再根据k大小取树。
     */
    int s = Integer.MIN_VALUE;
    int k;

    public int kthLargest(TreeNode root, int k) {
        this.k = k;
        find(root);
        return s;
    }

    void find(TreeNode root) {
        if (root == null) return;
        find(root.right);
        if (k == 0) return;//已经找到，提前终止。
        k = k - 1;
        if (k == 0) s = root.val;
//        s=root.val;
        find(root.left);
    }

    /**
     * 剑指68-||最大公共祖先，2个节点，p,q寻找最近公共祖先。
     * 思路：这个重点是，2个节点找到后，相互位置怎么确定，你知道二叉树有先序遍历，后序遍历，中序遍历。
     * 先序遍历到某个节点后，如果这个节点是p或者q，或者二者都是，就直接返回，如果是null就return null，然后递归左右子树。
     * 递归完后，返回的左右子树left，right，如果只有一个有结果就直接返回，比如left=p,那q肯定是left的左右子树，不管在哪，都返回q，说明在同一侧，都有结果就是root，在两侧，都没结果就是null
     * 最大公共节点。
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if (root == q || root == p || root == null) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null && right == null) {
            return null;
        }
        if (left != null && right != null) {
            return root;
        }
        if (left != null) {
            return left;
        } else {
            return right;
        }
    }

    /**
     * 剑指68-｜最大公共祖先，这个是二叉搜索树,直接比较2个节点val和root.val就能判断位置。上面那个题比较难。
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val < p.val && root.val < q.val) {
            return lowestCommonAncestor1(root.right, p, q);
        }
        if (root.val > p.val && root.val > q.val) {
            return lowestCommonAncestor1(root.left, p, q);
        }
        return root;
    }
}

/**
 * 8个排序。
 */
class eightsorts {
    //冒泡排序，这里优化了一下，没有比较直接终止。说明都排好序了。O(n2),O(n)
    void bubbleSort(int nums[]) {
        int len = nums.length;
        boolean flag = true;
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                if (nums[i] > nums[j]) {
                    int swap = nums[i];
                    nums[i] = nums[j];
                    nums[j] = nums[i];
                    flag = false;
                }
            }
            if (flag) break;
        }
    }


}

/**
 * LRU 终于来了。要实现双向链表的api
 */
class LRUCache {
    private HashMap<Integer, Node> map;
    private DoubleList cache;
    private int cap;

    public LRUCache(int capacity) {
        this.cap=capacity;
        map=new HashMap<>();
        cache=new DoubleList();
    }

    public int get(int key) {
        if(map.containsKey(key)){
            Node node=map.get(key);
            put(key,node.val);
            return node.val;
        }
        else {return -1;}
    }

    public void put(int key, int value) {
        Node node=new Node(key,value);
        if(map.containsKey(key)){
            Node temp=map.get(key);
            cache.remove(temp);
            map.put(key,node);
            cache.addFirst(node);
        }
        else {

            if(cache.size()+1>cap){
              Node temp1=  cache.removeLast();
              map.remove(temp1.key);
            }
            cache.addFirst(node);
            map.put(key,node);
        }
    }
}

/**
 * node, map 放<key,node>，和双向链表。
 */
class Node {
    public int key, val;
    public Node next, prev;
    public Node(int k, int v) {
        this.key = k;
        this.val = v;
    }
}
/**
 * 双向链表。
 */
class DoubleList {

    private Node head, tail;
    private int size;

    public void addFirst(Node node) {
        if(head==null){
            head=tail=node;
        }
       else {
            node.next=head;
            head.prev=node;
            head=node;
        }
       size++;
    }


    public void remove(Node node) {
         if(head==node&&node==tail){
            head=null;
            tail=null;
        }
        else if(node==head){
            head=head.next;
            head.prev=null;
        }
        else if(node==tail){
            tail=tail.prev;
            tail.next=null;
        }else {
            node.prev.next=node.next;
            node.next.prev=node.prev;
         }
        size--;

    }

    public Node removeLast() {
        Node res=tail;
        remove(res);
        return res;
    }

    public int size() {
        return size;
    }
}
/**
 * 09 双栈实现队列,pop的时候，如果第二个栈空的，要把第一个栈清空。全部放入第二个栈。
 */
class StackQueue {
    Stack stack = new Stack();
    Stack stack1 = new Stack();

    void push(TreeNode k) {
        stack.push(k);

    }

    void pop() {
        if (stack1.isEmpty()) {
            if (stack.isEmpty()) {
                return;
            }
            while (!stack.isEmpty()) {
                stack1.push(stack.pop());
            }

        }
        stack1.pop();
    }
}

/**
 * 定义TreeNode{}
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}


