package src.main.java.coding.onetoHundred;

import java.util.*;

public class codevolcano {
    public static void main(String[] args) {
//        [12,24,8,32]
//        [13,25,32,11]
//        int[] s = advantageCount(new int[]{12, 24, 8, 32}, new int[]{13, 25, 32, 11});
//        System.out.println(s);
        //干不过，就用nums2中的上等马的下标当作nums1中的下等马的下标

        new codevolcano().generateTrees(3);

    }

    public List<TreeNode> generateTrees(int n) {
        List<TreeNode> li = new ArrayList();
        if (n == 0) return li;
        return func(1, n);
    }

    List<TreeNode> func(int left, int right) {
        List<TreeNode> res=new ArrayList<>();
        if(left>right){
            //不加null会导致，下面循环都无法进行。
            res.add(null);
            return res;
        }
        //每个节点拿到所有的可能。
        for (int i = left; i <=right; i++) {
            List<TreeNode> listLeft=func(left,i-1);
            List<TreeNode> listRight=func(i+1,right);
            for (TreeNode treeNode : listLeft) {
                for (TreeNode node : listRight) {
                    TreeNode now=new TreeNode(i);
                    now.left=treeNode;
                    now.right=node;
                    res.add(now);
                }
            }
        }
        return res;
    }



//    public List<List<Integer>> verticalOrder(TreeNode root) {
//        if (root.left != null) verticalOrder(root.left);
//        list.add(root.val);
//
//    }

    public static int[] advantageCount(int[] nums1, int[] nums2) {
        //田忌赛马，1里面的最小值，和2的最小值比，大于直接拿下，小于就比较最大值。
        // 从1里面找到最小大于2的数字，然后排列。贪心应该是从2里面比较小的开始比较。
        int[][] index = new int[nums2.length][2];
        for (int i = 0; i < nums2.length; i++) {
            index[i] = new int[]{nums2[i], i};
        }
        // 1 小到大， 2 小到大。
        Arrays.sort(nums1);
        Arrays.sort(index, (o1, o2) -> o1[0] - o2[0]);
        int left = 0;
        int right = nums2.length - 1;
        int res[] = new int[nums2.length];
        //
        for (int num : nums1) {
            if (num > index[left][0]) {
                res[index[left][1]] = num;
                left++;
            } else {
                res[index[right][1]] = num;
                right--;
            }
        }
        return res;
    }


