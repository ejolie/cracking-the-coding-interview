package ch04;

import java.util.ArrayList;
import java.util.List;

/**
 * 4.5. BST 검증: 이진 트리가 이진 탐색 트리인지 확인하기
 *
 * #35. 중위 순회 이후 모든 원소의 순서가 제대로 배열되어 있다면, 트리가 제대로 된 순서로 배열되었다고 말할 수 있는가?
 * 원소가 중복되면 어떻게 되는가? 원소의 중복을 허용한다면, 중복된 원소는 모두 한쪽(보통 왼쪽)에 놓여야 한다.
 * #57. 왼쪽의 모든 노드가 현재 노드보다 작아야 하고, 현재 노드는 오른쪽의 모든 노드보다 작아야 한다.
 * #86. 왼쪽의 모든 노드가 현재 노드보다 작거나 같다면, 왼쪽에서 가장 큰 노드가 현재 노드보다 작거나 같다는 뜻이다.
 * #113. leftTree.max와 rightTree.min에 대해 현재의 값을 검증하는 대신
 * 이 로직을 뒤집어서 생각해 볼 수 없을까? 트리 왼쪽의 노드가 current.value보다 작은지를 검증해라.
 * #128. checkBST라는 재귀 함수를 생각해 보자. 이 함수는 각각의 노드가 (min, max) 영역 안에 있는지 확인한다.
 * 처음에 이 영역의 제한은 무한대이다. 왼쪽으로 탐색할 때 min은 음의 무한대이고 max는 root.value가 된다.
 * 트리를 탐색하면서 이 영역을 적절하게 조절하는 재귀 함수를 작성할 수 있는가?
 */
public class Q5 {

    public static void main(String[] args) {
        TreeNode left = new TreeNode(4, new TreeNode(2), new TreeNode(12));
        TreeNode right = new TreeNode(10, null, new TreeNode(20));
        TreeNode root = new TreeNode(8, left, right);

        Q5 q5 = new Q5();
        System.out.println(q5.isBST(root));
    }

    // Solution 1. In-order traversal
    // - time: O(N)
    // - space: O(N)
    // - 문제점: 트리 안에 중복된 값이 있는 경우 처리 불가
    // 예: root(20) - right(20) -> 잘못된 트리
    public boolean solution_checkBST_inOrder(TreeNode root) {
        List<Integer> arr = new ArrayList<>();
        solution_copyBST(root, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i) <= arr.get(i)) {
                return false;
            }
        }
        return true;
    }

    private void solution_copyBST(TreeNode root, List<Integer> arr) {
        if (root == null) {
            return;
        }
        solution_copyBST(root.left, arr);
        arr.add(root.value);
        solution_copyBST(root.right, arr);
    }

    // Solution 2. left <= current < right
    // - time: O(N)
    // - space: (균형 잡힌 트리에서) 트리의 깊이 만큼 재귀 호출을 수행하므로 O(logN)
    public boolean solution_checkBST(TreeNode root) {
        return solution_checkBST(root, null, null);
    }

    private boolean solution_checkBST(TreeNode root, Integer min, Integer max) {
        if (root == null) {
            return true;
        }

        if ((min != null && root.value <= min) || (max != null && root.value > max)) {
            return false;
        }

        if (!solution_checkBST(root.left, min, root.value) || !solution_checkBST(root.right, root.value, max)) {
            return false;
        }

        return true;
    }

    // Method 1. In-order traversal
    public boolean isBST(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inOrderTraverse(root, list);
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) > list.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    private void inOrderTraverse(TreeNode root, List<Integer> list) {
        if (root != null) {
            inOrderTraverse(root.left, list);
            list.add(root.value);
            inOrderTraverse(root.right, list);
        }
    }
}
