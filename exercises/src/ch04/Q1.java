package ch04;

import java.util.*;

/**
 * 4.1. 노드 사이의 경로: 방향 그래프, 두 노드 사이 경로 존재 여부 확인
 * - DFS or BFS
 */
public class Q1 {

    public static void main(String[] args) {
        Graph G = new Graph();
        Node A = new Node("A");
        Node B = new Node("B");
        Node C = new Node("C");
        Node D = new Node("D");
        A.adjacent = new Node[]{B, C};
        B.adjacent = new Node[]{D};
        C.adjacent = new Node[]{A, B};
        D.adjacent = new Node[]{};
        G.nodes = new Node[]{A, B, C, D};

        Q1 q1 = new Q1();
        System.out.println(q1.solution_search(G, A, D));
        System.out.println(q1.doesHavePathBetween_bfs(G, A, D));
        System.out.println(q1.doesHavePathBetween_dfs_iter(G, A, D));
        System.out.println(q1.doesHavePathBetween_dfs_recur(G, A, D));
    }

    public boolean solution_search(Graph G, Node start, Node end) {
        if (start == end) {
            return true;
        }

        LinkedList<Node> queue = new LinkedList<>(); // queue
        for (Node node : G.nodes) {
            node.state = State.Unvisited;
        }
        start.state = State.Visiting;
        queue.add(start);
        Node u;
        while (!queue.isEmpty()) {
            u = queue.removeFirst(); // dequeue()
            if (u != null) {
                for (Node v : u.adjacent) {
                    if (v.state == State.Unvisited) {
                        if (v == end) {
                            return true;
                        } else {
                            v.state = State.Visiting;
                            queue.add(v);
                        }
                    }
                }
                u.state = State.Visited;
            }
        }
        return false;
    }

    public boolean doesHavePathBetween_bfs(Graph G, Node start, Node end) {
        Queue<Node> queue = new ArrayDeque<>();
        Set<Node> visited = new HashSet<>(G.nodes.length);
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node == end) {
                return true;
            }
            for (Node v : node.adjacent) {
                if (!visited.contains(v)) {
                    visited.add(v);
                    queue.add(v);
                }
            }
        }
        return false;
    }

    public boolean doesHavePathBetween_dfs_iter(Graph G, Node start, Node end) {
        Stack<Node> stack = new Stack<>();
        Set<Node> visited = new HashSet<>(G.nodes.length);
        stack.add(start);

        while (!stack.empty()) {
            Node node = stack.pop();
            visited.add(node);
            if (node == end) {
                return true;
            }
            for (Node v : node.adjacent) {
                if (!visited.contains(v)) {
                    stack.add(v);
                }
            }
        }
        return true;
    }

    Set<Node> visited = new HashSet<>();
    public boolean doesHavePathBetween_dfs_recur(Graph G, Node curr, Node end) {
        visited.add(curr);
        if (curr == end) {
            return true;
        }

        for (Node v : curr.adjacent) {
            if (!visited.contains(v) && doesHavePathBetween_dfs_recur(G, v, end)) {
                return true;
            }
        }
        return false;
    }
}
