package com.backjoon.bfs;

import java.io.*;
import java.util.*;

public class BOJ10026_적록색약 {
    static int N;
    static Character[][] A;
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

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new Character[N][N];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                A[i][j] = line.charAt(j);
            }
        }

        StringBuilder sb = new StringBuilder();

        sb.append(check()).append(" ");
        changeBlindness();
        sb.append(check());

        System.out.println(sb);

    }

    static void bfs(Node node) {
        Queue<Node> q = new LinkedList<>();
        q.add(node);
        visited[node.y][node.x] = true;

        while (!q.isEmpty()) {
            Node poll = q.poll();

            for (int i = 0; i < 4; i++) {
                int X = poll.x + dx[i];
                int Y = poll.y + dy[i];

                if (X < 0 || X >= N || Y < 0 || Y >= N) continue;
                if (A[Y][X] != A[poll.y][poll.x] || visited[Y][X] ) continue;

                q.add(new Node(X, Y));
                visited[Y][X] = true;
            }
        }
    }

    static int check() {
        int result = 0;
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    bfs(new Node(j, i));
                    result++;
                }
            }
        }

        return result;
    }

    static void changeBlindness() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (A[i][j] == 'G') A[i][j] = 'R';
            }
        }

    }
}
