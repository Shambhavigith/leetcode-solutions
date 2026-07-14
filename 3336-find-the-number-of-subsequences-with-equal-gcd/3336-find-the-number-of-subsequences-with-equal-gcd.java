import java.util.Arrays;
class Solution {
    static final int MOD = 1_000_000_007;
    int[][][] dp;
    public int subsequencePairCount(int[] nums) {
        dp = new int[nums.length][201][201];

        for (int[][] a : dp) {
            for (int[] b : a) {
                Arrays.fill(b, -1);
            }
        }
        return dfs(nums, 0, 0, 0);
    }
    private int dfs(int[] nums, int idx, int g1, int g2) {

        if (idx == nums.length) {
            return (g1 != 0 && g1 == g2) ? 1 : 0;
        }

        if (dp[idx][g1][g2] != -1)
            return dp[idx][g1][g2];

        long ans = 0;
        ans += dfs(nums, idx + 1, gcd(g1, nums[idx]), g2);
        ans += dfs(nums, idx + 1, g1, gcd(g2, nums[idx]));
        ans += dfs(nums, idx + 1, g1, g2);

        return dp[idx][g1][g2] = (int) (ans % MOD);
    }
    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}