package ch02;

public class LinkedListNode {

    public LinkedListNode next;
    public LinkedListNode prev;
    public LinkedListNode last;
    public int data;

    public LinkedListNode(int data) {
        this.data = data;
    }

    public LinkedListNode(int data, LinkedListNode next, LinkedListNode prev) {
        this(data);
        setNext(next);
        setPrevious(prev);
    }

    public void setNext(LinkedListNode next) {
        this.next = next;
        if (this == last) {
            last = next;
        }
        if (next != null && next.prev != this) {
            next.setPrevious(this);
        }
    }

    public void setPrevious(LinkedListNode prev) {
        this.prev = prev;
        if (prev != null && prev.next != this) {
            prev.setNext(this);
        }
    }

    public LinkedListNode clone() {
        LinkedListNode next2 = null;
        if (next != null) {
            next2 = next.clone();
        }
        LinkedListNode head2 = new LinkedListNode(data, next2, null);
        return head2;
    }
}
