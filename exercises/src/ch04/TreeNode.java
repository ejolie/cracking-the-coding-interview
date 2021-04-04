package ch04;

public class TreeNode {

    public int value;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int value) {
        this.value = value;
    }

    public TreeNode(int value, TreeNode left, TreeNode right) {
        this(value);
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
