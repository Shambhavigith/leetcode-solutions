class Solution {
    public int minScore(int n, int[][] roads) {
        List<int[]>[] graph = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] road : roads) {
            graph[road[0]].add(new int[]{road[1], road[2]});
            graph[road[1]].add(new int[]{road[0], road[2]});
        }

        Queue<Integer> queue = new LinkedList<>();
        boolean[] vis = new boolean[n + 1];

        queue.offer(1);
        vis[1] = true;

        int ans = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            int node = queue.poll();

            for (int[] nei : graph[node]) {
                ans = Math.min(ans, nei[1]);

                if (!vis[nei[0]]) {
                    vis[nei[0]] = true;
                    queue.offer(nei[0]);
                }
            }
        }

        return ans;
    }
}