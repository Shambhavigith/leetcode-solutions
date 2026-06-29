class Solution {
    public long maxSum(int[] nums, int k, int mul) {
        Arrays.sort(nums);
        int n=nums.length;
        long sum=0;
        long prod=1;
        for(int i=n-1;i>=n-k;i--)
        {
            if(mul>0)
            {
                prod=(long)nums[i]*mul;
                sum=sum+prod;
                mul--;
            }
            else
            {
                sum=sum+nums[i];
            }
        }
        return sum;
    }
}