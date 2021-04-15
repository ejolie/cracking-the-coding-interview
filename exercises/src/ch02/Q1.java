package ch02;

import java.util.HashSet;
import java.util.Set;

/**
 * 2.1. 중복 없애기: 정렬 되지 않은 연결리스트. 중복되는 원소 제거하기.
 *
 * #9. 해시테이블을 생각해 보았나? 연결리스트를 한 번만 읽어서 해결할 수 있어야 한다.
 * #40. 추가 공간 없이 O(N^2) 시간에 할 수 있을 것이다. 포인터 두 개를 사용해라.
 * 두 번째 포인터는 첫 번째 포인터 앞에서 검색을 해나갈 것이다.
 * [연관문제] 임시 버퍼를 사용할 수 없다면 어떻게 풀 수 있을까?
 */
public class Q1 {

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(9);
        head.next.next = new Node(3);
        head.next.next.next = new Node(1);
        head.next.next.next.next = new Node(4);
        head.next.next.next.next.next = new Node(9);
        head.next.next.next.next.next.next = new Node(9);
        head.next.next.next.next.next.next.next = new Node(8);

        head.print();

        Q1 q1 = new Q1();
//        q1.removeDuplicate(head);
        q1.removeDuplicateWoSpace(head);
        head.print();
    }

    // Solution 1. Hash table
    // - time: O(N)
    public void solution_deleteDups_hashSet(LinkedListNode n) {
        HashSet<Integer> set = new HashSet<>();
        LinkedListNode previous = null;
        while (n != null) {
            if (set.contains(n.data)) {
                previous.next = n.next;
            } else {
                set.add(n.data);
                previous = n;
            }
            n = n.next;
        }
    }

    // Solution 2. Runner pattern - Two pointer
    // - time: O(N^2)
    // - space: O(1)
    public void solution_deleteDups_runner(LinkedListNode head) {
        LinkedListNode current = head;
        while (current != null) {
            // 값이 같은 다음 노드들을 모두 제거한다.
            LinkedListNode runner = current;
            while (runner.next != null) {
                if (runner.next.data == current.data) {
                    runner.next = runner.next.next;
                } else {
                    runner = runner.next;
                }
            }
            current = current.next;
        }
    }

    // Method 1. HashSet, Two pointer
    // - time: O(N)
    // - space: O(N)
    public void removeDuplicate(Node head) {
        if (head.next == null) {
            return;
        }

        Set<Integer> set = new HashSet<>();
        Node slow = head;
        Node fast = head.next;
        set.add(slow.data);

        while (fast != null) {
            if (set.contains(fast.data)) {
                slow.next = fast.next;
            } else {
                set.add(fast.data);
                slow = slow.next;
            }
            fast = fast.next;
        }
    }

    // Method 2. Two pointer
    // - time: O(N^2)
    // - space: O(1)
    public void removeDuplicateWoSpace(Node head) {
        if (head.next == null) {
            return;
        }

        Node i = head;
        while (i != null) {
            Node prev = i;
            Node j = i.next;
            while (j != null) {
                if (j.data == i.data) {
                    prev.next = j.next;
                } else {
                    prev = prev.next;
                }
                j = j.next;
            }
            i = i.next;
        }
    }
}