    public int longestWPI(int[] hours) {
        //最大长度。找出所有可能的长度。
        for (int i = 0; i < hours.length; i++) {
            if (hours[i] > 8) hours[i] = 1;
            else hours[i] = -1;
        }
        int res = 0;
        int[] arr = new int[hours.length + 1];
        Stack<Integer> stack = new Stack();
        for (int i = 1; i < arr.length; i++) {
            arr[i] = arr[i - 1] + hours[i - 1];
            System.out.print(arr[i] + " ");
        }
        for (int i = 0; i < arr.length; i++) {
            while (stack.isEmpty() || arr[i] < arr[stack.peek()]) {
                stack.push(i);
            }
        }
        //找的是子序列大于
        for (int i = arr.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() || arr[stack.peek()] <= arr[i]) {
                res = Math.max(stack.pop() - i, res);
            }
        }
        return res;
    }

    public int[] getModifiedArray(int length, int[][] updates) {
        int arr[] = new int[length];
        for (int i = 0; i < updates.length; i++) {
            int start = updates[i][0];
            int end = updates[i][1];
            int len = updates[i][2];
            arr[start] += len;
            if (end + 1 < length) arr[end + 1] -= len;
        }
        for (int i = 1; i < arr.length; i++) {
            arr[i] += arr[i - 1];
        }
        return arr;
    }

    public int[] constructArr(int[] a) {
        int[] le = new int[a.length];
        le[0] = a[0];
        for (int i = 0; i < a.length; i++) {
            if (i > 0) le[i] = le[i - 1] * a[i];
            // System.out.print(le[i]+" ");
        }
        int[] re = new int[a.length];
        re[0] = a[a.length - 1];

        for (int i = 0; i < re.length; i++) {
            if (i > 0) re[i] = a[a.length - 1 - i] * re[i - 1];
            System.out.print(re[i] + " ");
        }
        int[] res = new int[a.length];
        res[0] = re[re.length - 2];
        res[res.length - 1] = le[le.length - 2];
        for (int i = 1; i < res.length - 1; i++) {
            res[i] = le[i - 1] * re[i + 1];
        }
        return res;
    }

    public int longestOnes(int[] A, int K) {

        int N = A.length;
        int res = 0;
        int left = 0, right = 0;
        int zeros = 0;
        while (right < N) {
            if (A[right] == 0)
                zeros++;
            while (zeros > K) {
                if (A[left++] == 0)
                    zeros--;
            }
            res = Math.max(res, right - left + 1);
            right++;
        }
        return res;


    }

    public static int maxSubArrayLen(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > 0) nums[i] += nums[i - 1];
            if (map.containsKey(nums[i] - k)) {
                res = Math.max(res, i - map.get(nums[i] - k));
            }
            if (!map.containsKey(nums[i])) map.put(nums[i], i);
        }
        return res;
    }

    public int maxWidthRamp(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            while (stack.isEmpty() || nums[stack.peek()] > nums[i]) {
                stack.push(i);
            }
        }
        int res = 0;
        for (int length = nums.length - 1; length >= 0; length--) {
            while (!stack.isEmpty() && nums[length] > nums[stack.peek()]) {
                res = Math.max(res, length - stack.peek());
                stack.pop();
            }
        }
        return res;

    }

    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        List<int[]> list = new ArrayList();
        int l = 0, r = 0;
        while (l < firstList.length && r < secondList.length) {
            int left = Math.max(firstList[l][0], secondList[r][0]);
            int right = Math.min(firstList[l][1], secondList[r][1]);
            if (left <= right) list.add(new int[]{left, right});
            if (firstList[l][1] > secondList[r][1]) {
                r++;
            } else l++;
        }
        return list.toArray(new int[list.size()][2]);
    }

    public static int lengthOfLongestSubstring(String s) {
        int res = 0;
        int left = 0;
        HashMap<Character, Integer> map = new HashMap();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                res = Math.max(res, i - left + 1);
                //left 是不重复元素的第一个元素，abb
                left = Math.max(left, map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i), i);
            res = Math.max(res, i - left + 1);

        }
        return res;
    }

    public static String minWindow(String s, String t) {
        HashMap<Character, Integer> window = new HashMap();
        HashMap<Character, Integer> needs = new HashMap();
        int res = Integer.MAX_VALUE;
        int needLen = 0;
        int left = 0;
        int start = 0;
        for (int i = 0; i < t.length(); i++) {
            needs.put(t.charAt(i), needs.getOrDefault(t.charAt(i), 0) + 1);
        }
        for (int i = 0; i < s.length(); i++) {
            Character temp = s.charAt(i);
            window.put(temp, window.getOrDefault(temp, 0) + 1);
            if (window.get(temp).equals(needs.get(temp))) {
                needLen++;
            }
            while (needLen == needs.size()) {
                if (i - left < res) {
                    res = i - left;
                    start = left;
                }
                if (needs.containsKey(s.charAt(left)) && window.get(s.charAt(left)).equals(needs.get(s.charAt(left))))
                    needLen--;
                window.put(s.charAt(left), window.get(s.charAt(left)) - 1);
                left++;
            }

        }
        return res == Integer.MAX_VALUE ? "" : s.substring(start, start + res + 1);
    }


    public static int subarraySum(int[] nums, int target) {
        int res = Integer.MAX_VALUE;
        int sum = 0;
        int left = 0;
        for (int i = 0; i < nums.length; i++) {

            sum = sum + nums[i];
            while (target <= sum) {
                res = Math.min(res, i - left);
                sum = sum - nums[left];
                left++;
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;

    }

    public static int[] corpFlightBookings(int[][] bookings, int n) {
        int[] ans = new int[n];
        for (int[] booking : bookings) {
            ans[booking[0] - 1] += booking[2];
            if (booking[1] < n) {
                ans[booking[1]] -= booking[2];
            }
        }
        for (int i = 1; i < n; i++) {
            ans[i] += ans[i - 1];
        }
        return ans;

    }

    public int minMeetingRooms(int[][] intervals) {
        int res = 0;
        int arr[][] = new int[intervals.length * 2][2];

        int i = 0;
        for (int[] interval : intervals) {
            arr[i++] = new int[]{interval[0], 1};
            arr[i++] = new int[]{interval[1], -1};

        }
        Arrays.sort(arr, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            } else return o1[0] - o2[0];
        });
        int cur = 0;
        for (int[] ints : arr) {
            cur += ints[1];
            res = Math.max(cur, res);
        }
        return res;
    }

    public boolean exist1(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (contain(board, word, i, j, 0)) return true;
            }
        }
        return false;
    }

    boolean contain(char[][] board, String word, int i, int j, int k) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length
                || board[i][j] != word.charAt(k) || board[i][j] == '-') {
            return false;
        }
        if (k == word.length() - 1) return true;

        board[i][j] = '-';
        boolean res = contain(board, word, i + 1, j, k + 1) ||
                contain(board, word, i - 1, j, k + 1) ||
                contain(board, word, i, j + 1, k + 1) ||
                contain(board, word, i, j - 1, k + 1);
        board[i][j] = word.charAt(k);
        return res;

    }

    public int[] sortArray(int[] nums) {
        quicksort(nums, 0, nums.length - 1);
        return nums;
    }

    void quicksort(int[] nums, int left, int right) {
        if (left < right) {
            int a = adjust(nums, left, right);
            quicksort(nums, left, a - 1);
            quicksort(nums, a + 1, right);
        }

    }

    int adjust(int[] nums, int left, int right) {
        int base = new Random().nextInt(right - left + 1) + left;
        int target = nums[base];
        swap(nums, base, right);
        int l = left, r = right;
        while (l < r) {
            while (l < r && nums[l] <= target) l++;
            while (l < r && nums[r] >= target) r--;

            if (l < r) swap(nums, l, r);
        }
        swap(nums, r, right);
        return l;
    }

    public void sortColors(int[] nums, int left, int right) {
        int l = left;
        int r = right;
        int base = nums[l];
        while (l < r) {
            while (l < r && nums[r] >= base) {
                r--;
            }
            if (l < r) {
                nums[l] = nums[r];
            }
            while (l < r && nums[l] <= base) {
                l++;
            }
            if (l < r) {
                nums[r] = nums[l];
            }
        }
        nums[l] = base;
        sortColors(nums, left, l - 1);
        sortColors(nums, l + 1, r);
    }

    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < dp[0].length; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }
        for (int i = 1; i < dp.length; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
            for (int j = 1; j < dp[0].length; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                System.out.println(dp[i][j]);
            }
        }
        return dp[m - 1][n - 1];
    }

    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = 1;
            for (int j = 0; j < dp[0].length; j++) {
                dp[0][j] = 1;
                if (i == 0 || j == 0) continue;
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    public boolean canJump(int[] nums) {
        int maxroad = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (i > maxroad) {
                return false;
            } else maxroad = Math.max(maxroad, i + nums[i]);
        }
        return maxroad >= nums[nums.length - 1];
    }

    /**
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int sum = Integer.MIN_VALUE;
        int res = Integer.MIN_VALUE;
        for (int num : nums) {
            if (sum >= 0) {
                sum += num;
            } else sum = num;
            res = Math.max(res, sum);
        }
        return res;
    }

    /**
     * 字母异位词
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        List<char[]> characters = new ArrayList<>();
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] temp = str.toCharArray();
            Arrays.sort(temp);
            characters.add(temp);
            String temp1 = Arrays.toString(temp);
            if (map.containsKey(temp1)) {
                map.get(temp1).add(str);
            } else {
                List<String> s = new ArrayList<>();
                s.add(str);
                map.put(temp1, s);
            }
        }
        List<List<String>> res = new ArrayList<>(map.values());
        return res;

    }

    /**
     * 48 旋转图像
     * 先中心翻转，再左右翻转。
     */

    public void rotate(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i; j < matrix[0].length; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        for (int i = 0; i < matrix.length / 2; i++) {
            for (int j = 0; j < matrix[0].length / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][matrix[0].length - 1 - j];
                matrix[i][matrix[0].length - 1 - j] = temp;
            }
        }

    }

    /**
     * 08 字符串转整数，简单化了，第一位判断正负，后面的正负和空格碰到就丢掉。
     *
     * @param s
     * @return
     */
    public int myAtoi(String s) {
        int status = 0;//1:+.  2:-
        int res = 0;
        for (char i : s.toCharArray()) {
            if (status == 0 && i == ' ') {
                continue;
            } else if (status == 0 && i == '+') {
                status = 1;
            } else if (status == 0 && i == '-') {
                status = 2;
            } else if (i >= '0' && i <= '9') {
                if (status == 0) {
                    status = 1;
                }
                int temp = i - '0';
                //下面这个判断结合res=res*10+temp看，判断上一次计算是否溢出。
                if (res > (Integer.MAX_VALUE - temp) / 10) {

                    if (status == 1) return Integer.MAX_VALUE;
                    if (status == 2) return Integer.MIN_VALUE;
                }
                res = res * 10 + temp;
            } else {
                break;
            }
        }
        return status % 2 == 1 ? res : -res;

    }


    /**
     * 19. 删除链表的倒数第 N 个结点
     * 注意点：fast是空的时候，说明删除头节点。
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode fast = head, slow = head;
        while (n != 0) {
            fast = fast.next;
            n--;
        }
        if (fast == null) {
            return head.next;
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return head;

    }

    /**
     * 41 缺失的第一个正数。原地置换，比如索引1位置放2，所以要判断nums[i]元素是否==nums[nums[i]-1]，不一样就换。
     * 然后再遍历一遍，让nums[i]-1是否等于i，不想等就是缺少i+1这个元素。
     *
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            while (nums[i] > 0 && nums[i] <= n && nums[i] != nums[nums[i] - 1]) {
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;

            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] - 1 != i) {
                return i + 1;
            }
        }
        return n + 1;
    }

    /**
     * 接雨水，单调栈。栈只能放索引下标不能放元素，因为下标要拿出来计算宽度。
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        if (height == null || height.length <= 2) {
            return 0;
        }
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
                int top = stack.pop();
                if (stack.isEmpty()) {
                    continue;
                }
                int left = stack.peek();
                int width = i - left - 1;
                int hegiht = Math.min(height[left], height[i]) - height[top];
                res = res + width * hegiht;

            }
            stack.push(i);
        }
        return res;
    }

    /**
     * 55螺旋矩阵。生成顺时针二维数组。
     *
     * @param n
     * @return
     */
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][];

        //鸽了
        return new int[][]{};
    }

    /**
     * 77 组合+剪枝优化。
     */
    List<List<Integer>> res = new LinkedList<>();
    LinkedList<Integer> list = new LinkedList<>();

    public List<List<Integer>> combine(int n, int k) {
        function(n, k, 1);
        return res;
    }

    void function(int n, int k, int j) {
        if (list.size() == k) {
            res.add(new LinkedList<>(list));
            return;
        }
        for (int i = j; i <= n - (k - list.size()) + 1; i++) {
            list.add(i);
            function(n, k, i + 1);
            list.removeLast();
        }
    }


    int ans = Integer.MIN_VALUE;

    /**
     * 124 hard 二叉树的最大路径和。
     * 一个节点作为最大路径上面的节点只有两种情况。
     * 1.自己+左右子树就是最大路径。
     * 2.自己或者自己带着左右子树其中一个跟着父节点的最大路径
     *
     * @param root
     * @return
     */
    public int maxPathSum(TreeNode root) {
        function(root);
        return ans;
    }

    int function(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = Math.max(function(root.left), 0);//这边要和0比较，小于0肯定不是，只要当前节点，不要左右节点。
        int right = Math.max(function(root.right), 0);
        ans = Math.max(ans, root.val + left + right);

        return Math.max(left, right) + root.val;
    }


    /** 33. 搜索旋转排序数组
     *  二分变种了，将旋转数组重新排序了，找mid
     */
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
        Queue<TreeNode> queue = new LinkedList<>();
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


    //82. 删除排序链表中的重复元素 II,

    /**
     * 注意点：
     * 1. 头节点会被删除，所以要前区节点，然后用node.next作为当前节点.
     * 2. 不要老是想着双节点，这里一个节点就可以，写的有点复杂，其实都是有必要的。
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode slow = pre;
        while (slow.next != null && slow.next.next != null) {
            if (slow.next.val == slow.next.next.val) {
                int x = slow.next.val;//这里要保留值，不然到下一个节点了找不到原节点值判断了。
                while (slow.next != null && slow.next.val == x) {
                    slow.next = slow.next.next;//这里是把重复节点删除了（包括重复自身）
                }
            } else {
                slow = slow.next;
            }

        }
        return pre.next;
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
        int start = intervals[0][0];
        int end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= end) {
                end = Math.max(end, intervals[i][1]);
            } else {
                ans.add(new int[]{intervals[i][0], intervals[i][1]});
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

                if (fs(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean fs(char[][] board, String word, int i, int j, int k) {
        if (i >= board.length || i < 0 || j >= board[0].length || j < 0 || board[i][j] != word.charAt(k)) {
            return false;
        }
        if (word.length() - 1 == k) {
            return true;
        }
        //标记为
        board[i][j] = '\0';
        boolean res = fs(board, word, i + 1, j, k + 1) || fs(board, word, i - 1, j, k + 1)
                || fs(board, word, i, j + 1, k + 1) || fs(board, word, i, j - 1, k + 1);
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
     * @return 思路：在A的每个节点都需要确认 是否和B一样(遍历A)，然后一样再判断子树,
     * 判断子树：b==null 说明B完成匹配，A==null return false;
     * 你可能想在function1里面找到A和B相同的地方然后执行dfs1，但是这个结果要保存下来和dfs1做｜｜运算，不能直接返回。
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
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return res;
        }
        int len = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                break;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int L = i + 1;
            int R = len - 1;
            while (L < R) {
                int sum = nums[i] + nums[L] + nums[R];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[L], nums[R]));
                    while (L < R && nums[L] == nums[L + 1]) {
                        L++;
                    }
                    while (L < R && nums[R] == nums[R - 1]) {
                        R--;
                    }
                    L++;
                    R--;
                } else if (sum > 0) {
                    R--;
                } else {
                    L++;
                }
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
            res.add(new ArrayList<>(listc));
        }
        return res;
    }


    /**
     * 分治思想，就是递归参数，不好想，用map把中序遍历(value,key)放一遍，然后根据value查找索引。
     * 07重建二叉树，根据前序和中序遍历二叉树。
     * 麻烦在根索引的位置。
     *
     * @param preorder
     * @param inorder
     * @return
     */
    //递归的时候，左右字树的边界不好确定。左子树好说，根结点是前序的下一位，
    //右子树，根结点是前序+左字树长度+1 ，
    HashMap<Integer, Integer> map = new HashMap<>();
    int[] preorder;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        if (preorder == null) {
            return new TreeNode();
        }
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return recur(0, 0, inorder.length - 1);
    }

    /**
     * @param root  根索引
     * @param left  左边界
     * @param right 右边界。
     * @return
     */
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
    Set<String> res1 = new HashSet<>();

    public String[] permutation(String s) {

        function3(s.toCharArray(), new StringBuilder(), new Boolean[s.length()]);
        return res1.toArray(new String[0]);
    }

    void function3(char[] s, StringBuilder sb, Boolean[] visit) {
        if (s.length == sb.length()) {
            res1.add(sb.toString());
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
     * hot 34多个版本都会了，左右边界的问题,注意mid=right+left/2 是偏左的，4个元素，mid=第二个元素，所以找右边界的时候要偏右
     * 左边界：1。left<right 当num[mid]>=target :mid=right; 最后拿left当结果判断什么的。
     * 2。left<=right 当num[mid]>=target :mid=right-1;最后判断一下left越界或者是否不等于targt这种情况-1，剩下返回left；
     * 右边界：1。left<right, 当num[mid]<=target (mid=left+(right-left)/2+1):mid=left; 最后拿right当结果判断什么的。这里+1为了
     * 防止while不会结束。left=mid=right
     * 2。left<=right 当num[mid]<=target :mid=left+1;最后判断一下right<0越界或者是否不等于targt这种情况-1，剩下返回right；
     * <p>
     * 2版本容易写。1版本适用性强。我写的1版本，反正会。
     *
     * @param d
     * @param target
     * @return
     */
    int min = -1;
    int max = -1;

    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0 || target > nums[nums.length - 1]) {
            return new int[]{-1, -1};
        }
        return function(nums, target);

    }

    int[] function(int[] nums, int target) {
        //找左边界
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        if (nums[left] == target) {
            min = left;
        } else {
            min = -1;
        }
        //如果左边界没找到或者在最大索引处，直接返回就是了不用找右边界。
        if (min == -1) {
            return new int[]{-1, -1};
        }
        if (min == nums.length - 1 || nums[min + 1] != target) {
            return new int[]{min, min};
        }
        left = 0;
        right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2 + 1;
            if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        if (nums[right] == target) {
            max = right;
        } else {
            max = -1;
        }
        return new int[]{min, max};
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
     * 剑指 Offer 52. 两个链表的第一个公共节点
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode A = headA;
        ListNode B = headB;
        while (A != B) {//不相交跳出循环，就用null，所以是判断A!=null而不是A.next！=null
            A = A != null ? A.next : headB;
            B = B != null ? B.next : headA;
        }
        return A;
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
        LinkedList<Object> list = new LinkedList<>();
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
 * LRU 终于来了。要实现双向链表的api，Doublelist提供api，node创建结构。
 */
//class LRUCache {
//    private HashMap<Integer, Node> map;
//    private DoubleList cache;
//    private int cap;
//
//    public LRUCache(int capacity) {
//        this.cap=capacity;
//        map=new HashMap<>();
//        cache=new DoubleList();
//    }
//
//    public int get(int key) {
//        if(map.containsKey(key)){
//
//            cache.remove(map.get(key));
//            cache.addFirst(map.get(key));
//
//            //上面👆这2行可以直接用下面这2行替换。
//            Node node=map.get(key);
//            put(key,node.val);
//
//
//            return node.val;
//        }
//        else {return -1;}
//    }
//
//    public void put(int key, int value) {
//        Node node=new Node(key,value);
//        if(map.containsKey(key)){
//            Node temp=map.get(key);
//            cache.remove(temp);
//            map.put(key,node);
//            cache.addFirst(node);
//        }
//        else {
//
//            if(cache.size()+1>cap){
//              Node temp1=  cache.removeLast();
//              map.remove(temp1.key);
//            }
//            cache.addFirst(node);
//            map.put(key,node);
//        }
//    }
//}

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
        if (head == null) {
            head = tail = node;
        } else {
            node.next = head;
            head.prev = node;
            head = node;
        }
        size++;
    }


    public void remove(Node node) {
        if (head == node && node == tail) {
            head = null;
            tail = null;
        } else if (node == head) {
            head = head.next;
            head.prev = null;
        } else if (node == tail) {
            tail = tail.prev;
            tail.next = null;
        } else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
        size--;

    }

    public Node removeLast() {
        Node res = tail;
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
    Stack<TreeNode> stack = new Stack<>();
    Stack<TreeNode> stack1 = new Stack<>();

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


