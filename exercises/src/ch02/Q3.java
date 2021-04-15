package ch02;

/**
 * 2.3. 중간 노드 삭제: 단방향 연결리스트. 중간에 있는 노드를 하나 삭제. 단, 삭제할 노드에만 접근 가능.
 *
 * #72.
 */
public class Q3 {

    public static void main(String[] args) {
        LinkedListNode node = new LinkedListNode(1);
        node.next = new LinkedListNode(5);
        node.next.next = new LinkedListNode(9);
        node.next.next.next = new LinkedListNode(12);

        Q3 q3 = new Q3();
        System.out.println(q3.solution_deleteNode(node));
        while (node != null) {
            System.out.printf("%d ", node.data);
            node = node.next;
        }
    }

    // Solution 1. 다음 노드의 데이터를 현재 노드에 복사한 다음, 다음 노드를 지운다.
    // - 삭제할 노드가 리스트의 마지막 노드인 경우에는 풀 수 없다.
    // 마지막 노드인 경우에는 그냥 빈자리(dummy) 노드라고 표시해 두는 것도 한 방법이다.
    public boolean solution_deleteNode(LinkedListNode n) {
        if (n == null || n.next == null) {
            return false; // 실패
        }
        LinkedListNode next = n.next;
        n.data = next.data;
        n.next = next.next;
        return true;
    }
}
