## 解题思路：

题目说明：从棋盘的左上角开始拿格子里的礼物，并每次 **向右** 或者 **向下** 移动一格、直到到达棋盘的右下角。
根据题目说明，易得某单元格只可能从上边单元格或左边单元格到达。

设 $f(i, j)$ 为从棋盘左上角走至单元格 $(i ,j)$ 的礼物最大累计价值，易得到以下递推关系：$f(i,j)$ 等于 $f(i,j-1)$ 和 $f(i-1,j)$ 中的较大值加上当前单元格礼物价值 $grid(i,j)$ 。

$$
f(i,j) = \max[f(i,j-1), f(i-1,j)] + grid(i,j)
$$

因此，可用动态规划解决此问题，以上公式便为转移方程。

![Picture1.png](https://pic.leetcode-cn.com/1662cdf7aafd8c9ed6e1eadc41bfc9adf58ea808e11f1a3dd2e8ba4632b9d1ac-Picture1.png){:width=450}

### 动态规划解析：

- **状态定义：** 设动态规划矩阵 $dp$ ，$dp(i,j)$ 代表从棋盘的左上角开始，到达单元格 $(i,j)$ 时能拿到礼物的最大累计价值。 
- **转移方程：**
  1. 当 $i = 0$ 且 $j = 0$ 时，为起始元素；
  2. 当 $i = 0$ 且 $j \ne 0$ 时，为矩阵第一行元素，只可从左边到达；
  3. 当 $i \ne 0$ 且 $j = 0$ 时，为矩阵第一列元素，只可从上边到达；
  4. 当 $i \ne 0$ 且 $j \ne 0$ 时，可从左边或上边到达；

$$
dp(i,j)=
\begin{cases}
grid(i,j) & {,i=0, j=0}\\
grid(i,j) + dp(i,j-1) & {,i=0, j \ne 0}\\
grid(i,j) + dp(i-1,j) & {,i \ne 0, j=0}\\
grid(i,j) + \max[dp(i-1,j),dp(i,j-1)]& ,{i \ne 0, j \ne 0}
\end{cases}
$$

- **初始状态：** $dp[0][0] = grid[0][0]$ ，即到达单元格 $(0,0)$ 时能拿到礼物的最大累计价值为 $grid[0][0]$ ；
- **返回值：** $dp[m-1][n-1]$ ，$m, n$ 分别为矩阵的行高和列宽，即返回 $dp$ 矩阵右下角元素。

### 空间复杂度降低：

- 由于 $dp[i][j]$ 只与 $dp[i-1][j]$ , $dp[i][j-1]$ , $grid[i][j]$ 有关系，因此可以将原矩阵 $grid$ 用作 $dp$ 矩阵，即直接在 $grid$ 上修改即可。
- 应用此方法可省去 $dp$ 矩阵使用的额外空间，因此空间复杂度从 $O(MN)$ 降至 $O(1)$ 。

<![Picture2.png](https://pic.leetcode-cn.com/6189bf2953793ba132441027f38017ccf42c6a86d8d578b0d5848bc9f0247d00-Picture2.png),![Picture3.png](https://pic.leetcode-cn.com/af90f0122772097f0e2bb15dccc46357b5eddaa20b5d16923146cfece3f9305d-Picture3.png),![Picture4.png](https://pic.leetcode-cn.com/fada524d36bf8a11d7ea4eab2f84f5adf07dd7be4881a6fa93dc1bdce05bca1d-Picture4.png),![Picture5.png](https://pic.leetcode-cn.com/ef0653b42f1008d2fd1735e21987255a179094ce0774071bff7b74415bd8624a-Picture5.png),![Picture6.png](https://pic.leetcode-cn.com/3784679f83f195ffdf82f15ed0506f4f06f25202c7c2ad6d587e6fdd7bf32e35-Picture6.png),![Picture7.png](https://pic.leetcode-cn.com/6aeb93075d0213260552af7b8f1f97a5c470ff6d6d6d6a8cdc775aef79d8572b-Picture7.png),![Picture8.png](https://pic.leetcode-cn.com/be92ca4e4735866de0675b0014bdd937b5c2270c531b7efb28335191378db066-Picture8.png),![Picture9.png](https://pic.leetcode-cn.com/af9da50dde707d7be49d3764b482942fda28867f2624024c64c29fc0137131cc-Picture9.png),![Picture10.png](https://pic.leetcode-cn.com/f86f34be835f15f86c6a52ffde0d1af5e8030f514960631e324660445bddecd0-Picture10.png),![Picture11.png](https://pic.leetcode-cn.com/effdeef2927144b88f1a7f7601f6d7491f4f618f5ff506ce471336445dfccc31-Picture11.png)>

### 复杂度分析：

- **时间复杂度 $O(MN)$ ：** $M, N$ 分别为矩阵行高、列宽；动态规划需遍历整个 $grid$ 矩阵，使用 $O(MN)$ 时间。
- **空间复杂度 $O(1)$ ：** 原地修改使用常数大小的额外空间。

## 代码：

```Python []
class Solution:
    def maxValue(self, grid: List[List[int]]) -> int:
        for i in range(len(grid)):
            for j in range(len(grid[0])):
                if i == 0 and j == 0: continue
                if i == 0: grid[i][j] += grid[i][j - 1]
                elif j == 0: grid[i][j] += grid[i - 1][j]
                else: grid[i][j] += max(grid[i][j - 1], grid[i - 1][j])
        return grid[-1][-1]
```

```Java []
class Solution {
    public int maxValue(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(i == 0 && j == 0) continue;
                if(i == 0) grid[i][j] += grid[i][j - 1] ;
                else if(j == 0) grid[i][j] += grid[i - 1][j];
                else grid[i][j] += Math.max(grid[i][j - 1], grid[i - 1][j]);
            }
        }
        return grid[m - 1][n - 1];
    }
}
```

```C++ []
class Solution {
public:
    int maxValue(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(i == 0 && j == 0) continue;
                if(i == 0) grid[i][j] += grid[i][j - 1] ;
                else if(j == 0) grid[i][j] += grid[i - 1][j];
                else grid[i][j] += max(grid[i][j - 1], grid[i - 1][j]);
            }
        }
        return grid[m - 1][n - 1];
    }
};
```

以上代码逻辑清晰，和转移方程直接对应，但仍可提升效率，这是因为：当 $grid$ 矩阵很大时， $i = 0$ 或 $j = 0$ 的情况仅占极少数，相当循环每轮都冗余了一次判断。因此，可先初始化矩阵第一行和第一列，再开始遍历递推。

```Python []
class Solution:
    def maxValue(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        for j in range(1, n): # 初始化第一行
            grid[0][j] += grid[0][j - 1]
        for i in range(1, m): # 初始化第一列
            grid[i][0] += grid[i - 1][0]
        for i in range(1, m):
            for j in range(1, n):
                grid[i][j] += max(grid[i][j - 1], grid[i - 1][j])
        return grid[-1][-1]
```

```Java []
class Solution {
    public int maxValue(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        for(int j = 1; j < n; j++) // 初始化第一行
            grid[0][j] += grid[0][j - 1];
        for(int i = 1; i < m; i++) // 初始化第一列
            grid[i][0] += grid[i - 1][0];
        for(int i = 1; i < m; i++)
            for(int j = 1; j < n; j++)
                grid[i][j] += Math.max(grid[i][j - 1], grid[i - 1][j]);
        return grid[m - 1][n - 1];
    }
}
```

```C++ []
class Solution {
public:
    int maxValue(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        for(int j = 1; j < n; j++) // 初始化第一行
            grid[0][j] += grid[0][j - 1];
        for(int i = 1; i < m; i++) // 初始化第一列
            grid[i][0] += grid[i - 1][0];
        for(int i = 1; i < m; i++)
            for(int j = 1; j < n; j++)
                grid[i][j] += max(grid[i][j - 1], grid[i - 1][j]);
        return grid[m - 1][n - 1];
    }
};
```
