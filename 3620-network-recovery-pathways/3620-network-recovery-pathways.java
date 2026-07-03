class Solution {
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int n = online.length;
        List<int[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        int[] indegree = new int[n];
        int maxCost = 0;

        for (int[] e : edges) {
            graph[e[0]].add(new int[]{e[1], e[2]});
            indegree[e[1]]++;
            maxCost = Math.max(maxCost, e[2]);
        }
        int[] topo = new int[n];
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++)
            if (indegree[i] == 0)
                q.offer(i);
        int idx = 0;
        while (!q.isEmpty()) {
            int u = q.poll();
            topo[idx++] = u;
            for (int[] e : graph[u]) {
                if (--indegree[e[0]] == 0)
                    q.offer(e[0]);
            }
        }
        int ans = -1;
        int lo = 0, hi = maxCost;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            long[] dist = new long[n];
            Arrays.fill(dist, Long.MAX_VALUE / 4);
            dist[0] = 0;
            for (int u : topo) {
                if (dist[u] == Long.MAX_VALUE / 4) continue;
                if (u != 0 && u != n - 1 && !online[u]) continue;
                for (int[] e : graph[u]) {
                    int v = e[0], cost = e[1];

                    if (cost < mid) continue;
                    if (v != n - 1 && !online[v]) continue;
                    dist[v] = Math.min(dist[v], dist[u] + cost);
                }
            }
            if (dist[n - 1] <= k) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return ans;
    }
}