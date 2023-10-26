package io.output;

import domain.Customer;

public class OutputView {

    public void printInitView() {
        System.out.println("[ 뇌정지 호텔 ]");
        System.out.println("1. 로그인");
        System.out.println("2. 회원가입");
    }

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

    public void printAlreadyExistId() {
        System.out.println("이미 존재하는 ID입니다. 다른 ID를 입력해 주세요.");
        System.out.println();
    }

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

    public void printMyPageView(Customer customer) {
        System.out.printf("[ 뇌정지 호텔 - %s님 ]\n", customer.getName());
        System.out.println();
        System.out.printf("ID: %s\n", customer.getId());
        System.out.printf("이름: %s\n", customer.getName());
        System.out.printf("전화번호: %s\n", customer.getPhoneNumber());
        System.out.printf("소지금: %d\n", customer.getMoney());
        System.out.println();
        System.out.println("객실 예약 목록");
        /* TODO
            객실 예약 목록 출력
         */
        System.out.println();
        System.out.println("1. 돌아가기");
    }

    public void printCustomerMainView(Customer customer) {
        System.out.printf("[ 뇌정지 호텔 - %s님 ]\n", customer.getName());
        System.out.println("서비스를 선택해 주세요.");
        System.out.println();
        System.out.println("1. 객실 예약");
        System.out.println("2. 객실 예약 취소");
        System.out.println("3. 객실 예약 대기 목록");
        System.out.println("4. 마이 페이지");
        System.out.println("5. 로그아웃");
    }

    public void printAdminMainView(Customer customer) {
        System.out.printf("[ 뇌정지 호텔 - %s님 ]\n", customer.getName());
        System.out.println("서비스를 선택해 주세요.");
        System.out.println();
        System.out.println("1. 모든 예약 목록 조회");
        System.out.println("2. 로그아웃");
    }

    public void printLogoutView(Customer customer) {
        System.out.printf("[ 뇌정지 호텔 - %s님 ]\n", customer.getName());
        System.out.println("로그아웃을 하시겠습니까?");
        System.out.println();
        System.out.println("1. 확인\t\t2. 취소");
    }

    public void printLogoutCommentView() {
        System.out.println("로그아웃이 정상적으로 처리되었습니다.");
        System.out.println();
    }
}
