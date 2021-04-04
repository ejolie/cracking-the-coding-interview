# 04. 트리와 그래프(p.149-162)

## 트리의 종류
- 트리
  - 노드로 이루어진 자료구조
  - 재귀적 설명법
    - 트리는 하나의 루트 노드를 갖는다.
    - 루트 노드는 0개 이상의 자식 노드를 갖고 있다.
    - 그 자식 노드 또한 0개 이상의 자식 노드를 갖고 있고, 이는 반복적으로 정의된다.
  - 사이클 존재 X
  - 노드들이 특정 순서로 나열 O or X
  - 각 노드는 부모 노드로의 연결이 O or X
  - 각 노드는 어떤 자료형으로도 표현 가능하다.

```java
class Node {
  public String name;
  public Node[] children;
}
```



### 트리(tree) vs. 이진 트리(binary tree)

모든 트리가 이진 트리는 아니다.

- 이진 트리(binary tree)
  - **각 노드가 최대 두 개의 자식을 갖는 트리**

- 삼진 트리(ternary tree), ..., 10차 트리(10-ary tree)



### 이진 트리(binary tree) vs. 이진 탐색 트리(binary search tree)

- 이진 탐색 트리(binary search tree)

  - **모든 노드가 다음과 같은 특정 순서를 따르는 속성이 있는 이진 트리**

    > 모든 왼쪽 자식들 <= n <= 모든 오른쪽 자식들 (for 모든 노드 n)

  - 같은 값을 처리하는 방식에 따라 이진 탐색 트리는 약간씩 정의가 달라질 수 있다.

  - 부등식의 경우에 대해서는 바로 아래 자식뿐만 아니라 내 밑에 있는 모든 자식 노드들에 대해서 참이어야 한다.



### 균형(balanced) vs. 비균형(unbalanced)

- 균형을 잡는다 != 왼쪽과 오른쪽 부분 트리의 크기가 완전히 같게 한다. (like 완전 이진 트리)
- **O(logN) 시간에 insert과 find를 할 수 있을 정도로 균형이 잘 잡혀 있지만, 그렇다고 꼭 완벽하게 균형 잡혀 있을 필요는 없다.**
- 균형 트리의 일반적인 유형
  - 레드-블랙 트리
  - AVL 트리

  
### 완전 이진 트리(complete binary tree)

- 완전 이진트리(complete binary tree)
  - 트리의 모든 높이에서 노드가 꽉 차 있는 이진 트리
  - 마지막 단계(level)는 꽉 차 있지 않아도 되지만 노드가 왼쪽부터 채워져야 한다.




### 전 이진 트리(full binary tree)

- 전 이진 트리(full binary tree)
  - 모든 노드의 자식이 0개 or 2개
  - 자식이 1개인 노드는 존재하지 X




### 포화 이진 트리(perfect binary tree)

- 포화 이진 트리(perfect binary tree)
  - 전 이진 트리(full binary tree) && 완전 이진 트리(complete binary tree)
  - 모든 말단 노드는 같은 높이에 있어야 하며, 마지막 단계에서 노드의 개수가 최대가 되어야 한다.
  - 노드의 개수가 정확히 2^k - 1(k: 트리의 높이)개여야 한다.




## 이진 트리 순회

가장 빈번하게 사용되는 순회 방식은 중위 순회이다.

### 중위 순회(in-order traversal)

- 중위 순회(in-order traversal)
  - 왼쪽 가지(branch) -> **현재 노드** -> 오른쪽 가지 순서로 노드를 방문
- 이진 탐색 트리 + 중위 순회: 오름차순 방문




### 전위 순회(pre-order traversal)

- 전위 순회(pre-order traversal)
  - **현재 노드** -> 자식 노드(보통 왼쪽 가지 -> 오른쪽 가지) 순서로 노드를 방문
  - 첫 번째 방문: 루트 노드




### 후위 순회(post-order traversal)

