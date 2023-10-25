package io.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class InputView {
    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private StringTokenizer st;

    public int getInputNumber(int start, int from) throws IOException {
        st = new StringTokenizer(br.readLine());

        try {
            return Integer.parseInt(st.nextToken());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * 문자열 입력 메소드
     * @return
     * @throws IOException
     */
    public String getInputString() throws IOException {
        return br.readLine();
    }

    /**
     * 전화번호 형식에 맞는 입력 메소드
     * @return
     */
    public String getInputPhoneNumber(){
        // TODO
        return null;
    }
}
