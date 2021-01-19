# Week1 (20.01.18.월)

## 학습한 내용

### String vs. StringBuilder vs. StringBuffer

- StringBuilder

  - 스레드 세이프 X
  - 동기화 X
  - 자바 1.5 부터
  - (상대적으로) 빠름

- StringBuffer

  - 스레드 세이프 O
  - 동기화 O
  - 자바 1.0 부터
  - (상대적으로) 느림

- 참고자료
  - https://madplay.github.io/post/difference-between-string-stringbuilder-and-stringbuffer-in-java
  - https://www.journaldev.com/538/string-vs-stringbuffer-vs-stringbuilder

### String Pool

- 리터럴마다 스트링 풀에 하나의 카피만 저장 -> "String Interning"
- String 생성 방법

  - 리터럴 사용 시

    - JVM이 스트링 풀에서 같은 문자열 값을 가진 객체를 찾는다. (cf. String의 intern() 메서드)
    - 찾으면, 해당 객체의 주소값 리턴
    - 찾지 못하면, 스트링 풀에 추가하고 새로운 주소값 리턴

    ```java
    class Main {
      @Test
      void stringTest() {
        String str1 = "hello";
        String str2 = "hello";

        assertThat(str1).isSameAs(str2);
      }
    }
    ```

  - 생성자 사용 시: 항상 새로운 객체를 생성해 Heap에 저장한다.

    ```java
    class Main {
      @Test
      void stringTest() {
        String str1 = "hello";
        String str2 = new String("hello");

        assertThat(str1).isNotSameAs(str2);
      }
    }
    ```

- 위치: Perm 영역(자바 6) -> Heap 영역(자바 7)
  - 이점: 스트링 풀의 모든 문자열이 GC의 대상이 될 수 있다.
- 참고자료
  - https://www.baeldung.com/java-string-pool
  - https://www.nakjunizm.com/2017/07/25/String_Pool/
  - https://medium.com/@joongwon/string-%EC%9D%98-%EB%A9%94%EB%AA%A8%EB%A6%AC%EC%97%90-%EB%8C%80%ED%95%9C-%EA%B3%A0%EC%B0%B0-57af94cbb6bc

### 다음 주까지

- [ ] IX. 면접 문제 - 1. 자료구조 - 01. 배열과 문자열, 02 연결리스트 읽기
- [ ] StringBuilder 클래스 기능 구현 및 테스트 코드 작성하기
  - [ ] append
  - [ ] delete
  - [ ] insert
  - [ ] replace
  - [ ] reverse