- 후위 순회(post-order traversal)
  - 자식 노드(보통 왼쪽 가지 -> 오른쪽 가지) -> **현재 노드** 순서로 노드를 방문
  - 마지막 방문: 루트 노드



## 이진 힙(최소힙과 최대힙)
- 최소힙(min-heaps)
  - 트리의 마지막 단계에서 오른쪽 부분을 뺀 나머지 부분이 가득 채워져 있음 -> 완전 이진 트리(complete binary tree)
  - 각 노드의 원소가 자식들의 원소보다 작다.
  - 루트: 트리 전체에서 가장 작은 원소
- 최대힙(max-heaps)
  - 최소힙과의 차이: 원소가 내림차순으로 정렬됨



### 삽입(`insert`)

- 최소힙에 새로운 원소 삽입 시: **트리 밑바닥 가장 오른쪽 위치**로 삽입
- 새로 삽입된 원소가 제대로 된 자리를 찾을 때까지 부모 노드와 교환해 나간다. 이런 방식으로 **최소 원소를 위쪽으로 올린다.**
- Time complexity: O(logN) (N: 힙 노드 개수)



### 최소 원소 뽑아내기(`extract_min`)

- 최소힙의 최솟값: 루트 노드에 위치

- 최솟값 제거 방법

  1. 최소 원소를 제거한 후 힙에 있는 가장 마지막 원소(밑바닥 가장 왼쪽에 위치한 원소)와 교환한다.

  2. 최소힙 성질을 만족하도록, 해당 노드를 **자식 노드와 교환해 나감으로써 밑으로 내보낸다.**

     - 왼쪽 자식과 오른쪽 자식 중 누구와 교환해야 하는가?

       최소힙의 속성을 유지하기 위해선 더 작은 원소와 교환해야 한다.

- Time complexity: O(logN)



## 트라이(접두사 트리)

- 트라이(trie) = 접두사 트리(prefix tree)
  - **n-차 트리(n-ary tree)의 변종으로 각 노드에 문자를 저장하는 자료구조**
- 특징
  - 트리를 아래쪽으로 순회하면 단어 하나가 나온다.
  - 각 노드는 1개에서 ALPHABET_SIZE + 1개까지 자식을 갖고 있을 수 있다. (`*` 노드 대신 불린 플래그로 표현했다면 0개에서 ALPHABET_SIZE까지)
  - 접두사를 빠르게 찾아보기 위한 아주 흔한 방식
- 널 노드(null node)
  - 단어의 끝을 나타냄
  - 구현 방법
    - `*` 노드
    - TrieNode를 상속한 TerminatingTrieNode
    - 부모 TrieNode에 불린 플래그



## 그래프

트리(tree)는 그래프(graph)의 한 종류다. **트리는 사이클(cycle)이 없는 하나의 연결 그래프(connected graph)이다.**

- 그래프
  - 노드와 그 노드를 연결하는 간선(edge)을 하나로 모아 놓은 것
- 특징
  - 방향성
    - O: 단방향 그래프(directed graph) -> 일방통행 도로
    - X: 무방향 그래프(undirected graph) -> 양방향 통행 도로
  - 연결성
    - 여러 개의 고립된 부분 그래프(isolated subgraphs)
    - 연결 그래프(connected graph): 모든 정점 쌍(a pair of vertices) 간에 경로가 존재하는 그래프
  - 사이클
    - O: 순환 그래프(cyclic graph)
    - X: 비순환 그래프(acyclic graph)

- 그래프 표현 방법
  - 인접 리스트
  - 인접 행렬



### 인접 리스트

- 인접 리스트(adjacency list)
  - 모든 정점(= 노드)을 인접 리스트에 저장한다.
  - 무방향 그래프(undirected graph)에서 (a, b) 간선은 두 번 저장된다.
  - 가장 일반적인 방법

```java
class Node {
  public String name;
  public Node[] children;
}

class Graph {
  public Node[] nodes;
}
```



### 인접 행렬

