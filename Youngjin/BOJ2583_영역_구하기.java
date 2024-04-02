package com.backjoon.bfs;

import java.io.*;
import java.util.*;

public class BOJ2583_영역_구하기 {
    static int N;
    static int M;
    static int K;
    static boolean[][] visited;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        visited = new boolean[N][M];

        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            for (int i = y1; i < y2; i++) {
                for (int j = x1; j < x2; j++) {
                    visited[i][j] = true;
                }
            }

        }

        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j]) {
                    int size = bfs(new Node(j, i));
                    list.add(size);
                }
            }
        }

        Collections.sort(list);
        StringBuilder sb = new StringBuilder();
        sb.append(list.size()).append("\n");
        list.forEach(i -> {
            sb.append(i).append(" ");
        });

        System.out.println(sb);

    }

    static int bfs(Node node) {
        Queue<Node> q = new LinkedList<>();
        q.add(node);
        visited[node.y][node.x] = true;
        int size = 1;

        while (!q.isEmpty()) {
            Node poll = q.poll();

            for (int i = 0; i < 4; i++) {
                int X = poll.x + dx[i];
                int Y = poll.y + dy[i];

                if (X < 0 || X >= M || Y < 0 || Y >= N) continue;
                if (visited[Y][X]) continue;

                q.add(new Node(X, Y));
                visited[Y][X] = true;
                size++;
            }
        }

        return size;
    }
}
