package ch04;

/**
 * 4.4. 균형 확인: 이진 트리가 균형 잡혀있는지 확인.
 * 균형 잡힌 트리: 모든 노드에 대해 왼쪽 부분 트리 높이와 오른쪽 부분 트리 높이의 차이가 최대 하나인 트리
 *
 * #21. 하나의 노드에 대해서 해당 조건을 검증할 수 있는가? 모든 노드에 대해서도 검증할 수 있는가?
 * #33. 모든 노드에서 부분트리의 높이를 계산하고자 할 때, 아주 비효율적인 알고리즘을 사용했을 수도 있다.
 * #49. 각각의 노드가 부분트리의 높이를 저장할 수 있도록 이진 트리 노드 클래스를 수정할 수 있다면 어떨까?
 * #105. 부분트리의 높이를 저장하기 위해 이진 트리 클래스를 수정할 필요는 없다.
 * 재귀 함수에서 각 부분트리의 높이를 계산하고 균형잡혀 있는지를 동시에 확인할 수 있는가?
 * 여러 개의 값을 동시에 반환하는 함수를 고려해보라.
 * #124. 사실 checkHeight 함수 하나만 있으면 높이 계산과 균형 확인을 동시에 할 수 있다. 반환되는 정수값을 이용해서 이 둘을 확인할 수 있다.
 */
public class Q4 {

    public static void main(String[] args) {
        Q4 q4 = new Q4();
        TreeNode left = new TreeNode(4, new TreeNode(2), new TreeNode(6));
        TreeNode root = new TreeNode(8, left, null);
        System.out.println(q4.solution_isBalanced(root));
    }

    // Solution 1. 전체 트리를 재귀적으로 순회하면서, 각 노드에 대해 하위 트리의 높이를 계산
    // - time: O(NlogN)
    public boolean solution_isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        int heightDiff = solution_getHeight(root.left) - solution_getHeight(root.right);
        if (Math.abs(heightDiff) > 1) {
            return false;
        } else {
            return solution_isBalanced(root.left) && solution_isBalanced(root.right);
        }
    }

    private int solution_getHeight(TreeNode root) {
        if (root == null) {
            return -1;
        }
        return Math.max(solution_getHeight(root.left), solution_getHeight(root.right)) + 1;
    }

    // Solution 2.
    // - time: O(N)
    // - space: O(H) (H: 트리의 높이)
    public boolean solution_isBalanced_optimized(TreeNode root) {
        return solution_checkHeight(root) != Integer.MIN_VALUE;
    }

    private int solution_checkHeight(TreeNode root) {
        if (root == null) {
            return -1;
        }

        int leftHeight = solution_checkHeight(root.left);
        if (leftHeight == Integer.MIN_VALUE) { // 에러 반환
            return Integer.MIN_VALUE;
        }

        int rightHeight = solution_checkHeight(root.right);
        if (rightHeight == Integer.MIN_VALUE) { // 에러 반환
            return Integer.MIN_VALUE;
        }

        int heightDiff = leftHeight - rightHeight;
        if (Math.abs(heightDiff) > 1) {
            return Integer.MIN_VALUE; // 에러 반환
        } else {
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }
}
