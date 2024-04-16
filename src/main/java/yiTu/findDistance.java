package yiTu;

import java.util.ArrayList;
import java.util.List;

class Main1 {
    // 推荐算法，点击率，用户画像，推荐相似视频/新闻。
    // 共同特征，
    // 用户分类。
    // 资源角度。 用户行为数据分析。
    // 数据分析/架构开发。 模型训练，线上代码开发
    // 人员分布，推荐策略，策略数据分析，模型算法。
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        List<List<Integer>> res = func(nums);
        for (List<Integer> re : res) {
            for (int i = 0; i < re.size(); i++) {
                System.out.print(re.get(i)+" , ");
            }
            System.out.println();
        }
    }

    static List<List<Integer>> res = new ArrayList<>();

    public static List<List<Integer>> func(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        List list = new ArrayList();
        function(nums, 0, list);
        return res;
    }

    // [1,2,3]
    public static void function(int[] nums, int k, List<Integer> list) {
        // num 1,2,3
        if (list.size() == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }
        // 123
        // 123 ,132
        for (int i = 0; i < nums.length; i++) {
            if(list.contains(nums[i])){
                continue;
            }
            list.add(nums[i]);
            function(nums, i + 1, list);
            list.remove(list.get(list.size()-1));
        }
    }
}