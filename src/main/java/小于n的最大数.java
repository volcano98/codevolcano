import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class 小于n的最大数 {


    public static void main(String[] args) {


//        int[] nums = new int[]{7, 6, 8, 5};
//        //1.最后一位不符合，返回四位数
//        System.out.println("6879 --> " + getMaxNum(nums, "6879"));
//        //2.所有位不符合，返回三位数
//        System.out.println("5555 --> " + getMaxNum(nums, "5555"));
//        //3.中间位不符合，返回四位数
//        System.out.println("5698 --> " + getMaxNum(nums, "5698"));
//        //4.相等
//        System.out.println("4827 --> " + getMaxNum(nums, "4827"));
//        //5.不存在更小的数，返回-1
//        System.out.println("1 --> " + getMaxNum(nums, "1"));
    }

    //输出小于target的最大数，用nums中数字来拼凑，可以重复使用
    public static int getMaxNum(int[] nums, String digit) {
        StringBuilder sb = new StringBuilder();
        //用于标记之后位是否可以直接取最大值
        boolean flag = false;
        //先对nums排序
        Arrays.sort(nums);
        //从目标数字的第一个字符开始，一个一个取
        for (int i = 0; i < digit.length(); i++) {
            if (flag) {
                sb.append(nums[nums.length - 1]);
                continue;
            }
            //当前位的数字
            int target = digit.charAt(i) - '0';
            //已经走到了最后一轮，则本轮不能选相等的，找小于等于target-1的数即可
            if (i == digit.length() - 1) {
                target = target - 1;
            }
            //搜索nums中最后一个小于等于target的数
            int temp = search(nums, target);
            //1.当前位不存在小于等于target的数
            //2.当前位存在小于等于target的数，再具体看是等于还是小于
            if (temp == -1) {
                //两种情况：
                //1.1 现在正处于第一位，则说明后面的位直接取最大值即可，当前位不取值
                //1.2 现在处于后面的位，则需要依次回溯前面位，继续往小了取
                if (i == 0) {
                    flag = true;
                } else {
                    //从上一位开始，取一个更小的数
                    int index = i - 1;
                    int newTemp = -1;
                    while (newTemp == -1 && index >= 0) {
                        //上一位继续取一个比之前取的数小一点的数
                        newTemp = search(nums, sb.charAt(index) - '0' - 1);
                        //由于上一位必定要换一个数填，则直接从sb中删除
                        sb.deleteCharAt(index);
                        index--;
                    }

                    //前面所有位都找不到更小的数了，则最终结果就是除了第一位，后面位全部取最大值
                    if (newTemp == -1) {
                        //简化处理，i回到第一位，并触发flag，后面所有位填最大值
                        i = 0;
                        flag = true;
                        continue;
                    }

                    //此时说明回溯时某一位找到更小的值了，则此时后面所有位都应该是最大值，先把这个值加入sb
                    sb.append(newTemp);
                    //从找到更小数的这位的下一位开始，一直到i，都赋成最大值。由于之前index多减了一次，所以这里是index+2
                    for (int j = index + 2; j <= i; j++) {
                        sb.append(nums[nums.length - 1]);
                    }
                    //触发flag，后面所有位填最大值
                    flag = true;
                }
            } else {
                //等于，直接加，判下一位
                if (temp == target) {
                    sb.append(temp);
                } else {
                    //小于，//触发flag，后面所有位填最大值
                    sb.append(temp);
                    flag = true;
                }
            }
        }

        if (sb.length() == 0) {
            return -1;
        }
        return Integer.parseInt(sb.toString());
    }

    public static int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left + 1) / 2;
            if (nums[mid] <= target) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }

        return nums[left] <= target ? nums[left] : -1;
    }


    int solution(int[] A) {
        int sum = 0;
        for (int i : A) {
            sum = sum + i;
        }
        float sum1 = sum / 2;
        float s = 0;
        int totalFilters = 0;
        Arrays.sort(A);
        for (int i = 0; i < A.length / 2; i++) {
            int temp = A[i];
            A[i] = A[A.length - i - 1];
            A[A.length - i - 1] = temp;
        }
        for (int i = 0; i < A.length; i++) {
            totalFilters++;
            s+=(float) A[i]/2;
            if(s>=sum1){break;}
        }
        return totalFilters;

    }


}
