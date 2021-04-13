# 02. 연결리스트(p.139-143)

- 연결리스트: 차례로 연결된 노드를 표현해주는 자료구조
  - 단방향 연결리스트: 각 노드는 다음 노드를 가리킨다.
  - 양방향 연결리스트: 각 노드는 다음 노드와 이전 노드를 함께 가리킨다.
- 단점: **배열과는 달리 특정 인덱스를 상수 시간에 접근할 수 없다.**
- 장점: **리스트의 시작 지점에 아이템을 추가하거나 삭제하는 연산을 상수 시간에 할 수 있다.**



## 연결 리스트 만들기

- 아주 기본적인 단방향 연결리스트

  ```java
  class Node {
    Node next;
    int data;
    
    public Node(int data) {
      this.data = data;
    }
    
    void appendToTail(int d) {
      Node end = new Node(d);
      Node n = this;
      while (n.next != null) {
        n = n.next;
      }
      n.next = end;
    }
  }
  ```



## 단방향 연결리스트에서 노드 삭제

- 단방향 연결리스트
  - 노드 `n`이 주어지면, 그 이전 노드 `prev`를 찾아 `prev.next`를 `n.next`와 같도록 설정한다.
- 양방향 연결리스트
  - `n.next`가 가리키는 노드를 갱신하여 `n.next.prev`가 `n.prev`와 같도록 설정해야 한다.
  - 주의사항
    1. 널 포인터 검사를 반드시 해야 한다.
    2. 필요하면 `head`와 `tail` 포인터도 갱신해야 한다.

```java
Node deleteNode(Node head, int d) {
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
```



## Runner 기법

- Runner(부가 포인터)
  - 연결리스트 문제에서 많이 활용되는 기법
  - **연결리스트를 순회할 때 두 개의 포인터를 동시에 사용한다. 이때 한 포인터가 다른 포인터보다 앞서도록 한다.** 앞선 포인터가 따라오는 포인터보다 항상 지정된 개수만큼을 앞서도록 할 수도 있고, 아니면 따라오는 포인터를 여러 노드를 한 번에 뛰어넘도록 설정할 수도 있다.



## 재귀 문제

- 연결리스트 관련 문제 가운데 상당수는 재귀 호출에 의존한다.
- **하지만 재귀 호출 깊이가 n이 될 경우, 해당 재귀 알고리즘이 적어도 O(n) 만큼의 공간을 사용한다는 사실을 기억하자.**



## 면접 문제

1. 중복 없애기
2. 뒤에서 k번째 원소 구하기
3. 중간 노드 삭제
4. 분할
5. 리스트의 합
6. 회문
7. 교집합
8. 루프 발견

