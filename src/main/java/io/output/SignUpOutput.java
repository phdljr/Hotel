package io.output;

/**
 * 회원가입 화면
 */
public class SignUpOutput {

    public void printSignUpTitleView() {
        System.out.println("[ 뇌정지 호텔 ]");
        System.out.println("회원가입 정보를 입력해 주세요.");
        System.out.println("전화번호를 다음 형식과 같이 입력해 주세요. (XXX-XXXX-XXXX)");
        System.out.println();
    }

    public void printSignUpView(int step) {
        switch (step) {
            case 1:
                System.out.print("ID: ");
                break;
            case 2:
                System.out.print("이름: ");
                break;
            case 3:
                System.out.print("전화번호: ");
                break;
            case 4:
                System.out.print("소지금: ");
                break;
        }
    }

    public void printAlreadyExistId() {
        System.out.println("이미 존재하는 ID입니다. 다른 ID를 입력해 주세요.");
        System.out.println();
    }
}
