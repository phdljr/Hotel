package io.output;

import domain.Customer;

public class OutputView {
    public void printInitView() {
        System.out.println("[ 뇌정지 호텔 ]");
        System.out.println("1. 로그인");
        System.out.println("2. 회원가입");
        System.out.println();
    }

    public void printSignUpView(int step){
        switch (step){
            case 0:
                System.out.println("[ 뇌정지 호텔 ]");
                System.out.println("회원가입 정보를 입력해 주세요.");
                System.out.println("전화번호를 다음 형식과 같이 입력해 주세요. (XXX-XXXX-XXXX)");
                System.out.println();
                System.out.print("ID: ");
                break;
            case 1:
                System.out.print("이름: ");
                break;
            case 2:
                System.out.print("전화번호: ");
                break;
            case 3:
                System.out.print("소지금: ");
                break;
        }
    }

    public void printMyPageView(Customer customer){
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
}
