package io.output;

/**
 * 로그인 화면
 */
public class LoginOutput {

    public void printLoginView() {
        System.out.println("[ 뇌정지 호텔 ]");
        System.out.println("로그인 정보를 입력해 주세요.");
        System.out.println();
        System.out.print("ID: ");
    }

    public void printWrongInputId() {
        System.out.println("로그인 정보가 잘못되었습니다. 다시 입력해 주세요.");
        System.out.println();
    }
}
