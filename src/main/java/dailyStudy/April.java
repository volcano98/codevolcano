package src.main.java.dailyStudy;

public class April {
    /**
     * 不要只关心对当前元素的操作，对非目标元素操作就行
     *
     * @题目：元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。（注意审题）
     */
    public int removeElement(int[] nums, int val) {
        if(nums.length==0){
            return 0;
        }//1 2 3 4   5
        int start = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i]!=val){
                nums[start++]=nums[i];
            }
        }
        System.out.println(nums);
        return  start;
    }
}
