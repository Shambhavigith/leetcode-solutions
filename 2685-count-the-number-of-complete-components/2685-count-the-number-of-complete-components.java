class Solution {
    public int countCompleteComponents(int n, int[][] edges) {
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        boolean[] vis = new boolean[n];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                Queue<Integer> queue = new LinkedList<>();
                queue.offer(i);
                vis[i] = true;

                int nodes = 0;
                int degreeSum = 0;

                while (!queue.isEmpty()) {
                    int cur = queue.poll();
                    nodes++;
                    degreeSum += graph[cur].size();

                    for (int nei : graph[cur]) {
                        if (!vis[nei]) {
                            vis[nei] = true;
                            queue.offer(nei);
                        }
                    }
                }

                int edgesInComponent = degreeSum / 2;
                int requiredEdges = nodes * (nodes - 1) / 2;

                if (edgesInComponent == requiredEdges) {
                    ans++;
                }
            }
        }
        return ans;
    }
}