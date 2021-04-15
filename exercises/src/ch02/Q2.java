package ch02;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 2.2. 뒤에서 k번째 원소 구하기: 단방향 연결리스트, 뒤에서 k번째 원소 찾기
 *
 * #8. 연결리스트의 크기를 알고 있다면 어떠한가? 마지막에서 k번째 원소를 찾는 것과 X번째 원소를 찾는 것의 다른 점은 무엇인가?
 * #25. 연결리스트의 크기를 모른다면, 계산해서 알아낼 수 있는가? 이 방법이 수행 시간에 어떤 영향을 미치는가?
 * #41. 재귀로 구현해 보라. 마지막에서 (k-1)번째 원소를 찾을 수 있다면, k번째 원소도 찾을 수 있겠는가?
 * #67. 값을 여러 개 반환하는 것이 유용할 수도 있다.
 * #126. 순환적 방법으로 할 수 있는가? 두 포인터가 인접한 노드를 가리키고 있고 같은 속도로 연결리스트를 순회한다고 가정해 보자.
 * 포인터 하나가 연결리스트의 끝에 도달했을 때 다른 하나는 어디에 있겠는가?
 */
public class Q2 {

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(9);
        head.next.next = new Node(3);
        head.next.next.next = new Node(1);
        head.next.next.next.next = new Node(4);
        head.next.next.next.next.next = new Node(9);
        head.next.next.next.next.next.next = new Node(9);
        head.next.next.next.next.next.next.next = new Node(8);

        Q2 q2 = new Q2();
        System.out.println(q2.findKthBehindNode_stack(head, 4).data);
        System.out.println(q2.findKthBehindNode_iterative(head, 4).data);
    }

    // Solution 1. Recursion
    // 1) 연결리스트를 재귀적으로 순회한다.
    // 2) 마지막 원소를 만나면, 0으로 설정된 카운터 값을 반환한다.
    // 3) 부모 메서드는 그 값에 1을 더한다. 그러다 카운터 값이 k가 되는 순간, 뒤에서 k번째 원소에 도달한다.
    // 하지만 불행히도 노드와 카운터 값을 동시에 반환할 수가 없다. 어떻게 해야 할까?
    // -> 방법 C: Wrapper 클래스 구현 / 원소 하나짜리 배열 사용 (참조에 의한 값 전달 흉내) / static 변수 사용
    //
    // - space: O(N) (재귀 호출)
    class Index {
        public int value = 0;
    }

    public LinkedListNode solution_kthToLast(LinkedListNode head, int k) {
        Index idx = new Index();
        return solution_kthToLast(head, k, idx);
    }

    private LinkedListNode solution_kthToLast(LinkedListNode head, int k, Index idx) {
        if (head == null) {
            return null;
        }
        LinkedListNode node = solution_kthToLast(head.next, k, idx);
        idx.value = idx.value + 1;
        if (idx.value == k) {
            return head;
        }
        return node;
    }

    // Solution 2. Iteration
    // 1) 두 개의 포인터 p1, p2
    // - p2: 연결리스트의 시작 노드를 가리킴
    // - p1: k 노드만큼 움직여서 p2과 k 노드만큼 떨어져 있게 함
    // 2) p1은 LENGTH-k번 후 연결리스트의 맨 마지막 노드에 도달하게 된다.
    // 이때 p2는 LENGTH-k번 노드, 즉 뒤에서부터 k번째 노드를 가리키게 된다.
    //
    // - time: O(N)
    // - space: O(1)
    public LinkedListNode solution_nthToLast(LinkedListNode head, int k) {
        LinkedListNode p1 = head;
        LinkedListNode p2 = head;

        // p1을 k노드만큼 이동시킨다.
        for (int i = 0; i < k; i++) {
            if (p1 == null) {
                return null; // out of bounds
            }
            p1 = p1.next;
        }

        // p1과 p2를 함께 움직인다.
        // p1이 끝에 도달하면, p2는 LENGTH-k번째 원소를 가리키게 된다.
        while (p1 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p2;
    }

    // Method 1. Stack
    // - time: O(N)
    // - space: O(N)
    public Node findKthBehindNode_stack(Node head, int k) {
        if (k < 1) {
            throw new IllegalArgumentException();
        }
        Deque<Node> stack = new ArrayDeque<>();
        while (head != null) {
            stack.push(head);
            head = head.next;
        }
        while (k > 1) {
            stack.pop();
            k--;
        }
        return stack.pop();
    }

    // Method 2. 첫 번째 순회에서 전체 길이 구하고, 두 번째 순회에서 노드 찾기
    // - time: O(N)
    // - space: O(1)
    public Node findKthBehindNode_iterative(Node head, int k) {
        int N = 0;
        Node curr = head;
        while (curr != null) {
            N++;
            curr= curr.next;
        }

        int i = N - k;
        while (head != null && i > 0) {
            i--;
            head = head.next;
        }
        return head;
    }
}
