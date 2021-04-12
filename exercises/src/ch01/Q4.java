package ch01;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 1.4. 회문 순열
 *
 * #106. 모든 순열을 전부 생성할 필요가 없다. 아주 비효율적이다.
 * #121. 어떤 문자열이 회문의 순열일 때, 그 특성은 무엇인가?
 * #134. 해시테이블을 사용해 본 적이 있는가? O(N) 시간으로 줄일 수 있다.
 * #136. 비트 벡터를 사용해서 공간을 줄일 수 있는가?
 */
public class Q4 {

    public static void main(String[] args) {
        Q4 q4 = new Q4();
        assertTrue(q4.isPalindromePermutation("Tact Coa")); // t: 2, a: 2, c:2, o: 1
    }

    // Solution 1. Hash Table
    // - 회문의 순열: 어느 방향으로 읽어도 같은 문자열
    // 1) 문자열의 길이가 짝수일 때: 모든 문자의 개수가 짝수 개
    // 2) 문자열의 길이가 홀수일 때: 단 한 개의 문자만 홀수 개, 나머지는 짝수 개
    // - time: O(N)
    public boolean solution_isPermutationOfPalindrome_hashTable(String phrase) {
        int[] table = solution_buildCharFrequencyTable(phrase);
        return solution_checkMaxOneOdd(table);
    }

    /**
     * 홀수 문자가 한 개 이상 존재하는지 확인한다.
     */
    private boolean solution_checkMaxOneOdd(int[] table) {
        boolean foundOdd = false;
        for (int count : table) {
            if (count % 2 == 1) {
                if (foundOdd) {
                    return false;
                }
                foundOdd = true;
            }
        }
        return foundOdd;
    }

    /**
     * 각 문자가 몇 번 등장했는지 센다.
     */
    private int[] solution_buildCharFrequencyTable(String phrase) {
        int[] table = new int[Character.getNumericValue('z') - Character.getNumericValue('a') + 1];

        for (char c : phrase.toCharArray()) {
            int x = solution_getCharNumber(c);
            if (x != -1) {
                table[x]++;
            }
        }
        return table;
    }

    /**
     * 각 문자에 숫자를 대응시킨다. (a -> 0, b -> 1, c -> 2 등)
     * 대소문자 구분이 없고, 문자가 아닌 경우에는 -1로 대응시킨다.
     */
    private int solution_getCharNumber(char c) {
        int a = Character.getNumericValue('a');
        int z = Character.getNumericValue('z');
        int val = Character.getNumericValue(c);
        if (a <= val && val <= z) {
            return val - a;
        }
        return -1;
    }

    // Solution 2. 순회 한 번으로 개선(big-O는 동일)
    // - 마지막에 가서 홀수 개인지 확인하기보단 문자열을 훑어 나가면서 동시에 홀수의 개수를 확인할 수도 있다.
    public boolean isPermutationOfPalindrome_optimized(String phrase) {
        int countOdd = 0;
        int[] table = new int[Character.getNumericValue('z') - Character.getNumericValue('a') + 1];

        for (char c : phrase.toCharArray()) {
            int x = solution_getCharNumber(c);
            if (x != -1) {
                table[x]++;
                if (table[x] % 2 == 1) {
                    countOdd++;
                } else {
                    countOdd--;
                }
            }
        }
        return countOdd <= 1;
    }

    // Solution 3. 비트 벡터
    public boolean isPermutationOfPalindrome_bit(String phrase) {
        int bitVector = solution_createBitVector(phrase);
        return bitVector == 0 || soltuion_checkExactlyOneBitSet(bitVector);
    }

    /**
     * 문자열에 대한 비트 벡터를 만든다.
     * 값이 i인 문자가 등장하면 i번째 비트값을 바꾼다.
     */
    private int solution_createBitVector(String phrase) {
        int bitVector = 0;
        for (char c : phrase.toCharArray()) {
            int x = solution_getCharNumber(c);
            bitVector = solution_toggle(bitVector, x);
        }
        return bitVector;
    }

    /**
     * 정수의 i번째 비트값을 바꾼다.
     */
    private int solution_toggle(int bitVector, int index) {
        if (index < 0) {
            return bitVector;
        }
        int mask = 1 << index;
        if ((bitVector & mask) == 0) {
            bitVector |= mask;
        } else {
            bitVector &= ~mask;
        }
        return bitVector;
    }

    /**
     * 정확히 비트 한 개만 1로 세팅됐는지 확인하기 위해 주어진 정수값에서 1을 뺀 뒤
     * 원래 값과 AND 연산을 한다.
     */
    private boolean soltuion_checkExactlyOneBitSet(int bitVector) {
        return (bitVector & (bitVector - 1)) == 0;
    }

    // Method 1. HashMap(Hash Table)
    public boolean isPalindromePermutation(String str) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (Character.isAlphabetic(ch)) {
                ch = Character.toLowerCase(ch);
                map.put(ch, map.getOrDefault(ch, 0) + 1);
            }
        }

        boolean doesExistOddCount = false;
        for (Character ch : map.keySet()) {
            if (map.get(ch) % 2 == 1) {
                if (doesExistOddCount) {
                    return false;
                }
                doesExistOddCount = true;
            }
        }

        return true;
    }
}
