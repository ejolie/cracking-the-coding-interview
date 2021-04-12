package ch01;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 1.3. URL화
 *
 * #53. 문자열의 끝에서 시작해서 앞으로 읽어 나가면서 수정하는 것이 보통 가장 쉽다.
 * #118. 필요한 공백을 알아야 할지도 모른다. 하나씩 세어 볼 수 있는가?
 */
public class Q3 {

    public static void main(String[] args) {
        Q3 q3 = new Q3();
        char[] str = "Mr John Smith".toCharArray();

        assertEquals("Mr%20John%20Smith", q3.convertToUrl(str, 13));
    }

    // Solution 1. 문자배열 조작
    // - 문자열을 뒤에서부터 거꾸로 편집: 마지막 부분에 여유 공간을 만들어 유용하게 사용 가능, 덮어쓸 걱정 X
    // 1) 문자열 내에 공백 문자 개수 확인
    // 2) 최종 문자열에 추가 공간이 얼마나 필요한지 계산
    // 3) 역방향으로 진행하며 문자열을 실제로 편집
    public void solution_replaceSpaces(char[] str, int trueLength) {
        int spaceCount = 0;
        int i;
        for (i = 0; i < trueLength; i++) {
            if (str[i] == ' ') {
                spaceCount++;
            }
        }

        int index = trueLength + spaceCount * 2;
        if (trueLength < str.length) {
            str[trueLength] = '\0'; // 배열의 끝
        }

        for (i = trueLength - 1; i >= 0; i--) {
            if (str[i] == ' ') {
                str[index - 1] = '0';
                str[index - 2] = '2';
                str[index - 3] = '%';
                index = index - 3;
            } else {
                str[index - 1] = str[i];
                index--;
            }
        }
    }

    // Method 1. StringBuilder
    public String convertToUrl(char[] str, int length) {
        StringBuilder target = new StringBuilder();
        for (char ch : str) {
            if (ch == ' ') {
                target.append("%20");
            } else {
                target.append(ch);
            }
        }
        return target.toString();
    }

}
