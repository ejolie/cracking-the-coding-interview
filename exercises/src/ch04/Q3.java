package ch04;

import java.util.*;

/**
 * 4.3. 깊이의 리스트: 이진 트리, 같은 깊이에 있는 노드를 연결리스트로 연결하기. 트리의 깊이가 D라면 D개의 연결리스트
 *
 * #107. 그래프 탐색 알고리즘을 수정해서 루트의 깊이를 추적해 보라.
 * #123. 깊이에서 그 깊이에 있는 노드로의 대응관계가 있는 해시테이블 혹은 배열을 사용하면 유용한가?
 * #135. 깊이 우선 탐색과 너비 우선 탐색을 사용하는 알고리즘을 찾아 볼 수 있다.
 */
public class Q3 {

    public static void main(String[] args) {
        Q3 q3 = new Q3();
        TreeNode left = new TreeNode(4, new TreeNode(2), new TreeNode(6));
        TreeNode right = new TreeNode(10, null, new TreeNode(20));
        TreeNode root = new TreeNode(8, left, right);

        Map<Integer, List<TreeNode>> map = q3.makeLinkedList(root);
        for (Map.Entry<Integer, List<TreeNode>> entry : map.entrySet()) {
            System.out.printf("depth %d: %s\n", entry.getKey(), Arrays.toString(entry.getValue().toArray()));
        }

        ArrayList<LinkedList<TreeNode>> lists = q3.solution_createLevelLinkedList_preOrder(root);
        for (int i = 0; i < lists.size(); i++) {
            System.out.printf("depth %d: %s\n", i, Arrays.toString(lists.get(i).toArray()));
        }
    }

    // Solution 1. Pre-order traversal
    // - time: O(N)
    // - space: (균형 잡힌 트리의 경우) O(logN) 만큼의 재귀 호출 필요 -> 새로운 깊이 탐색 시 스택 사용
    // O(N) 만큼의 데이터 반환해야 함
    public ArrayList<LinkedList<TreeNode>> solution_createLevelLinkedList_preOrder(TreeNode root) {
        ArrayList<LinkedList<TreeNode>> lists = new ArrayList<>();
        solution_createLevelLinkedList_preOrder(root, lists, 0);
        return lists;
    }

    private void solution_createLevelLinkedList_preOrder(TreeNode root, ArrayList<LinkedList<TreeNode>> lists, int level) {
        if (root == null) {
            return;
        }

        LinkedList<TreeNode> list = null;
        if (lists.size() == level) { // 리스트에 해당 레벨이 없다.
            list = new LinkedList<>();
            lists.add(list);
            /**
             * 깊이가 증가하는 순서로 순회했다는 사실에 유의하자.
             * 따라서 깊이 i를 처음 마주쳤다면, 0부터 i-1번째까지는 이전에 이미 lists에 추가되어야 한다.
             * 따라서 새로운 깊이 i를 lists의 끝에 추가해도 안전하다.
             */
        } else {
            list = lists.get(level);
        }
        list.add(root);
        solution_createLevelLinkedList_preOrder(root.left, lists, level + 1);
        solution_createLevelLinkedList_preOrder(root.right, lists, level + 1);
    }

    // Solution 2. BFS
    // - time: O(N)
    // - space: 순환적 구현 -> 추가 공간 요구 X
    // O(N) 만큼의 데이터 반환해야 함
    public ArrayList<LinkedList<TreeNode>> solution_createLevelLinkedList_bfs(TreeNode root) {
        ArrayList<LinkedList<TreeNode>> result = new ArrayList<>();
        // 루트 방문
        LinkedList<TreeNode> current = new LinkedList<>();
        if (root != null) {
            current.add(root);
        }

        while (current.size() > 0) {
            result.add(current); // 이전 깊이 추가

            LinkedList<TreeNode> parents = current; // 다음 깊이로 진행
            current = new LinkedList<>();

            for (TreeNode parent : parents) {
                // 자식 노드들 방문
                if (parent.left != null) {
                    current.add(parent.left);
                }
                if (parent.right != null) {
                    current.add(parent.right);
                }
            }
        }
        return result;
    }

    public Map<Integer, List<TreeNode>> makeLinkedList(TreeNode root) {
        Map<Integer, List<TreeNode>> map = new HashMap<>();
        preOrderTraverse(root, map, 0);
        return map;
    }

    private void preOrderTraverse(TreeNode node, Map<Integer, List<TreeNode>> map, int depth) {
        if (node == null) {
            return;
        }

        List<TreeNode> nodes = map.getOrDefault(depth, new LinkedList<>());
        nodes.add(node);
        map.put(depth, nodes);

        preOrderTraverse(node.left, map, depth + 1);
        preOrderTraverse(node.right, map, depth + 1);
    }

}
