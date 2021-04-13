# 03. 스택과 큐(p.144-148)



## 스택 구현하기

- 스택
  - 데이터를 쌓아 올린다(stack).
  - LIFO(Last-In-First-Out)에 따라 자료를 배열한다.
  - **배열과 달리 상수 시간에 i번째 항목에 접근할 수 없다. 하지만 스택에서 데이터를 추가하거나 삭제하는 연산은 상수 시간에 가능하다.**
- 연산
  - `pop()`
  - `push(item)`
  - `peek()`
  - `isEmpty()`

- 구현

  - 스택은 같은 방향에서 아이템을 추가하고 삭제한다는 조건하에 연결리스트로 구현할 수도 있다.

  ```java
  public class MyStack {
    
    private static class StackNode {
      private T data;
      private StackNode next;
      
      public StackNode(T data) {
        this.data = data;
      }
    }
    
    private StackNode top;
    
    public T pop() {
      if (top == null) throw new EmptyStackException();
      T item = top.data;
      top = top.next;
      return item;
    }
    
    public void push(T item) {
      StackNode t = new StackNode(item);
      t.next = top;
      top = t;
    }
    
    public T peek() {
       if (top == null) throw new EmptyStackException();
  		return top.data;
    }
    
    public boolean isEmpty() {
      return top == null;
    }
  }
  ```

- **재귀 알고리즘을 사용할 때 유용하다.**
  - 재귀적으로 함수를 호출해야 하는 경우에 임시 데이터를 스택에 넣어 주고, 재귀 함수를 빠져 나와 **퇴각 검색(backtrack)**을 할때는 스택에 넣어 두었던 임시 데이터를 빼 줘야 한다.
- **재귀 알고리즘을 반복적 형태(iterative)를 통해서 구현할 수 있게 해준다.**





## 큐 구현하기

- 큐

  - FIFO(First-In-First-Out)

- 연산

  - `add(item)`
  - `remove()`
  - `peek()`
  - `isEmpty()`

- 구현

  - 큐 또한 연결리스트로 구현할 수 있다. 연결리스트의 반대 방향에서 항목을 추가하거나 제거하도록 구현한다면 근본적으로 큐와 같다.

  ```java
  public class MyQueue {
    
    private static class QueueNode {
      private T data;
      private QueueNode next;
      
      public QueueNode(T data) {
        this.data = data;
      }
    }
    
    private QueueNode first;
    private QueueNode last;
    
    public void add(T item) {
      QueueNode t = new QueueNode(item);
      if (last != null) {
        last.next = t;
      }
      last = t;
      if (first == null) {
        first = last;
      }
    }
    
    public T remove() {
      if (first == null) throw new NoSuchElementException();
      T data = first.data;
  		first = first.next;
      if (first == null) {
        last = null;
      }
      return data;
    }
    
    public T peek() {
      if (first == null) throw new NoSuchElementException();
      return first.data;
    }
    
    public boolean isEmpty() {
      return first == null;
    }
  }
  ```

- 큐에서 처음과 마지막 노드를 갱신할 때 실수가 나오기 쉽다.
- **너비 우선 탐색(breadth-first search)을 하거나 캐시를 구현하는 경우에 종종 사용된다.**
  - 노드를 하나 처리할 때마다 해당 노드와 인접한 노드들을 큐에 다시 저장한다. 이렇게 함으로써 노드를 접근한 순서대로 처리할 수 있게 된다.



## 면접 문제

1. 한 개로 세 개
2. [스택 Min](Q2.java)
3. 접시 무더기
4. [스택으로 큐](Q4.java)
5. 스택 정렬
6. 동물 보호소