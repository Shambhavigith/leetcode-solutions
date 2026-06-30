class Solution {
    public int numberOfSubstrings(String s) {
        int left = 0;
        int right = 0;
        int countA = 0;
        int countB = 0;
        int countC = 0;
        int ans = 0;

        while (right < s.length()) 
        {
            char ch = s.charAt(right);

            if (ch == 'a') 
                countA++;
            if (ch == 'b') 
                countB++;
            if (ch == 'c') 
                countC++;

            while (countA > 0 && countB > 0 && countC > 0) 
            {
                ans += s.length() - right;
                char remove = s.charAt(left);
                if (remove == 'a') countA--;
                if (remove == 'b') countB--;
                if (remove == 'c') countC--;
                left++;
            }
            right++;
        }
        return ans;
    }
}