class Solution {

    public int removeDuplicates(int[] nums) {

        int x = nums[0];
        int index = 1;

        for (int i = 1; i < nums.length; i++) {
            if (x != nums[i]) {
                x = nums[i];
                nums[index] = nums[i];
                index++;
            }
        }

        return index;
    }
}
