package ch04;

public class MyTreeNode {

    public int value;
    public MyTreeNode left;
    public MyTreeNode right;

    public MyTreeNode(int value) {
        this.value = value;
    }

    public MyTreeNode(int value, MyTreeNode left, MyTreeNode right) {
        this(value);
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
