class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int max = 0;
        for (int x : nums) max = Math.max(max, x);
        int[] freq = new int[max + 1];
        for (int x : nums) freq[x]++;
        long[] exact = new long[max + 1];

        for (int g = max; g >= 1; g--) {

            long cnt = 0;

            for (int multiple = g; multiple <= max; multiple += g)
                cnt += freq[multiple];

            exact[g] = cnt * (cnt - 1) / 2;

            for (int multiple = g * 2; multiple <= max; multiple += g)
                exact[g] -= exact[multiple];
        }
        long[] prefix = new long[max + 1];
        for (int g = 1; g <= max; g++)
            prefix[g] = prefix[g - 1] + exact[g];

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            long target = queries[i] + 1; // convert index to order statistic
            int lo = 1, hi = max;
            while (lo < hi) {
                int mid = (lo + hi) / 2;
                if (prefix[mid] >= target)
                    hi = mid;
                else
                    lo = mid + 1;
            }
            ans[i] = lo;
        }
        return ans;
    }
}