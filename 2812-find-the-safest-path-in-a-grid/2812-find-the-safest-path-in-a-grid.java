class Solution {
    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        Queue<int[]> q = new LinkedList<>();
        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid.get(i).get(j) == 1) {
                    dist[i][j] = 0;
                    q.offer(new int[]{i, j});
                }
            }
        }

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];

            for (int k = 0; k < 4; k++) {
                int nr = r + dr[k];
                int nc = c + dc[k];

                if (nr < 0 || nc < 0 || nr >= n || nc >= n)
                    continue;
                if (dist[nr][nc] != Integer.MAX_VALUE)
                    continue;
                dist[nr][nc] = dist[r][c] + 1;
                q.offer(new int[]{nr, nc});
            }
        }
        int low = 0;
        int high = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                high = Math.max(high, dist[i][j]);
            }
        }
        int ans = 0;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (dist[0][0] < mid) {
                high = mid - 1;
                continue;
            }
            Queue<int[]> bfs = new LinkedList<>();
            boolean[][] vis = new boolean[n][n];
            bfs.offer(new int[]{0, 0});
            vis[0][0] = true;
            boolean reached = false;
            while (!bfs.isEmpty()) {
                int[] cur = bfs.poll();
                int r = cur[0];
                int c = cur[1];
                if (r == n - 1 && c == n - 1) {
                    reached = true;
                    break;
                }
                for (int k = 0; k < 4; k++) {
                    int nr = r + dr[k];
                    int nc = c + dc[k];
                    if (nr < 0 || nc < 0 || nr >= n || nc >= n)
                        continue;
                    if (vis[nr][nc])
                        continue;
                    if (dist[nr][nc] < mid)
                        continue;
                    vis[nr][nc] = true;
                    bfs.offer(new int[]{nr, nc});
                }
            }
            if (reached) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }
}