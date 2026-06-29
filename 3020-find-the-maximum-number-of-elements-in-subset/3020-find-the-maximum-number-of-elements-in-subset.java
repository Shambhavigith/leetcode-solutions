class Solution {
    public int maximumLength(int[] nums) {
        HashMap<Integer,Integer> freq=new HashMap<>();
        int ans=1;
        int length=0;
        for (int num : nums) 
        {
            freq.put(num,freq.getOrDefault(num,0)+1);
        }
        for(int num: freq.keySet())
        {
            if(num==1)
            {
                continue;
            }
            int current=num;
            length=0;
            while(freq.getOrDefault(current,0)>=2)
            {
                current=current*current;
                length=length+2;
            }
            if(freq.getOrDefault(current,0)==1)
            {
                length++;
            }
            else
            {
                length--;
            }
            ans = Math.max(ans, length);
        }
        if(freq.getOrDefault(1,0)>=ans)
        {
            int ones=freq.get(1);
            if(ones%2==0)
            {
                ones--;
            }
            ans=ones;
        }
        return ans;
    }
}