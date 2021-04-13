package ch03;

import java.util.Stack;

/**
 * 3.2. 스택 Min: push, pop + min 함수 추가. 모두 O(1) 시간 안에 동작해야 함.
 *
 * #27. 최소 원소는 자주 변하지 않는다. 더 작은 원소가 추가되거나 최소 원소를 빼내야 할 때만 바뀐다.
 * #59. 각각의 스택 노드에서 추가 데이터를 저장하고 있다면 어떨까? 어떤 종류의 데이터를 갖고 있어야 문제를 풀기 쉬워지나?
 * #78. 각각의 노드가 '부분스택'(자신을 포함해서 자신보다 아래에 있는 모든 원소)의 최솟값을 알고 있다고 가정하자.
 */
public class Q2 {

    public static void main(String[] args) {
        Q2Stack q2Stack = new Q2Stack();
        q2Stack.push(3);
        System.out.println(q2Stack.min());
        q2Stack.push(4);
        System.out.println(q2Stack.min());
        q2Stack.push(1);
        System.out.println(q2Stack.min());
        q2Stack.push(6);
        System.out.println(q2Stack.min());
    }

    // Solution 1. min 값들을 기록하는 스택을 하나 더 둔다.
    public class StackWithMin2 extends Stack {
        Stack minStack;

        public StackWithMin2() {
            minStack = new Stack();
        }

        public void push(int value) {
            if (value <= min()) {
                minStack.push(value);
            }
            super.push(value);
        }

        public Integer pop() {
            int value = (int) super.pop();
            if (value == min()) {
                minStack.pop();
            }
            return value;
        }

        public int min() {
            if (minStack.isEmpty()) {
                return Integer.MAX_VALUE; // 에러 값
            }
            return (int) minStack.peek();
        }
    }

    // Method 1. 스택의 각 상태마다 최솟값을 함께 기록한다.
    static class Q2Stack {

        // 앞쪽으로만 넣고 빼는 LinkedList와 동일
        private static class StackNode {
            private int data;
            private StackNode next;
            private int minToHere;

            public StackNode(int data) {
                this.data = data;
            }
        }

        private StackNode top;

        public void push(int item) {
            StackNode node = new StackNode(item);
            if (top == null) {
                node.minToHere = item;
            } else {
                node.minToHere = Math.min(top.minToHere, item);
            }
            node.next = top;
            top = node;
        }

        public StackNode pop() {
            if (top == null) {
                throw new IllegalStateException("Stack is empty.");
            }
            StackNode node = top;
            top = top.next;
            return node;
        }

        public int min() {
            if (top == null) {
                throw new IllegalStateException("Stack is empty.");
            }
            return top.minToHere;
        }
    }
}