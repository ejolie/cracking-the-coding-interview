# 01. 배열과 문자열(p.133-138)
- 배열이나 문자열에 대한 문제들은 서로 대체 가능하다.

## 해시테이블
- 해시테이블(hash table)
  - 효율적인 탐색을 위한 자료구조
  - 키(key)를 값(value)에 대응시킴
- 간단한 구현방법: 연결리스트(linked list) + 해시 코드 함수(hash code function)
- 해시테이블에 키와 값을 넣는 과정
  1. 키의 해시 코드를 계산한다. 키의 자료형은 보통 int 또는 long이다. **키의 개수는 무한하지만 int의 개수는 유한하므로 서로 다른 두 개의 키가 같은 해시 코드를 가리킬 수 있다는 사실을 명심하라.**
  2. `hash(key) % array_length`와 같이 해시 코드를 이용해 배열의 인덱스를 구한다.
  3. 배열의 각 인덱스에는 키와 값으로 이루어진 연결리스트가 존재한다. 키와 값을 해당 인덱스에 저장한다. **충돌에 대비해서 반드시 연결리스트를 이용해야 한다.**
    
    > 충돌: 서로 다른 두 개의 키가 같은 해시 코드를 가리키거나 서로 다른 두 개의 해시 코드가 같은 인덱스를 가리키는 경우
- 탐색 시간복잡도
  - 충돌이 자주 발생하는 최악의 경우의 수행 시간(worst case runtime) -> O(N) (N은 키의 개수)
  - 일반적으로 해시에 대해 이야기할 때는 충돌을 최소화하도록 잘 구현된 경우를 가정 -> O(1)
  - 연결리스트가 아닌 균형 이진 탐색 트리(balanced binary search tree)를 이용해 구현-> O(logN)
    - 크기가 큰 배열을 미리 할당해 놓지 않아도 되므로 잠재적으로 적은 공간을 사용
    - 키의 집합을 특정 순서로 차례대로 접근 가능



## ArrayList와 가변 크기 배열

- 배열(array)
  - Python, JavaScript 등 특정 언어: 크기를 자동으로 조절 -> "리스트(list)"
  - Java: 배열의 길이가 고정되어 배열 생성 시 크기를 함께 지정해야 함
- ArrayList
  - 동적 가변 크기 기능이 내재되어 있는 배열과 비슷한 자료구조
  - O(1)의 접근 시간(access time)
  - **배열이 가득 차는 순간, 배열의 크기를 두 배로 늘린다. 크기를 두 배 늘리는 시간은 O(N)이지만, 자주 발생하는 일이 아니라서 상환 입력 시간(amortized insertion time)으로 계산했을 때 여전히 O(1)이 된다.**



### 상환 입력 시간은 왜 O(1)이 되는가

- 크기가 N인 배열을 생각해 보자. 배열의 크기를 K로 늘리면 그 전 배열의 크기는 K의 절반이었을 것이므로 K/2만큼의 원소를 복사해야 한다. 따라서 N개의 원소를 삽입하기 위해서 복사해야 하는 원소의 총 개수는 N/2 + N/4 + N/8 + ... + 2 + 1, 즉 N보다 작다.
- **따라서 N개의 원소를 삽입할 때 소요되는 작업은 O(N)이 된다. 배열의 상황에 따라 최악의 경우에 O(N)이 소요되는 삽입 연산도 존재하긴 하지만 평균적으로 각 삽입은 O(1)이 소요된다.** 



## StringBuilder

문자열의 리스트가 주어졌을 때 이 문자열들을 하나로 이어 붙이려고 한다. 모든 문자열의 길이(x)가 같은 n개의 문자열이 주어졌다고 가정하자.
```java
String joinWords(String[] words) {
    String sentence = "";
    for (String w : words) {
        sentence = sentence + w;
    }
    return sentence;
} 
```
문자열을 이어붙일 때마다 두 개의 문자열을 읽어 들인 뒤 문자를 하나하나 새로운 문자열에 복사해야 한다. 처음에는 x개, 두 번째는 2x개, 세 번째는 3x개, n번째는 nx개의 문자를 복사해야 한다. 따라서 총 수행 시간은 O(x + 2x + ... + nx), 즉 O(xn^2)이 된다.

StringBuilder가 이 문제를 해결해 줄 수 있다. **StringBuilder는 단순하게 가변 크기 배열을 이용해서 필요한 경우에만 문자열을 복사하게끔 해준다.**

```java
String joinWords(String[] words) {
    String sentence = new StringBuilder();
    for (String w : words) {
        sentence.append(w);
    }
    return sentence.toString();
} 
```



## 추가로 읽을 거리

### 해시테이블에서 충돌을 해결하는 방법(p.814)
모든 해시테이블에선 충돌이 발생할 수 있다. 이를 해결하는 몇 가지 방법이 존재한다.



#### 체이닝(chaining)

- 연결리스트를 이용한 체이닝
  - 해시테이블의 각 원소가 연결리스트로 대응되는 방법이다.
  - 가장 흔한 방법이다. 충돌 횟수가 꽤 작을 경우에는 이 방법으로 충분하다.
  - 해시테이블에 N개의 원소가 있을 때, 최악의 경우 원소를 찾는 데 걸리는 시간: O(N)
    - 데이터가 아주 이상하거나 해시 함수 성능이 아주 나쁠 때(혹은 둘 다일 때) 발생
- 이진 탐색 트리를 이용한 체이닝
  - 최악의 경우: O(logN)
  - 실제로는 데이터가 극단적으로 균일하게 분포되어 있지 않는 이상 이 방법을 사용하지 않는다.



#### 선형 탐사법(linear probing)을 이용한 개방 주소법(open addressing)

- 선형 탐사법(linear probing)을 이용한 개방 주소법(open addressing)
  - 충돌이 발생했을 떄(주어진 인덱스에 이미 데이터가 들어있을 때), 비어 있는 인덱스를 찾을 때까지 다음 인덱스로 이동하는 방법이다(`index + 5`처럼 고정된 거리만큼 움직인다).
  - 충돌 횟수가 작을 때, 아주 빠르고 공간 절약적인 방법이다.
  - 단점
    - 해시테이블에 담을 수 있는 전체 데이터가 배열의 크기에 제한된다. != 체이닝
    - 클러스터링(clustering)
- 2차 탐색(quadratic probing)과 2중 해시(double hashing)
  - 탐색 거리를 2차식으로 증가시킬 수도 있다.
  - 혹은 탐사 거리를 결정할 때 두 번째 해시 함수를 사용할 수도 있다.



### Rabin-Karp 부분 문자열 탐색 알고리즘(p.815)



## 면접 문제

1. [중복이 없는가](Q1.java)
2. [순열 확인](Q2.java)
3. [URL화](Q3.java)
4. [회문 순열](Q4.java)
5. 하나 빼기
6. 문자열 압축
7. 행렬 회전
8. ()행렬
9. 문자열 회전
