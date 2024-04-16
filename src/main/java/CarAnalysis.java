class Solution {
    // 2个指针，维护滑动窗口，right移动，记录count值，count值为数组内部成绩
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        //同样排除k为1的情况比如  [1,1,1] k=1
        if (k <= 1) {
            return 0;
        }
        int left = 0;
        int right = 0;
        //创建一个变量记录路上的乘积
        int count = 1;
        //记录连续数组的组合个数
        int res = 0;
//        你的代码ok 的

  // 没有问题啊，你的代码ok 的
        //todo 如果乘积大于 k 了，则左指针右移，注意此处用的是 while 来使左指针右移，因为实际情况可能是：
        // 右指针最后右移一次指向了一个比较大的数使得 mul 不小于目标值，此时左指针需要右移多次才能使得 mul 刚小于 k；
        //todo 华东窗口，
        //最后用 ans 记录 mul 小于 k 时的数组组合
        //
        //作者：ren-feiye
        //链接：https://leetcode.cn/problems/subarray-product-less-than-k/solution/jian-dan-yi-dong-xiang-xi-zhu-jie-shuang-jvy3/
        //来源：力扣（LeetCode）
        //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
        //用右指针遍历整个数组，每次循环右指针右移一次
        while(right<nums.length) {
            //记录乘积
            count *= nums[right];
            //当大于等于k，左指针右移并把之前左指针的数除掉
            while (count >= k) {
                count /= nums[left];
                left++;
            }
            //每次右指针位移到一个新位置，应该加上 x 种数组组合：
            res += right - left + 1;
            //todo ①right必须在子数组中存在，②并且子数组是连续的，③[left,right]的长度是right-left+1，
            // 记为n。接着我枚举一下[left, right]的子数组，可以分别是：长度为1的[right], 长度为2的[right-1,
            // right]，一直到长度为n的[left, left+1, ..., right-1, right] 这样子的n个子数组。
            //右指针右移
            right++;
        }
        return res;
    }
    // todo 左右指针起始均在位置 0 ；用右指针遍历数组，每次循环中右指针右移一次；
    //
    //移动过程中纪录从左指针到右指针路上的连续数的乘积为 mul；
    //
    //如果乘积大于 k 了，则左指针右移，注意此处用的是 while 来使左指针右移，因为实际情况可能是：右指针最后右移一次指向了一个比较大的数使得 mul 不小于目标值，此时左指针需要右移多次才能使得 mul 刚小于 k；
    //
    //最后用 ans 记录 mul 小于 k 时的数组组合
    //

}


//todo //输入：nums = [10,5,2,6], k = 100
////输出：8
////解释：8 个乘积小于 100 的子数组分别为：[10]、[5]、[2],、[6]、[10,5]、[5,2]、[2,6]、[5,2,6]。
//todo 需要注意的是 [10,5,2] 并不是乘积小于 100 的子数组。
//todo 如果饱含等于 就while(mul>k)改成while(mul>=k)
//不包含就不是最开始的意思