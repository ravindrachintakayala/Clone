/*
* File: sfo_53i_find_a_number_in_a_sorted_array_s1.java
* Created Time: 2021-12-09
* Author: Krahets (krahets@163.com)
*/

package sfo_53i_find_a_number_in_a_sorted_array_s1;

import include.*;
import java.util.*;

// ===== Solution Code =====
class Solution {
    public int search(int[] nums, int target) {
        // 搜索右边界 right
        int i = 0, j = nums.length - 1;
        while (i <= j) {
            int m = (i + j) / 2;
            if (nums[m] <= target)
                i = m + 1;
            else
                j = m - 1;
        }
        int right = i;
        // 若数组中无 target ，则提前返回
        if (j >= 0 && nums[j] != target)
            return 0;
        // 搜索左边界 right
        i = 0;
        j = nums.length - 1;
        while (i <= j) {
            int m = (i + j) / 2;
            if (nums[m] < target)
                i = m + 1;
            else
                j = m - 1;
        }
        int left = j;
        return right - left - 1;
    }
}

public class sfo_53i_find_a_number_in_a_sorted_array_s1 {
    public static void main(String[] args) {
        // ======= Test Case =======
        int[] nums = { 5, 7, 7, 8, 8, 10 };
        int target = 8;
        // ====== Driver Code ======
        Solution slt = new Solution();
        int res = slt.search(nums, target);
        System.out.println(res);
    }
}
