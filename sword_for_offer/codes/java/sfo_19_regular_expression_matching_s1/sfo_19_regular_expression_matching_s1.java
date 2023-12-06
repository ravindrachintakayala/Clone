/*
* File: sfo_19_regular_expression_matching_s1.java
* Created Time: 2021-12-09
* Author: Krahets (krahets@163.com)
*/

package sfo_19_regular_expression_matching_s1;

import include.*;
import java.util.*;

// ===== Solution Code =====
class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length() + 1, n = p.length() + 1;
        boolean[][] dp = new boolean[m][n];
        dp[0][0] = true;
        for (int j = 2; j < n; j += 2)
            dp[0][j] = dp[0][j - 2] && p.charAt(j - 1) == '*';
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = p.charAt(j - 1) == '*'
                        ? dp[i][j - 2] || dp[i - 1][j] && (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.')
                        : dp[i - 1][j - 1] && (p.charAt(j - 1) == '.' || s.charAt(i - 1) == p.charAt(j - 1));
            }
        }
        return dp[m - 1][n - 1];
    }
}

public class sfo_19_regular_expression_matching_s1 {
    public static void main(String[] args) {
        // ======= Test Case =======
        String s = "mississippi";
        String p = "mis*is*p*.";
        // ====== Driver Code ======
        Solution slt = new Solution();
        boolean res = slt.isMatch(s, p);
        System.out.println(res);
    }
}
