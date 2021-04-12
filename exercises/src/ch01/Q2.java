package ch01;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 1.2. 순열 확인
 *
 * #1. 두 문자열이 서로 순열관계에 있다는 말이 무슨 의미인가?
 * #84. O(NlogN) 해법이 하나 존재한다. 다른 해법은 추가 공간을 사용하지만 O(N) 시간이 걸린다.
 * #122. 해시테이블이 유용한가?
 * #131. 서로 순열 관계에 있는 두 문자열은 같은 문자 집합으로 이루어져 있고, 그 순서만 다를 것이다. 순서도 같게 만들 수 있는가?
 */
public class Q2 {

    public static void main(String[] args) {
        Q2 q2 = new Q2();
        assertTrue(q2.isPermutation("abc", "cba"));
        assertFalse(q2.isPermutation("abc", "ccb"));
        assertTrue(q2.isPermutation_hashMap("abc", "cba"));
        assertFalse(q2.isPermutation_hashMap("abc", "ccb"));
    }

    // Solution 1. Sorting
    // - 순열: 같은 문자로 구성되어 있고 순서만 다르므로 정렬하면 같은 결과가 나와야 한다.
    // - time: O(NlogN)
    public boolean solution_permutation_sort(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        return solution_sort(s).equals(solution_sort(t));
    }

    private String solution_sort(String s) {
        char[] content = s.toCharArray();
        Arrays.sort(content);
        return new String(content);
    }

    // Solution 2. Hash table
    // - 순열: 두 문자열이 동일한 문자 개수를 갖고 있다.
    // - 배열을 두 개 사용해서 각 문자열 내의 문자 출현 횟수를 기록한 다음, 두 배열을 비교한다.
    // - time: O(N)
    public boolean solution_permutation_hashTable(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        // 가정: ASCII 문자 집합
        int[] letters = new int[128];

        char[] sArray = s.toCharArray();
        for (char c : sArray) {
            letters[c]++;
        }

        for (int i = 0; i < t.length(); i++) {
            int c = (int) t.charAt(i);
            letters[c]--;
            if (letters[c] < 0) {
                return false;
            }
        }
        return true;
    }

    // Method 1. Sorting
    public boolean isPermutation(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }

        char[] chars1 = word1.toCharArray();
        char[] chars2 = word2.toCharArray();
        Arrays.sort(chars1);
        Arrays.sort(chars2);

        return Arrays.equals(chars1, chars2);
    }

    // Method 2. HashMap
    public boolean isPermutation_hashMap(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }

        Map<Character, Integer> charMap = new HashMap<>();
        for (int i = 0; i < word1.length(); i++) {
            char ch = word1.charAt(i);
            charMap.put(ch, charMap.getOrDefault(ch, 0) + 1);
        }

        for (int i = 0; i < word2.length(); i++) {
            char ch = word2.charAt(i);
            if (!charMap.containsKey(ch) || charMap.get(ch) == 0) {
                return false;
            }
            charMap.put(ch, charMap.get(ch) - 1);
        }

        return true;
    }
}
