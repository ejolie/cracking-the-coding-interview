# Week4 (21.03.29.월)

## 학습한 내용

### Linked List

```java
    public class LinkedList<E> extends AbstractSequentialList<E>
        implements List<E>, Deque<E>, Cloneable, Serializable
```

- 자바의 LinkedList는 Stack으로도 Queue로도 사용될 수 있다.
- `AbstractSequentialList`를 상속한다.
- `List`, `Deque`를 구현한다.
- **Doubly Linked List**로 구현되어 있다.
- 중복 값과 `null` 모두 삽입 가능하다.


### Stack 

```java
public class Stack<E> extends Vector<E>
```

- [공식 문서](https://docs.oracle.com/javase/7/docs/api/java/util/Stack.html)에서 `Deque<Integer> stack = new ArrayDeque<Integer>();` 사용을 권장한다.
  - Why?
    
    https://stackoverflow.com/questions/12524826/why-should-i-use-deque-over-stack
    
    - Object-oriented design - inheritance, abstraction, classess and interfaces
    
      Stack은 클래스고 Deque는 인터페이스다. 클래스는 단일 상속만 가능하지만 인터페이스는 다중 구현이 가능하다. Deque를 사용함으로써 Stack 클래스와 그의 부모클래스로부터 의존관계를 제거하고 좀 더 유연하게 개발할 수 있다. 예를 들어, Deque 인터페이스를 구현한 다른 클래스(LinkedList)로 쉽게 교체가 가능하다.

    - Inconsistency
    
      Stack은 아주 오래전에 만들어진 클래스로서 Vector 클래스를 상속받고 있다. Vector 클래스를 상속함으로 인해 Stack에 불필요한 기능(인덱스로 요소에 접근하기)이 추가된다.

    - Performance
    
      Stack 클래스가 상속한 Vector 클래스는 ArrayList의 스레드 세이프한 버전이다. 따라서 동기화 과정은 추가적인 메모리를 요구하거나 성능의 오버헤드를 야기한다.


### Queue

```java
public interface Queue<E> extends Collection<E>
```

- Collection 인터페이스의 메소드에 추가적으로 Queue가 제공하는 삽입/삭제/조회 메소드가 있다. 메소드는 두 가지 형태로 제공된다. 메소드에서 수행하는 작업이 실패했을 때 하나는 예외를 던지는 방식이고 다른 하나는 특별한 값(`null`이나 `false`)을 반환하는 방식이다.

  cf. https://docs.oracle.com/javase/7/docs/api/java/util/Queue.html

  ||Throws exception|Returns special value|
  |------|---|---|
  |Insert|`add(e)`|`offer(e)`|
  |Remove|`remove()`|`poll()`|
  |Examine|`element()`|`peek()`|
