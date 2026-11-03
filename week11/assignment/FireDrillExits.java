package week11.assignment;

public class FireDrillExits {
    static int[][] exitDistance(char[][] plan) {
        int m = plan.length;
        int n = plan[0].length;

        int[][] dist = new int[m][n];
        int[] qx = new int[m * n];
        int[] qy = new int[m * n];

        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                dist[i][j] = -1;

        int front = 0, rear = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (plan[i][j] == 'E') {
                    dist[i][j] = 0;
                    qx[rear] = i;
                    qy[rear++] = j;
                }
            }
        }

        int[] dx = { -1, 1, 0, 0 };
        int[] dy = { 0, 0, -1, 1 };

        while (front < rear) {
            int x = qx[front];
            int y = qy[front++];

            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                if (nx >= 0 && nx < m && ny >= 0 && ny < n &&
                        plan[nx][ny] != '#' && dist[nx][ny] == -1) {

                    dist[nx][ny] = dist[x][y] + 1;
                    qx[rear] = nx;
                    qy[rear++] = ny;
                }
            }
        }

        return dist;
    }
}