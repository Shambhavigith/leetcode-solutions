class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int total = m * n;
        k %= total;
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            result.add(new ArrayList<>(Collections.nCopies(n, 0)));
        }
        for (int i = 0; i < total; i++) {
            int newIndex = (i + k) % total;
            int oldRow = i / n;
            int oldCol = i % n;
            int newRow = newIndex / n;
            int newCol = newIndex % n;

            result.get(newRow).set(newCol, grid[oldRow][oldCol]);
        }
        return result;
    }
}