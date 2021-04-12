# Week5 (21.04.05.월)

## 학습한 내용

### DFS & BFS

- DFS vs. BFS
  - **방문 체크 타이밍(visited check timing)**
 
    cf. https://stackoverflow.com/questions/25990706/breadth-first-search-the-timing-of-checking-visitation-status
  
    - DFS: after pop
    - BFS: before enqueue (or after dequeue: 중복 노드 추가, 메모리 낭비)
 
- iterative DFS vs. recursive DFS
  - **방문 순서(visit order)**

    cf. https://stackoverflow.com/questions/9201166/iterative-dfs-vs-recursive-dfs-and-different-elements-order
 
    - iterative DFS: 스택에 push한 마지막 이웃노드부터 방문
    - recursive DFS: 재귀 호출한 첫번째 이웃노드부터 방문
    
    동일한 결과를 얻기 위해서는 iterative DFS에서 스택에 역순으로 노드를 push하면 된다.