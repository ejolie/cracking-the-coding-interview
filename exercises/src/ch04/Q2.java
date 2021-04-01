package ch04;

/**
 * 4.2. 최소 트리: 오름차순으로 정렬된 정수 배열, 중복 값 X, 높이가 최소가 되는 이진 탐색 트리 만들기
 *
 * #19. 최소 이진 트리: 모든 노드에서 왼쪽 노드의 개수와 오른쪽 노드의 개수가 같은 경우. 일단 루트에서 어떻게 왼쪽, 오른쪽 같은 개수 노드 확인?
 * #73. 다음에 추가해야 할 '이상적인' 원소를 찾은 뒤 insertValue를 반복적으로 호출. 재귀 호출 추천.
 * #116. 배열이 주어졌을 때 최소 트리를 반환하는 createMinimalTree. 루트에 적용 가능한지?
 */
public class Q2 {

    boolean[] used;

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 4, 8, 9, 15, 30};

        Q2 q2 = new Q2();
        TreeNode result1 = q2.createMinimalTree(nums);
        TreeNode result2 = q2.solution_createMinimalBST(nums);
        inOrderTraversal(result1);
        inOrderTraversal(result2);
    }

    public TreeNode solution_createMinimalBST(int[] arr) {
        return solution_createMinimalBST(arr, 0, arr.length - 1);
    }

    private TreeNode solution_createMinimalBST(int[] arr, int start, int end) {
        if (end < start) {
            return null;
        }
        int mid = (start + end) / 2;
        TreeNode node = new TreeNode(arr[mid]);
        node.left = solution_createMinimalBST(arr, start, mid - 1);
        node.right = solution_createMinimalBST(arr, mid + 1, end);

        return node;
    }

    public TreeNode createMinimalTree(int[] nums) {
        TreeNode root = insertValue(nums, 0, nums.length - 1);
        return root;
    }

    private TreeNode insertValue(int[] nums, int left, int right) {
        if (left <= right) {
            int mid = left + (right - left) / 2;
            TreeNode root = new TreeNode(nums[mid]);
            root.left = insertValue(nums, left, mid - 1);
            root.right = insertValue(nums, mid + 1, right);

            return root;
        }
        return null;
    }

    private static void inOrderTraversal(TreeNode node) {
        if (node != null) {
            inOrderTraversal(node.left);
            System.out.println(node.value);
            inOrderTraversal(node.right);
        }
    }
}
