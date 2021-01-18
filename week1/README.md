# Week1 (20.01.18.월)

### 학습한 내용

- String vs. StringBuilder vs. StringBuffer
  - String 생성
    - 리터럴 사용 시: String Pool 에서 문자열 객체를 찾는다.
    - 생성자 사용 시: Heap 에 객체를 생성한다.
    
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


### 다음 주까지

- [ ] IX. 면접 문제 - 1. 자료구조 - 01. 배열과 문자열, 02 연결리스트 읽기
- [ ] StringBuilder 클래스 기능 구현 및 테스트 코드 작성하기
  - [ ] append
  - [ ] delete
  - [ ] insert
  - [ ] replace
  - [ ] reverse

