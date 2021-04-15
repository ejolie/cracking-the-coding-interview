package ch02;

public class Node {

    public Node next;
    public int data;

    public Node(int d) {
        data = d;
    }

    public void appendToTail(int d) {
        Node end = new Node(d);
        Node n = this;
        while (n.next != null) {
            n = n.next;
        }
        n.next = end;
    }

    public Node deleteNode(Node head, int d) {
        Node n = head;
        if (n.data == d) {
            return head.next; // head를 움직인다.
        }

        while (n.next != null) {
            if (n.next.data == d) {
                n.next = n.next.next;
                return head; // head는 변하지 않는다.
            }
            n = n.next;
        }
        return head;
    }

    public void print() {
        Node n = this;
        while (n != null) {
            System.out.printf("%d ", n.data);
            n = n.next;
        }
        System.out.println();
    }
}
