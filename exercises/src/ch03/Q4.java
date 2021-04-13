package ch03;

/**
 * 3.4. 스택으로 큐
 *
 * #98. 큐와 스택의 가장 큰 차이점은 원소의 순서다. 큐는 오래된 원소부터 삭제하고 스택은 최근 원소부터 삭제한다.
 * 스택의 최근 원소에만 접근이 가능할 때, 스택에서 가장 오래된 원소를 삭제하려면 어떻게 해야 할까?
 * #114.
 */
public class Q4 {

    public static void main(String[] args) {
        StackQueue<Integer> queue = new StackQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        queue.enqueue(4);
        System.out.println(queue.dequeue());
    }

    static class StackQueue<T> {

        private MyStack<T> enqueueStack;
        private MyStack<T> dequeueStack;

        public StackQueue() {
            enqueueStack = new MyStack<>();
            dequeueStack = new MyStack<>();
        }

        public void enqueue(T data) {
            enqueueStack.push(data);
        }

        public T dequeue() {
            shiftStacks();
            return dequeueStack.pop();
        }

        public T peek() {
            shiftStacks();
            return dequeueStack.peek();
        }

        private void shiftStacks() {
            if (dequeueStack.isEmpty()) {
                while (!enqueueStack.isEmpty()) {
                    dequeueStack.push(enqueueStack.pop());
                }
            }
        }
    }
}
