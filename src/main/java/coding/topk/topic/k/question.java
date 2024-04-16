package coding.topk.topic.k;

import java.util.PriorityQueue;

public class question {
    public int[] findKthLargest(int[] nums, int k) {
        //构造函数，利用lambda表达式构造大顶堆 (a,b) -> b-a
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, (o1, o2) -> o1 - o2);
        for (int num : nums) {
            maxHeap.offer(num);
        }

        maxHeap.forEach(o -> {
            System.out.print(o + "\t");
        });
        for (int i = 0; i < nums.length; i++) {
            nums[i] = maxHeap.poll();
        }
        for (int num : nums) {
            System.out.print(num + "\t");
        }
        return nums;
    }

    //堆排序
    int function(int[] nums, int k) {
        buildHeap(nums, k);
        for (int i = k; i < nums.length; i++) {
            if (nums[i] > nums[0]) {
                swap(i, 0, nums);
                adjustHeap(nums, 0, k);
            }

        }
        return nums[0];
    }

    int func1(int[] nums) {
        //建堆。
        for (int i = (nums.length - 1) / 2; i >= 0; i--) {
            adjustHeap1(nums, i, nums.length);
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            int temp = nums[i];
            nums[i] = nums[0];
            nums[0] = temp;
            adjustHeap1(nums, 0, i);
        }
        return 1;
    }

    void adjustHeap1(int[] nums, int parent, int len) {
        int temp = nums[parent];
        for (int i = parent * 2 + 1; i < len; i = i * 2 + 1) {
            if (i + 1 < len && nums[i] < nums[i + 1]) {
                i++;
            }
            if (temp < nums[i]) {
                nums[parent] = nums[i];
                parent = i;
            } else {
                break;
            }
        }
        nums[parent] = temp;
    }

    void buildHeap(int[] nums, int k) {
        for (int i = k / 2 - 1; i >= 0; i--) {
            adjustHeap(nums, i, k);
        }

    }

    public void swap(int i, int j, int[] nums) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * 大顶堆
     *
     * @param nums   建堆
     * @param parent 父节点
     * @param len    长度
     */

    void adjustHeap(int[] nums, int parent, int len) {
        int temp = nums[parent];
        //j==左孩子
        for (int j = parent * 2 + 1; j < len; j = j * 2 + 1) {
            //如果有右孩子并且比左孩子大，选右孩子
            if (j + 1 < len && nums[j + 1] < nums[j]) {
                j++;
            }
            //父节点比子节点小，换值，选取孩子结点的左孩子结点,继续向下筛选
            if (nums[j] < temp) {
                nums[parent] = nums[j];
                parent = j;
            }
            //
            else {
                break;
            }
        }
        nums[parent] = temp;
    }
}
