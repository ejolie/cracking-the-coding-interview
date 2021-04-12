package ch01;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 1.1. 중복이 없는가
 *
 * #44. 해시테이블을 사용해 보라.
 * #117. 비트 벡터가 유용한가?
 * #132. O(NlogN) 시간에 풀 수 있겠는가? 그 해법은 어떤 것인가?
 */
public class Q1 {

    // Solution 1. (만약 ASCII 문자열인 경우) Array
    // - 확장된 ASCII의 경우 길이가 280인 문자열을 만들 수도 있겠지만,
    //   총 문자의 개수가 256개라고 가정해도 괜찮다.
    // - time: O(n)
    // - space: O(1)
    public boolean solution_isUniqueChars(String str) {
        if (str.length() > 128) {
            return false;
        }
        boolean[] charSet = new boolean[128];
        for (int i = 0; i < str.length(); i++) {
            int val = str.charAt(i);
            if (charSet[val]) {
                return false;
            }
            charSet[val] = true;
        }
        return true;
    }

    // Solution 2. (만약 문자열이 a부터 z까지로 구성된다면) Bit Vector
    // - 하나의 int 변수만 사용해서 풀 수 있음
    public boolean solution_isUniqueChars_bit(String str) {
        int checker = 0;
        for (int i = 0; i < str.length(); i++) {
            int val = str.charAt(i) - 'a';
            if ((checker & (1 << val)) > 0) {
                return false;
            }
            checker |= (1 << val);
        }
        return true;
    }

    // Method 1. HashSet
    // - time: O(N)
    // - space: O(N)
    public boolean doesHaveDuplicateChar(String word) {
        Set<Character> charSet = new HashSet<>();
        for (int i = 0; i < word.length(); i++) {
            if (charSet.contains(word.charAt(i))) {
                return false;
            }
            charSet.add(word.charAt(i));
        }
        return true;
    }

    // Method 2. Sorting
    // - time: O(NlogN)
    // - space: O(N)
    public boolean doesHaveDuplicateChar_noExtraSpace(String word) {
        char[] chars = word.toCharArray();
        Arrays.sort(chars);
        for (int i = 0; i < chars.length - 1; i++) {
            if (chars[i] == chars[i + 1]) {
                return false;
            }
        }
        return true;
    }
}
