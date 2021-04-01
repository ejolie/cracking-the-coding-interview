package ch04;

public class Node {

    public String name;
    public Node[] adjacent;
    public State state;

    public Node(String name) {
        this.name = name;
    }

    public Node(String name, Node[] adjacent) {
        this(name);
        this.adjacent = adjacent;
    }
}
