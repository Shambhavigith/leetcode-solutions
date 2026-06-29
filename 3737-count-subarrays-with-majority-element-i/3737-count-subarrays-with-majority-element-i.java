class Solution {
    public int countMajoritySubarrays(int[] nums, int target) {
        int n=nums.length;
        int targetcount=0;
        int count=0;
        for(int i=0;i<n;i++)
        {
            targetcount=0;
            for(int j=i;j<n;j++)
            {
                if(nums[j]==target)
                {
                    targetcount++;
                }
                if(targetcount>(j-i+1)/2)
                {
                    count++;
                }
            }
        }
        return count;
    }
}