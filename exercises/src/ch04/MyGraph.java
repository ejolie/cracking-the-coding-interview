package ch04;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 1. DFS vs. BFS: visited check timing
 * cf. https://stackoverflow.com/questions/25990706/breadth-first-search-the-timing-of-checking-visitation-status
 * - DFS: after pop
 * - BFS: before enqueue (or after dequeue: 중복 노드 추가, 메모리 낭비)
 *
 * 2. iterative DFS vs. recursive DFS: visit order
 * cf. https://stackoverflow.com/questions/9201166/iterative-dfs-vs-recursive-dfs-and-different-elements-order
 * - iterative DFS: 스택에 push한 마지막 이웃노드부터 방문
 * - recursive DFS: 재귀 호출한 첫번째 이웃노드부터 방문
 * -> 동일한 결과를 얻기 위해서는 iterative DFS에서 스택에 역순으로 노드를 push하면 됨
 */
public class MyGraph {

    int n;
    LinkedList<Integer>[] neighbors;

    public MyGraph(int n) {
        this.n = n;
        neighbors = new LinkedList[n];

        for (int i = 0; i < n; i++) {
            neighbors[i] = new LinkedList<>();
        }
    }

    public static void main(String[] args) {
        int[][] inputs = new int[][]{
            {1, 2},
            {2, 3, 4},
            {},
            {2},
            {}
        };

        int n = inputs.length;
        MyGraph G = new MyGraph(n);
        // add edges
        for (int v = 0; v < n; v++) {
            for (int w : inputs[v]) {
                G.neighbors[v].add(w);
            }
        }

        System.out.println("BFS");
        G.bfs(0);

        System.out.println("DFS - iterative");
        G.dfs_iterative(0);

        System.out.println("DFS - recursive");
        G.dfs_recursive(new boolean[n], 0);
    }

    // 1. BFS(Breadth-First Search)
    public void bfs(int start) {
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n];

        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int v = queue.poll();
            doSomething(v);

            for (int w : neighbors[v]) {
                if (!visited[w]) {
                    // visit check
                    visited[w] = true;
                    queue.add(w);
                }
            }
        }
    }

    // 2.1. DFS(Depth-First Search) iterative ver.
    public void dfs_iterative(int start) {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[n];

        stack.push(start);

        while (!stack.empty()) {
            int v = stack.pop();
            if (visited[v]) {
                continue;
            }
            // visit check
            visited[v] = true;
            doSomething(v);

            // to synchronize visit order with recursive DFS
            LinkedList<Integer> nodes = neighbors[v];
            for (int i = nodes.size() - 1; i >= 0; i--) {
                if (!visited[nodes.get(i)]) {
                    stack.push(nodes.get(i));
                }
            }
        }
    }

    // 2.2. DFS(Depth-First Search) recursive ver.
    public void dfs_recursive(boolean[] visited, int v) {
        if (visited[v]) {
            return;
        }
        // visit check
        visited[v] = true;
        doSomething(v);

        for (int w : neighbors[v]) {
            if (!visited[w]) {
                dfs_recursive(visited, w);
            }
        }
    }

    private void doSomething(int node) {
        System.out.println("visited: " + node);
    }
}