- 인접 행렬(adjacency matrix)
  - N*N 불린 행렬
    - `matrix[i][j] == true`: `i` 에서 `j` 로의 간선이 있다.
  - 무방향 그래프를 인접 행렬로 표현하면 이 행렬은 대칭행렬(symmetric matrix)이 된다.
  - 인접 리스트에 비해 효율성이 떨어진다.
    - 인접 리스트: 어떤 노드에 인접한 노드들을 쉽게 찾을 수 있다.
    - 인접 행렬: 어떤 노드에 인접한 노드를 차직 위해서는 모든 노드를 전부 순회해야 한다.



### 그래프 탐색

- 그래프 탐색 방법
  - 깊이 우선 탐색
  - 너비 우선 탐색



#### 깊이 우선 탐색(DFS, depth-first search)

- 깊이 우선 탐색
  - 루트 노드(혹은 다른 임의의 노드)에서 시작해서 **다음 분기(branch)로 넘어가기 전에 해당 분기를 완벽하게 탐색**하는 방법
  - a의 이웃인 b의 분기를 전부 완벽하게 탐색한 뒤에야 a의 다른 이웃 노드를 방문할 수 있다.

- 특징
  - 그래프에서 모든 노드를 방문하고자 할 때 더 선호되는 방법(더 간단)
  - 전위 순회를 포함한 다른 형태의 트리 순회는 모두 DFS의 한 종류
    - 트리 순회와 그래프 탐색의 차이점: **그래프 탐색은 어떤 노드를 방문했었는지 여부를 반드시 검사해야 한다.**
- 구현 방법
  - 재귀 호출
  - Stack

```java
void search(Node root) {
  if (root == null) return;
  visit(root);
  root.visited = true; // 방문 체크
  for (Node n : root.adjacent) {
    if (n.visited == false) {
      search(n);
    }
  }
}
```



#### 너비 우선 탐색(BFS, breadth-first search)

- 너비 우선 탐색
  - 루트 노드(혹은 다른 임의의 노드)에서 시작해서 **인접한 노드를 먼저 탐색**하는 방법
  - a 노드의 이웃 노드를 모두 방문한 다음에 이웃의 이웃들을 방문한다. 즉, a에서 시작해서 거리에 따라 단계별로 탐색한다.

- 특징
  - **두 노드 사이의 최단 경로 혹은 임의의 경로를 찾고 싶을 때** 더 효율적인 방법
  - DFS는?
    - 정답에서 동떨어진 경로를 계속해서 탐색할 수 있다.
    - 경로를 찾을 수 있겠지만 굉장히 오래걸리고 최단 경로가 아닐 수 있다.
- 구현 방법
  - Queue

```java
void search(Node root) {
  Queue queue = new LinkedList();
  root.marked = true;
  queue.enqueue(root);
  
  while (!queue.isEmpty()) {
    Node r = queue.dequeue();
    visit(r);
    for (Node n : root.adjacent) {
      if (n.visited == false) {
        n.marked = true; // 방문 체크
        queue.enqueue(n);
      }
    }
  }
}
```



#### 양방향 탐색(bidirectional search)

- 양방향 탐색
  - **출발지와 도착지 두 노드에서 동시에 너비 우선 탐색을 수행한 뒤, 두 탐색 지점이 충돌하는 경우에 경로를 찾는 방식**
- 특징 
  - 출발지와 도착지 사이에 최단 경로를 찾을 때 사용됨



## 추가로 읽을거리

### 위상정렬(topological sort)(p.808)



### 다익스트라 알고리즘(dijkstra's algorithm)(p.810)



### AVL 트리(p.816)



### 레드-블랙 트리(p.818)



## 면접 문제

1. [노드 사이의 경로](Q1.java)
2. [최소 트리](Q2.java)
3. [깊이의 리스트](Q3.java)
4. [균형 확인](Q4.java)
5. [BST 검증](Q5.java)
6. 후속자
7. 순서 정하기
8. 첫 번째 공통 조상
9. BST 수열
10. 하위 트리 확인
11. 임의의 노드
12. 합의 경로