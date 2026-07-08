class Solution {
    static final int MOD = 1_000_000_007;
    public int[] sumAndMultiply(String s, int[][] queries) {
        int n = s.length();
        int[] cnt = new int[n + 1];
        List<Integer> digits = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            cnt[i + 1] = cnt[i];
            int d = s.charAt(i) - '0';
            if (d != 0) {
                digits.add(d);
                cnt[i + 1]++;
            }
        }
        int m = digits.size();
        long[] pow10 = new long[m + 1];
        long[] prefNum = new long[m + 1];
        long[] prefSum = new long[m + 1];
        pow10[0] = 1;
        for (int i = 1; i <= m; i++) {
            pow10[i] = (pow10[i - 1] * 10) % MOD;
            prefNum[i] = (prefNum[i - 1] * 10 + digits.get(i - 1)) % MOD;
            prefSum[i] = prefSum[i - 1] + digits.get(i - 1);
        }
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0];
            int r = queries[i][1];
            int L = cnt[l] + 1;
            int R = cnt[r + 1];
            if (L > R) {
                ans[i] = 0;
                continue;
            }
            int len = R - L + 1;
            long x = (prefNum[R] - (prefNum[L - 1] * pow10[len]) % MOD + MOD) % MOD;
            long sum = prefSum[R] - prefSum[L - 1];

            ans[i] = (int) ((x * (sum % MOD)) % MOD);
        }
        return ans;
    }
}