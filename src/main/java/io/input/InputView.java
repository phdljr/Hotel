package io.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class InputView {

    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final String REGEX_UUID = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";
    private StringTokenizer st;

    public int getInputNumber(int start, int from) {
        while (true) {
            try {
                st = new StringTokenizer(br.readLine());
                int inputNumber = Integer.parseInt(st.nextToken());

                if (!(start <= inputNumber && inputNumber <= from)) {
                    throw new IllegalArgumentException();
                }

                return inputNumber;
            } catch (IllegalArgumentException | IOException e) {
                System.out.println("입력이 잘못되었습니다.");
            }
        }
    }

    /**
     * 돈 입력받는 메소드
     *
     * @return long
     */
    public long getInputMoney() {
        while (true) {
            try {
                st = new StringTokenizer(br.readLine());

                return Long.parseLong(st.nextToken());
            } catch (IllegalArgumentException | IOException e) {
                System.out.println("입력이 잘못되었습니다.");
            }
        }
    }

    /**
     * 문자열 입력 메소드
     *
     * @return
     */
    public String getInputString() {
        while (true) {
            try {
                return br.readLine();
            } catch (IOException e) {
                System.out.println("입력이 잘못되었습니다.");
            }
        }
    }

    /**
     * 전화번호 형식에 맞는 입력 메소드
     *
     * @return
     */
    public String getInputPhoneNumber() {
        while (true) {
            try {
                String input = br.readLine();

                if (!Pattern.matches("^\\d{3}-\\d{3,4}-\\d{4}$", input)) {
                    throw new IllegalArgumentException();
                }

                return input;
            } catch (Exception e) {
                System.out.println("입력이 잘못되었습니다.");
            }
        }
    }

    public String getReservationUuid() {
        while (true) {
            try {
                String input = br.readLine();

                if (!Pattern.matches(REGEX_UUID, input)) {
                    throw new IllegalArgumentException();
                }

                return input;
            } catch (Exception e) {
                System.out.println("입력이 잘못되었습니다.");
            }
        }
    }
}
